@FunctionalTest @Smoke 
Feature: Sanity Test on any site

	
@test1
Scenario: Verify the funcnality of the Hompage and Login  
	Given I landing to the home page
	Then I enter the productname and verify appropriate search result is displayed
	Then The user creates and new account 
	
@test2
Scenario: Verify the user is able to Login with Existing credentials   
	Given I landing to the home page
	Then The user login in account with the existing credentials 
	
@test
Scenario: Verify the funcnality of PLP and PDP and the Cart page   
	Given I landing to the home page
	Then the user searches for product and lands to PLP page 	
	Then the user selects the product from PLP and lands to PDP page 
	Then the user is able to increase and decreease the product qty and adds prduct to cart
	Then the user verifies the mini cart having the product details 
	
@test
Scenario: Verify the funcnality of checkout page and user bale to place order  
	Given I landing to the home page
	Then The user login in account with the existing credentials 	
	And the user searched and add product to cart 
	Then the user verifies the order details and moves to checkout page and verifies the producr details
	When the user enters the credit card details 
	Then the user is moved to review order page and places order

	@apitest
	Scenario Outline: Dummy Rest Api GET Students
		Given Get Call to "<url>"
		Then Response Code "<responseMessage>" is validated

		Examples:
			| url      | responseMessage |
			| /posts | 200 |

	@apitest1
	Scenario Outline:  Verify Code
		Given Get Call to "<url>"
		Then Response  is array total "<total>"

		Examples:
			| url      | total |
			| /posts | 12    |