## Introduction to Object-Oriented Programming & Java

### Objective

With an example of Customer-specific discount calculation of Tariff prices, concepts of Object-Oriented Programming, Java basics, the JDK, and Java Standard Libraries are introduced.  

### What you will learn

* Basics of the Java language: Classes, Methods, Conditions, and Loops
* OOP Programming Concepts: Inheritance, Composition, Interfaces
* Sample for Tariff-Manager Object Graph with Inheritance and Polymorphism

### Use Case: Calculate Discounts for Customers depending on their Type

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

<img src="../../../docs/img/oop1-customer.png" width="80%"/>

In OOP Languages, the implementation of the specified data model is realized by Inheritance, Abstract Classes and Interfaces.

#### Starting with the base class: the generic `Customer`

The generic [Customer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/Customer.java) is implemented as an `abstract` class. Abstract classes cannot be instantiated, only non-abstract subclasses can be instantiated as instances of classes.

#### Investigating the Class Hierarchies

In our data model, [VICustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/VICustomer.java) and [SpecialCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/SpecialCustomer.java) are direct subclasses of `Customer`, so they represent specific customers, which can be instantiated.

[StandardCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomer.java) is a subclass of `Customer`, but is itself still abstract and cannot be instantiated. Only subclasses of `StandardCustomer`: [StandardCustomerWithPotential](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomerWithPotential.java), [StandardCustomerNoPotential](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/StandardCustomerNoPotential.java) and [JuniorCustomer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/JuniorCustomer.java) are instantiable classes.

#### Specifying Behaviour for Implementation Classes

Our logic implies that different customers get different discounts. We will use the inheritance to realize the different discount calculation logic.

The abstract class [Customer](../../../src/main/java/dev/wcs/nad/tariffmanager/customer/model/Customer.java) specifies that all instantiable subclasses of `Customer` **must** implement the `abstract` method `calculateDiscountedPrice()`. Note that the abstract class can also implement methods which are valid for all subclasses if they don't override these methods. One example of these kind of methods is `whoAmI()` in `Costumer`. All subclasses inherit this method from this super class.

#### OOP in Action: Testing the Discount Calculation for different Customers

In the test class [CustomerTest](../../../src/test/java/dev/wcs/nad/tariffmanager/customer/CustomerTest.java) these test methods can be used to understand the logic of inheritance for `Customers`:

* Unit test `shouldCreateDifferentCustomersWithDifferentDiscounts`
  * Examine this test to understand the different `Customer` implementations. 
  * Understand how the different discount calculation works. 
  * How can new dicounts be introduced?
* Unit test `shouldImplementLogicOnBaseMethods`
  * Understand how the specific subclass type is checked if necessary. 
  * Make sure you understand the difference between (external) explicit type checking and the (internal) method call (`isRelevantForMailing`). 
  * Which method is easier to extend? 

#### Challenge: Add a new Customer Type

In this challenge, we add a new `Customer`: `EmployeeCustomer`

```
EmployeeCustomer are customers who are also employees of the company and get a discount of 15%. 
```
_Rule: If the email address is at domain **acme.org**, the `Customer` should be classified as a `EmployeeCustomer`, so **herbert@acme.org** is a `EmployeeCustomer`._

1. Create a new class `EmployeeCustomer` which directly extends abstract class `Customer`. The `calculateDiscount(int value)` should return 15% of the value as discount.
2. Create a unit test which creates two `EmployeeCustomer`, adds those to a `List<Customer>` and adds a `JuniorCustomer` to the list (see Method `shouldTestNewEmployeeCustomer`) [sources](../../../src/test/java/dev/wcs/nad/tariffmanager/customer/CustomerTest.java)
3. For the assertion part in the test, make sure the correct discounts are calculated. See the existing tests for reference.