package com.facebook.register;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.facebook.LoginPageObject;

public class Level_13_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowerDriver(browserName, appURL);
		loginPage = pageObject.facebook.PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton(driver);
		
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed(driver));
		
	}

	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		// Verify True - mong đợi Confirm Email displayed(true)
	
	loginPage.enterEmailAddressTextbox("automation@gmail.com");
	loginPage.sleepInSecond(3);
	verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
	// Verify False - mong đợi Confirm Email undisplayed(false)
	loginPage.enterEmailAddressTextbox("");
	loginPage.sleepInSecond(3);
	//verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
	verifyTrue(loginPage.isConfirmEmailAddressTextboxUnDisplayed());
	}
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUnDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;

}
