package com.nopcomerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;

public class Level_15_ReportNG_Screenshot extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		validpassword = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' link");
		registerPage = homePage.openRegisterPage();

		log.info("Register - Step 02: Enter to FirstName textbox with value is " + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register - Step 03: Enter to FirstName textbox with value is " + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register - Step 04: Enter to emailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputToEmailTextbox(emailAdress);

		log.info("Register - Step 05: Enter to password textbox with value is " + validpassword + "'");
		registerPage.inputToPasswordTextbox(validpassword);

		log.info("Register - Step 06: Enter to ConfirmPasswod textbox with value is " + validpassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validpassword);

		log.info("Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Login - Step 02: Enter to EmailAdress textbox with value is " + emailAdress + "'");
		loginPage.inputToEmailTextbox(emailAdress);

		log.info("Login - Step 03: Enter to Password textbox with value is " + validpassword + "'");
		loginPage.inputToPasswordTextbox(validpassword);

		log.info("Login - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - Step 05: Verify 'MyAccount' link is displayed ");
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		log.info("Login - Step 06: Navigate to Customer infor page");
		custormerInforPage = homePage.openCustomerInforPage();

		log.info("Login - Step 07: Verify 'Customer infor' page is displayed ");
		Assert.assertFalse(custormerInforPage.isCustomerInforPageDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, emailAdress, validpassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject custormerInforPage;

}
