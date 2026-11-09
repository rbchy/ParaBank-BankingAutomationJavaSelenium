package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** openaccount.htm — open a CHECKING or SAVINGS account funded from an existing one. */
public class OpenAccountPage extends BasePage {

    private final By type = By.id("type");
    private final By fromAccount = By.id("fromAccountId");
    private final By openButton = By.cssSelector("input[type='button'][value='Open New Account'], input[type='submit'][value='Open New Account']");
    private final By newAccountId = By.id("newAccountId");

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("openaccount.htm");
        loadedSelect(type);
        loadedSelect(fromAccount);
    }

    /** Opens the account and returns the new account number. */
    public String openAccount(String accountType, String fundingAccount) {
        loadedSelect(type).selectByVisibleText(accountType);
        if (fundingAccount != null) {
            loadedSelect(fromAccount).selectByValue(fundingAccount);
        }
        click(openButton);
        wait.until(d -> !d.findElement(newAccountId).getText().isBlank());
        return text(newAccountId);
    }

    public String firstFundingAccount() {
        return optionValues(fromAccount).get(0);
    }
}
