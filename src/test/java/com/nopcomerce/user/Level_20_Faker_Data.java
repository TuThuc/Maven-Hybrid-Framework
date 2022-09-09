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
import utlities.DataHelper;

public class Level_20_Faker_Data extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		dataFaker = DataHelper.getDataHelper();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		validpassword = dataFaker.getPassword();
		emailAdress = dataFaker.getEmailAddress();
		date = "10";
		month = "August";
		year = "1992";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' link");
		registerPage = homePage.openRegisterPage();

		log.info("Register - Step 01: Click to Checkbox Gender");
		registerPage.clickToCheckboxByLabel(driver, "Female");

		log.info("Register - Step 02: Enter to FirstName textbox with value is " + firstName + "'");
		registerPage.inputTextboxByID(driver, "FirstName", firstName);

		log.info("Register - Step 03: Enter to LastName textbox with value is " + lastName + "'");
		registerPage.inputTextboxByID(driver, "LastName", lastName);

		log.info("Step 04: Select Day of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);

		log.info("Step 04: Select Month of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);

		log.info("Step 04: Select year of Birthday");
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

		log.info("Register - Step 04: Enter to emailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputTextboxByID(driver, "Email", emailAdress);

		log.info("Register - Step 01: Click to Radio ");
		registerPage.clickToRadioByLabel(driver, "Newsletter:");

		log.info("Register - Step 05: Enter to password textbox with value is " + validpassword + "'");
		registerPage.inputTextboxByID(driver, "Password", validpassword);

		log.info("Register - Step 06: Enter to ConfirmPasswod textbox with value is " + validpassword + "'");
		registerPage.inputTextboxByID(driver, "ConfirmPassword", validpassword);

		log.info("Register - Step 07: Click to 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Login - Step 02: Enter to EmailAdress textbox with value is " + emailAdress + "'");
		registerPage.inputTextboxByID(driver, "Email", emailAdress);

		log.info("Login - Step 03: Enter to Password textbox with value is " + validpassword + "'");
		registerPage.inputTextboxByID(driver, "Password", validpassword);

		log.info("Login - Step 04: Click to 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Login - Step 05: Verify 'MyAccount' link is displayed ");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_My_Account() {
		log.info("MyAccount - Step 01: Navigate to Customer infor page");
		custormerInforPage = homePage.openCustomerInforPage();

		log.info("MyAccount - Step 02: Verify 'Customer infor' page is displayed ");
		verifyTrue(custormerInforPage.isCustomerInforPageDisplayed());

		log.info("MyAccount - Step 03: Verify 'FirstName' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "FirstName"), firstName);

		log.info("MyAccount - Step 04: Verify 'LastName' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "LastName"), lastName);

		log.info("MyAccount - Step 05: Verify 'Email' value is correctly ");
		verifyEquals(custormerInforPage.getTextboxValueByID(driver, "Email"), emailAdress);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private DataHelper dataFaker;
	private String firstName, lastName, emailAdress, validpassword, date, month, year;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject custormerInforPage;

}
