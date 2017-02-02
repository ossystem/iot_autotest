import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.pageObjets.Dashboard;
import ui.pageObjets.LogInPage;
import ui.pageObjets.UserList;
import ui.utils.TestListener;


@Listeners(TestListener.class)
public class OSF_200 {


    @Test
    public void Login() {
        LogInPage login = new LogInPage();
        login.getToIoTPage();
        login.enterGoogleCred();
    }

    @Test(priority = 1)
    public void openUserList() {
        Dashboard dashboard = new Dashboard();
        dashboard.openAdministration();
        dashboard.openUserList();

    }

    @Test(priority = 2)
    public void checkDefaultColumns() {
        UserList userList = new UserList();
        userList.defaultColumnsCheck();
    }

    @Test(priority = 3)
    public void addColumnService() {
        UserList userList = new UserList();
        userList.openMenu();
        userList.addServices();
    }

    @Test(priority = 4)
    public void addColumnUpdatedAt() {
        UserList userList = new UserList();
        userList.openMenu();
        userList.addUpdatedAt();
    }

    @Test(priority = 5)
    public void addColumnRegTime() {
        UserList userList = new UserList();
        userList.openMenu();
        userList.addRegTime();
    }

    @Test(priority = 6)
    public void markUser() {
        UserList userList = new UserList();
        userList.markUser();
        userList.deactivateSeveralUsers();

    }
}



