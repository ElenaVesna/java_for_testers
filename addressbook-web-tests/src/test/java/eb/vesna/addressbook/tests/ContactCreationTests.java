package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().
                withLastName("Vesna-L5").withFirstname("Elena").
                withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000").
                withEmail("test@test.com").withAddress("Ryazan").withGroup("testGroup2");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
