package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.GlobalVariables;
import wdMethods.ProjMethods;

public class Hooks extends ProjMethods{
	
	@Before
	public void launchBrowser(Scenario sc) throws InterruptedException {
		
	    String scenarioName = null;
		dataSheetName = "TC002_MessagingTest"; 
		if(sc.getId().contains("USMobile")) {
			GlobalVariables.addGlobalVariable("environment", "US");
			GlobalVariables.addGlobalVariable("mobileWeb", "true");
			scenarioName = "US Mobile - " + sc.getName();
		}else if(sc.getId().contains("USDesktop")) {
			GlobalVariables.addGlobalVariable("environment", "US");
			GlobalVariables.addGlobalVariable("mobileWeb", "false");
			scenarioName = "US Desktop - " + sc.getName();
		}else if(sc.getId().contains("CanadaDesktop")) {
			GlobalVariables.addGlobalVariable("environment", "CANADA");
			GlobalVariables.addGlobalVariable("mobileWeb", "false");
			scenarioName = "Canada Desktop - " + sc.getName();
		}else if(sc.getId().contains("CanadaMobile")) {
			GlobalVariables.addGlobalVariable("environment", "CANADA");
			GlobalVariables.addGlobalVariable("mobileWeb", "true");
			scenarioName = "Canada Mobile - " + sc.getName();
		}else {
			reportStep("Invalid feature file name", "FAIL");
		}
		
		startApp(browser,scenarioName ,bRemote);
		startTestModule(scenarioName, sc.getId());
	}
	
	@After
	public void executeAfterScenario() {
		closeAllBrowsers();
	}




}
