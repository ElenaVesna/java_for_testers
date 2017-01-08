package eb.vesna.addressbook.generator;

import eb.vesna.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactsDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateGroups(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getAddress(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                    contact.getHomePhone(), contact.getWorkPhone(), contact.getMobilePhone(),
                    contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateGroups(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("name-%s", i))
                    .withLastName(String.format("surname-%s", i))
                    .withAddress(String.format("address-%s", i))
                    .withEmail(String.format("email-%s", i))
                    .withEmail2(String.format("secondEmail-%s", i))
                    .withEmail3(String.format("thirdEmail-%s", i))
                    .withHomePhone(String.format("homePhone-%s", i))
                    .withWorkPhone(String.format("workPhone-%s", i))
                    .withMobilePhone(String.format("mobilePhone-%s", i))
                    .withGroup(String.format("testGroup%s", i)));
        }
        return contacts;
    }


}
