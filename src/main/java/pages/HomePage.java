package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//import com.sun.tools.javac.util.Context.Key;

import io.appium.java_client.android.nativekey.KeyEvent;
import utils.GlobalVariables;
//import wdMethods.EddieBauerMethods;
import utils.ReadExcel;
import wdMethods.ProjMethods;

public class HomePage extends ProjMethods {
	

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	// new repo
	@FindBy(how = How.XPATH, using = "//*[contains(@aria-label,'Enter Keyword or Item Number')]")
	public WebElement searchIconLesslies;
	
	// Home Page Elements
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'styles__StyledSearchButton')]")
	public WebElement searchIcon;
	
	@FindBy(how = How.XPATH, using = "//*[@class='header-flyout-login btn']")
	public WebElement joinOrSignIn;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn-cta signup-button']")
	public WebElement joinForFree;
	
	@FindBy(how = How.XPATH, using = "//*[@name='dwfrm_profile_customer_firstname']")
	public WebElement firstName;
	
	@FindBy(how = How.XPATH, using = "//*[@name='dwfrm_profile_customer_lastname']")
	public WebElement lastName;
	
	@FindBy(how = How.XPATH, using = "//*[@name='dwfrm_profile_customer_email']")
	public WebElement Email;
	
	@FindBy(how = How.XPATH, using = "//*[@name='dwfrm_profile_login_password']")
	public WebElement password;
	
	@FindBy(how = How.XPATH, using = "//*[@name='dwfrm_profile_login_passwordconfirm']")
	public WebElement confirmPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@name='save']")
	public WebElement createAccount;
	
	@FindBy(how = How.XPATH, using = "//*[@id='login-form-email']")
	public WebElement logInEmailID;
	
	@FindBy(how = How.XPATH, using = "//*[@id='login-form-password']")
	public WebElement logInPassword;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Hello!']")
	public WebElement verifyUSerLoggedIN;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Sign In')])[2]")
	public WebElement SignIn;
	
	@FindBy(how = How.XPATH, using = "//*[@data-pid='12074']")
	public WebElement firstPLPProduct;
	
	@FindBy(how = How.XPATH, using = "//*[@class='breadcrumb-item']")
	public WebElement hamburgerMenu;
	
	@FindBy(how = How.XPATH, using = "//*[@class='quantity-select custom-select form-control']")
	public WebElement qtyDropdown;
	
	@FindBy(how = How.XPATH, using = "//*[@class='add-to-cart-text p-4 text-center']")
	public WebElement addToCart;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary btn-block checkout-btn text-white']")
	public WebElement checkout;
	
	@FindBy(how = How.XPATH, using = "//*[@id='shippingAddressOnedefault']")
	public WebElement Address1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='shippingStatedefault']")
	public WebElement state;
	
	@FindBy(how = How.XPATH, using = "//*[@id='shippingAddressCitydefault']")
	public WebElement city;
	
	@FindBy(how = How.XPATH, using = "//*[@id='shippingZipCodedefault']")
	public WebElement zipcode;
	
	@FindBy(how = How.XPATH, using = "//*[@class='minicart-link d-flex position-relative align-items-center']")
	public WebElement cart;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary btn-block submit-shipping']")
	public WebElement nextPayment;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control cardNumber']")
	public WebElement creditCardNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control expirationMonth custom-select is-invalid']")
	public WebElement expiryMonth;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control expirationYear custom-select is-invalid']")
	public WebElement expiryYear;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control securityCode']")
	public WebElement SecurityCode;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control saved-payment-security-code']")
	public WebElement savedSecurityCode;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary btn-block submit-payment']")
	public WebElement reviewOrder;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary btn-block place-order ']")
	public WebElement placeOrder;
	
	@FindBy(how = How.XPATH, using = "//*[@class=' order-thank-you-msg text-dosis text-primary font-weight-bold mt-5 mb-4']")
	public WebElement thankyouOrder;

	@FindBy(how = How.XPATH, using = "//*[@id='button3']")
	public WebElement closeIcon;
	
	// Home Page Function
	public void clicksearch() {
		clickElementByJavaScript(searchIcon);
	}
	
	
	public void verifyHomePage() throws AWTException, InterruptedException {
		/*
		Robot rb = new Robot();

		rb.keyPress(java.awt.event.KeyEvent.VK_S);
		rb.keyRelease(java.awt.event.KeyEvent.VK_S);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_T);
		rb.keyRelease(java.awt.event.KeyEvent.VK_T);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_O);
		rb.keyRelease(java.awt.event.KeyEvent.VK_O);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_R);
		rb.keyRelease(java.awt.event.KeyEvent.VK_R);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_E);
		rb.keyRelease(java.awt.event.KeyEvent.VK_E);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_F);
		rb.keyRelease(java.awt.event.KeyEvent.VK_F);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_R);
		rb.keyRelease(java.awt.event.KeyEvent.VK_R);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_O);
		rb.keyRelease(java.awt.event.KeyEvent.VK_O);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_N);
		rb.keyRelease(java.awt.event.KeyEvent.VK_N);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_T);
		rb.keyRelease(java.awt.event.KeyEvent.VK_T);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_TAB);
		rb.keyRelease(java.awt.event.KeyEvent.VK_TAB);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_S);
		rb.keyRelease(java.awt.event.KeyEvent.VK_S);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_T);
		rb.keyRelease(java.awt.event.KeyEvent.VK_T);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_O);
		rb.keyRelease(java.awt.event.KeyEvent.VK_O);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_R);
		rb.keyRelease(java.awt.event.KeyEvent.VK_R);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_E);
		rb.keyRelease(java.awt.event.KeyEvent.VK_E);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_F);
		rb.keyRelease(java.awt.event.KeyEvent.VK_F);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_R);
		rb.keyRelease(java.awt.event.KeyEvent.VK_R);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_O);
		rb.keyRelease(java.awt.event.KeyEvent.VK_O);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_N);
		rb.keyRelease(java.awt.event.KeyEvent.VK_N);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_T);
		rb.keyRelease(java.awt.event.KeyEvent.VK_T);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_TAB);
		rb.keyRelease(java.awt.event.KeyEvent.VK_TAB);
		Thread.sleep(10);
		rb.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		rb.keyRelease(java.awt.event.KeyEvent.VK_ENTER);

		 */
		Thread.sleep(10);
		//click(closeIcon);
		getDriver().navigate().refresh();

		verifyTitleContains("Pool Supplies, Service & Repair | Leslie's Pool Supplies");
		waitForServerToPerformAction(10);
		reportStep("Successfully Landed to Home Page", "INFO");
	}
	


	
	public void searchByProductName() {
		click(searchIconLesslies);
		Actions searchProduct = new Actions(getDriver());
		searchProduct.sendKeys("Chlorine tablets", Keys.ENTER).build().perform();
		 
		
	}
	
	public void createNewLoginUser() {
		ReadExcel user = new ReadExcel();
		String[][] data = user.getSheet("datasheet.xlsx","user");
		String firstNamedata = null;
		String lastNamedata = null;
		String emaildata = null;
		String passworddata = null;

		System.out.println(Arrays.deepToString(data));

		for (int i = 0; i < data.length; i++) {
			if(data[i][5].equalsIgnoreCase("Y") || data[i][5].equalsIgnoreCase("Yes")){
				for (int j = 0; j < data[i].length; j++) {
					 firstNamedata = data[i][1];
					 lastNamedata = data[i][2];
					 emaildata = data[i][3];
					 passworddata = data[i][4];
				}
			}
		}

		click(joinOrSignIn);
		click(joinForFree);
		waitForServerToPerformAction(3);
		type(firstName, firstNamedata);
		type(lastName, lastNamedata);
		type(Email, emaildata);
		type(password, passworddata);
		type(confirmPassword, passworddata);
		click(createAccount);
		waitForServerToPerformAction(3);
		
		if (verifyUSerLoggedIN.isDisplayed()) {
			reportStep("the user has created a new account ", "PASS", true);
		} else {
			reportStep("the user failed to create a new account", "FAIL", true);
		}

	}
	
	public void loginWithExistingCredentials() {
		click(joinOrSignIn);
		type(logInEmailID, "lokeshtester@gmail.com");
		type(logInPassword, "Tester@27..");
		click(SignIn);
		waitForServerToPerformAction(3);
		if (verifyUSerLoggedIN.isDisplayed()) {
			reportStep("the user has created a new account ", "PASS", true);
		} else {
			reportStep("the user failed to create a new account", "FAIL", true);
		}

	}
	
	public void searchProductAndLAndTOPLP() {
		click(searchIconLesslies);
		Actions searchProduct = new Actions(getDriver());
		searchProduct.sendKeys("Chlorine tablets", Keys.ENTER).build().perform();
		waitForServerToPerformAction(3);
		if (firstPLPProduct.isDisplayed()) {
			reportStep("the user has sucesfully landed the PLP page ", "PASS", true);
		} else {
			reportStep("the user failed to land the PLP page", "FAIL", true);
		}

	}
	
	public void PLPtoPDP() {
		click(getDriver().findElement(By.xpath("//*[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")));
		waitForServerToPerformAction(3);
		click(firstPLPProduct);
		waitForServerToPerformAction(3);
		if (firstPLPProduct.isDisplayed()) {
			reportStep("the user has sucesfully landed the PDP page ", "PASS", true);
		} else {
			reportStep("the user failed to land the PDP page", "FAIL", true);
		}

	}
	
	public void changeQtyinPDP() {
		waitForServerToPerformAction(3);
		selectDropDownUsingIndex(qtyDropdown, 3);
		waitForServerToPerformAction(5);
		selectDropDownUsingIndex(qtyDropdown, 1);
		waitForServerToPerformAction(5);
		click(addToCart);
		
		
	}
	
	public void verifyproductinMiniCart() {
		waitForServerToPerformAction(3);
		click(checkout);
		getDriver().navigate().refresh();

	}
	
	public void searchesProductAddtoCart() {
		click(searchIconLesslies);
		Actions searchProduct = new Actions(getDriver());
		searchProduct.sendKeys("Chlorine tablets", Keys.ENTER).build().perform();
		waitForServerToPerformAction(3);
		click(getDriver().findElement(By.xpath("//*[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")));
		waitForServerToPerformAction(3);
		click(firstPLPProduct);
		waitForServerToPerformAction(3);
		click(addToCart);
		waitForServerToPerformAction(3);
		click(checkout);
		waitForServerToPerformAction(3);
		

	}
	
	public void checkoutAndAddsAddress() {
		type(Address1, "7447 County Road 53");
		selectDropDownUsingIndex(state, 0);
		selectDropDownUsingIndex(state, 1);
		type(city, "Abbeville");
		type(zipcode, "36310-6565");
		waitForServerToPerformAction(3);
		click(nextPayment);

	}
	
	public void addCreditCardDetails() {
//		type(creditCardNumber, "4011-3611-0000-0012");
//		selectDropDownUsingIndex(expiryMonth, 3);
//		selectDropDownUsingIndex(expiryYear, 3);
		type(savedSecurityCode, "222");
		waitForServerToPerformAction(3);
		click(reviewOrder);
		

	}
	
	public void userPlacesOrder() {
		waitForServerToPerformAction(3);
		click(placeOrder);
		waitForServerToPerformAction(5);
		if (thankyouOrder.isDisplayed()) {
			reportStep("the user has sucesfully placed an order ", "PASS", true);
		} else {
			reportStep("the user failed to place order", "FAIL", true);
		}
		

	}
	
	
}
