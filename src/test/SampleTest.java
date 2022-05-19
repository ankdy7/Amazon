

public class SampleTest extends BaseTest {
	
	@Test
	public void methodEnterEmail()
	{
		HomePageEvents homePageEvents = new HomePageEvents();
		homePageEvents.clickOnSignInButton();
		
		LoginPageEvents loginPageEvents = new LoginPageEvents();
		loginPageEvents.verifyLoginPageOpenOrNot();
		loginPageEvents.enterEmailId();
	}

}
