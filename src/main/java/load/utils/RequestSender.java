package load.utils;


import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import load.objects.AWSCredentials;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class RequestSender {
        private RequestSpecification requestSpecification = null;
        public Response response = null;
        public static AWSCredentials awsCredentials = new AWSCredentials();

        //these three booleans controls console output messages
        private final boolean enableGatlingReportMessages = true; //should be used with debug messages off (if you want load.gatling reports to work)
        private final boolean enableErrorDebugResponseMessages = false;
        private final boolean enableAllDebugResponseMessages = false;
        private final boolean replaceTimeStampsInUrls = true; //should be on, if you want to generate small and nimble load.gatling reports

        public RequestSender(){
        }

        public RequestSender createEmptyRequestWithHeaders(Map<String, ?> map){
            requestSpecification = given().when().headers(map);
            return this;
        }

        public RequestSender createRequestWithHeaders(Map<String, ?> map, String body){
            requestSpecification = given().when().headers(map).body(body);
            return this;
        }

        public RequestSender createRequestSpecification() {
            requestSpecification = given().
                    when();
            return this;
        }

        // этот метод сможет добавлять столько угодно хедеров из Map
        public RequestSender addHeaders(Map<String, ?> map) {
            requestSpecification.headers(map);
            return this;
        }

        // этот метод сможет добавлять столько угодно хедеров
        public RequestSender addHeader(String headerName, String headerValue) {
            requestSpecification.header(headerName, headerValue);
            return this;
        }

        private RequestSender addBody(String body) {
            requestSpecification.body(body);
            return this;
        }

        RequestSender post(String uri) {
            response = requestSpecification.post(uri);
            debugInfoPrint("POST_"+uri);
            gatlingInfoPrintRequest("POST_"+uri);
            return this;
        }

        RequestSender options(String uri) {
            response = requestSpecification.options(uri);
            debugInfoPrint("OPTIONS_"+uri);
            gatlingInfoPrintRequest("OPTIONS_"+uri);
            return this;
        }

        RequestSender delete(String uri){
            response = requestSpecification.delete(uri);
            debugInfoPrint("DELETE_"+uri);
            gatlingInfoPrintRequest("DELETE_"+uri);
            return this;
        }

        public RequestSender get(String uri){
            response = requestSpecification.get(uri);
            debugInfoPrint("GET_"+uri);
            gatlingInfoPrintRequest("GET_"+uri);
            checkExpiredCredentials(response,uri);
            return this;
        }

        RequestSender put(String uri) {
            response = requestSpecification.put(uri);
            debugInfoPrint("PUT_"+uri);
            gatlingInfoPrintRequest("PUT_"+uri);
            return this;
        }

        public String extractResponseByPath(String path){
            return response.then().extract().path(path);
        }

        public String extractAllResponseAsString(){
            return response.then().extract().asString();
        }


        private void checkExpiredCredentials(Response response, String url){
            if (url.contains("notification?status=unread")){
                if (response.asString().contains("\"expired\":true")){
                    JSONParser parser = new JSONParser();
                    JSONObject json = null;
                    try {
                        json = (JSONObject) parser.parse(response.asString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    JSONObject creds = (JSONObject) json.get("creds");
                    //write new creds to credential storage
                    awsCredentials.setAccessKeyId(creds.get("accessKeyId").toString());
                    awsCredentials.setSecretAccessKey(creds.get("secretAccessKey").toString());
                    awsCredentials.setSessionToken(creds.get("sessionToken").toString());
                }
            }
        }


        private void gatlingInfoPrintRequest(String methodAndUri){
            //if response with error status code = print error
            //if response with ok status code - check response body
            //if response body contains error messages - check again
            //if body contains "expired":false = print ok (not error in our service)
            //if body contains error/exception/expired/timed our = print error
            if (enableGatlingReportMessages) {
                //we parsing methodAndUri for unix timestamp and replacing it with *
                //this step required in order to greatly reduce weight of report in case of time-generated urls
                String requestName;
                if(replaceTimeStampsInUrls) {
                    requestName = methodAndUri.replaceAll("[0-9]{13}", "*************");
                }
                else{
                    requestName = methodAndUri;
                }
                String name = "somename";
                long thread = Thread.currentThread().getId();

                String responseOK = "REQUEST\t" + name + "\t" + thread + "\t\t" + requestName + "\t" + (System.currentTimeMillis() - response.time()) + "\t" + System.currentTimeMillis() + "\t" + "OK\t ";
                String responseErrorInBody = "REQUEST\t" + name + "\t" + thread + "\t\t" + requestName + "\t" + (System.currentTimeMillis() - response.time()) + "\t" + System.currentTimeMillis() + "\t" + "KO\tstatus.find.in(200,304,201,202,203,204,205,206,207,208,209), but actually found " + response.statusCode() + " with response body contains error : "+response.asString();
                String responseErrorStatusCode = "REQUEST\t" + name + "\t" + thread + "\t\t" + requestName + "\t" + (System.currentTimeMillis() - response.time()) + "\t" + System.currentTimeMillis() + "\t" + "KO\tstatus.find.in(200,304,201,202,203,204,205,206,207,208,209), but actually found " + response.statusCode() + " with response body: "+response.asString();


                if (response.statusCode() == 200 || response.statusCode() == 304 || response.statusCode() == 201 || response.statusCode() == 202
                        || response.statusCode() == 203 || response.statusCode() == 204 || response.statusCode() == 205 || response.statusCode() == 206
                        || response.statusCode() == 207 || response.statusCode() == 208 || response.statusCode() == 209) {
                    if(!response.asString().contains("error") && !response.asString().contains("exception") && !response.asString().contains("expired") && !response.asString().contains("timed out")) {
                        System.out.println(responseOK);
                    }
                    else{
                        if (response.asString().contains("\"expired\":false")){
                            System.out.println(responseOK);
                        }
                        else{
                            System.out.println(responseErrorInBody);
                        }
                        }
                } else {
                    System.out.println(responseErrorStatusCode);
                }
            }
        }

        private void debugInfoPrint(String url){
            if(enableErrorDebugResponseMessages || enableAllDebugResponseMessages) {
                if (response.statusCode() != 200 || response.asString().contains("error") || enableAllDebugResponseMessages) {
                    System.out.println("==================================");
                    System.out.println("Request: ");
                    System.out.println(url);
                    System.out.println("-------------------------------");
                    System.out.println("Response time: "+response.time());
                    System.out.println("Status code: "+response.statusCode());
                    System.out.println("-------------------------------");
                    System.out.println(response.headers().toString());
                    System.out.println("-------------------------------");
                    System.out.println(response.asString());
                    System.out.println("==================================");
                }
            }
        }
    }