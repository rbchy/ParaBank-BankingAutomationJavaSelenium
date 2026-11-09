package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/** billpay.htm — pay a payee from one of the customer's accounts. */
public class BillPayPage extends BasePage {

    private final By fromAccount = By.name("fromAccountId");
    private final By sendButton = By.cssSelector("input[value='Send Payment']");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("billpay.htm");
        loadedSelect(fromAccount);
    }

    public void pay(Map<String, String> d, String from) {
        type(By.name("payee.name"), d.get("payeeName"));
        type(By.name("payee.address.street"), d.get("address"));
        type(By.name("payee.address.city"), d.get("city"));
        type(By.name("payee.address.state"), d.get("state"));
        type(By.name("payee.address.zipCode"), d.get("zipCode"));
        type(By.name("payee.phoneNumber"), d.get("phone"));
        type(By.name("payee.accountNumber"), d.get("account"));
        type(By.name("verifyAccount"), d.get("verifyAccount"));
        type(By.name("amount"), d.get("amount"));
        loadedSelect(fromAccount).selectByValue(from);
        click(sendButton);
    }

    public String resultPayee() {
        wait.until(dr -> !dr.findElement(By.id("payeeName")).getText().isBlank());
        return text(By.id("payeeName"));
    }

    public String resultAmount() {
        return text(By.id("amount"));
    }

    public String resultFromAccount() {
        return text(By.id("fromAccountId"));
    }
}
