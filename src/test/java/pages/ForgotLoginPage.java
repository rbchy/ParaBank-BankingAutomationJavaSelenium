package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Customer;

/** lookup.htm — "Forgot login info?" finds a customer by name, address and SSN. */
public class ForgotLoginPage extends BasePage {

    public ForgotLoginPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("lookup.htm");
        visible(By.id("firstName"));
    }

    public void lookup(Customer c, String ssn) {
        type(By.id("firstName"), c.firstName);
        type(By.id("lastName"), c.lastName);
        type(By.id("address.street"), c.street);
        type(By.id("address.city"), c.city);
        type(By.id("address.state"), c.state);
        type(By.id("address.zipCode"), c.zipCode);
        type(By.id("ssn"), ssn);
        click(By.cssSelector("input[value='Find My Login Info']"));
    }
}
