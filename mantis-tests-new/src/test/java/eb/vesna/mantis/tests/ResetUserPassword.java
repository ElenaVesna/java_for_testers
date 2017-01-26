package eb.vesna.mantis.tests;

import eb.vesna.mantis.appmanager.HttpSession;
import eb.vesna.mantis.models.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class ResetUserPassword extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void resetPasswordByAdmin() throws IOException {
        String username = "user1";
        String email = "user1@localhost.localdomain";
        String newPassword = "password1-new";

        app.manageUsers().loginUI("administrator", "root");
        app.manageUsers().chooseUser(username);
        app.manageUsers().initResetPassword();

        HttpSession session = app.newSession();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.manageUsers().finishPasswordReset(confirmationLink, newPassword);

        assertTrue(session.login(username, newPassword));
        assertTrue(session.isLoggedInAs(username));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
