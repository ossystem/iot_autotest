package load.unused;

import load.utils.ListenerLoad;
import load.utils.RequestManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by user on 04.04.2017.
 */
@Listeners(ListenerLoad.class)
public class CredsForGatling {
    private final int testOperationTimeMins = 20;

    @Test
    public void checkNewCreds(){
        RequestManager requestManager = new RequestManager();
        requestManager.checkExpiredCredentials(testOperationTimeMins);
    }
}
