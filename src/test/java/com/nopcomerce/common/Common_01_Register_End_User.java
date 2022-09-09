package com.nopcomerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;

public class Common_01_Register_End_User extends BaseTest {

	@Parameters("browser")
	@BeforeTest(description = "Creat new common User for all classes Test")
	public void Register(String browserName) {
		driver = getBrowerDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";
		log.info("Pre-Condition - Step 01: Navigate to 'Register' link");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-Condition- Step 02: Enter to FirstName textbox with value is " + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-Condition - Step 03: Enter to FirstName textbox with value is " + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-Condition - Step 04: Enter to emailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputToEmailTextbox(emailAdress);

		log.info("Pre-Condition - Step 05: Enter to password textbox with value is " + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-Condition - Step 06: Enter to ConfirmPasswod textbox with value is " + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-Condition - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-Condition - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName;
	public static String emailAdress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

}
