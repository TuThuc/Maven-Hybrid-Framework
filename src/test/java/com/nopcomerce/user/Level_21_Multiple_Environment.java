package com.nopcomerce.user;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import environmentConfig.Environment;

public class Level_21_Multiple_Environment extends BaseTest {
	Environment environment;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		// Get environment value from Maven command line
		String environmentName = System.getProperty("envMaven");
		System.out.println("Ten moi trương= " + environmentName);
		ConfigFactory.setProperty("envOwner", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowerDriver(browserName, environment.appUrl());

		System.out.println(environment.appUrl());
		System.out.println(environment.appUsername());
		System.out.println(environment.appPassword());

	}

	@Test
	public void User_01_Register() {
	}

	@Test
	public void User_02_Login() {

	}

	@Test
	public void User_03_My_Account() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;

}
