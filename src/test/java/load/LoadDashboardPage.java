package load;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LoadListener.class)
public class LoadDashboardPage {
    final int threadsDashboardPage = 20;
    final int testOperationTimeMins = 1;

    @Test
    public void checkNewCreds(){
        RequestManager requestManager = new RequestManager();
        requestManager.checkExpiredCredentials(testOperationTimeMins);
    }

    @Test(threadPoolSize = threadsDashboardPage, invocationCount = threadsDashboardPage)
    public void loadDashboardPage (){
        RequestManager requestManager = new RequestManager();
        requestManager.loadDashboardPage(10,2000);
    }
}
