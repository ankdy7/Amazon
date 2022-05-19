package pageObjects;

public interface LoginPageElements {

	String signInText = "//h1[@class='a-spacing-small']";
	String usernameField = "//input[@id='ap_email']";
	String continueButton = "//input[@id='continue']";
	String passwordField = "//input[@id='ap_password']";
	String signInButton = "//input[@id='signInSubmit']";
	String keepMeSignedInCheckBox = "//span[@class='a-label a-checkbox-label']";
}
