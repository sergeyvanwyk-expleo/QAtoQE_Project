package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageObject {
    private WebElement firstname;
    private WebElement lastname;
    private WebElement email_address;
    private WebElement password;
    private WebElement is_subscribed;

    @FindBy(id = "password-confirmation")
    private WebElement passwordConfirmation;

    @FindBy(xpath = "//button[@class='action submit primary']")
    private WebElement createAnAccountButton;

    public void enterFirstName(String firstName){
        firstname.clear();
        firstname.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastname.clear();
        lastname.sendKeys(lastName);
    }

    public void enterEmailAddress(String emailAddress){
        email_address.clear();
        email_address.sendKeys(emailAddress);
    }

    public void enterPassword(String newPassword){
        password.clear();
        password.sendKeys(newPassword);
    }

    public void clickOnSignUpForNewsletter(){
     //   is_subscribed.clear();
        is_subscribed.click();
    }
    public void enterPasswordConfirmation(String newPassword){
        passwordConfirmation.clear();
        passwordConfirmation.sendKeys(newPassword);
    }

    public void clickCreateAnAccountButton(){
        createAnAccountButton.click();
    }
}
