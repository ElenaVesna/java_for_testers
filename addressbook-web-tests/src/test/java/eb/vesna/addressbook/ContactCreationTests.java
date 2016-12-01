package eb.vesna.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoAddNewContact();
        fillContactForm(new ContactData("Elena", "Vesna", "+79000000", "test@test.com", "Ryazan"));
        submitContactCreation();
        returnToHomePage();
    }

}
