package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Elena").withLastName("Vesna")
                    .withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000")
                    .withEmail("test@test.com").withEmail2("test2@test.com").withEmail3("test3@test.com")
                    .withAddress("Ryazan")
            );
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup1"));
        }
    }

    @Test
    public void removeComtactFromGroup() {
        app.goTo().homePage();
        ContactData modifyContact = app.db().contacts().iterator().next();
        app.contact().removeFromGroup(modifyContact);
    }


}
