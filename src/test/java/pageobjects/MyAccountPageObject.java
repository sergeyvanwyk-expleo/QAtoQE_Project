package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPageObject {

    @FindBy(xpath = "//div[@class='box box-information']//div[@class='box-content']")
    private WebElement contactInformation;

    public String getContactInformation(){
        return contactInformation.getText();
    }

}
