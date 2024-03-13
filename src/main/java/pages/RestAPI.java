package pages;

import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import wdMethods.ProjMethods;

import java.net.URI;

public class RestAPI extends ProjMethods {
    private Scenario scenario;
    private Response response;
    // private final String BASE_URL = "http://localhost:3000";
    private final String BASE_URL = "https://api.lesl.blue/ws/rest/ABHSubscription/V1/subscription/customer/00101045/subscription/SOR-1-WLP9187316";

    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();

        //response = req.when().get(new URI(url));
        response = req.given().header("x-api-key", "316a4539-02b6-4957-bc17-0333b2d983b6").when().get(new URI(url)).then().extract().response();

        System.out.println("Response Received == " + response.asPrettyString());
        reportStep(response.asPrettyString() + "", "Pass");
    }

    public void response_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseMessage, responseCode + "");
    }

    public void responseIsArrayWith(String arg0) {
        try{
            response.then().statusCode(200);
            response = response.then().extract().response();
            //scenario.log("Response Received == " + response.asPrettyString());
            // System.out.println("Response Received == " + response.asPrettyString());
            JSONObject resJson=new JSONObject(new JSONTokener(response.asString()));
            //JSONArray resJson = new JSONArray(response.asString());
            Assert.assertEquals(resJson.length() + "", arg0);
            for(int i=0;i<resJson.length();i++){
                System.out.println(resJson.getString("orderNumber"));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
