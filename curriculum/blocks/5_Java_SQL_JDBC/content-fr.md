# Java et les bases de données relationnelles : JDBC

In this quest you will learn about accessing relational databases with SQL and from Java with JDBC. 

### Ce que vous allez apprendre

* SQL et les bases de données relationnelles
* Accès et utilisation d'une BDD relationnelle en Java avec JDBC
* Mapping relationnel => objet simple en Java

### Ce que vous devez savoir au préalable

* Java Basics

### Pré-requis

* Le repository cloné en local
* IDE supportant Gradle
* Java SDK 11+

### Accéder à une base de données relationnelle en Java

<img src="../../../docs/img/jdbc_delegation.png" width="50%"/>

_Couches d'appel pour l'accès à une BDD en Java avec JDBC_

### Interfaces utiles à l'interaction avec la BDD

Suivez les étapes de la documentation de `src/main/java/dev/wcs/nad/tariffmanager/persistence/jdbc/CustomerLegacyDao.java`

#### Etapes pour un accès JDBC standard utilisant une `DataSource` Spring configurée

1. Importer les packages JDBC _[Line 9]_
2. Créer une connexion dans la méthode `getByIdJava7Syntax` à partir de la `DataSource` (la DataSource est une Connection Pool fournie par Spring, cela permet de ne pas recréer une connexion - ce qui est couteux - à chaque requête) _[Line 30]_
3. Appeler `preparedStatement` sur `Connection` _[Line 38]_
4. Récupérer le `ResultSet` à partir du `Statement` avec `executeQuery` _[Line 44]_ 
5. Lire les résultats depuis le `ResultSet` et mapper les valeurs _[Line 49]_

<img src="../../../docs/img/java_jdbc_sq.png" width="80%"/>

### Gestion manuelle avec JDBC : Connection/Statement/ResultSet 

Jetez un oeil à l'extrait de code suivant mettant en oeuvre un _try-with-resources_ pour la gestion des ressources JDBC. Depuis Java 7 c'est une bonne practique pour la gestion manuelle de JDBC.

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

Dans `src/main/java/dev/wcs/nad/tariffmanager/persistence/jdbc/CustomerLegacyDao.java` vous pouvez constater l'ancienne façon de faire (Java <7) dans la méthode `getByIdBeforeJava7`, à titre informatif uniquement. Elle apparait parfois sur internet mais n'est pas recommandée.

### Challenge: requêter les Customers avec du JDBC

L'objectif de ce challenge est de faire marcher le test `AddressJdbcTest.shouldLoadAddressWithId2`

* Implémenter `addressLegacyDao.getByIdJava7Syntax` en prenant exemple sur CustomerLegacyDao
  * Actuellement cette méthode est vide, il faut l'améliorer pour qu'elle cherche les données en base de données
* Pour lancer le test, utiliser `./gradlew test --info --stacktrace`

_Note : les champs de la table ADDRESS dans la base de données de test H2._
![](../../../docs/img/ADDRESS_ER.png)