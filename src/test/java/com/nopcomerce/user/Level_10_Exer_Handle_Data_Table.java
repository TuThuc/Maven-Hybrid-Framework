package com.nopcomerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;

public class Level_10_Exer_Handle_Data_Table extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Tu";
		lastName = "Thuc";
		password = "123456";
		emailAdress = "afc" + generateFakeNumber() + "@mail.vn";
		// adminEmail = "admin@yourstore.com";
		// adminPassword = "123456";
	}

	@Test
	public void TC_01_Verify_Register_New_Account() {
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
		System.out.println("Step 05: Switch to page admin - Open URL admin");
		registerPage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		// adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		// System.out.println("Step 05: Switch to page admin - Login admin Page");
		// adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, emailAdress, password;
	private UserHomePageObject homePage;

	private UserRegisterPageObject registerPage;
	// private AdminLoginPageObject adminLoginPage;
	// private AdminDashboardPageObject adminDashboardPage;

}
