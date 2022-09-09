package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;


public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Page UI 
	@FindBy(xpath= "//button[text()='Log in']")
	private WebElement loginButton;
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	@FindBy(id = "Email")
	private WebElement emailTextbox; 
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	@FindBy(xpath= "//div[contains(@class,'message-error')]")
	private WebElement unsuccessfulErrorMessage ;
	//Page Object / Action
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);

	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAdress);

	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);

	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, unsuccessfulErrorMessage);
		return getElementText(driver, unsuccessfulErrorMessage);
	}
}
