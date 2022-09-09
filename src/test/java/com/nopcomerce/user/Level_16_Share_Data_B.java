package com.nopcomerce.user;

import org.openqa.selenium.WebDriver;
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

public class Level_16_Share_Data_B extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		validpassword = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";
		log.info("Precondition - Step 01: Navigate to 'Register' link");
		registerPage = homePage.openRegisterPage();

		log.info("Precondition - Step 02: Enter to FirstName textbox with value is " + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Precondition - Step 03: Enter to FirstName textbox with value is " + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Precondition - Step 04: Enter to emailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputToEmailTextbox(emailAdress);

		log.info("Precondition - Step 05: Enter to password textbox with value is " + validpassword + "'");
		registerPage.inputToPasswordTextbox(validpassword);

		log.info("Precondition - Step 06: Enter to ConfirmPasswod textbox with value is " + validpassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validpassword);

		log.info("Precondition - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Precondition - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("Precondition - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("Precondition - Step 10: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Precondition - Step 11: Enter to EmailAdress textbox with value is " + emailAdress + "'");
		loginPage.inputToEmailTextbox(emailAdress);

		log.info("Precondition - Step 12: Enter to Password textbox with value is " + validpassword + "'");
		loginPage.inputToPasswordTextbox(validpassword);

		log.info("Precondition - Step 13: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Precondition - Step 14: Verify 'MyAccount' link is displayed ");
		verifyFalse(homePage.isMyAccountLinkDisplayed());

		log.info("Precondition - Step 15: Navigate to Customer infor page");
		custormerInforPage = homePage.openCustomerInforPage();

		log.info("Precondition - Step 16: Verify 'Customer infor' page is displayed ");
		verifyFalse(custormerInforPage.isCustomerInforPageDisplayed());

	}

	@Test
	public void Search_01_Empty_Data() {

	}

	@Test
	public void Search_02_Relative_Product_Name() {

	}

	@Test
	public void Search_03_Absolute_Product_Name() {

	}

	@Test
	public void Search_04_Parent_Category() {

	}

	@Test
	public void Search_05_Incorrect_Manufactorer() {

	}

	@Test
	public void Search_06_Incorrect_Manufactorer() {

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
