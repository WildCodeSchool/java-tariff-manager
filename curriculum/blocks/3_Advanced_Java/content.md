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