package pageObject.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
	}

	public boolean isProductNameSortByAscending() {
		// Khai bao ra ArrayList để chưa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		// Tạo ra arrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		// Sort cái ProductList
		Collections.sort(productSortList);

		// So sánh 2 list đã bằng nhau

		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByDescending() {
		// Khai bao ra ArrayList để chưa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		// Tạo ra arrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		// Sort cái ProductList
		Collections.sort(productSortList);

		// Reverse cái ProductList
		Collections.reverse(productSortList);

		// So sánh 2 list đã bằng nhau

		return productSortList.equals(productUIList);
	}

	// Note chưa chạy trên java 11
	// public boolean isProductNameSortByDescendingLambda() {
	// List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
	// List<String> names = elementLists.stream().map(n-> n.getText()).collect(Collections.toList());
	// List<String> sortedNames = new ArrayList<String>(names);
	// Collections.sort(sortedNames);
	// return names.equals(sortedNames);
	// }
	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);

		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);

		Collections.reverse(productSortList);

		return productSortList.equals(productUIList);
	}

}
