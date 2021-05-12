package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Body;
import pojo.Location;

public class TestDataBuild {

	public Body addPlaceLoad(String name, String language, String address) {
		// TODO Auto-generated method stub
		Body B = new Body();
		B.setAccuracy(50);
		B.setAddress(address);
		B.setLanguage(language);
		B.setWebsite("http://google1.com");
		B.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		B.setTypes(myList);

		Location L = new Location();
		L.setLat(-38.383497);
		L.setLng(33.427363);
		B.setLocation(L);
		return B;
	}
}
