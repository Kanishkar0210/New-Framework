package org.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Recruitment extends BasePage {

	public Recruitment(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Recruitment']")
	private WebElement recruitmentbt;
	@FindBy(xpath="//button[normalize-space()='Add']")
	private WebElement addbt;
	@FindBy(xpath="//input[@placeholder='First Name']")
	private WebElement firstName;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	private WebElement lastName;
	@FindBy(xpath="//div[3]//div[1]//div[1]//div[1]//div[2]//input[1]")
	private WebElement email;
	@FindBy(xpath="//button[normalize-space()='Save']")
	private WebElement savebt;
	@FindBy(xpath="//button[normalize-space()='Save']")
	private WebElement savebt1;
	
	
	public void getRecruitmentbt() {
		recruitmentbt.click();
	}
	public void getAddbt() {
		addbt.click();
	}
	public void getFirstName(String username) throws InterruptedException {
		 firstName.sendKeys(username);
	}
	public void getLastName(String lastname) {
		lastName.sendKeys(lastname);;
	
	}
	public void getEmail(String emailid) {
		 email.sendKeys(emailid);
	}
	public void getSavebt() {
		 savebt.click();
	}
	public WebElement getSavebt1() {
		 return savebt1;
	}
	
	
}
