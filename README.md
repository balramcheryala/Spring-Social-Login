Enable Facebook

Before you can fetch a user’s data from Facebook, you must specify your application’s ID and secret by setting the spring.social.facebook.appId and spring.social.facebook.appSecret properties. You can set these via any means supported by Spring Boot, including setting them in an application.properties file:

src/main/resources/application.properties

spring.social.facebook.appId=962223610477458
spring.social.facebook.appSecret=b7dfec28b08ac4e8c2a09cbac4662c15
As shown here, the properties have fake values. The values given to these properties correspond to your application’s consumer key and secret you obtain when you register the application with Facebook. For the code to work, substitute the real values given to you by Facebook in place of the fake values.

The presence of these properties and Spring Social Facebook in the classpath will trigger automatic configuration of Spring Social’s ConnectController, FacebookConnectionFactory, and other components of Spring Social’s connection framework.

Create connection status views

Although much of what ConnectController does involves redirecting to Facebook and handling a redirect from Facebook, it also shows connection status when a GET request to /connect is made. It defers to a view named connect/{providerId}Connect when no existing connection is available and to connect/{providerId}Connected when a connection exists for the provider. In this case, provider ID is "facebook".

ConnectController does not define its own connection views, so you need to create them. First, here’s a Thymeleaf view to be shown when no connection to Facebook exists:

src/main/resources/templates/connect/facebookConnect.html

<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Connect to Facebook</h3>

		<form action="/connect/facebook" method="POST">
			<input type="hidden" name="scope" value="user_posts" />
			<div class="formInfo">
				<p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
			</div>
			<p><button type="submit">Connect to Facebook</button></p>
		</form>
	</body>
</html>
The form on this view will POST to /connect/facebook, which is handled by ConnectController and will kick off the OAuth authorization code flow.

Here’s the view to be displayed when a connection exists:

src/main/resources/templates/connect/facebookConnected.html

<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Connected to Facebook</h3>

		<p>
			You are now connected to your Facebook account.
			Click <a href="/">here</a> to see some entries from your Facebook feed.
		</p>
	</body>
</html>
Fetch Facebook data

With Facebook configured in your application, you now can write a Spring MVC controller that fetches data for the user who authorized the application and presents it in the browser. HelloController is just such a controller:

src/main/java/hello/HelloController.java

package hello;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    private Facebook facebook;
	private ConnectionRepository connectionRepository;

    @Inject
    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
		this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }

}
HelloController is created by injecting a Facebook object into its constructor. The Facebook object is a reference to Spring Social’s Facebook API binding.

The helloFacebook() method is annotated with @RequestMapping to indicate that it should handle GET requests for the root path (/). The first thing the method does is check whether the user has authorized the application to access the user’s Facebook data. If not, the user is redirected to ConnectController with the option to kick off the authorization process.

If the user has authorized the application to access Facebook data, the application fetches the user’s profile as well as several of the most recent entries in the user’s feed. The data is placed into the model to be displayed by the view identified as "hello".

Speaking of the "hello" view, here it is as a Thymeleaf template:

src/main/resources/templates/hello.html

<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Hello, <span th:text="${facebookProfile.name}">Some User</span>!</h3>

		<h4>Here is your feed:</h4>

		<div th:each="post:${feed}">
			<b th:text="${post.from.name}">from</b> wrote:
			<p th:text="${post.message}">message text</p>
			<img th:if="${post.picture}" th:src="${post.picture}"/>
			<hr/>
		</div>
	</body>
</html>
Make the application executable

Although it is possible to package this service as a traditional web application archive or WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application. You package everything in a single, executable JAR file, driven by a good old Java main() method. And along the way, you use Spring’s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

src/main/java/hello/Application.java

package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
@SpringBootApplication is a convenience annotation that adds all of the following:

@Configuration tags the class as a source of bean definitions for the application context.
@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
@ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the HelloController.
The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did you notice that there wasn’t a single line of XML? No web.xml file either. This web application is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.

Build an executable JAR

If you are using Gradle, you can run the application using ./gradlew bootRun.

You can build a single executable JAR file that contains all the necessary dependencies, classes, and resources. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

./gradlew build
Then you can run the JAR file:

java -jar build/libs/gs-accessing-facebook-0.1.0.jar
If you are using Maven, you can run the application using mvn spring-boot:run. Or you can build the JAR file with mvn clean package and run the JAR by typing:

java -jar target/gs-accessing-facebook-0.1.0.jar
 The procedure above will create a runnable JAR. You can also opt to build a classic WAR file instead.
... app starts up ...
Once the application starts up, point your web browser to http://localhost:8080. No connection is established yet, so this screen prompts you to connect with Facebook:

No connection to Facebook exists yet.
When you click the Connect to Facebook button, the browser is redirected to Facebook for authorization:

Facebook needs your permission to allow the application to access your data.
Click "Okay" to grant permission for the sample application to access your public profile and your feed.

Once permission is granted, Facebook redirects the browser back to the application. A connection is created and stored in the connection repository. You should see this page indicating that a connection was successful:

A connection with Facebook has been created.
Click the link on the connection status page, and you are taken to the home page. This time, now that a connection has been created, you see your name on Facebook and a list of some recent entries in your home feed. Included for each post in the feed list is the name of the sender, the post’s message, and the post’s picture (if available).

# Spring-Social-Login
