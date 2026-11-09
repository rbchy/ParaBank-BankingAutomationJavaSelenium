package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AccountsOverviewPage;
import pages.FindTransactionsPage;
import pages.OpenAccountPage;
import pages.TransferPage;
import utils.DriverFactory;
import utils.TestContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Accounts overview, open account, fund transfer and find transactions. */
public class AccountSteps {

    private WebDriver driver() {
        return DriverFactory.getDriver();
    }

    private AccountsOverviewPage overview() {
        return new AccountsOverviewPage(driver());
    }

    private List<String> accountNumbers() {
        AccountsOverviewPage page = overview();
        page.openPage();
        return new ArrayList<>(page.balances().keySet());
    }

    /** "first" is the customer's first account; "second" is opened if missing. */
    static String first() {
        return TestContext.accounts.get("first");
    }

    @When("user navigates to Accounts Overview page")
    public void userNavigatesToAccountsOverview() {
        overview().openPage();
    }

    @Then("user should see at least {int} account with a balance")
    public void userShouldSeeAccounts(int min) {
        Map<String, BigDecimal> balances = overview().balances();
        Assert.assertTrue(balances.size() >= min, "Expected at least " + min + " account, got " + balances);
        balances.keySet().forEach(n -> Assert.assertTrue(n.matches("\\d+"), "Account number should be digits: " + n));
    }

    @And("the Total row should equal the sum of the account balances")
    public void totalShouldEqualSum() {
        AccountsOverviewPage page = overview();
        BigDecimal sum = page.balances().values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(page.total().compareTo(sum), 0,
                "Total " + page.total() + " should equal the sum " + sum);
    }

    @When("user opens a new {string} account funded from the first account")
    public void userOpensNewAccount(String type) {
        List<String> existing = accountNumbers();
        OpenAccountPage page = new OpenAccountPage(driver());
        page.openPage();
        String created = page.openAccount(type, existing.get(0));
        Assert.assertTrue(created.matches("\\d+"), "New account number should be digits: " + created);
        Assert.assertFalse(existing.contains(created), "New account must be a new number");
        TestContext.accounts.put("new", created);
    }

    @And("the new account should be listed in Accounts Overview")
    public void theNewAccountShouldBeListed() {
        Assert.assertTrue(accountNumbers().contains(TestContext.accounts.get("new")),
                "Accounts Overview should list " + TestContext.accounts.get("new"));
    }

    @Given("user has a second account")
    public void userHasASecondAccount() {
        List<String> numbers = accountNumbers();
        if (numbers.size() < 2) {
            OpenAccountPage page = new OpenAccountPage(driver());
            page.openPage();
            page.openAccount("CHECKING", numbers.get(0));
            numbers = accountNumbers();
        }
        TestContext.accounts.put("first", numbers.get(0));
        TestContext.accounts.put("second", numbers.get(1));
    }

    @Given("the account balances are noted")
    public void theAccountBalancesAreNoted() {
        AccountsOverviewPage page = overview();
        page.openPage();
        Map<String, BigDecimal> balances = page.balances();
        TestContext.balancesBefore.putAll(balances);
        TestContext.accounts.putIfAbsent("first", balances.keySet().iterator().next());
    }

    @When("user transfers {string} from the first account to the second account")
    public void userTransfers(String amount) {
        TransferPage page = new TransferPage(driver());
        page.openPage();
        page.transfer(amount, TestContext.accounts.get("first"), TestContext.accounts.get("second"));
        TestContext.lastAmount = amount;
        Assert.assertTrue(page.pageShows("Transfer Complete!"), "Transfer did not complete");
    }

    @Then("the confirmation should show {string} from the first account to the second account")
    public void theConfirmationShouldShow(String amount) {
        TransferPage page = new TransferPage(driver());
        Assert.assertEquals(page.transferredAmount(), amount);
        Assert.assertEquals(page.fromAccountResult(), TestContext.accounts.get("first"));
        Assert.assertEquals(page.toAccountResult(), TestContext.accounts.get("second"));
    }

    @Then("the {word} account balance should be {double} lower")
    public void accountBalanceLower(String which, double delta) {
        assertBalanceChange(which, BigDecimal.valueOf(delta).negate());
    }

    @Then("the {word} account balance should be {double} higher")
    public void accountBalanceHigher(String which, double delta) {
        assertBalanceChange(which, BigDecimal.valueOf(delta));
    }

    private void assertBalanceChange(String which, BigDecimal delta) {
        String account = TestContext.accounts.get(which);
        BigDecimal before = TestContext.balancesBefore.get(account);
        Assert.assertNotNull(before, "No noted balance for the " + which + " account");
        AccountsOverviewPage page = overview();
        page.openPage();
        BigDecimal after = page.balances().get(account);
        Assert.assertEquals(after.compareTo(before.add(delta)), 0,
                "Account " + account + ": before " + before + ", after " + after + ", expected change " + delta);
    }

    // ---------- find transactions ----------

    @When("user searches the first account's transactions for amount {string}")
    public void userSearchesByAmount(String amount) {
        FindTransactionsPage page = new FindTransactionsPage(driver());
        page.openPage();
        page.findByAmount(TestContext.accounts.get("first"), amount);
    }

    @When("user searches the first account's transactions from {string} to {string}")
    public void userSearchesByDateRange(String from, String to) {
        FindTransactionsPage page = new FindTransactionsPage(driver());
        page.openPage();
        page.findByDateRange(TestContext.accounts.get("first"), from, to);
    }

    @Then("every result should mention {string}")
    public void everyResultShouldMention(String text) {
        List<String> rows = new FindTransactionsPage(driver()).results();
        Assert.assertFalse(rows.isEmpty(), "Expected at least one transaction");
        rows.forEach(r -> Assert.assertTrue(r.contains(text), "Row should mention " + text + ": " + r));
        rows.forEach(r -> Assert.assertTrue(r.contains("$" + TestContext.lastAmount),
                "Row should show the amount $" + TestContext.lastAmount + ": " + r));
    }

    @Then("at least {int} transaction should be listed")
    public void atLeastTransactions(int min) {
        List<String> rows = new FindTransactionsPage(driver()).results();
        Assert.assertTrue(rows.size() >= min, "Expected at least " + min + " transaction, got " + rows);
    }
}
