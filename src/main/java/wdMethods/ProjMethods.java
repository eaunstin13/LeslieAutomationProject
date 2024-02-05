package wdMethods;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.ReadExcel;

public class ProjMethods extends SeMethods {

	public static String dataSheetName;

	@BeforeSuite
	public void beforeSuite() {
		startResult();
	}

	@BeforeTest
	public void beforeTest() {

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@AfterTest
	public void afterTest() {
	}

	@AfterMethod
	public void afterMethod() {

	}

	@DataProvider(name = "fetchData")
	public Object[][] getData() {
		System.out.println("I am inside dataprovider");
		return ReadExcel.getSheet(dataSheetName);
	}

	public void waitForLoaderToBeGone() {
		waitForServerToPerformAction(10);
		List<WebElement> loader = getDriver().findElements(By.xpath("//div[@color='grey']"));
		try {
			if(loader.size()>0){
			new WebDriverWait(getDriver(), 15)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@color='grey']")));
			}
		} catch (Exception e) {
		
		}

	}

	/**
	 * This method used to generate random number in the given range
	 * 
	 * @author Eddibauer QA
	 * @param low  - lowest number
	 * @param high - highest number
	 * @return
	 * 
	 */
	public int getRandomNumberGivenRange(int low, int high) {
		Random r = new Random();
		int result = r.nextInt(high - low) + low;
		return result;
	}

	/**
	 * This method used to generate random string in small letter with given length
	 * 
	 * @author Eddibauer QA
	 * @param stringlenth - specify required string length.
	 * @return
	 */

	public String generateRandomStringSmallLetter(int stringlenth) {
		String stringValue = "test";
		for (int i = 0; i < stringlenth - 4; i++) {
			stringValue += (char) getRandomNumberGivenRange(97, 122);
		}
		return stringValue;
	}

	/**
	 * This method used to generate random string with capital letter with given
	 * length
	 * 
	 * @author Eddibauer QA
	 * @param stringlenth - specify required string length.
	 * @return
	 */

	public String generateRandomStringCapitalLetter(int stringlenth) {
		String stringValue = "TEST";
		for (int i = 0; i < stringlenth - 4; i++) {
			stringValue += (char) getRandomNumberGivenRange(65, 90);
		}
		return stringValue;
	}

	/**
	 * This method used to generate random 10 digit cell phone number with 10.
	 * 
	 * @author Eddibauer QA
	 * @return
	 */

	public String generateRandomCellPhoneNum() {
		String stringValue = "9";
		for (int i = 0; i < 11; i++) {
			if (i == 2 || i == 6) {
				stringValue += ".";
			} else {
				stringValue += (char) getRandomNumberGivenRange(48, 57);
			}
		}
		return stringValue;
	}

	/**
	 * This method used to generate random email.
	 * 
	 * @author Eddibauer QA
	 * @return
	 */

	public String generateRandomEmail() {
		String stringValue = "test";
		for (int i = 0; i < 5; i++) {
			stringValue += (char) getRandomNumberGivenRange(97, 122);
		}
		return stringValue + "@mailinator.com";
	}

	/**
	 * This method used to get the month name wile passing the month number.
	 * 
	 * @author Eddibauer QA
	 * @param monthNumber - specify the month number.
	 * @return
	 */

	public String getMonthString(String monthNumber) {
		int result = Integer.parseInt(monthNumber);
		String monthString = new DateFormatSymbols().getMonths()[result - 1];
		return monthString;
	}
}
