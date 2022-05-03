## Advanced Java Concepts: Inheritance & Polymorphism, File Handling, Collections

### Objective

With a Reporting Tool for the Tariff-Manager, concepts of Object-Oriented Programming are deepened and Java SDK utilities like File I/O, String handling and different methods of traversing collections are introduced.

### What you will learn

* Advanced Structures in Java: Collections, Data Structures
* Loops, Conditions for Java <8 and newer versions
* Streams and Lambdas
* Little Helpers: Lombok

### Use Case Reporting Tool: Generate Customer Reports for Sales Optimization

```
As a controller, I want to create reports about customers with all relevant data.
```

### Sequence for Data Model Creation and Report Generation

For our walk-through, we will implement the following steps:

1. Import `Customers` from CSV file `src/test/resources/customer_reporting.csv`
   1. Create classes and class dependencies as described in the diagram (link)
   2. All `Customers` have `ID`, `name`, `email` address, `birthdate` and `lastPurchase` date
   3. The following types of `Customers` exists: `StandardCustomer`, `SpecialCustomer`, `VICustomer` 
   4. There are two specializations of `StandardCustomer`: ... 
   5. All `Customers` implement a `calculateDiscountedPrice(int price)` method, which calculates and returns the discount depending on the type of Customer 
   6. The type of Kunde is determined by the last column of the CSV file: S,E,V
   7. This should be implemented in a dedicated class `CustomerImporter`

* For each file: Split each line into a String array, use the correct separator string ","
* For each line: Convert each line into an object of the correct type of Kunde

_Note: If a line cannot be converted due to errors, log the ID and continue processing_

#### Conversionlogic as Pseudo-Code

```
IF (TYPE='E')
  NEW EXKLUSIVKUNDE
ELSE IF (TYPE='V')
  NEW VIKUNDE
ELSE IF (TYPE='S') AND (AGE < 25)
  NEW JUNIORKUNDE
ELSE IF (TYPE='S') AND (LAST_PURCHASE < 90 DAYS)
  NEW STANDARDKUNDE_MIT_POTENTIAL
ELSE
  NEW STANDARDKUNDE_OHNE_POTENTIAL
```

### Data Import Process

We will simulate a bootstrapping of the Web application by importing a CSV file with all customer data.
After the data is imported and the data model set up, we will run the Report engine for Reporting about imported Customers.

1. 
2. Add all Kunde objects to a new List<Kunde>
3. Create a method which prints only Kunden of type parameter, List<Kunde> filterKunden(List<Kunde> allKunden, String type)
4. Create a method which sorts List<Kunde> for name
5. Create a method which sorts List<Kunde> for lastPurchase
6. This should be implemented in class KundeSales, the class and the method should be implemented using TDD

7. Create a method sendNewsletter(List<Kunde> allKunden) which sends a newsletter to VI-Kunden and StandardKundenMitPotential: Hello NAME, you are invited to our new product demo.