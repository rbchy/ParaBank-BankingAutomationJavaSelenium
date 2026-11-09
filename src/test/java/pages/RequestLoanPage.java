package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** requestloan.htm — apply for a loan with a down payment from an existing account. */
public class RequestLoanPage extends BasePage {

    private final By amount = By.id("amount");
    private final By downPayment = By.id("downPayment");
    private final By fromAccount = By.id("fromAccountId");
    private final By applyButton = By.cssSelector("input[value='Apply Now']");
    private final By status = By.id("loanStatus");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("requestloan.htm");
        loadedSelect(fromAccount);
    }

    public void apply(String loanAmount, String down, String from) {
        type(amount, loanAmount);
        type(downPayment, down);
        loadedSelect(fromAccount).selectByValue(from);
        click(applyButton);
    }

    public String loanStatus() {
        wait.until(d -> !d.findElement(status).getText().isBlank());
        return text(status);
    }

    public String newLoanAccount() {
        return text(By.id("newAccountId"));
    }
}
