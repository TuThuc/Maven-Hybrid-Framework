package pageUIs.wordpress;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/following-sibling::div//time[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE_UPDATED = "xpath=//article//h1[text()='%s']/following-sibling::div//time[@class='entry-date published' and text()='%s']";

	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/parent::header/following-sibling::div/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/following-sibling::div//span[@class='author vcard']/a[text()='%s']";

}
