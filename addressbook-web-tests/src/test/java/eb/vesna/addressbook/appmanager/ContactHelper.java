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
            wd.findElement(By.xpath("//*[@id='content']/*/input[@value='Enter']")).click();
        }

    public void gotoAddNewContact() {
            wd.findElement(By.linkText("add new")).click();
        }

    public void editContact() {
        wd.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")).click();
    }

    public void saveUpdatedContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[1]/input[22]")).click();
    }

    public void selectContact() {
        wd.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")).click();
    }

    public void initContactDeletion() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }
}
