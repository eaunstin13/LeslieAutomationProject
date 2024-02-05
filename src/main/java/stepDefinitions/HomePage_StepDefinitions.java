package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import wdMethods.ProjMethods;

public class HomePage_StepDefinitions extends ProjMethods {

	private HomePage homePage;

	public HomePage_StepDefinitions() {
		homePage = new HomePage();
	}

	@Given("I landing to the home page")
	public void iLandingToTheHomePage() {
		try {
			homePage.verifyHomePage();
		} catch (Exception e) {
			reportStep("Failed in landing to the home page " + e, "FAIL", true);
		}

	}

	@When("I enter the productname and verify appropriate search result is displayed")
	public void iEnterTheProductnameAndVerifyAppropriateSearchResultIsDisplayed() {
		try {
			homePage.searchByProductName();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed in enter the productname and verify appropriate search result is displayed " + e, "FAIL",
					true);
		}
	}

	@Then("The user creates and new account")
	public void theUserCreatesAndNewAccount() {
		try {
			homePage.createNewLoginUser();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed in creating the new user account  " + e, "FAIL", true);
		}
	}

	@Then("The user login in account with the existing credentials")
	public void theUserLoginInAccountWithTheExistingCredentials() {
		try {
			homePage.loginWithExistingCredentials();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed to login with existing credentials" + e, "FAIL", true);
		}
	}

	@Then("the user searches for product and lands to PLP page")
	public void theUserSearchesForProductAndLandsToPLPPage() {
		try {
			homePage.searchProductAndLAndTOPLP();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed to search the product and land to PLP" + e, "FAIL", true);
		}
	}

	@Then("the user selects the product from PLP and lands to PDP page")
	public void theUserSelectsTheProductFromPLPAndLandsToPDPPage() {
		try {
			homePage.PLPtoPDP();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed to land to PDP" + e, "FAIL", true);
		}
	}

	@Then("the user is able to increase and decreease the product qty and adds prduct to cart")
	public void theUserIsAbleToIncreaseAndDecreeaseTheProductQtyAndAddsPrductToCart() {
		try {
			homePage.changeQtyinPDP();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user has increased and decreased the qty" + e, "FAIL", true);
		}
	}

	@Then("the user verifies the mini cart having the product details")
	public void theUserVerifiesTheMiniCartHavingTheProductDetails() {
		try {
			homePage.verifyproductinMiniCart();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user has verified the product has been added in mini cart" + e, "FAIL", true);
		}
	}

	@Then("the user searched and add product to cart")
	public void theUserSearchedAndAddProductToCart() {
		try {
			homePage.searchesProductAddtoCart();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user failed to search the product and add to cart" + e, "FAIL", true);
		}
	}

	@Then("the user verifies the order details and moves to checkout page and verifies the producr details")
	public void theUserVerifiesTheOrderDetailsAndMovesToCheckoutPageAndVerifiesTheProducrDetails() {
		try {
			homePage.checkoutAndAddsAddress();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user failed to checkout page and to add address " + e, "FAIL", true);
		}
	}

	@When("the user enters the credit card details")
	public void theUserEntersTheCreditCardDetails() {
		try {
			homePage.addCreditCardDetails();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user failed to add the credit card details" + e, "FAIL", true);
		}
	}

	@Then("the user is moved to review order page and places order")
	public void theUserIsMovedToReviewOrderPageAndPlacesOrder() {
		try {
			homePage.userPlacesOrder();
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("user failed to place order" + e, "FAIL", true);
		}
	}

}
