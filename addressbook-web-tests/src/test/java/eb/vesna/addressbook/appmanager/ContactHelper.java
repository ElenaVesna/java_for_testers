package eb.vesna.addressbook.appmanager;

import eb.vesna.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Elena_Bogomolova on 01.12.2016.
 */
public class ContactHelper {
    private FirefoxDriver wd;

    public ContactHelper (FirefoxDriver wd) {
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
}
