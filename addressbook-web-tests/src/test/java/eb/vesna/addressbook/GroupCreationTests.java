package eb.vesna.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("testGroup", "TestGroup", "repeat actions"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
