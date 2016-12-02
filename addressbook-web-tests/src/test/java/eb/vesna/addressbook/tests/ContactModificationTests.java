package eb.vesna.addressbook.tests;

// Created by Elena_Bogomolova on 01.12.2016.

import eb.vesna.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
        @Test
        public void testContactCreation() {
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().editContact();
            app.getContactHelper().fillContactForm(new ContactData("Elena-new", "Vesna-new", "+79000001", "test1@test.com", "Ryazan1", null), false);
            app.getContactHelper().saveUpdatedContact();
            app.getNavigationHelper().returnToHomePage();
        }


}
