package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.ForgotLoginPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Customer;
import utils.DriverFactory;
import utils.TestContext;

/** Login, logout, registration, forgot-login and the shared message checks. */
public class AuthSteps {

    private WebDriver driver() {
        return DriverFactory.getDriver();
    }

    private LoginPage login() {
        return new LoginPage(driver());
    }

    /** Registers the run's customer once; leaves the browser logged in as that customer. */
    @Given("a registered ParaBank customer")
    public void aRegisteredCustomer() {
        if (TestContext.customer == null) {
            Customer c = Customer.unique();
            RegisterPage page = new RegisterPage(driver());
            page.openPage();
            page.register(c, c.password);
            Assert.assertTrue(page.pageShows("Your account was created successfully"),
                    "Registration of the test customer failed: " + page.bodyText());
            TestContext.customer = c;
        } else {
            loginAsCustomer();
        }
        Assert.assertTrue(login().isLoggedIn(), "Customer should be logged in");
    }

    @Given("user is logged into Parabank")
    public void userIsLoggedIn() {
        aRegisteredCustomer();
    }

    private void loginAsCustomer() {
        LoginPage page = login();
        page.openHome();
        page.login(TestContext.customer.username, TestContext.customer.password);
        Assert.assertTrue(page.pageShows("Accounts Overview"),
                "Login as the test customer failed: " + page.bodyText());
    }

    @Given("user is on the ParaBank home page")
    public void userIsOnHomePage() {
        login().openHome();
    }

    @When("user logs in with the registered credentials")
    public void userLogsInWithRegisteredCredentials() {
        login().login(TestContext.customer.username, TestContext.customer.password);
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWith(String username, String password) {
        login().login(username, password);
    }

    @Then("user should be logged in and see {string}")
    public void userShouldBeLoggedInAndSee(String title) {
        LoginPage page = login();
        Assert.assertTrue(page.pageShows(title), "Expected page title " + title);
        Assert.assertEquals(page.title(), title);
        Assert.assertTrue(page.isLoggedIn(), "Log Out link should be visible");
    }

    @Then("the login error {string} should be shown")
    public void theLoginErrorShouldBeShown(String message) {
        Assert.assertEquals(login().errorMessage(), message);
    }

    @Then("user should not be logged in")
    public void userShouldNotBeLoggedIn() {
        Assert.assertFalse(login().isLoggedIn(), "Log Out link must not be visible");
    }

    @When("user clicks logout")
    public void userClicksLogout() {
        login().logout();
    }

    @Then("user should be logged out successfully")
    public void userShouldBeLoggedOut() {
        LoginPage page = login();
        Assert.assertTrue(page.isLoginFormShown(), "Login form should be back and Log Out gone");
        Assert.assertTrue(driver().getCurrentUrl().contains("index.htm"),
                "Logout should return to the home page, was " + driver().getCurrentUrl());
    }

    // ---------- registration ----------

    private Customer newCustomer;

    @Given("user is on the registration page")
    public void userIsOnRegistrationPage() {
        new RegisterPage(driver()).openPage();
    }

    @When("user registers a new customer with a unique username")
    public void userRegistersNewCustomer() {
        newCustomer = Customer.unique();
        new RegisterPage(driver()).register(newCustomer, newCustomer.password);
    }

    @When("user registers a new customer with confirm password {string}")
    public void userRegistersWithConfirm(String confirm) {
        newCustomer = Customer.unique();
        new RegisterPage(driver()).register(newCustomer, confirm);
    }

    @When("user registers again with the same username")
    public void userRegistersAgainWithSameUsername() {
        Customer copy = Customer.unique();
        copy.username = TestContext.customer.username;
        new RegisterPage(driver()).register(copy, copy.password);
    }

    @Then("the welcome message for the new username should be shown")
    public void theWelcomeMessageShouldBeShown() {
        LoginPage page = login();
        Assert.assertTrue(page.pageShows("Welcome " + newCustomer.username),
                "Expected 'Welcome " + newCustomer.username + "'");
        Assert.assertTrue(page.isLoggedIn(), "A new customer is logged in straight away");
    }

    // ---------- forgot login info ----------

    @When("user looks up login info with the registered SSN")
    public void userLooksUpLoginInfo() {
        ForgotLoginPage page = new ForgotLoginPage(driver());
        page.openPage();
        page.lookup(TestContext.customer, TestContext.customer.ssn);
    }

    @When("user looks up login info with SSN {string}")
    public void userLooksUpLoginInfoWithSsn(String ssn) {
        ForgotLoginPage page = new ForgotLoginPage(driver());
        page.openPage();
        page.lookup(TestContext.customer, ssn);
    }

    @And("the registered username should be shown")
    public void theRegisteredUsernameShouldBeShown() {
        Assert.assertTrue(new ForgotLoginPage(driver()).pageShows("Username: " + TestContext.customer.username),
                "Expected the looked-up username");
    }

    // ---------- shared message checks ----------

    @Then("the message {string} should be shown")
    public void theMessageShouldBeShown(String message) {
        BasePage page = login();
        Assert.assertTrue(page.pageShows(message),
                "Expected the page to show \"" + message + "\"");
    }

    @Then("the message {string} should not be shown")
    public void theMessageShouldNotBeShown(String message) {
        Assert.assertFalse(login().bodyText().contains(message),
                "The page must not show \"" + message + "\"");
    }
}
