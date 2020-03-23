import static java.lang.System.out;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Scanner;

public class UsersHttpClient {

	public static void main(String[] args) throws Exception {
		
		printMenu();
		Scanner sc = new Scanner(System.in);
				
		int i = sc.nextInt();
		switch(i) {
		case 1:
			doGetRequest();
			break;
		case 2:
			doPostRequest();
			break;
		default:
			System.out.println("Invalid option");
		}
			
	}
	
	private static void printMenu() {
		System.out.println("Scegli:\n - GET REQUEST: 1;\n - POST REQUEST: 2;\n");
	}
	
	public static void doGetRequest() throws Exception {
		
		var client = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().GET().uri(URI.create("https://reqres.in/api/users?page=2")).build();

		var response = client.send(request, BodyHandlers.ofString());
		out.printf("Response code is: %d %n", response.statusCode());
		out.printf("The response body is:%n %s %n", response.body());
		
	}
	
	public static void doPostRequestFromFile() throws IOException, InterruptedException {	
		var client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("https://reqres.in/api/users"))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .POST(BodyPublishers.ofFile(Paths.get("file.json")))
		        .build();
		   
		  client.sendAsync(request, BodyHandlers.ofString())
		        .thenApply(HttpResponse::body)
		        .thenAccept(System.out::println);
		   
		  Thread.sleep(10 * 1000);
		  System.out.println("FINE SLEEP");
		  
		   /*
		   var response = client.send(request, BodyHandlers.ofString());
			out.printf("Response code is: %d %n", response.statusCode());
			out.printf("The response body is:%n %s %n", response.body());
			*/
	}
	
	public static void doPostRequest() throws Exception {	
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
}