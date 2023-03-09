# Tariff-Manager & Application d'entreprise en Spring

Tariff-Manager : spécifications et projet du backend visant à accompagner la formation Java & Spring Boot pour un contexte d'entreprise.  
_Note: Ce document applique le modèle [arc42](https://github.com/arc42) de spécifications & de documentation._

## Introduction & Objectifs

Ce document donne les spécifications d'une app qui simule la gestion opérationnelle du SI d'un fournisseur de réseau mobile. Le périmètre fonctionnel et technique est détaillé ci-dessous.
L'enjeu est de fournir une immersion dans un projet thématique similaire à ce que l'on peut rencontrer en entreprise, en utiliser les technologies étudiées : Java, Spring, Angular

## Contraintes

### Enjeux Techniques

* Developper un application client-server multi-utilisateurs par le biais d'une connexion internet.
* Les données sont stockées côté serveur. 
  * Chaque client peut télécharger une partie des données, mais après chaque opération, les changements de données sont synchronisés avec le serveur.
* L'application doit gérer les erreurs matérielles et logicielles. 

### Stack des technologies

#### Serveur

* RESTful Web Services
* Build Tool Maven 
* Spring Framework (Spring Boot, Spring Data JPA)
* Base de données : H2

##### Base de données H2

⚠️ Vous allez découvrir un nouveau type de base de données minimaliste dont le moteur est H2. 

Ce système est assez pratique car la base est stockée dans un seul fichier. Ce SGBD minimaliste est écrit en pur Java.

Un autre énorme avantage, c'est que Spring Boot + H2 génèrent une web UI pour parcourir la base de données. Pour y accéder : 

* Rendez vous sur http://localhost:8080/h2-console/login.jsp & utiliser JDBC URL `jdbc:h2:./data/tariff` , le username : `sa` et **aucun** mot de passe
* Tester cette requête sur l'UI : `SELECT * FROM CUSTOMER WHERE FIRSTNAME='Herbert'` qui doit retourner le customer en question. 


#### Client

* Navigateur moderne (Firefox, Chrome, Edge)
* Build Tool npm
* Single Page Application using React

## Contexte métier & rôles

<img src="docs/img/tariff-manager-ctxd.png" width="80%"/>

## Diagramme des communications (Composants & Interactions)

<img src="docs/img/tariff-manager-dd.png" width="80%"/>

## MCD (Modèle Conceptuel de Données)

Autrement dit le diagramme de classes

<img src="docs/img/tariff-manager-cd.png" width="80%"/>

## MPD (Modèle Physique de Données)

Diagramme des relations entre entités, représentation proche de la base de données

<img src="docs/img/tariff-manager-erd.png" width="100%" style="border: 1px solid"/>

## Diagramme de séquence

<img src="docs/img/tariff-manager-sd.png" width="80%"/>

## Interface REST 

### [UC1] Display Customers

<img src="docs/img/tariff-manager-dst-uc1.png" width="80%"/>

#### Request

`GET /customer`

#### Response

<details>
    <summary>Datatypes</summary>

```
[
  {
    "firstname": "string",
    "lastname": "string",
    "birthdate": "date",
    "passportNo": "string",
    "addresses": [
      {
        "address": "string"
      }
    ],
    "contractInfo": [
      {
        "tariff": "string",
        "options": [
          "string"
        ]
      }
    ]
  }
]
```

</details>

##### Sample Response

<details>
    <summary>Sample Response</summary>

```json
[
  {
    "firstname": "Kendrick",
    "lastname": "Ortiz",
    "birthdate": "1981-08-21",
    "passportNo": "036-53-2166",
    "addresses": [
      {
        "address": "031 Becker Islands 308 31966-4886 Lake Fredland"
      }
    ],
    "contractInfo": [
      {
        "tariff": "SYNERGISTIC-CONCRETE-BENCH",
        "options": [
          "small wooden pants v 17.48322"
        ]
      }
    ]
  },
  {
    "firstname": "Jc",
    "lastname": "Towne",
    "birthdate": "1979-04-29",
    "passportNo": "736-86-8610",
    "addresses": [
      {
        "address": "631 Norman Brooks 92208 37890-8042 Kendallview"
      }
    ],
    "contractInfo": [
      {
        "tariff": "DURABLE-PAPER-KNIFE",
        "options": [
          "synergistic wool keyboard v 17.19142"
        ]
      }
    ]
  }
]
```
</details>

### [UC2] Display Tariff with selectable options


<img src="docs/img/tariff-manager-dst-uc2.png" width="80%"/>

#### Request

`GET /tariff`

#### Response

<details>
    <summary>Datatypes</summary>

```
[
  {
    "id": "string",
    "name": "string",
    "price": "string",
    "possibleOptions": [
      {
        "id": "string",
        "name": "string",
        "price": "string",     
        "setup": "string"      
      }
    ]
  }
]
```

</details>

##### Sample Response

<details>
    <summary>Sample Response</summary>

```json
[
  {
    "id": 957,
    "name": "SLEEK-IRON-COAT",
    "price": 25.88,
    "possibleOptions": [
      {
        "id": 956,
        "name": "gorgeous iron lamp v 12.27718",
        "price": 59.01,
        "setup": 33.88
      }
    ]
  },
  {
    "id": 960,
    "name": "SYNERGISTIC-PAPER-SHIRT",
    "price": 99.3,
    "possibleOptions": [
      {
        "id": 959,
        "name": "durable concrete shoes v 14.62467",
        "price": 58.35,
        "setup": 45.38
      }
    ]
  }
]
```
</details>

## Spring Boot Configuration

```
spring.datasource.url=jdbc:h2:./data/tariff
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

## Spring Boot - quelques info

### Developer Database

### OpenAPI / Swagger

* https://springdoc.org/v2

### Références

#### Diagrammes

* Contexte : https://app.diagrams.net/#G1rEl42atanalQigqd9faOygsIDW7XSZM1
* Classes : https://app.diagrams.net/#G1s9y-_f4spHFDbNqI9KunE7OX1JnG0FTw
* Séquence : https://app.diagrams.net/#G1joAg8E6hUbHD13t9PNJJnR8pClGaBs8I
* Interactions : https://app.diagrams.net/#G1qJHES7U5cNr5X_KfAkzZ7gasSE7KlGDK

#### Templates

* Quêtes : https://odyssey.wildcodeschool.com/admin/quests/1535
