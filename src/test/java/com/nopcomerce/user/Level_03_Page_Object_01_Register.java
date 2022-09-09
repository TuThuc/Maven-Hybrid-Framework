package com.nopcomerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register {
	private WebDriver driver;
	private String firstName, lastName, emailAdress, password;

	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Mở URL lên nó qua trang HomePage
		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";

	}

	@Test
	public void Register_01_Rgister_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Register_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_02 - Step 02: Input to required fields");

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@!@#$");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_02 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Register_Success() {
		System.out.println("Register_03 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_03 - Step 02: Input to required fields");

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_03 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_Register_Existing_Email() {

		System.out.println("Register_04 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_04 - Step 02: Input to required fields");

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_04 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_04 - Step 04: Verify error existing email message displayed");

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Register_Password_Less_Than_6_Chars() {

		System.out.println("Register_05 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_05 - Step 02: Input to required fields");

		registerPage.inputToFirstnameTextbox(lastName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register_05 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Register_Invalid_Confirm_Password() {

		System.out.println("Register_06 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		// Click Register link -> nhảy qua trang Register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_06 - Step 02: Input to required fields");

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(emailAdress);

		System.out.println("Register_06 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
