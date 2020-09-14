
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.MyAccountPageObject;
import pageobjects.ProductDescriptionPageObject;
import pageobjects.RegistrationPageObject;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class NegativeTesting {
    WebDriver driver;
    String homePageLink = "https://magento.abox.co.za/index.php/";
    String product = "Marco Lightweight Active Hoodie";

    // the link to the prodect description page. Removing the spaces and "/" from the name and replacing them with "-" so the link can be accessed
    String productLink = homePageLink + product.replace("/", "-").replace(" ", "-") + ".html";
    String firstName = "John",
            lastName = "Cena",
            emailAddress = "I am not an email",
            password = "Dont think this is a password";

    @Before
    public void setupBeforeTest()
    {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(productLink);
        // System.out.println(driver.getTitle());
        Assert.assertEquals(product, driver.getTitle());
        ProductDescriptionPageObject productDescriptionPage = PageFactory.initElements(driver, ProductDescriptionPageObject.class);
        productDescriptionPage.clickOnCreateAnAccountLink();
    }

    @After
    public void tearDownAfterTest() {
        driver.close();
    }

    @Test
    public void registerTest() {
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

        /*
        driver.findElement(By.id("email")).sendKeys("ww@centralcity.com");
        driver.findElement(By.id("pass")).sendKeys("thefl@sh10");
        driver.findElement(By.id("send2")).click();
        */

        // Testing to make sure that we have successfully registered the client by testing the contact information
        MyAccountPageObject myAccountPage = PageFactory.initElements(driver, MyAccountPageObject.class);
        Assert.assertEquals(firstName + " " + lastName + "\n" + emailAddress, myAccountPage.getContactInformation());
        driver.close();
    }

}