package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import eb.vesna.addressbook.models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type (By.name("firstname"), contactData.getFirstname());
        type (By.name("lastname"), contactData.getLastName());
        type (By.name("mobile"), contactData.getMobilePhone());
        type (By.name("email"), contactData.getEmail());
        type (By.name("address"), contactData.getAddress());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
//        String hrefName = "edit.php?id=" + id;
//        wd.findElement(By.cssSelector("a[href='" + hrefName + "']")).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
            String[] phones = element.findElement(By.xpath("td[5]")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastName(lastName).
                    withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
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
        //initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).
                withFirstname(firstname).withLastName(lastname).
                withMobilePhone(mobile).withHomePhone(home).withWorkPhone(work);
    }

//    private void initContactModificationById(int id) {
//        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
//        WebElement row = checkbox.findElement(By.xpath("./../.."));
//        List<WebElement> cells = row.findElements(By.tagName("td"));
//        cells.get(7).findElement(By.tagName("a")).click();
//    }
}
