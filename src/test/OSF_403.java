import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjets.Dashboard;
import pageObjets.LogInPage;
import pageObjets.Requests;
import pageObjets.UserList;
import ru.yandex.qatools.allure.annotations.Step;
import utils.TestListener;


@Listeners(TestListener.class)

public class OSF_403 {

    @Test
    public void LogInWithGoogle2() {
        LogInPage log = new LogInPage();
        log.getToIoTPage();
        log.enterGoogleCred2();
        log.goToLogInPage();
    }
    @Test(priority = 1)
    public void LogInWithAdminAcc(){
        LogInPage log = new LogInPage();
        log.getToIoTPage();
        log.enterGoogleCred();

    }

    @Test(priority = 2)
    public void openRequests() {
        Dashboard dashboards = new Dashboard();
        dashboards.openAdministration();
        dashboards.openRequests();
    }

    @Test(priority = 3)
    public void checkExpectedRequest() {
        Requests requests = new Requests();
        requests.checkRequest();

    }
}