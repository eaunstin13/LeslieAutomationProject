package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.ReadExcel;
import wdMethods.ProjMethods;

import java.awt.*;
import java.util.Arrays;
import java.util.Properties;

public class HomePage extends ProjMethods {


	protected Properties prop;
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

	@FindBy(how = How.XPATH, using = "//*[@id='phoneNumber']")
	public WebElement phone;

	@FindBy(how = How.XPATH, using = "//*[@class='minicart-link d-flex position-relative align-items-center']")
	public WebElement cart;
	
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary btn-block submit-shipping']")
	public WebElement nextPayment;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control cardNumber']")
	public WebElement creditCardNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@class='form-control expirationMonth custom-select is-invalid']")
	public WebElement expiryMonth;

	@FindBy(how = How.XPATH, using = "//*[@id='expirationDate']")
	public WebElement expiration;

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

		prop = new Properties();
		String myvariable = prop.getProperty("myvariable");

		//String MyTestVariable="null";
		System.out.println(myvariable);
		Thread.sleep(10);
		//click(closeIcon);
		getDriver().navigate().refresh();

		verifyTitleContains("Pool Supplies, Service & Repair | Leslie's Pool Supplies");
		waitForServerToPerformAction(10);
		reportStep("Successfully Landed to Home Page", "INFO");

		click(getDriver().findElement(By.xpath("//*[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")));

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
		ReadExcel user = new ReadExcel();
		String[][] data = user.getSheet("datasheet.xlsx","user");
		String emaildata = null;
		String passworddata = null;

		for (int i = 0; i < data.length; i++) {
			if(data[i][6].equalsIgnoreCase("Y") || data[i][6].equalsIgnoreCase("Yes")){
				for (int j = 0; j < data[i].length; j++) {
					emaildata = data[i][3];
					passworddata = data[i][4];
				}
			}
		}

		click(joinOrSignIn);
		type(logInEmailID, emaildata);
		type(logInPassword, passworddata);
		waitForServerToPerformAction(3);
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
		//click(getDriver().findElement(By.xpath("//*[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")));
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

		//click(getDriver().findElement(By.xpath("//*[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")));
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

		type(phone,"9876543219");
		click(nextPayment);
	}
	
	public void addCreditCardDetails() {
		type(creditCardNumber, "4111111111111111");
		type(expiration,"02/2025");
		//selectDropDownUsingIndex(expiryMonth, 3);
		//selectDropDownUsingIndex(expiryYear, 3);
		type(SecurityCode, "123");
		waitForServerToPerformAction(3);
		//click(placeOrder);
		

	}
	
	public void userPlacesOrder() {
		//waitForServerToPerformAction(3);
		click(placeOrder);
		waitForServerToPerformAction(5);
		if (thankyouOrder.isDisplayed()) {
			reportStep("the user has sucesfully placed an order ", "PASS", true);
		} else {
			reportStep("the user failed to place order", "FAIL", true);
		}
		

	}

}
