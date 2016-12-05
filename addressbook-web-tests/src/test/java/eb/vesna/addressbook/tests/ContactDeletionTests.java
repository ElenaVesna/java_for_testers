package eb.vesna.addressbook.tests;

//Created by Elena_Bogomolova on 01.12.2016.


import eb.vesna.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Elena", "Vesna", "+79000000", "test@test.com", "Ryazan", "testGroup2"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactDeletion();
        app.getNavigationHelper().clickOkToAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
