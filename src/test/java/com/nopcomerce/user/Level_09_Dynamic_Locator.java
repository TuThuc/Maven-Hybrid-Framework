package com.nopcomerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserRewardPointPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {

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
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Step 05: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		System.out.println("Login_02 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_06 - Step 02: Input to required fields");

		loginPage.inputToEmailTextbox(emailAdress);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_06 - Step 03: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		System.out.println("Login_06 - Step 04: Verify Login Success");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		custormerInforPage = homePage.openCustomerInforPage();
		Assert.assertTrue(custormerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_02_Switch_Page() {
		// Customer -> Address
		addressPage = custormerInforPage.openAddressPage(driver);
		// Address -> My Product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		// My Product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		// Reward Point --> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		// address ->reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		// Reward Point -> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		// My Product Review -> Address
		addressPage = myProductReviewPage.openAddressPage(driver);

		custormerInforPage = addressPage.openCustomerInforPage(driver);

	}

	@Test
	public void User_03_Dynamic_Page_01() {
		// Customer -> Address
		addressPage = (UserAddressPageObject) custormerInforPage.openPagesAtMyAccountByName(driver, "Addresses");
		// Address -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openPagesAtMyAccountByName(driver, "My product reviews");
		// My Product Review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		// Reward Point --> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		// address ->reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		// Reward Point -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		// My Product Review -> Customer info
		custormerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");

	}

	@Test
	public void User_03_Dynamic_Page_02() {
		// Customer -> Address
		custormerInforPage.openPagesAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		// Address -> My Product Review
		addressPage.openPagesAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		// My Product Review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		// Reward Point --> Address
		rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		// address ->reward Point
		addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		// Reward Point -> My Product Review
		rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		// My Product Review -> Customer info
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
		custormerInforPage = PageGeneratorManager.getUserCustomerInforPage(driver);

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
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

}
