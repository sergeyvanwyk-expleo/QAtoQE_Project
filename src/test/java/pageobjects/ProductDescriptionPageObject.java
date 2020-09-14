package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDescriptionPageObject {

    @FindBy(linkText = "Create an Account")
    private WebElement createAnAccountLink;
/*
    @FindBy(linkText = "Sign In")
    private WebElement signInLink;
 */
    public void clickOnCreateAnAccountLink() {
        createAnAccountLink.click();
    }
/*
    public void clickOnSignInLink() {
        signInLink.click();
    }
 */
}
