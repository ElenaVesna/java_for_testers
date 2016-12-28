package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

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
            Set<ContactData> before = app.contact().all();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstname("Elena-new").withLastName("Vesna-new").withMobilePhone("+79000001").withEmail("test1@test.com").withAddress("Ryazan1");
            app.contact().modify(contact);
            Set<ContactData> after = app.contact().all();
            Assert.assertEquals(after.size(), before.size());

            before.remove(modifiedContact);
            before.add(contact);
            Assert.assertEquals(before, after);
        }

}
