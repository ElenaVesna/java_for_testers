package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import eb.vesna.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static eb.vesna.addressbook.tests.TestBase.app;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type (By.name("firstname"), contactData.getFirstname());
        type (By.name("lastname"), contactData.getLastName());
        type (By.name("address"), contactData.getAddress());
        type (By.name("email"), contactData.getEmail());
        type (By.name("email2"), contactData.getEmail2());
        type (By.name("email3"), contactData.getEmail3());
        type (By.name("home"), contactData.getHomePhone());
        type (By.name("mobile"), contactData.getMobilePhone());
        type (By.name("work"), contactData.getWorkPhone());

//        attach (By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private void submitContactCreation() {
            click(By.xpath("//*[@id='content']/*/input[@value='Enter']"));
        }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void goToHomePage() {
        click(By.linkText("home"));
    }

    private void gotoAddNewContact() {
            click(By.linkText("add new"));
        }

    private void editContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void viewContactDetailsById (int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    private void saveUpdatedContact() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    private void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void create(ContactData contact) {
        gotoAddNewContact();
        fillContactForm (contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        saveUpdatedContact();
        contactCache = null;
        returnToHomePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name=\"entry\"]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstName).withLastName(lastName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return contactCache;
    }

    public void clickOkToAlert () {
        wd.switchTo().alert().accept();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        clickOkToAlert();
        contactCache = null;
        goToHomePage();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).
                withFirstname(firstname).withLastName(lastname).
                withMobilePhone(mobile).withHomePhone(home).withWorkPhone(work).
                withAddress(address).
                withEmail(email).withEmail2(email2).withEmail3(email3)
//                .inGroup(contact.getGroups().iterator().next())
                ;
    }

    public String infoFromDetailsForm(ContactData contact) {
        viewContactDetailsById(contact.getId());
        String allInfo = wd.findElement(By.id("content")).getText();
        String[] info = allInfo.split("\n\n\n");
        allInfo = info[0];
        return allInfo;
    }


    public void addToGroup(ContactData contact) {
        selectContactById(contact.getId());
        GroupData group = app.db().groups().iterator().next();
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.xpath("//input[@name='add']"));
    }

    public void removeFromGroup(ContactData contact) {
        if (!contact.getGroups().isEmpty()) {
            String groupName = contact.getGroups().iterator().next().getName();
            new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
            selectContactById(contact.getId());
            click(By.name("remove"));
            click(By.linkText("group page \"" + groupName + "\""));
        } else {
            System.out.println("The selected contact is not assigned to any group");
        }
    }
}
