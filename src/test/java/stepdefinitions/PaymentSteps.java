package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AccountsOverviewPage;
import pages.BillPayPage;
import pages.RequestLoanPage;
import utils.DriverFactory;
import utils.TestContext;

import java.util.Map;

/** Bill pay and loan requests. */
public class PaymentSteps {

    private String firstAccount() {
        String first = TestContext.accounts.get("first");
        if (first == null) {
            AccountsOverviewPage page = new AccountsOverviewPage(DriverFactory.getDriver());
            page.openPage();
            first = page.balances().keySet().iterator().next();
            TestContext.accounts.put("first", first);
        }
        return first;
    }

    @When("user pays a bill from the first account with:")
    public void userPaysABill(Map<String, String> details) {
        String from = firstAccount();
        BillPayPage page = new BillPayPage(DriverFactory.getDriver());
        page.openPage();
        page.pay(details, from);
    }

    @And("the bill payment confirmation should show payee {string} and amount {string}")
    public void billPaymentConfirmation(String payee, String amount) {
        BillPayPage page = new BillPayPage(DriverFactory.getDriver());
        Assert.assertEquals(page.resultPayee(), payee);
        Assert.assertEquals(page.resultAmount(), amount);
        Assert.assertEquals(page.resultFromAccount(), TestContext.accounts.get("first"));
    }

    @When("user requests a loan of {string} with down payment {string} from the first account")
    public void userRequestsALoan(String amount, String down) {
        String from = firstAccount();
        RequestLoanPage page = new RequestLoanPage(DriverFactory.getDriver());
        page.openPage();
        page.apply(amount, down, from);
    }

    @Then("the loan status should be {string}")
    public void theLoanStatusShouldBe(String status) {
        Assert.assertEquals(new RequestLoanPage(DriverFactory.getDriver()).loanStatus(), status);
    }

    @And("a new loan account number should be shown")
    public void aNewLoanAccountShouldBeShown() {
        String id = new RequestLoanPage(DriverFactory.getDriver()).newLoanAccount();
        Assert.assertTrue(id.matches("\\d+"), "Loan account number should be digits: " + id);
    }
}
