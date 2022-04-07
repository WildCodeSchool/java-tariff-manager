# Spring Data JPA

In this quest you will learn about querying a storage with Spring Data using JPA. 

### You should know about

* Java Basics
* Spring DI/IoC
* Java Persistence with JDBC

### Prerequisites

* Locally cloned Repository
* IDE (IntelliJ) with Gradle
* Java SDK 11+

## Recap: Java Persistence with JDBC

...

## Introducing the Java Persistence API (JPA)

In this walk-through you will learn how to model a specific Data Model in JPA. 

### Entities in Domain "Tariff-Manager"

#### Tariff

* Tariff name
* Price
* List of possible options

#### Option

* Option name
* Price
* Connection cost for option

#### Customer

* Customer name
* Last Name
* Date of birth
* Passport data
* Address

#### Contract

* Contract number
* Tariff
* Selected options for the tariff

### Data Model as Entity Relationship Diagram

<img src="../../../docs/img/tariff-manager-erd.png" width="80%"/>

## Create JPA Classes

### Class Diagramm of Corresponding JPA Diagram

<img src="../../../docs/img/tariff-manager-cd.png" width="50%"/>

### Entity Annotations

[sources](../../../src/main/java/dev/wcs/nad/tariffmanager/persistence/entity/Address.java) | [GitHub](../../../src/main/java/dev/wcs/nad/tariffmanager/persistence/entity/Address.java)

### Modelling One-to-Many Relations
```
@OneToMany(mappedBy = "customer", orphanRemoval = true)
private List<Contract> contracts = new ArrayList<>();
```

[sources](../../../src/main/java/dev/wcs/nad/tariffmanager/persistence/entity/Customer.java) | [GitHub](../../../src/main/java/dev/wcs/nad/tariffmanager/persistence/entity/Customer.java#L28)