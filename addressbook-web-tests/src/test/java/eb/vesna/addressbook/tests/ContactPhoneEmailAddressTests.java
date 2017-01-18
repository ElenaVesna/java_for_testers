package eb.vesna.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import eb.vesna.addressbook.models.ContactData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstname("Elena").withLastName("Vesna").
                    withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000").
                    withEmail("test@test.com").withEmail2("111@test.com").withEmail3("222@test.com").
                    withAddress("Ryazan")
//                    .withGroup("testGroup2")
            );
        }
    }

    @Test
    public void testContactPhonesEmailsAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(contactInfoFromEditForm.mergePhones()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditForm.mergeEmails()));
   }




}
