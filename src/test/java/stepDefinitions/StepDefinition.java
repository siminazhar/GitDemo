package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id ;
	
	@Given("Add Place Payload with {string}{string}{string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
							
		// pass request specification object from inherited Utils class
		reqSpec = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	
	@When("User calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource,String method) {
		
		// Create object of enum class
		APIResources resourceAPI =APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		//Create object of response spec builder
		 resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		 if(method.equalsIgnoreCase("POST")) {
		
			 response =reqSpec.when().post(resourceAPI.getResource());
		 }
		 
		 else if(method.equalsIgnoreCase("GET"))
			 response =reqSpec.when().get(resourceAPI.getResource());
	
	}
	
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		System.out.println("response is"+response.asString());
	  assertEquals(response.getStatusCode(),200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	  assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	
	@Then("verify place_id create maps to {string} using {string}")
	public void verify_place_id_create_maps_to_using(String expectedName, String resource) throws IOException {
		
		//extract place ID from response
		place_id = getJsonPath(response,"place_id");
		//hit request
		reqSpec= given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		
		//extract name from response
		String actualName = getJsonPath(response,"name");
		//validation for name
		assertEquals(actualName,expectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		// pass request specification object from inherited Utils class
		reqSpec = given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));	  

	}

}
