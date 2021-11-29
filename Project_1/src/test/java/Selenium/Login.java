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
public class Login {

	
	
	private static WebDriver driver;
	
	
	@BeforeClass
	public static void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://localhost:8080/loginpage.html");
	}
	
	@Test
	public void loginemployee() {
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		usernameBox.sendKeys("user1");
		passwordBox.sendKeys("pass1");
		
		loginButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/home.html", driver.getCurrentUrl());
	}
	
	@Test
	public void loginmanager() {
		
		driver.get("http://localhost:8080/loginpage.html");
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		usernameBox.sendKeys("user2");
		passwordBox.sendKeys("pass2");
		
	
		loginButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/managerhome.html", driver.getCurrentUrl());
	}
	
	@Test
	public void loginfail() {
		
		driver.get("http://localhost:7000/loginpage.html");
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		usernameBox.sendKeys("user1");
		passwordBox.sendKeys("pass2");
		
		loginButton.click();
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/loginpage.html", driver.getCurrentUrl());
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}

