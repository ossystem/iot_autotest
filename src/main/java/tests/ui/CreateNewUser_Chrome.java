package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import mechanics.ui.pageObjets.LogInPage;
import mechanics.ui.utils.ListenerUi;

@Listeners(ListenerUi.class)

public class CreateNewUser_Chrome {
    @Features("Create New User")
    @Stories("User")
    @Test
    public void createNewUser() {
        LogInPage log = new LogInPage();
        log.getToIoTPage();
        log.enterGoogleCred2();
    }
}