package resources;

public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	// constructor
	APIResources(String resource){
		this.resource = resource;
	}
	
	//return resource object
	public String getResource() {
		return resource;
	}
	
	
}
