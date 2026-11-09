package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/** updateprofile.htm — the form is filled with the customer's data by AJAX. */
public class UpdateContactInfoPage extends BasePage {

    private final By firstName = By.id("customer.firstName");
    private final By lastName = By.id("customer.lastName");
    private final By street = By.id("customer.address.street");
    private final By city = By.id("customer.address.city");
    private final By state = By.id("customer.address.state");
    private final By zipCode = By.id("customer.address.zipCode");
    private final By phone = By.id("customer.phoneNumber");
    private final By updateButton = By.cssSelector("input[value='Update Profile']");

    public UpdateContactInfoPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("updateprofile.htm");
        waitForValue(firstName);
    }

    public void enterContactInfo(Map<String, String> data) {
        type(firstName, data.get("firstName"));
        type(lastName, data.get("lastName"));
        type(street, data.get("address"));
        type(city, data.get("city"));
        type(state, data.get("state"));
        type(zipCode, data.get("zipCode"));
        type(phone, data.get("phone"));
    }

    public void clearFirstName() {
        type(firstName, "");
    }

    public void clickUpdate() {
        click(updateButton);
    }

    public String value(String field) {
        By locator = switch (field) {
            case "firstName" -> firstName;
            case "lastName" -> lastName;
            case "address" -> street;
            case "city" -> city;
            case "state" -> state;
            case "zipCode" -> zipCode;
            case "phone" -> phone;
            default -> throw new IllegalArgumentException("Unknown field " + field);
        };
        return visible(locator).getAttribute("value");
    }
}
