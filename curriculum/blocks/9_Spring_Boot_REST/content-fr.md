# REST APIs with Spring Boot

Dans cette quête vous allez apprendre à créer une API REST rapide avec Spring Data, JPA et Spring Boot. 

### Ce que vous allez apprendre

* Spring REST
* Services Spring

### Ce que vous devez savoir au préalable

* Java Basics
* Spring DI/IoC
* Java et les bases de données relationnelles : JDBC
* Persistence avec un ORM en Java / Spring : Spring Data / JPA

### Pré-requis

* Le repository cloné en local
* IDE supportant Gradle
* Java SDK 11+

## Spring DI/IoC, Spring Boot and REST

Pour comprendre comment Spring Boot accélère le développement d'applications Web 

<img src="../../../docs/img/diioc_layer.png" width="80%"/>

Si vous lancez cette application, vous pouvez accéder au endpoint REST (`CustomerController.displayCustomer`) à l'URL http://localhost:8080/customer

Le `CustomerController` est instancié par le container Spring (IoC) et le graphe de dépendances est chargé et injecté (notamment `CustomerService` et `CustomerRepository`).

Avec l'injection de dépendance Spring, toutes les dépendances requises sont évaluées et fournies à l'exécution (runtime). Toutes les contraintes liées aux dépendances entres les composants sont testées avant la fin du démarrage de l'appplication. Les composants ont donc la garantie de pouvoir utiliser leur dépendances, et se concentrer sur leur responsabilité, leur apport pour l'appication.

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

### Challenge: Ajouter un nouveau endpoint GET avec un filtrage de résultat

```
Un nouveau endpoint du `CustomerController` doit être ajouté dans le contexte d'un ajout de fonctionnalité visant à fournir un accès sans restrictions. 
Ce endpoint doit filtrer les customers pour ne garder que ceux qui ont 18 ans ou plus et les retourner.
```

Étapes : 

* Après avoir récupéré tous les customers de la base depuis le service, récupérez un `Stream` et filtrez les customers pour garder ceux de 18 ans ou plus.
* Ajoutez un paramètre `QueryParam` pour filtrer encore davantage les customers : ce nouveau paramètre `searchFilter` doit permettre de ne garer que les customers dont le `name` commence par `searchFilter` 
* Déplacer la prise en compte des filtres (age et `searchFilter`) dans une couche inférieure afin qu'ils soient pris en compte directement dans la requête SQL, dans le but d'optimiser les performances (cela évitera de charger **tous** les customers, mais seulement ceux dont le nom débute par `searchFilter` dont l'age est supérieur ou égal à `minAge`).

### Challenge: Rendre l'âge de maturité configurable

* Actuellement, l'âge (18) est hard codé. Utiliser `application.properties` pour le rendre configurable. Vous pouvez choisir le nom de propriété qui vous convient, instinctivement on penserait à quelque chose comme `maturity.age`.

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
```

