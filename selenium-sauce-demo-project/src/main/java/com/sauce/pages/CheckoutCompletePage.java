package com.sauce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.base.TestBase;

public class CheckoutCompletePage extends TestBase{
	
	@FindBy(className="title")
	WebElement pageTitle;
	
	@FindBy(xpath="//h2[contains(text(),'Thank you for your order!')]")
	WebElement thanks;
	
	@FindBy(id="back-to-products")
	WebElement backHome;
	
	public CheckoutCompletePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void verifyCompleteTitle() {
		pageTitle.getText();
	}
	public String verifyThanksMsg() {
		return thanks.getText();
	}
	public LoginPage backToHome() {
		backHome.click();
		return new LoginPage();
	}
	
}
