package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Created by Elena_Bogomolova on 01.12.2016.

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

    public void submitContactCreation() {
            click(By.xpath("//*[@id='content']/*/input[@value='Enter']"));
        }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void goToHomePage() {
        click(By.linkText("home"));
    }

    public void gotoAddNewContact() {
            click(By.linkText("add new"));
        }

    public void editContact(int id) {
        String hrefName = "edit.php?id=" + id;
        wd.findElement(By.cssSelector("a[href='" + hrefName + "']")).click();
    }

    public void saveUpdatedContact() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void create(ContactData contact) {
        gotoAddNewContact();
        fillContactForm (contact, true);
        submitContactCreation();
        returnToHomePage();
    }

//    public boolean isThereAContact() {
//        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
//    }
//
//    public int getContactCount() {
//        return wd.findElements(By.name("selected[]")).size();
//    }

    public void modify(ContactData contact) {
        editContact(contact.getId());
        fillContactForm(contact, false);
        saveUpdatedContact();
        returnToHomePage();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name=\"entry\"]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public void clickOkToAlert () {
        wd.switchTo().alert().accept();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        clickOkToAlert();
        goToHomePage();
    }

}
