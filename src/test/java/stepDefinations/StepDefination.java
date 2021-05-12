package stepDefinations;

import static io.restassured.RestAssured.given;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resexpect;
	Response store;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException{
		
		resexpect = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(requestSpecification())
				.body(data.addPlaceLoad(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_something_with_post_http_request(String strArg1,String string2) {
		APIResources apipath=APIResources.valueOf(strArg1);
		System.out.println(apipath.getResource());
		//store = res.when().post(apipath.getResource()).then().spec(resexpect).extract().response();
		if(string2.equalsIgnoreCase("POST")) {
			store=res.when().post(apipath.getResource());
		}
		else if (string2.equalsIgnoreCase("GET")) {
			store=res.when().get(apipath.getResource());
		}
	}

	@Then("^the API callis success with status code 200$")
	public void the_api_callis_success_with_status_code_200() {
		// assertEquals(store.statusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String strArg1, String strArg2) {
		
		assertEquals(getJsonPath(store,strArg1), strArg2);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_plac_e_id_created_maps_to_using(String string, String string2) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 place_id=getJsonPath(store,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id );
		user_calls_something_with_post_http_request(string2,"GET");
		String name=getJsonPath(store,"name");
		assertEquals(name, string);
	}
	
	@Given("DeletePlace Payload")
	public void deletePlace_Payload() throws IOException {
		res=given().spec(requestSpecification()).body(deletePlacePayload(place_id));
		
	}
}