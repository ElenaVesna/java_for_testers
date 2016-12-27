package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
        @Test
        public void testContactCreation() {
            app.goTo().homePage();
            if (app.contact().list().size() == 0) {
                app.contact().create(new ContactData().withFirstname("Elena").withLastName("Vesna").withMobilePhone("+79000000").withEmail("test@test.com").withAddress("Ryazan").withGroup("testGroup2"));
            }
            List<ContactData> before = app.contact().list();
            int index = before.size() - 1;
            ContactData contact = new ContactData()
                    .withId(before.get(index).getId()).withFirstname("Elena-new").withLastName("Vesna-new").withMobilePhone("+79000001").withEmail("test1@test.com").withAddress("Ryazan1");
            app.contact().modify(index, contact);
            List<ContactData> after = app.contact().list();
            Assert.assertEquals(after.size(), before.size());

            before.remove(index);
            before.add(contact);

            Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
            before.sort(byId);
            after.sort(byId);
            Assert.assertEquals(before, after);
        }


}
