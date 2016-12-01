package eb.vesna.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testsGroupDeletion() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }


}
