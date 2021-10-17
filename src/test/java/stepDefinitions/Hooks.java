package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

//	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinition sd = new StepDefinition();
		if(StepDefinition.place_id ==null) {
		sd.add_place_payload_with("simin", "hindi", "dubai");
		sd.user_calls_with_http_request("AddPlaceAPI","POST");
		sd.verify_place_id_create_maps_to_using("simin", "GetPlaceAPI");
		}
	}
}
