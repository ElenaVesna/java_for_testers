package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstname("Elena").withLastName("Vesna-L5").
                    withMobilePhone("+79000000").withHomePhone("999-999").withWorkPhone("+7(100)000000").
                    withEmail("test@test.com").withEmail2("111@test.com").withEmail3("222@test.com").
                    withAddress("Ryazan, Best St, 8-12")
                    .inGroup(app.db().groups().iterator().next())
            );
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String detailsContactInfo = app.contact().infoFromDetailsForm(contact);
        String info = allContactInfo(contactInfoFromEditForm);

        assertThat(allContactInfo(contactInfoFromEditForm), equalTo(detailsContactInfo));
    }


    public String allContactInfo(ContactData contact) {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append(contact.getFirstname());
        allInfo.append(" ");
        allInfo.append(contact.getLastName());
        allInfo.append("\n");
        if (!contact.getAddress().isEmpty()) {
            allInfo.append(contact.getAddress());
            allInfo.append("\n");
        }
        if (!contact.getHomePhone().isEmpty()) {
            allInfo.append("\nH: ");
            allInfo.append(contact.getHomePhone());
        }
        if (!contact.getMobilePhone().isEmpty()) {
            allInfo.append("\nM: ");
            allInfo.append(contact.getMobilePhone());
        }
        if (!contact.getWorkPhone().isEmpty()) {
            allInfo.append("\nW: ");
            allInfo.append(contact.getWorkPhone());
        }
        if (!contact.mergeEmails().isEmpty()) {
            allInfo.append("\n\n");
            allInfo.append(contact.mergeEmails());
        }
        if (!contact.getGroups().isEmpty()) {
            allInfo.append("\n\n\nMember of: ");
            allInfo.append(contact.getGroups().iterator().next().getName());
        }
        return String.valueOf(allInfo);
    }

}