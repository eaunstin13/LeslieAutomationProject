@FunctionalTest @Smoke 
Feature: Sanity Test on anysite

	
@mobiletest
Scenario: To verify the search functionality 
	Given I landing to the home page
	Then I enter the productname and verify appropriate search result is displayed
	
		