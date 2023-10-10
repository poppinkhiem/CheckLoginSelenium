package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckLoginPage {

 CheckLoginTestNG checklogintestng;

 private By usernameInput = By.xpath("/html/body/div[1]/div/form/div/div/div[1]/input");
 private By passwordInput = By.xpath("/html/body/div[1]/div/form/div/div/div[2]/input");
 private By loginButton = By.xpath("/html/body/div[1]/div/form/div/div/div[3]/div/input");
 private By errorMessage = By.xpath("/html/body/div[1]/div/form/div/div/div[1]/span");

private WebDriver driver;

 public CheckLoginPage(WebDriver driver) {
	    this.driver = driver;
	}
 
 public void setUsernameInput(String username) {
	 System.out.println("checklog");
		WebElement usernameField = driver.findElement(usernameInput);
		usernameField.sendKeys(username);
	}


	public void setPasswordInput(String password) {
	WebElement passwordField = driver.findElement(passwordInput);
	passwordField.sendKeys(password);

}






	public boolean isErrorMessageDisplayed()  {
 return driver.findElement(errorMessage).isDisplayed();
	}


    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }


	public void ClickLoginPage() {
		// TODO Auto-generated method stub
		WebElement clickLogin = driver.findElement(loginButton);
		clickLogin.click();
	}
}