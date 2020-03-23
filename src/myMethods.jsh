import static java.lang.System.*;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;
import java.net.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public void doGetRequest() throws Exception {
		
		var client = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().GET().uri(URI.create("https://reqres.in/api/users?page=2")).build();

		var response = client.send(request, BodyHandlers.ofString());
		out.printf("Response code is: %d %n", response.statusCode());
		out.printf("The response body is:%n %s %n", response.body());
		
	}
	
public void doPostRequest() throws Exception {	
		var client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("https://reqres.in/api/users"))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .POST(BodyPublishers.ofString("{\r\n" + 
		        		"    \"name\": \"morpheus\",\r\n" + 
		        		"    \"job\": \"leader\"\r\n" + 
		        		"}"))
		        .build();
		  
			  /*
			  client.sendAsync(request, BodyHandlers.ofString())
			        .thenApply(HttpResponse::body)
			        .thenAccept(System.out::println);
			   
			  Thread.sleep(10 * 1000);
			  System.out.println("FINE SLEEP");
			  */
		  
		   
		   var response = client.send(request, BodyHandlers.ofString());
		   out.printf("Response code is: %d %n", response.statusCode());
		   out.printf("The response body is:%n %s %n", response.body());
			
	}