# REST APIs and Spring MVC Server-Side Content Rendering

In this quest you will learn about REST Clients like Single Page Applications (SPA) and the difference to Server-Side Rendering with Spring MVC. 

### What you will learn

* Differences between Spring MVC and Spring REST
* Using htmx with Thymeleaf for Server-side processing with Client-side content rendering

### What you should know

* Spring DI/IoC / Spring Boot
* Creating REST APIs with Spring Boot

### Prerequisites

* Locally cloned Repository
* IDE (IntelliJ) with Gradle
* Java SDK 11+

## Spring MVC vs Spring REST

To understand why Spring Boot accelerates Web Application Development.

### Htmx for Client-Side HTML Rendering

Htmx is a quite new technology for Client-based HTML Rendering which focuses on the use of HTML more than JavaScript. We use it in combination with Thymeleaf to focus on Server-side processing in Java, rather than mixing in JavaScript.
Htmx is a JavaScript library for performing AJAX requests, triggering CSS transitions, and invoking WebSocket and server-sent events directly from HTML elements which would typically require adding JavaScript like jQuery or more modular frameworks like React or Angular.
In Block 12 we will dive into React as a modular JavaScript framework, in this Block we will focus on the difference between MVC and REST.

### Understand REST Service "GetCustomers"

To understand the differences between data-oriented REST JSON Services and HTML-based Spring MVC, we start with the *GetCustomer* Service.

#### Calling the Service with the generated Swagger-UI

Spring Boot activates the OpenAPI plugin, which generates the OpenAPI-format for our services here: http://localhost:8080/v3/api-docs
With the information provided in this OpenAPI-JSON, a client can be generated. With the Swagger dependency in the project's `gradle.build`, a generic Swagger client is generated from the JSON at http://localhost:8080/swagger-ui.html

We can now use the generated Swagger-UI to call the *GetCustomer* REST Endpoint with *GET /customer* directly in the browser. The request and the response are both HTTP/JSON-based, so no markup is used to render the data human-readable.

![img.png](../../../docs/img/get_cust_rest.png)

The `GetMapping` annotation defines the method which is called by the HTTP GET request to */customer* 
Note that `application/json` is used as the default format, so the `CustomerDto` is automatically unmarshalled into JSON and returned with a HTTP Response.

```java
@GetMapping("/customer")
public List<CustomerDto> displayCustomers() {
    List<CustomerDto> customerDtos = new ArrayList<>();
    for (Customer customer: customerService.readAllCustomers()) {
        customerDtos.add(entityToDtoMapper.customerToCustomerDto(customer));
    }
    return customerDtos;
}
```

#### Calling the Service with Server-Side processing MVC and Client-side HTML Rendering

We use Thymeleaf for server-side rendering, ie. a simple markup language is used in the HTML to process the content on the server and deliver the result as a HTTP Response in HTML.
_Note: We use htmx for augmenting the HTML with server-side data, you can ignore this technique here and focus on the server processing with Spring Boot_

![](../../../docs/img/thymeleaf_htmx_mvc.png)

* The model is responsible for managing the data of the application. It receives user input from the controller.
* The view renders presentation of the model in a particular format.
* The controller responds to the user input and performs interactions on the data model objects. The controller receives the input, optionally validates it and then passes the input to the model.

![](../../../docs/img/thymeleaf_htmx.png)

The server-side rendering uses Thymeleaf as a Template Engine. The Template Engine is responsible for augmenting the View (in our case HTML) with the current state of the Model (in our case Java Beans). Responsible for associating the View and the Model is the Controller (in our case Spring MVC).
