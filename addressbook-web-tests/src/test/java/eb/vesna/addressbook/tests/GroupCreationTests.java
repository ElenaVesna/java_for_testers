package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.GroupData;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("testGroup", "TestGroup", "repeat actions"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
