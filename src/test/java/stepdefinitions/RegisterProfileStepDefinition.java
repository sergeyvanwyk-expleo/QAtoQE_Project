package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.ProductDescriptionPageObject;
import pageobjects.MyAccountPageObject;
import pageobjects.RegistrationPageObject;

import java.util.concurrent.TimeUnit;

public class RegisterProfileStepDefinition {
    WebDriver driver;
    String homePageLink = "https://magento.abox.co.za/index.php/";
    String product = "Marco Lightweight Active Hoodie";

    // the link to the prodect description page. Removing the spaces and "/" from the name and replacing them with "-" so the link can be accessed
    String productLink = homePageLink + product.replace("/", "-").replace(" ", "-") + ".html";
    String firstName = "John",
            lastName = "Cena",
            emailAddress = "JC1234@wwe.com",
            password = "I@MJohnCena";

    @Given("i opened product description page url")
    public void i_opened_product_description_page_url() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(productLink);
        // System.out.println(driver.getTitle());
        Assert.assertEquals(product, driver.getTitle());
    }


    @When("i view product description page")
    public void i_view_product_description_page() {
        ProductDescriptionPageObject productDescriptionPage = PageFactory.initElements(driver, ProductDescriptionPageObject.class);

        // Getting the product name to test that we are on the correct product description page
        String getProductName = driver.findElement(By.className("base")).getText();
        Assert.assertEquals(getProductName, product);
        productDescriptionPage.clickOnCreateAnAccountLink();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        // homePage.clickOnSignInLink();
    }

    @Then("i must be able to register my profile")
    public void i_must_be_able_to_register_my_profile() {
        System.out.println(driver.getTitle());

        // Testing to make sure that we are on the create new customer account page
        Assert.assertEquals("Create New Customer Account", driver.getTitle());
        Assert.assertEquals("Create New Customer Account", driver.findElement(By.className("page-title")).getText());

        // enter information to register new client
        RegistrationPageObject registrationPage = PageFactory.initElements(driver, RegistrationPageObject.class);
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.clickOnSignUpForNewsletter();
        registrationPage.enterEmailAddress(emailAddress);
        registrationPage.enterPassword(password);
        registrationPage.enterPasswordConfirmation(password);
        registrationPage.clickCreateAnAccountButton();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // Testing to make sure that we have successfully registered the client by testing the contact information
        MyAccountPageObject myAccountPage = PageFactory.initElements(driver, MyAccountPageObject.class);
        Assert.assertEquals(firstName + " " + lastName + "\n" + emailAddress, myAccountPage.getContactInformation());
        driver.close();
    }
}
