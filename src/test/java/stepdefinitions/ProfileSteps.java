package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.UpdateContactInfoPage;
import utils.Customer;
import utils.DriverFactory;
import utils.TestContext;

import java.util.HashMap;
import java.util.Map;

/** Update Contact Info. */
public class ProfileSteps {

    private Map<String, String> entered = new HashMap<>();

    private UpdateContactInfoPage page() {
        return new UpdateContactInfoPage(DriverFactory.getDriver());
    }

    @When("user navigates to Update Contact Info page")
    public void userNavigatesToUpdateContactInfo() {
        page().openPage();
    }

    @And("user updates contact info with:")
    public void userUpdatesContactInfo(Map<String, String> data) {
        entered = new HashMap<>(data);
        page().enterContactInfo(data);
    }

    @And("user clears the first name")
    public void userClearsFirstName() {
        page().clearFirstName();
    }

    @And("user clicks Update")
    public void userClicksUpdate() {
        page().clickUpdate();
    }

    @Then("reopening Update Contact Info should show the new values")
    public void reopeningShouldShowNewValues() {
        Assert.assertTrue(page().pageShows("Profile Updated"), "Update did not finish");
        // keep the shared customer in step with what is now stored (used by Forgot Login)
        Customer c = TestContext.customer;
        c.firstName = entered.get("firstName");
        c.lastName = entered.get("lastName");
        c.street = entered.get("address");
        c.city = entered.get("city");
        c.state = entered.get("state");
        c.zipCode = entered.get("zipCode");
        c.phone = entered.get("phone");

        UpdateContactInfoPage page = page();
        page.openPage();
        entered.forEach((field, value) ->
                Assert.assertEquals(page.value(field), value, "Saved value of " + field));
    }
}
