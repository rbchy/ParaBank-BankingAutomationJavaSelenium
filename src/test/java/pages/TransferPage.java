package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** transfer.htm — move money between two of the customer's accounts. */
public class TransferPage extends BasePage {

    private final By amount = By.id("amount");
    private final By fromAccount = By.id("fromAccountId");
    private final By toAccount = By.id("toAccountId");
    private final By transferButton = By.cssSelector("input[value='Transfer']");
    private final By amountResult = By.id("amountResult");
    private final By fromResult = By.id("fromAccountIdResult");
    private final By toResult = By.id("toAccountIdResult");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("transfer.htm");
        loadedSelect(fromAccount);
        loadedSelect(toAccount);
    }

    public void transfer(String value, String from, String to) {
        type(amount, value);
        loadedSelect(fromAccount).selectByValue(from);
        loadedSelect(toAccount).selectByValue(to);
        click(transferButton);
    }

    public String transferredAmount() {
        wait.until(d -> !d.findElement(amountResult).getText().isBlank());
        return text(amountResult);
    }

    public String fromAccountResult() {
        return text(fromResult);
    }

    public String toAccountResult() {
        return text(toResult);
    }
}
