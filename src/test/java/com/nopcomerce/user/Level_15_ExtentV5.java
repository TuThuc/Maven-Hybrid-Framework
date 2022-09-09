package com.nopcomerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_15_ExtentV5 extends BaseTest {

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
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' link");

		registerPage = homePage.openRegisterPage();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to FirstName textbox with value is " + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to FirstName textbox with value is " + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to emailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputToEmailTextbox(emailAdress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to password textbox with value is " + validpassword + "'");
		registerPage.inputToPasswordTextbox(validpassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to ConfirmPasswod textbox with value is " + validpassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validpassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click to Logout link");

		homePage = registerPage.clickToLogoutLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to EmailAdress textbox with value is " + emailAdress + "'");
		loginPage.inputToEmailTextbox(emailAdress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is " + validpassword + "'");
		loginPage.inputToPasswordTextbox(validpassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'MyAccount' link is displayed ");
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Navigate to Customer infor page");
		custormerInforPage = homePage.openCustomerInforPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 07: Verify 'Customer infor' page is displayed ");
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
