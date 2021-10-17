package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		// logic to fix logging rewrite issue
		if(req==null) {
			
			PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
			//Create object of request spec builder
			req = new RequestSpecBuilder().setBaseUri(Utils.getGlobalValue("baseUrl")).
			addQueryParam("key", "qaclick123").
			addFilter(RequestLoggingFilter.logRequestTo(log)).
			addFilter(ResponseLoggingFilter.logResponseTo(log)).
			setContentType(ContentType.JSON).build();
			System.out.println("Request specifivarion "+req.toString());
			return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\simin\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fs);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath path = JsonPath.from(resp);
		System.out.println(path.get(key).toString());
		return path.get(key).toString();
	}
}
