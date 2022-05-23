# Consuming REST APIs with htmx

In this quest you will learn about REST Clients like Single Page Applications and the difference to Server-Side Rendering with Spring MVC. 

### What you will learn

* Differences between Spring MVC and Spring REST
* Using htmx for Client-side content rendering

### What you should know

* Spring DI/IoC / Spring Boot
* Creating REST APIs with Spring Boot

### Prerequisites

* Locally cloned Repository
* IDE (IntelliJ) with Gradle
* Java SDK 11+

## Spring MVC vs Spring REST

To understand why Spring Boot accelerates Web Application Development.

### Htmx for Client-side HTML Rendering

Htmx is a quite new technology for Client-based HTML Rendering which focuses on the use of HTML more than JavaScript.
Htmx is a library that allows you to access modern browser features directly from HTML, rather than using javascript.

Htmx is a JavaScript library for performing AJAX requests, triggering CSS transitions, and invoking WebSocket and server-sent events directly from HTML elements.

Htmx is a JavaScript library for performing AJAX requests, triggering CSS transitions, and invoking WebSocket and server-sent events directly from HTML elements. Htmx lets you build modern and powerful user interfaces with simple markups.

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

#### Calling the Service with Client-side HTML Rendering

