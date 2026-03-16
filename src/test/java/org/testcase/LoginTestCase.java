package org.testcase;

import org.pageObject.LoginPage;
import org.pageObject.Recruitment;
import org.testbase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utilities.ConfigReader;

public class LoginTestCase extends BaseClass {

	private ConfigReader config;
	
	@BeforeClass
    public void loadConfig() {
        // Load the property file for this test class
		 initDriver("chrome");
        config = new ConfigReader();
    }
	
	@Test(priority = 1)
	public  void Logintest() {
	
		LoginPage login = new LoginPage(driver);
		launchUrl(config.getProperty("url"));
		login.getUserName(config.getProperty("username"));
		login.getPassword(config.getProperty("password"));
		login.getLoginbt();
		
		String dashboard = getText(login.getDashboardtxt());
		Assert.assertEquals(dashboard, config.getProperty("successlogin"));
	}
	
	@Test(priority = 2)
	public void recruitmentAdd() throws InterruptedException {
		
		Recruitment Recruit = new Recruitment(driver);
		Recruit.getRecruitmentbt();
		Recruit.getAddbt();
		Recruit.getFirstName(config.getProperty("firstName"));
		Recruit.getLastName(config.getProperty("lastName"));
		Recruit.getEmail(config.getProperty("email"));
		
		
		scrollIntoView(Recruit.getSavebt1());
		
		
		
		
		
		

	}
	
	
	
}
