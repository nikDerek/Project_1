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
import org.openqa.selenium.support.ui.WebDriverWait;

import poms.LoginPage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Submit {

	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://localhost:8080/loginpage.html");
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		usernameBox.sendKeys("user1");
		passwordBox.sendKeys("pass1");
		loginButton.click();
	}
	
	@Test
	public void submit() {
		
		WebElement descriptionBox = driver.findElement(By.id("description"));
		WebElement amountBox = driver.findElement(By.id("amount"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		descriptionBox.sendKeys("test");
		amountBox.sendKeys("0.01");
		
		
		loginButton.click();
		
		Assert.assertNotNull(driver.findElement(By.id("reimbursement_container")));
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:8080/home.html", driver.getCurrentUrl());
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