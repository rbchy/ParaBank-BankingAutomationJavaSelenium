package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** overview.htm — the account table is filled by AJAX after the page loads. */
public class AccountsOverviewPage extends BasePage {

    private final By accountLinks = By.cssSelector("#accountTable tbody tr td:first-child a");
    private final By rows = By.cssSelector("#accountTable tbody tr");

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("overview.htm");
        wait.until(d -> !d.findElements(accountLinks).isEmpty());
    }

    /** Account number → balance, in table order. */
    public Map<String, BigDecimal> balances() {
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (WebElement row : driver.findElements(rows)) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<WebElement> link = row.findElements(By.cssSelector("td:first-child a"));
            if (!link.isEmpty() && cells.size() >= 2) {
                result.put(link.get(0).getText().trim(), money(cells.get(1).getText()));
            }
        }
        return result;
    }

    /** The value in the "Total" row. */
    public BigDecimal total() {
        for (WebElement row : driver.findElements(rows)) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && cells.get(0).getText().trim().equalsIgnoreCase("Total")) {
                return money(cells.get(1).getText());
            }
        }
        throw new AssertionError("No Total row in the accounts table");
    }

    public static BigDecimal money(String text) {
        String t = text.trim().replace("$", "").replace(",", "");
        boolean negative = t.startsWith("-") || t.startsWith("(");
        t = t.replace("-", "").replace("(", "").replace(")", "");
        BigDecimal v = new BigDecimal(t);
        return negative ? v.negate() : v;
    }
}
