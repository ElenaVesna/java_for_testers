package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("testGroup", null, "my new group"));
        int after = app.getGroupHelper().getGroupCount();
        System.out.println("before = " + before + ", after = " + after);
        Assert.assertEquals(after, before + 1);
    }

}
