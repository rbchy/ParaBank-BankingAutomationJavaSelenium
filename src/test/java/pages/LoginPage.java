package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Home page login panel and the Log Out link. */
public class LoginPage extends BasePage {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginButton = By.cssSelector("input[type='submit'][value='Log In']");
    private final By error = By.cssSelector("#rightPanel p.error");
    private final By logoutLink = By.linkText("Log Out");
    private final By pageTitle = By.cssSelector("#rightPanel h1.title");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openHome() {
        open("index.htm");
        visible(username);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }

    public boolean isLoggedIn() {
        return !driver.findElements(logoutLink).isEmpty() && driver.findElement(logoutLink).isDisplayed();
    }

    public String title() {
        return text(pageTitle);
    }

    public String errorMessage() {
        return text(error);
    }

    public void logout() {
        click(logoutLink);
        visible(username);
    }

    public boolean isLoginFormShown() {
        return !driver.findElements(username).isEmpty() && driver.findElement(username).isDisplayed()
                && driver.findElements(logoutLink).isEmpty();
    }
}
