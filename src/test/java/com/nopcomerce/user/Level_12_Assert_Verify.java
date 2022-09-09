package com.nopcomerce.user;

import java.util.concurrent.TimeUnit;

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

public class Level_12_Assert_Verify extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";
	}

	@Test
	public void User_01_Register_Login() {
		System.out.println(" Step 01: Click to Register link");
		registerPage = homePage.openRegisterPage();
		System.out.println("Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Step 04: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		System.out.println("Step 05: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		System.out.println("Login_02 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_06 - Step 02: Input to required fields.");

		loginPage.inputToEmailTextbox(emailAdress);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_06 - Step 03: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		System.out.println("Login_06 - Step 04: Verify Login Success");
		verifyFalse(homePage.isMyAccountLinkDisplayed());

		custormerInforPage = homePage.openCustomerInforPage();
		verifyFalse(custormerInforPage.isCustomerInforPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, emailAdress, password;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject custormerInforPage;

}
