package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserCustomerInforPageUI;

public class UserCustomerInforPageObject extends BasePage {
	WebDriver driver;

	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInforPageDisplayed() {
		waitForElementVisible(driver, UserCustomerInforPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplayed(driver, UserCustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}

}
