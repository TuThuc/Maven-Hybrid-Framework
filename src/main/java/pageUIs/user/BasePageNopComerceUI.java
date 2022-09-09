package pageUIs.user;

public class BasePageNopComerceUI {
	public static final String ADDRESS_LINK = "XPath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String REWARD_POINTS_LINK = "Xpath=//a[text()='Reward points']";
	public static final String ADDRESS_PAGE = "XPATH=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//a[text()='My product reviews']";
	public static final String CUSTOMER_INFOR__LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Customer info']";

	public static final String LOGOUT__LINK_AT_USER = "css=a[class = 'ico-logout']";
	public static final String LOGOUT__LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	// Pattern Object
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";

	public static final String DYNAMIC_TEXTBOX_BY_ID = "Xpath=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "Xpath=//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "Xpath=//select[@name='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "Xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_RADIO_BY_LABEL = "Xpath=//label[contains(text(),'%s')]/following-sibling::input";

}
