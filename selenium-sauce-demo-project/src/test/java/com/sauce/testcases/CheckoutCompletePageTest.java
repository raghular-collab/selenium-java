package com.sauce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sauce.base.TestBase;
import com.sauce.pages.CheckoutCompletePage;
import com.sauce.pages.CheckoutOverviewPage;
import com.sauce.pages.CheckoutPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductsPage;
import com.sauce.pages.YourCartPage;
import com.sauce.util.TestUtil;

public class CheckoutCompletePageTest extends TestBase{
	
	LoginPage loginPage;
	ProductsPage productsPage;
	YourCartPage yourCartPage;
	CheckoutPage checkoutPage;
	TestUtil testUtil;
	CheckoutOverviewPage checkoutOverviewPage;
	CheckoutCompletePage checkoutCompletePage;
	
	public CheckoutCompletePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();	
		productsPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		productsPage.clickBackPack();
		yourCartPage = productsPage.validateCart();
		checkoutPage = yourCartPage.verifyCheckoutBtn();
		checkoutPage.passInfo(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("zipcode"));
		checkoutOverviewPage = checkoutPage.validationContinue();
		checkoutCompletePage = checkoutOverviewPage.clickFinish();
	}
	
	@Test(priority = 1)
	public void verifyMessage() {
		String thanks = checkoutCompletePage.verifyThanksMsg();
		Assert.assertEquals(thanks, "Thank you for your order!");
	}
	
	@Test(priority =2)
	public void verifyBackHomeTest() {
		checkoutCompletePage.backToHome();
		System.out.println("Back to product");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
