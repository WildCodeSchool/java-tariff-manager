# Spring IoC / DI

Dans cette quête vous allez apprendre comment inverser le contrôle sur les instanciations grâce à l'injection de dépendances Spring. 

### Ce que vous allez apprendre

* Comment Spring lie et met à disposition les composants de l'application
* Ajout de paramètres de configuration et utilisation dans un service
* Création d'un 

### Ce que vous devez savoir au préalable

* Java Basics
* Spring DI/IoC

### Pré-requis

* Le repository cloné en local
* IDE supportant Gradle
* Java SDK 11+

### Inversion of Control and Dependency Injection

Sans l'aide d'un framework d'injection de dépendances fournissant l'Inversion de Contrôle, nos composants seraient contraints d'avoir un couplage fort et strict avec leurs dépendances, ce qui résulterait dans une architecture très peu flexible et extensible.

Le Controller ne doit pas s'occuper de la création du service, et le Service ne devrait pas être responsable du Repository etc. Cela ne respecterait pas le principe de séparation des responsabilités.

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

#### Déléguer l'instanciation des classes et la création du graphe de dépendances au container

Avec un framework de Dependency Injection et d'Inversion of Control comme Spring, les dependences peuvent être contrôlées à l'exécution, au Runtime.

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

### Challenge: Ajouter une Configuration pour les Services

Le fichier de configuration par défaut de Spring Boot est `src/main/resources/application.properties`

* Comprendre comment fonctionnent les propriétés de cette configuration
  * Découvrir la propriété existante `junior.customer.discount.percent` et comment elle est utilisée dans `src/main/java/dev/wcs/nad/tariffmanager/customer/reporting/CustomerImporter.java`
* Ajouter une nouvelle propriété `TYPE.customer.discount.percent` (remplacer TYPE par *vi* ou *special*) qui peut être injectée dans la classe `CustomerImporter`. La classe spécifique `Customer` doit prendre cette valeur de discount en paramètre de constructeur plutôt que d'utiliser une valeur statique dans la méthode `calculateDiscount`.
* Positionner la valeur de `discount` à 5, 10 et 15 % dans `application.properties` et relancer l'application pour vérifier la bonne prise en compte.

### Challenge: Ajouter un mapping entre Entité <-> DTO (Data Transfer Object)

* Nous avons préparé une simple classe de mapping : `src/main/java/dev/wcs/nad/tariffmanager/mapper/simple/CustomerMapper.java` qui transforme des DTOs en Entités et vice versa. Cette classe n'est pas encore utilisée dans le projet.
* Transformer le Mapper en composant Spring (avec l'annotation `@Component`), et injectez le puis utilisez le dans le controller pour convertir les entités Customer en DTO Customer et vice versa.
* Prendre connaissance du test `src/test/java/dev/wcs/nad/tariffmanager/customer/CustomerMapperTest.java` pour comprendre comment un test peut être écrit pour tester et valider le comportement de nos composants Spring. 
* Implémenter la méthode de test `CustomerMapperTest.shouldMapObjects` pour tester la méthode `convertEntityToDto` à partir du Mapper injecté dans la classe de test.

_TODO: Tester: un mapper MapStruct_

