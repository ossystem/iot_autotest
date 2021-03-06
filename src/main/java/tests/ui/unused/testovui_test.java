package tests.ui.unused;

import mechanics.ui.pageObjets.DDMenus;
import mechanics.ui.pageObjets.DDMenusChrome;
import mechanics.ui.pageObjets.LogInPage;
import mechanics.ui.pageObjets.dashboard.Dasboards.Dashboard;
import mechanics.ui.pageObjets.dashboard.Notifications.Notifications;
import mechanics.ui.utils.ListenerUi;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;


@Listeners(ListenerUi.class)
public class testovui_test {
    @Test
    public void LogIn() {
        LogInPage log = new LogInPage();
        log.getToIoTPage();
        log.enterGoogleCred();
    }
    @Features("Regression")
    @Stories("[OSF-53] Equipment view")
    @Test
    public void checkWhatIsActiveInDDMenu(){
        Dashboard dashboard = new Dashboard();
        dashboard.openEquipment();
        dashboard.openMenu();
        DDMenus ddm = new DDMenusChrome();
        ddm.checkMenuEquipment();
        dashboard.closeMenu();
    }
    @Features("Regression")
    @Stories("Notifications Rule, default columns check")
    @Test(priority = 1)
    public void NotificationsRuleDefault() {
        Dashboard dash = new Dashboard();
        dash.openNotifications();
        dash.openNotificationsRules();
        Notifications not = new Notifications();
        not.checkNotificationSubject();
        not.checkMessage();
        not.checkActive();
    }

    @Features("Regression")
    @Stories("Notifications Rule, drop down menu with additional columns check")
    @Test(priority = 2)
    public void NotificationsRuleDDMCheck() {
        Dashboard dash = new Dashboard();
        dash.openMenu();
        Notifications not = new Notifications();
        not.checkDDMNotificationSubject();
        not.checkDDMMessage();
        not.checkDDMActive();
        not.checkDDMRuleType();
        not.checkDDMId();
        not.checkDDMTriggerAgainAfter();
        not.checkDAMAcknowledgementLast();

    }

    @Features("Regression")
    @Stories("Create new Rule Abnormal vibration,Chanel main ")
    @Test(priority = 3)
    public void CreateNewRule() {
        Notifications not = new Notifications();
        not.floatingButton();
        not.createNewRule();
        not.createRuleAbnormalVibration();
        not.insertName();
        not.insertDescription();
        not.clickContinue();
        not.clickOnFloatingButton();
        not.chooseEquip();
        not.clickAdd();
        not.checkAddedEquip();
        not.selectChannelCheck();
        not.clickContinue2Chrome();
        not.checkGlobalNotification();
    }

}



