## Development Environment Setup for Tariff-Manager Web Application

In this guide, students will learn how to setup locally a Java Development Environment for the service component of the Tariff-Manager web application.

The environment setup in this guide is used throughout all walk-throughs in this tutoring.

### Prerequisites

To run the walk-throughs, these components must be installed locally and usable by the logged in user: 

* Java 11+
* Gradle
* Git client
* Editor or IDE for Java Development

#### Recommended Installation

* Download and Install [IntelliJ IDE Community Edition](https://www.jetbrains.com/idea/download/#section=windows)

_Note: When installing an IDE, all other pre-requisites are solved automatically guided by the IDE. For the walkthroughs, we assume the Java IDE IntelliJ Community Edition is installed._

#### Or manual Installation of Java, Gradle and Git client

If you choose to install the pre-requisites manually, we recommend

* [Eclipse Temurin, Latest LTS Release](https://adoptium.net/de/)
* [Gradle, Latest Release](https://gradle.org/install/)
* [Git client, Latest Version](https://gitforwindows.org/) (for [Windows](https://gitforwindows.org/)) or use [GitHub Desktop](https://desktop.github.com/)

### Cloning the GitHub Repository

* Import as **Project from Version Control** in IntelliJ

![](../../../docs/img/file-new-vcs.png)

* Use `https://github.com/ice09/tariff-manager.git` as Repository URL

### Starting the Web Application

We use Spring Boot as a Rapid Application Development Tool for Web Application in Java.

Usually newer IDEs like IntelliJ, Visual Studio Code, Eclipse or Netbeans analyze the application when its opened and identify the Tariff-Manager as a Spring Boot Web Application.

If the identification process is successful, the main class is identified and support for starting the Web application is provided.

![](../../../docs/img/create-run-config.png)
