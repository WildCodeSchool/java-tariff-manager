# Spring Data JPA

In this quest you will learn about querying a storage with Spring Data using JPA. 

### What you will learn

* Testing in Java: Types and Context (Unit, Integration, ...)
* Unittesting with JUnit 5 & Refactoring
* Introduction to Mocking, Stubbing and modular component testing
* Test-Driven-Development with JUnit 5, AssertJ, Spring and Mockito
* Introduction to modular enterprise development with IoC/DI (Spring)

### What you should know

* Java Basics
* Spring DI/IoC
* Java Persistence with JDBC

### Prerequisites

* Locally cloned Repository
* IDE (IntelliJ) with Gradle
* Java SDK 11+

### Inversion of Control and Dependency Injection

Without a Dependency Injection Framework which provides Inversion of Control, the dependencies of the components would be strong and strict and defined at compile-time already, resulting in a highly unflexible and unextendible architecture.

The Controller should not be responsible for the Service, the Service should not be responsible for the Repository etc. This pattern clearly violates the Seperation of Concerns best practice.

<img src="../../../docs/img/trad_layer.png" width="30%"/>

```java
// Instantiate Controller - but what Service should be used?
CustomerController customerController = new CustomerController(???);

// Instantiate Service - but what Repository should be used?
CustomerService customerService = new CustomerService(???);

// Instantiate Repository, but with what Datasource?
CustomerRepository customerRepository = new CustomerRepository(???);

// This would have to be created on startup:
String databaseParameter = "USERNAME:PASSWORD@DB_URL:DB_PORT/DB_SCHEMA/";
CustomerRepository customerRepository = new CustomerRepository(databaseParameter);
CustomerService customerService = new CustomerService(customerRepository);
CustomerController customerController = new CustomerController(customerService);
```

#### Delegating Class Instantiations and Object Graph Creation to Runtime Containers

With a Dependency Injection and Inversion of Control Framework like Spring, the dependencies can be controlled at Runtime.

<img src="../../../docs/img/diioc_layer.png" width="80%"/>

```java
// Give DI/IoC Framework hints by Annotations, Depend on Interfaces only.
// The Runtime Container (Spring Boot/Spring Framework) will build the object graph on startup.
// All dependencies are runtime dependencies only, not compile-time dependencies.

@Controller
public class CustomerController {
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
} 

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}

@Repository
public class CustomerRepository {
    //...
}
```