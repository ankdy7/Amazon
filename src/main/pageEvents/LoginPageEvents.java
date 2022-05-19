package pageEvents;

import org.testng.Assert;

import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {

	public void verifyLoginPageOpenOrNot()
	{
		ElementFetch elementFetch=new ElementFetch();
		Assert.assertTrue(elementFetch.getListWebElement("XPATH", LoginPageElements.signInButton).size()>0, "Login Page did not open");
		
	}
	
	public void enterEmailId()
	{
		ElementFetch elementFetch=new ElementFetch();
		elementFetch.getWebElement("XAPTH", LoginPageElements.usernameField).sendKeys("ankur.dey.008@gmail.com");
		
	}
}
