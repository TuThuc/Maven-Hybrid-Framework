package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Level_11_Upload_Files extends BaseTest {
	String image = "images.png";
	String logo = "logo.png";
	String user = "user.png";
	String[] multipleFileNames = { image, logo, user };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowerDriver(browserName, appURL);
		homePage = pageObject.JQuery_UploadFile.PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		// Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, image);
		// Step 02 - Verify single file load success
		Assert.assertTrue(homePage.isFileLoadedByName(image));
		// Step 03 -click button Start
		homePage.clickToStartButtons();
		// Step 04- Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(image));
		// Step 05- Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUploadedByName(image));
	}

	@Test
	public void Upload_02_Multi_File_Per_Time() {
		homePage.refreshCurrentPage(driver);
		// Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, multipleFileNames);
		// Step 02 - Verify single file load success
		Assert.assertTrue(homePage.isFileLoadedByName(image));
		Assert.assertTrue(homePage.isFileLoadedByName(logo));
		Assert.assertTrue(homePage.isFileLoadedByName(user));
		// Step 03 -click button Start
		homePage.clickToStartButtons();
		// Step 04- Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(image));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(logo));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(user));
		// Step 05- Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUploadedByName(image));
		Assert.assertTrue(homePage.isFileImageUploadedByName(logo));
		Assert.assertTrue(homePage.isFileImageUploadedByName(user));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	pageObject.JQuery_UploadFile.HomePageObject homePage;

}
