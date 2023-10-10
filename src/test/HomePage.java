package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage  {
private WebDriver driver;
private By toggleButton = By.cssSelector("button.d-lg-none.navbar-toggler.auction-toggler");
private By logoutButton = By.xpath("/html/body/div[1]/div/div/div/div[3]");

public HomePage(WebDriver driver) {
	this.driver = driver;
}

public void setLogoutButton() {

	WebElement clickLogout = driver.findElement(logoutButton);

	try {
        Thread.sleep(5000); // Chờ 2 giây
    	clickLogout.click();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


public void clickToggleButton() {
WebElement clickToggleButton;

clickToggleButton = driver.findElement(toggleButton);

    clickToggleButton.click();

}

}