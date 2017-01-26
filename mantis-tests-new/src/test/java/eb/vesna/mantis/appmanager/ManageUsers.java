package eb.vesna.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageUsers extends HelperBase{

    public ManageUsers(ApplicationManager app) {
        super(app);
    }

    public void chooseUser(String username) {
        click(By.linkText("Manage Users"));
        click(By.linkText(username));
    }

    public void initResetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finishPasswordReset(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void loginUI(String username, String password) {
        wd.get(app.getProperty("web.baseURL") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}
