
package stepDefinitions;


import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import pages.RestAPI;
import wdMethods.ProjMethods;
import wdMethods.WdMethods;


public class RestAPIPost extends ProjMethods implements WdMethods {
    private RestAPI restAPI;

    public RestAPIPost() {
        restAPI = new RestAPI();
    }
    private Scenario scenario;
    private Response response;
    private final String BASE_URL = "http://localhost:3000";


    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        try {
            restAPI.get_call_to_url(url);
        } catch (Exception e) {
            reportStep("Failed in landing to the home page " + e, "FAIL", true);
        }
    }

    @Then("Response Code {string} is validated")
    public void response_is_validated(String responseMessage) {
        try {
            restAPI.response_is_validated(responseMessage);
        } catch (Exception e) {
            reportStep("Failed in landing to the home page " + e, "FAIL", true);
        }
    }

    @Then("Response  is array total {string}")
    public void responseIsArrayWith(String arg0) {
        try {
            restAPI.responseIsArrayWith(arg0);
        } catch (Exception e) {
            reportStep("Failed in landing to the home page " + e, "FAIL", true);
        }

    }


}



