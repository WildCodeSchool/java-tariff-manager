# Java and Relational Databases: JDBC

In this quest you will learn about accessing relational databases with SQL and from Java with JDBC. 

### What you will learn

* SQL and Relational Databases
* Object-Relational Mapping with Java
* Accessing Relational Databases from Java with JDBC

### What you should know

* Java Basics

### Prerequisites

* Locally cloned Repository
* IDE (IntelliJ) with Gradle
* Java SDK 11+

### Java and JDBC

<details>
    <summary>Manual JDBC Connection/Statement/ResultSet Handling in Java</summary>

```java
public Optional<Customer> getByIdJava7Syntax(int id) {
        // We use try-catch-finally introduced in Java 7
        try (Connection connection = dataSource.getConnection();
             // NOTE
             // For security reasons: Always use PreparedStatements, not Statement
             PreparedStatement stmt = connection.prepareStatement("my_call");
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                String value = resultSet.getString(1);
                System.out.println(value);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.empty();
    }
```
</details>

### Challenge: Query Customers with plain JDBC

* 