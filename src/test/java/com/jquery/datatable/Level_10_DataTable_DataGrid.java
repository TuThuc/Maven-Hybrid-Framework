package com.jquery.datatable;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.JQuery_Datatable.HomePageObject;
import pageObject.JQuery_Datatable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowerDriver(browserName, appURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	// @Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		homePage.openPagingByPageNumber("15");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("15"));
		homePage.openPagingByPageNumber("5");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("5"));
	}

	// @Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
		homePage.enterToHeaderTextboxByLabel("Females", "276880");
		homePage.enterToHeaderTextboxByLabel("Males", "276472");
		homePage.enterToHeaderTextboxByLabel("Total", "553353");
		homePage.sleepInSecond(3);

	}

	// @Test
	public void Table_03_Enter_To_Header() {
		// Đọc dữ liệu của file country.txt ra
		// Lưu vào 1 List<String> = Expected Value

		// actual value
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}

	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(5);
		// Value de nhap lieu - tham so 1
		// Row number: tại row nào
		// ex: nhập vào textbox tai dong so 3/5/2
		// Column name: Album/Artist/year/ price

		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "2", "Micheal 97");
		homePage.sleepInSecond(2);

		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "3", "Micheal Jackson");
		homePage.sleepInSecond(2);
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "4", "1997");
		homePage.sleepInSecond(2);
		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "5", "15");

		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Japan");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "4", "Hong Kong");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "US");
		homePage.sleepInSecond(3);
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.sleepInSecond(3);
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		homePage.sleepInSecond(3);
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.sleepInSecond(3);
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.sleepInSecond(3);
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		homePage.sleepInSecond(3);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1", "Move Down");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;

}
