package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withLastName("Elena-lambda").withFirstname("Vesna-l").withMobilePhone("+79000000").withEmail("test@test.com").withAddress("Ryazan").withGroup("testGroup2");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Assert.assertEquals(before, after);
    }

}
