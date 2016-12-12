package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

//Created by Elena_Bogomolova on 01.12.2016.

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("testGroup", null, "repeat actions"));
        }
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("testGroup2", "test", "testDescription"));
        app.getGroupHelper().submitGroupModiifcation();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }

}
