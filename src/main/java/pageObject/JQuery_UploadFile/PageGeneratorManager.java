package pageObject.JQuery_UploadFile;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
public static HomePageObject getHomePage(WebDriver driver) {
return new HomePageObject(driver);
}
}
