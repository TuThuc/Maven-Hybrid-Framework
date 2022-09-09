package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class Post_01_Creat_Update_Delete_Search extends BaseTest {

	@Parameters({ "browser", "adminUrl", "endUserUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-condition - Step 01: Open browser and admin Site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowerDriver(browserName, this.adminUrl);

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-condition - Step 02: Enter to Username textbox with value " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);

		log.info("Pre-condition - Step 03: Enter to Password textbox with value " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Creat_New_Post() {
		log.info("Creat_Post - Step 01: Click to 'Post' menu link ");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Creat_Post - Step 02: Get 'Search Post' page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);

		log.info("Creat_Post - Step 03: Click to 'Add New' button ");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();

		log.info("Creat_Post - Step 04: Enter to post title ");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Creat_Post - Step 05: Enter to post body ");
		adminPostAddNewPage.enterToAddNewBody(postBody);

		log.info("Creat_Post - Step 06: Click to 'Pre - Publish' button ");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Creat_Post - Step 07: Click to 'Publish' button ");
		adminPostAddNewPage.clickToPrePublishButton();

		log.info("Creat_Post - Step 08: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}

	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);

		log.info("Search_Post - Step 02: Enter to 'Search textbox'");
		adminPostSearchPage.enterToSearchTexbox(postTitle);

		log.info("Search_Post - Step 03: Click to 'Search posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Search_Post - Step 04: Verify search table contains" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));

		log.info("Search_Post - Step 05: Verify search table contains" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Search_Post - Step 06: Open End User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Search_Post - Step 07: Verify Post infor displayed on at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithCurrentDay(postTitle, currentDay));

		log.info("Search_Post - Step 08: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);

		log.info("Search_Post - Step 09: Verify Post infor displayed on at Post Detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithCurrentDay(postTitle, currentDay));
	}

	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);

		log.info("Edit_Post - Step 02: Click to 'Post' menu link ");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Edit_Post - Step 03: Enter to 'Search textbox'");
		adminPostSearchPage.enterToSearchTexbox(postTitle);

		log.info("Edit_Post - Step 04: Click to 'Search posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Edit_Post - Step 05: Click to Post title link and navigate to Edit Post page ");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);

		log.info("Edit_Post - Step 06: Enter to post title ");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);

		log.info("Edit_Post - Step 07: Enter to post body ");
		adminPostAddNewPage.enterToEditPostBody(editPostBody);

		log.info("Edit_Post - Step 08: Click to 'Update' button ");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Edit_Post - Step 09: Verify 'Post updated' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));

		log.info("Edit_Postt - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);

		log.info("Edit_Post - Step 11: Enter to 'Search textbox'");
		adminPostSearchPage.enterToSearchTexbox(editPostTitle);

		log.info("Edit_Post - Step 12: Click to 'Search posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Edit_Post - Step 13: Verify search table contains" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));

		log.info("Edit_Post - Step 14: Verify search table contains" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Edit_Post - Step 15: Open End User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Edit_Post - Step 16: Verify Post infor displayed on at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInforUpdatedDisplayedWithCurrentDay(editPostTitle, currentDay));

		log.info("Edit_Post - Step 08: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);

		log.info("Edit_Post - Step 09: Verify Post infor displayed on at Post Detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforUpdatedDisplayedWithCurrentDay(editPostTitle, currentDay));
	}

	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);

		log.info("Delete_Post - Step 02: Click to 'Post' menu link ");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Delete_Post - Step 03: Enter to 'Search textbox'");
		adminPostSearchPage.enterToSearchTexbox(editPostTitle);

		log.info("Delete_Post - Step 04: Click to 'Search posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Delete_Post - Step 05: Select Post detail checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectTextItemtInActionDropdown("Move to Trash");

		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();

		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));

		log.info("Delete_Post - Step 09: Enter to 'Search textbox'");
		adminPostSearchPage.enterToSearchTexbox(editPostTitle);

		log.info("Delete_Post - Step 10: Click to 'Search posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));

		log.info("Delete_Post - Step 12: Open End User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Delete_Post - Step 13: Verify Post title undisplayed on at Home Page");
		verifyTrue(userHomePage.isNoPostInforUnDisplayedWithPostTitle(editPostTitle));

		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 15: Click to Search button");
		userSearchPostPage = userHomePage.clickToSearchButton();

		log.info("Delete_Post - Step 16: Verify 'Nothing Found.' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private String adminPassword = "automationfc";
	private String adminUsername = "automationfc";
	int randomNumber = generateFakeNumber();
	String searchPostUrl;
	String postTitle = "Live codeing Title " + randomNumber;
	String postBody = "Live codeing Body " + randomNumber;
	String editPostTitle = "Edit Title " + randomNumber;
	String editPostBody = "Edit Body " + randomNumber;
	String authorName = "automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentDay();
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private UserSearchPostPO userSearchPostPage;

}
