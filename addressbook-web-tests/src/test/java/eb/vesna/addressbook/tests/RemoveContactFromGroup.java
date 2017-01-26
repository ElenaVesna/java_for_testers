package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import eb.vesna.addressbook.models.GroupData;
import eb.vesna.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Elena").withLastName("Vesna")
                    .withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000")
                    .withEmail("test@test.com").withEmail2("test2@test.com").withEmail3("test3@test.com")
                    .withAddress("Ryazan")
                    .inGroup(app.db().groups().iterator().next())
            );
        }
    }

    @Test
    public void removeComtactFromGroup() {
        Contacts before = app.db().contacts();

        app.goTo().homePage();
        ContactData modifyContact = app.db().contacts().iterator().next();
        Groups groupsBefore = modifyContact.getGroups();
        app.contact().removeFromGroup(modifyContact);
        app.goTo().homePage();

        assertThat(app.db().contacts().size(), equalTo(before.size()));
        assertThat(modifyContact.getGroups().size(), equalTo(groupsBefore.size() - 1));
    }


}
