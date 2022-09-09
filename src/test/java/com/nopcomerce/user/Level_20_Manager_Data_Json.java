package com.nopcomerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_20_Manager_Data_Json extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMapper.getUserData();
		emailAddress = userData.getEmailAddress() + generateFakeNumber() + "@hotmail.com";
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' link");
		registerPage = homePage.openRegisterPage();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click to Checkbox Gender");
		// log.info("Register - Step 01: Click to Checkbox Gender");
		registerPage.clickToCheckboxByLabel(driver, "Female");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to FirstName textbox with value is " + userData.getUsername() + "'");
		registerPage.inputTextboxByID(driver, "FirstName", userData.getUsername());

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to LastName textbox with value is " + userData.getLastName() + "'");
		registerPage.inputTextboxByID(driver, "LastName", userData.getLastName());

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Select Day of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", userData.getDate());

		ExtentTestManager.getTest().log(Status.INFO, "Select Month of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", userData.getMonth());

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select year of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", userData.getYear());

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Enter to emailAdress textbox with value is " + emailAddress);
		registerPage.inputTextboxByID(driver, "Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Click to Radio ");
		registerPage.clickToRadioByLabel(driver, "Newsletter:");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09: Enter to password textbox with value is " + userData.getPassword() + "'");
		registerPage.inputTextboxByID(driver, "Password", userData.getPassword());

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10: Enter to ConfirmPasswod textbox with value is " + userData.getPassword() + "'");
		registerPage.inputTextboxByID(driver, "ConfirmPassword", userData.getPassword());

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11: Click to 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 13: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click to Logout link");
		loginPage = homePage.openLoginPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to EmailAdress textbox with value is " + emailAddress);
		registerPage.inputTextboxByID(driver, "Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is " + userData.getPassword() + "'");
		registerPage.inputTextboxByID(driver, "Password", userData.getPassword());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'MyAccount' link is displayed ");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_My_Account(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_03_My_Account");
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 01: Navigate to Customer infor page");
		custormerInforPage = homePage.openCustomerInforPage();

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 02: Verify 'Customer infor' page is displayed ");
		verifyTrue(custormerInforPage.isCustomerInforPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 03: Verify 'FirstName' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "FirstName"), userData.getFirstName());

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 04: Verify 'LastName' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "LastName"), userData.getLastName());

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 05: Verify 'Email' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;

	private String emailAddress;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject custormerInforPage;
	UserDataMapper userData;

}
