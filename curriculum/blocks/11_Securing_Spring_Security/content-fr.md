# Securing REST Services with Spring Security

Dans cette quête vous allez apprendre à sécuriser vos services REST avec une Authentification & des Authorizations en utilisant Spring Security.

### Ce que vous allez apprendre

* Comprendre l'Authentification & les Authorizations de Spring Security
* Sécuriser des services REST avec Spring Security

### Ce que vous devez savoir au préalable

* Spring MVC
* REST APIs avec Spring Boot
* Spring DI/IoC
* Spring Data / JPA

### Pré-requis

* Le repository cloné en local
* IDE supportant Gradle
* Java SDK 11+

## Spring DI/IoC, Spring Boot and REST

Dans la précédente session, vous avez vu ce diagramme. Avec cette configuration, vous pouvez appeler les endpoints de `CustomerController` (méthodes) via HTTP et ces appels vont mettre à contribution les différentes couches de votre application, comme `CustomerService` et `CustomerRepository`.

Le `CustomerController` est un service REST, et nous pouvons avoir à côté un controller Spring MVC accessible pour compléter avec des vues, comme http://localhost:8080/public/customer/view

<img src="../../../docs/img/diioc_layer.png" width="60%"/>

Le problème avec cette app Spring est que les chemins sont visibles et accessible publiquement sans restrictions. Tout le monde peut accéder à notre serveur, à toutes les vues servies par Spring MVC et tous les endpoints des services REST.

### Protection des ressources avec Spring Security

![](../../../docs/img/security_filter_flow.png)

### Flow d'authentification & d'autorisation

![](../../../docs/img/spring_security_flow.png)

### Configuration Spring Security pas à pas 

_Note: Nous allons utiliser Thymeleaf pour faire le rendu des pages HTML, donc l'accès aux fichiers *.html est restreint de base. Le HTML est seulement fourni aux clients par Spring MVC et Thymeleaf._

Donc `index.html` redirige (côté client avec une balise `<meta ...>`) vers le controller correspondant au chemin */public/index/view*, qui délègue à `indexview.html`

Dans le controller `RestrictedView`, nous voulons que seuls les utilisateurs _authentifiés_ aient accès à cette page. 

```java
@Controller
@RequestMapping("/public/restricted")
public class RestrictedView {
    @GetMapping("/view")
    public String list(Model model) {
        model.addAttribute("now", new Date().toInstant());
        return "restricted";
    }
}
```

Pour restreindre l'accès à ce controller, nous choisissons de mettre en place une _restriction basée sur les URL_, et non une _restriction basée sur les ressources_. Cette autre option va arriver dans une étape ultérieure.
Afin d'activer le filtrage basé sur l'URL, il nous faut :

1. Activer la capacité d'un utilisateur à s'inscrire et à se connecter au système
2. Ajouter des rôles (permettant d'allouer des autorisations) aux utilisateurs
3. Construire une `Security Filter Chain` pour valider l'authentification et les autorisations
4. Configurer le `UserRepository`, le `UserService` and password handling

Référence complète: toutes les étapes nécessaires sont expliquées dans le guide Spring: https://spring.io/guides/gs/securing-web/

#### Inscription / connexion des utilisateurs

Nous démarrons avec la configuration principale, située dans la classe WebSecurityConfig (src/main/java/dev/wcs/nad/tariffmanager/identity/config/WebSecurityConfig.java).

Vous trouverez deux classes injectées dans le constructeur: `SecurityUserService` and `PasswordEncoder`.

Ouvrir le `SecurityUserService` pour comprendre comment la persistance des Users est implémentée. Vous allez reconnaitre plusieurs éléments, comme le JpaRepository, venant de Spring Data / JPA. Le `PasswordEncoder` est utilisé pour [hasher](https://fr.wikipedia.org/wiki/Fonction_de_hachage) le mot de passe de l'utilisateur d'une façon sécurisée. Spring Security nous garantit que cet encoder fournira une manière standard, et à jour de hasher nos mots de passe pour qu'ils soient stockés en base de données. *Rappel* : le hash permet de ne pas stocker directement le mot de passe en clair, et il n'est pas possible de retrouver le mot de passe à partir du hash. 

_Cet aspect est très important on ne stocke jamais un mot de passe directement en base de données._  

#### Configurer Spring Security

Toute la configuration relative à la sécurité est effectuée dans la méthode `WebSecurityConfig.configure`. Découvrez cette configuration à base d'[interface fluide](https://en.wikipedia.org/wiki/Fluent_interface) et vérifiez que vous comprenez les étapes de configuration. Si vous avez un doute, n'hésitez pas à ajouter retirer ou modifier une partie pour voir l'effet.

### Challenge : changer la Configuration

Changer la configuration pour autoriser uniquement les utilisateurs authentifiés à accéder à la vue liste des customers sur le site. Actuellement, tous les utilisateurs, même non connectés y ont accès.

### Challenge : Authentication & Authorization : Gestion des accès par rôle 

Puis restreindre encore davantage l'accès à cette section customers pour qu'elle ne soit plus accessible que par le rôle BACKOFFICE

### Challenge: Ajouter une page d'administration sécurisée par une restriction d'accès basée sur l'URL pour le rôle ADMIN

