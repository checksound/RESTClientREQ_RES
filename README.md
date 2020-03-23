# Client REST al servizio REQ | RES

Il servizio di test [REQ | RES](https://reqres.in/)

Client di esempio che utilizza le classi di java.net.http per fare
richieste REST al servizio di test.

Nell'esempio [UsersHttpClient](./src/UsersHttpClient.java) è possibile fare richieste di `GET` e `POST` con l'invocazione dei metodi:

```java

public static void doGetRequest() throws Exception {
		
		var client = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().GET().uri(URI.create("https://reqres.in/api/users?page=2")).build();

		var response = client.send(request, BodyHandlers.ofString());
		out.printf("Response code is: %d %n", response.statusCode());
		out.printf("The response body is:%n %s %n", response.body());
		
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
		  
  		   var response = client.send(request, BodyHandlers.ofString());
		   out.printf("Response code is: %d %n", response.statusCode());
		   out.printf("The response body is:%n %s %n", response.body());
			
	}

```

Esempio output:

```text
Scegli:
 - GET REQUEST: 1;
 - POST REQUEST: 2;

1
Response code is: 200 
The response body is:
 {"page":2,"per_page":6,"total":12,"total_pages":2,"data":[{"id":7,"email":"michael.lawson@reqres.in","first_name":"Michael","last_name":"Lawson","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"},{"id":8,"email":"lindsay.ferguson@reqres.in","first_name":"Lindsay","last_name":"Ferguson","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg"},{"id":9,"email":"tobias.funke@reqres.in","first_name":"Tobias","last_name":"Funke","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg"},{"id":10,"email":"byron.fields@reqres.in","first_name":"Byron","last_name":"Fields","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg"},{"id":11,"email":"george.edwards@reqres.in","first_name":"George","last_name":"Edwards","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg"},{"id":12,"email":"rachel.howell@reqres.in","first_name":"Rachel","last_name":"Howell","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg"}],"ad":{"company":"StatusCode Weekly","url":"http://statuscode.org/","text":"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things."}} 
```

Utilizzo di `jshell` per fare le request (è prima necessario caricare 
li script con la definizione delle funzioni):
 
```text

C:\dev\eclipse-workspace\RESTClientREQ_RES\src>jshell
|  Welcome to JShell -- Version 12.0.1
|  For an introduction type: /help intro

jshell> /set start myMethods.jsh

jshell> /list

jshell> /reset
|  Resetting state.

jshell> /list

jshell> /list -all

  s1 : import static java.lang.System.*;
  s2 : import java.net.http.*;
  s3 : import java.net.http.HttpRequest.BodyPublishers;
  s4 : import java.net.http.HttpResponse.BodyHandlers;
  s5 : import java.nio.file.Paths;
  s6 : import java.time.Duration;
  s7 : import java.net.*;
  s8 : import java.io.FileNotFoundException;
  s9 : import java.io.IOException;
 s10 : public void doPostRequest() throws Exception {
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

jshell>

jshell>

jshell> doPostRequest()
Response code is: 201
The response body is:
 {"name":"morpheus","job":"leader","id":"418","createdAt":"2020-03-23T08:49:39.472Z"}

jshell>

```

Vedi [Java 9 Jshell Tutorial](https://examples.javacodegeeks.com/core-java/java-9-jshell-tutorial/)

## APPROFONDIMENTI

[Java theory and practice: Explore the new Java SE 11 HTTP Client and WebSocket APIs](https://developer.ibm.com/technologies/java/tutorials/java-theory-and-practice-3/)


