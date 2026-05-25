package com.sauce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sauce.base.TestBase;
import com.sauce.pages.CheckoutOverviewPage;
import com.sauce.pages.CheckoutPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductsPage;
import com.sauce.pages.YourCartPage;
import com.sauce.util.TestUtil;

public class CheckoutPageTest extends TestBase{
	LoginPage loginPage;
	ProductsPage productsPage;
	YourCartPage yourCartPage;
	CheckoutPage checkoutPage;
	TestUtil testUtil;
	CheckoutOverviewPage checkoutOverviewPage;
	
	String sheetname = "Sheet1";
	
	public CheckoutPageTest() {
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
	}
	@Test(priority =1)
	public void verifyTitleTest() {
		String title = checkoutPage.verifyTitle();
		Assert.assertEquals(title,"Checkout: Your Information");
		}
	@Test(priority = 2)
	public void validatingCheckoutPageTest() {
		String checkoutInfo = checkoutPage.passInfo(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("zipcode"));
		System.out.println(checkoutInfo);
	}
	@Test(priority =3)
	public void verifyCancelTest() {
		checkoutPage.clickCancel();
		String cartTitle = yourCartPage.verifyTitle();
		System.out.println(cartTitle);
		if(cartTitle.equals("Your Cart")) {
		    System.out.println("PASS : Title verified");
		} else {
		    System.out.println("FAIL : Title not matched");
		}
	}
	@Test(priority = 4)
	public void continueBtnTest() {
		String checkoutInfo = checkoutPage.passInfo(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("zipcode"));
		System.out.println(checkoutInfo);
		checkoutPage.validationContinue();
	}
	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = testUtil.getTestData(sheetname);
		return data;
	}
	@Test(priority =5,dataProvider="getTestData")
	public void passDataTest(String firstname, String lastname, String postalcode) {
		System.out.println("checkoutPage.passData(firstname, lastname, postalcode");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
