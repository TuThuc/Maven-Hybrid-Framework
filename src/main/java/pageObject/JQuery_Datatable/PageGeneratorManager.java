package pageObject.JQuery_Datatable;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
public static HomePageObject getHomePage(WebDriver driver) {
return new HomePageObject(driver);
}
}
