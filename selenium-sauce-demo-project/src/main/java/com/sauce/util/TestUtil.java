package com.sauce.util;

	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

	public class TestUtil {

	    // Time constants
	    public static long IMPLICIT_WAIT = 30;
	    public static long PAGE_LOAD_TIMEOUT = 40;
	    public static long EXPLICIT_WAIT = 10;
	    
	    public static String TESTDATA_SHEET_PATH  = "/Users/raghular/eclipse-workspace/selenium-sauce-demo/src/main/java/com/sauce/testdata/SauceTestData.xlsx";

	    
	    static Workbook book;
		static Sheet sheet;
		static JavascriptExecutor js;
		
	    // Method to apply waits
	    public static void applyImplicitWait(WebDriver driver) {
	        driver.manage().timeouts()
	              .implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
	    }

	    public static void applyPageLoadTimeout(WebDriver driver) {
	        driver.manage().timeouts()
	              .pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
	    }
	    
	    public static void waitForElements(WebDriver driver,List<WebElement> elements) {

	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
	    	wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	    }
	    
	    public static void waitForElement(WebDriver driver, WebElement elements) {
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
	    	wait1.until(ExpectedConditions.visibilityOfAllElements(elements));
	    }
	    
	    public static Object[][] getTestData(String sheetName){
	    	FileInputStream file = null;
	    	try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}


	}


