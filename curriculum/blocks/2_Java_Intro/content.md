## Introduction to Object-Oriented Programming & Java

### Objective

In this block, Students learn basics about Object-Oriented Programming and get to know Java, the JDK and Standard Libraries.

### What will Students learn?

* OOP Programming Concepts: Inheritance, Composition, Interfaces
* Sample for Object Graph with Inheritance and Polymorphism

### Use Case: Calculate Discounts for Customers depending on their Relevance

```
As a Customer, I want to see the discount I get on a Tariff and its Options.
```

### Discount Calculation Logic

The Tariff-Manager application uses different types of customers for the calculation of a discount based on certain attributes of a customer. 

A `SpecialCustomer` will get 5% discount on the Tariff prices, a `VICustomer` will get 10% discount. 

_Note: The classification of the `SpecialCustomer`, `VICustomer`, and `StandardCustomer` is not part of our system. Our system will get a marker for the Customer type on data import. For `StandardCustomers` the type (`StandardCustomerWithPotential`...) is derived from attributes like `lastPurchase` and `birthDate`._

### Data Model of Tariff-Manager Customers

Depending on their rating (which is external to this system), `Customer` are ranked for `VI-Customer`, `SpecialCustomer`, or `StandardCustomer`.  

A `StandardCustomer` can be further classfied as `StandardCustomerWithPotential`, `StandardCustomerNoPotential`, or `JuniorCustomer`. The criteria for any customer type are stated in the diagram.

### Data Model Implementation in Object-Oriented Languages

![](../../../docs/img/oop1-customer.png)

In OOP Languages, the implementation of the specified data model is realized by Inheritance, Abstract Classes and Interfaces.

#### Starting with the base class: the generic `Customer`

The generic [Customer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/Customer.java) is implemented as an `abstract` class. Abstract classes cannot be instantiated, only non-abstract subclasses can be instantiated as instances of classes.

#### Investigating the Class Hierarchies

In our data model, [VICustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/VICustomer.java) and [SpecialCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/SpecialCustomer.java) are direct subclasses of `Customer`, so they represent specific customers, which can be instantiated.

[StandardCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomer.java) is a subclass of `Customer`, but is itself still abstract and cannot be instantiated. Only subclasses of `StandardCustomer`: [StandardCustomerWithPotential](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomerWithPotential.java), [StandardCustomerNoPotential](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomerNoPotential.java) and [JuniorCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/JuniorCustomer.java) are instantiable classes.

#### Specifying Behaviour for Concrete Classes

Our logic implies that different customers get different discounts. We will use the inheritance to realize the different discount calculation logic.

The abstract class [Customer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/Customer.java) specifies that all instantiable subclasses of `Customer` **must** implement the `abstract` method `calculateDiscountedPrice()`. Note that the abstract class can also implement methods which are valid for all subclasses if they don't override these methods. One example of these kind of methods is `whoAmI()` in `Costumer`. All subclasses inherit this method from this super class.

#### OOP in Action: Testing the Discount Calculation for different Customers

In the test class ... these test methods can be used to understand the logic of inheritance for `Customers`:

* ....
