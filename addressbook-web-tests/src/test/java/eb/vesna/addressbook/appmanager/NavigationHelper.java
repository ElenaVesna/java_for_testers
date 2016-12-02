package eb.vesna.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Elena_Bogomolova on 01.12.2016.
 */
public class NavigationHelper extends HelperBase{


    public NavigationHelper(WebDriver wd) {
        super (wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

    public void clickOkToAlert () {
        wd.switchTo().alert().accept();
    }
}
