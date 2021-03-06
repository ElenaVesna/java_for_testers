package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Elena").withLastName("Vesna")
                    .withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000")
                    .withEmail("test@test.com")
                    .withAddress("Ryazan")
                    .inGroup(app.db().groups().iterator().next())
            );
        }
    }

    @Test
        public void testContactModification() {
            Contacts before = app.db().contacts();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData()
                    .withId(modifiedContact.getId())
                    .withFirstname("Elena-new").withLastName("Vesna-new")
                    .withAddress("Ryazan1")
                    .withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000")
                    .withEmail("test1@test.com").withEmail2("test2@test.com").withEmail3("test3@test.com")
                    .inGroup(modifiedContact.getGroups().iterator().next());
            app.contact().modify(contact);
            assertThat(app.contact().count(), equalTo(before.size()));
            Contacts after = app.db().contacts();
            assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
            verifyContactListInUI();
        }


}
