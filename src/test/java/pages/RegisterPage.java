package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Customer;

/** register.htm — sign up a new customer. */
public class RegisterPage extends BasePage {

    private final By registerButton = By.cssSelector("input[type='submit'][value='Register']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("register.htm");
        visible(By.id("customer.firstName"));
    }

    public void register(Customer c, String confirmPassword) {
        type(By.id("customer.firstName"), c.firstName);
        type(By.id("customer.lastName"), c.lastName);
        type(By.id("customer.address.street"), c.street);
        type(By.id("customer.address.city"), c.city);
        type(By.id("customer.address.state"), c.state);
        type(By.id("customer.address.zipCode"), c.zipCode);
        type(By.id("customer.phoneNumber"), c.phone);
        type(By.id("customer.ssn"), c.ssn);
        type(By.id("customer.username"), c.username);
        type(By.id("customer.password"), c.password);
        type(By.id("repeatedPassword"), confirmPassword);
        click(registerButton);
    }
}
