package poms;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

	
	
	@FindBy(id = "user")
	private WebElement usernameBox;
	@FindBy(id = "pass")
	private WebElement passwordBox;
	@FindBy(id = "submit")
	private WebElement loginButton;
	
	
	
	public LoginPage(WebDriver driver) {
		
		
		PageFactory.initElements(driver, this);
		
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(usernameBox));
	}
	
	
	
	public void login(String username, String password) {
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password);
		loginButton.click();
	}
	
	
	public void loginByHittingEnter(String username, String password) {
		
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password, Keys.ENTER);
	}
}

