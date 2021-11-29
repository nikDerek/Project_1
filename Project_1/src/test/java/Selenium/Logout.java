package Selenium;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import poms.LoginPage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Logout {

	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//
		driver.get("http://localhost:8080/home.html");
	}
	
	@Test
	public void logoutemployee() {
		
		WebElement logoutButton = driver.findElement(By.id("logout"));
		
		logoutButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/loginpage.html", driver.getCurrentUrl());
	}
	
	@Test
	public void logoutmanager() {
		
		driver.get("http://localhost:8080/managerhome.html");
		
		WebElement logoutButton = driver.findElement(By.id("logout"));
		
		logoutButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/loginpage.html", driver.getCurrentUrl());
	}
	
	@Test
	public void logoutstatistics() {
		
		driver.get("http://localhost:8080/statistics.html");
		
		WebElement logoutButton = driver.findElement(By.id("logout"));
		
		logoutButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/loginpage.html", driver.getCurrentUrl());
	}
}

