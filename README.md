# Tariff-Manager Java & Spring Enterprise Application

Tariff-Manager Specification and Backend Service for Tutoring Java & Spring Boot in Enterprise Contexts.  
_Note: This document uses [arc42](https://github.com/arc42) for specifications & documentation._

## Introduction & Goals

This document specifies an application that simulates the operation of the information system of a cellular operator. The subject area and technical requirements are described in more detail below.  
On a meta level, this document frames the tutorials for Java & Spring Framework usage in Enterprise environments.

## Constraints

### Technical requirements

* Develop a multi-user client-server application with a network connection.
* All data is stored on the server side. 
  * Each client can download some data, after each operation, data changes must be synchronized with the server.
* The application must handle hardware and software errors. 

### Technology Stack

#### Server

* RESTful Web Services
* Build Tool Maven
* Spring Framework (Spring Boot, Spring Data JPA)
* Database Backend – MySQL

#### Client

* Browser based (Chrome, Firefox, Edge)
* Build Tool npm
* Single Page Application using React

## Business System Context

<img src="https://user-images.githubusercontent.com/2828581/160578353-e8435cf0-3e6d-4c6f-939f-ba3d48ae8ec9.png" width="80%"/>

## Deployment Diagram (Components & Interactions)

<img src="https://user-images.githubusercontent.com/2828581/160689334-98d8ea9f-555f-41be-ad83-1636bd8e728e.png" width="80%"/>

## Data Model (Class Diagram)

<img src="https://user-images.githubusercontent.com/2828581/160578346-ac7083b8-121f-4cfc-91ab-b1f131166384.png" width="80%"/>

## Entity Relationship Diagram

<img src="https://user-images.githubusercontent.com/2828581/160789917-6705780e-8392-404f-8ace-1b851b3fbe36.png" width="100%"/>

## Component Interactions (Sequence Diagram) 

<img src="https://user-images.githubusercontent.com/2828581/160578355-7b4351e2-e8df-4db3-b6c8-fdb6984c3304.png" width="80%"/>

## Spring Boot Specifics

### Developer Database

### OAPI / Swagger Support

* https://springdoc.org/
![img.png](docs/img/openapi-doc.png)

### Env-Specific Properties

### Automatic Schema Generation
