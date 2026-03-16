package org.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
     @FindBy(xpath = "//input[@placeholder='Username']")
	 private WebElement userName;
     @FindBy(xpath ="//input[@placeholder='Password']")
	 private WebElement password;
     @FindBy(xpath="//button[normalize-space()='Login']")
	 private WebElement loginbt;
     @FindBy(xpath="//h6[normalize-space()='Dashboard']")
     private WebElement dashboardtxt;
	
     
     
     public void getUserName(String userName1) {
		userName.sendKeys(userName1);
	}
	public void getPassword(String password1) {
		password.sendKeys(password1);
	}
	public void getLoginbt() {
		loginbt.click();
	}
	public WebElement getDashboardtxt() {
		return dashboardtxt;
	}
     
    
}
