package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

    public void gotoAddNewContact() {
            click(By.linkText("add new"));
        }

    public void editContact(int index) {
        wd.findElements(By.xpath("//img[@src=\"icons/pencil.png\"]")).get(index).click();
    }

    public void saveUpdatedContact() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void createContact(ContactData contact) {
        gotoAddNewContact();
        fillContactForm (contact, true);
        submitContactCreation();
        returnToHomePage();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
