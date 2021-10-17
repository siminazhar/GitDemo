package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
public AddPlace addPlacePayload(String name, String language, String address) {
	// create object of pojo class to serialse json from object
	AddPlace  p = new AddPlace();
	p.setAccuracy(50);
	p.setName(name);
	p.setPhone_number("(+91) 983 893 3937");
	p.setAddress(address);
	p.setWebsite("http://google.com");
	p.setLanguage(language);
	List<String> myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	p.setTypes(myList);
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	p.setLocation(l);
	
	return p;
}

public String DeletePlacePayload(String Place_id) {
	System.out.println("{\\r\\n    \\\"place_id\\\":\\\""+Place_id+"\\\"\\r\\n}\\r\\n");
	return "{\\r\\n    \\\"place_id\\\":\\\""+Place_id+"\\\"\\r\\n}\\r\\n";
}
}
	

