package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().gotoAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Elena", "Vesna", "+79000000", "test@test.com", "Ryazan", "testGroup2"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }

}
