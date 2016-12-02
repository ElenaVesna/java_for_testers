package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Created by Elena_Bogomolova on 01.12.2016.

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper (WebDriver wd) {
        this.wd = wd;
    }

    public void fillContactForm(ContactData contactData) {
            enterFirstName(contactData.getName());
            enterLastName(contactData.getLastName());
            enterMobilePhone(contactData.getMobilePhone());
            enterEmail(contactData.getEmail());
            enterAddress(contactData.getAddress());
        }

    public void submitContactCreation() {
            wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        }

    private void enterAddress(String address) {
            wd.findElement(By.name("address")).click();
            wd.findElement(By.name("address")).clear();
            wd.findElement(By.name("address")).sendKeys(address);
        }

    private void enterEmail(String email) {
            wd.findElement(By.name("email")).click();
            wd.findElement(By.name("email")).clear();
            wd.findElement(By.name("email")).sendKeys(email);
        }

    private void enterMobilePhone(String mobilePhone) {
            wd.findElement(By.name("mobile")).click();
            wd.findElement(By.name("mobile")).clear();
            wd.findElement(By.name("mobile")).sendKeys(mobilePhone);
        }

    private void enterLastName(String lastName) {
            wd.findElement(By.name("lastname")).click();
            wd.findElement(By.name("lastname")).clear();
            wd.findElement(By.name("lastname")).sendKeys(lastName);
        }

    private void enterFirstName(String firstName) {
            wd.findElement(By.name("firstname")).click();
            wd.findElement(By.name("firstname")).clear();
            wd.findElement(By.name("firstname")).sendKeys(firstName);
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
