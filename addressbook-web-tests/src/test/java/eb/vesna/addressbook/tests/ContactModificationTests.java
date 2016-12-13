package eb.vesna.addressbook.tests;

// Created by Elena_Bogomolova on 01.12.2016.

import eb.vesna.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{
        @Test
        public void testContactCreation() {
            app.getNavigationHelper().gotoHomePage();
            if (! app.getContactHelper().isThereAContact()) {
                app.getContactHelper().createContact(new ContactData("Elena", "Vesna", "+79000000", "test@test.com", "Ryazan", "testGroup2"));
            }
            List<ContactData> before = app.getContactHelper().getContactList();
            app.getContactHelper().editContact(before.size() - 1);
            ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Elena-new", "Vesna-new", "+79000001", "test1@test.com", "Ryazan1", null);
            app.getContactHelper().fillContactForm(contact, false);
            app.getContactHelper().saveUpdatedContact();
            app.getNavigationHelper().returnToHomePage();
            List<ContactData> after = app.getContactHelper().getContactList();
            Assert.assertEquals(after.size(), before.size());

            before.remove(before.size() - 1);
            before.add(contact);
            Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }


}
