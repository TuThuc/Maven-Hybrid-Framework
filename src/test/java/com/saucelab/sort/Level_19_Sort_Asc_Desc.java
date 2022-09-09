package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.sauceLab.LoginPageObject;
import pageObject.sauceLab.PageGeneratorManager;
import pageObject.sauceLab.ProductPageObject;

public class Level_19_Sort_Asc_Desc extends BaseTest {

	@Parameters({ "browser", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String saucelabUrl) {
		driver = getBrowerDriver(browserName, saucelabUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickToLoginButton();
	}

	// @Test
	public void Sort_01_Name() {
		// Ascending
		productPage.selectItemInProductSortDropdown("Name (A to Z)");

		Assert.assertTrue(productPage.isProductNameSortByAscending());
		// Descending
		productPage.selectItemInProductSortDropdown("Name (Z to A)");

		Assert.assertTrue(productPage.isProductNameSortByDescending());

	}

	@Test
	public void Sort_02_Price() {
		// Ascending
		productPage.selectItemInProductSortDropdown("Price (low to high)");

		Assert.assertTrue(productPage.isProductPriceSortByAscending());

		// Descending
		productPage.selectItemInProductSortDropdown("Price (high to low)");

		Assert.assertTrue(productPage.isProductPriceSortByDescending());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;

}
