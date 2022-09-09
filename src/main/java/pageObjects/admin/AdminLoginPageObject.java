package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject  extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
}
	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAdress);
	}
public void inputPasswordTextbox(String password) {
	waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
	sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}
public AdminDashboardPageObject clickToLoginButton() {
	waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
	clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
	 return  PageGeneratorManager.getAdminDashboardPage(driver);
}
public AdminDashboardPageObject loginAsAdmin(String emailAdress, String password) {
	inputToEmailTextbox(emailAdress);
	inputPasswordTextbox(password);
	clickToLoginButton();
	return PageGeneratorManager.getAdminDashboardPage(driver);
}
}
