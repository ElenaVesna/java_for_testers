package eb.vesna.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import eb.vesna.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactsDataGenerator {

    @Parameter (names = "-c", description = "Contacts count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactsDataGenerator generator = new ContactsDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateGroups(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getAddress(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getGroup()));
        }
        writer.close();
    }

    private List<ContactData> generateGroups(int count) {
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
