package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination SD = new StepDefination();
		if(StepDefination.place_id == null) {
		SD.add_Place_Payload_with("Abh", "eng", "india");
		SD.user_calls_something_with_post_http_request("AddPlaceApi", "POST");
		SD.verify_plac_e_id_created_maps_to_using("Abh", "getPlaceAPI");
		}
	}

}
