package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import eb.vesna.addressbook.models.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Elena").withLastName("Vesna").withMobilePhone("+79000000").withEmail("test@test.com").withAddress("Ryazan").withGroup("testGroup2"));
        }
    }

    @Test
        public void testContactCreation() {
            Contacts before = app.contact().all();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstname("Elena-new").withLastName("Vesna-new").withMobilePhone("+79000001").withEmail("test1@test.com").withAddress("Ryazan1");
            app.contact().modify(contact);
            assertThat(app.contact().count(), equalTo(before.size()));
            Contacts after = app.contact().all();
            assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        }

}
