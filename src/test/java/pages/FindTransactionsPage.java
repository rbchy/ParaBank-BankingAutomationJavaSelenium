package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/** findtrans.htm — search an account's transactions by amount or date range. */
public class FindTransactionsPage extends BasePage {

    private final By account = By.id("accountId");
    private final By resultRows = By.cssSelector("#transactionTable tbody tr");

    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("findtrans.htm");
        loadedSelect(account);
    }

    public void findByAmount(String accountId, String value) {
        loadedSelect(account).selectByValue(accountId);
        type(By.id("amount"), value);
        click(By.id("findByAmount"));
    }

    public void findByDateRange(String accountId, String from, String to) {
        loadedSelect(account).selectByValue(accountId);
        type(By.id("fromDate"), from);
        type(By.id("toDate"), to);
        click(By.id("findByDateRange"));
    }

    /** Text of each result row (date, description, debit, credit). */
    public List<String> results() {
        wait.until(d -> !d.findElements(resultRows).isEmpty());
        // with no match ParaBank still draws one placeholder row ("NaN-NaN-NaN undefined")
        return driver.findElements(resultRows).stream()
                .map(WebElement::getText).map(String::trim)
                .filter(t -> !t.isEmpty() && !t.contains("NaN"))
                .collect(Collectors.toList());
    }
}
