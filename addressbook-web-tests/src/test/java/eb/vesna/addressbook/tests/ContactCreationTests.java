package eb.vesna.addressbook.tests;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData()
                    .withFirstname(split[0]).withLastName(split[1])
                    .withAddress(split[2])
                    .withEmail(split[3]).withEmail2(split[4]).withEmail3(split[5])
                    .withHomePhone(split[6]).withMobilePhone(split[7]).withWorkPhone(split[8])
                    .withGroup(split[9])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
//        File photo = new File("src/test/resources/kitty.jpg");
//        ContactData contact = new ContactData().
//                withLastName("Vesna-L5").withFirstname("Elena").
//                withMobilePhone("+79000000").withHomePhone("999999").withWorkPhone("+7100000").
//                withEmail("test@test.com").withEmail2("111@test.com").withEmail3("222@test.com").
//                withAddress("Ryazan").withGroup("testGroup2").
//                withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/kitty.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
