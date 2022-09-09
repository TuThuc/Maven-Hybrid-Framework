package pageObject.facebook;


import org.openqa.selenium.WebDriver;


import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
WebDriver driver;


	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
public void clickToCreateNewAccountButton(WebDriver driver) {
	waitForElementVisible(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
	clickToElement(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
}
public boolean isEmailAddressTextboxDisplayed(WebDriver driver) {
	waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	
	return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
}
public void enterEmailAddressTextbox(String emailAddress) {
	waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	
}
public boolean isConfirmEmailAddressTextboxDisplayed() {
	
	return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
}
public void clickCloseIconAtRegisterForm() {
	waitForElementVisible(driver, LoginPageUI.CLOSE_ICON);
	clickToElement(driver, LoginPageUI.CLOSE_ICON);
}
public boolean isConfirmEmailAddressTextboxUnDisplayed() {
	waitForElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	return isElementUnDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
}
	
}

