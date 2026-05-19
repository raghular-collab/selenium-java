package com.sauce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.base.TestBase;

public class CheckoutOverviewPage extends TestBase {
	
	@FindBy(className="title")
	WebElement overviewTitle;
	
	@FindBy(xpath="//div[contains(text(),'Payment Information:')]")
	WebElement paymentInfo;
	
	@FindBy(xpath="//div[contains(text(),'Shipping Information:')]")
	WebElement shipInfo;
	
	@FindBy(xpath="//div[contains(text(),'Price Total')]")
	WebElement priceInfo;
	
	@FindBy(id="finish")
	WebElement finishBtn;
	
	@FindBy(id="cancel")
	WebElement cancelBtn;
	
	@FindBy(xpath="//div[starts-with(text(),'Sauce Labs')]")
	WebElement itemLabel;
	
	@FindBy(className="cart_quantity_label")
	WebElement qty;
	
	@FindBy(className="cart_desc_label")
	WebElement desc;
	
	
	public CheckoutOverviewPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String fetchTotalPrice() {
		return priceInfo.getText();
	}
	
	public String itemInfo() {
		return itemLabel.getText();
	}
	public ProductsPage clickCancel() {
		cancelBtn.click();
		return new ProductsPage();
	}
	public CheckoutCompletePage clickFinish() {
		finishBtn.click();
		return new CheckoutCompletePage();
	}
	
}
