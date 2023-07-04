<a name="readme-top"></a>
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#project-setup">Project Setup</a></li>
      </ul>
    </li>
    <li><a href="#api-documentation">API Documentation</li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<p align="center">
    <img src="src/main/resources/static/images/logo.png" width="150" height="150">
</p>

# About The Project
The backend of a simple food delivery website.

## Built With
* [![Java][Java-shield]][Java-url]
* [![Spring][Spring-shield]][Spring-url]
* [![Apache Maven][Apache Maven-shield]][Apache Maven-url]
* [![Apache Tomcat][Apache Tomcat-shield]][Apache Tomcat-url]
* [![Swagger][Swagger-shield]][Swagger-url]
* [![MySQL][MySQL-shield]][MySQL-url]
* [![Stripe][Stripe-shield]][Stripe-url]
* [![Power BI][Power BI-shield]][Power BI-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Getting Started

## Prerequisites
Before cloning and using this application, you'll need to install these things on your computer:
* [Java SE Development Kit 17](https://www.oracle.com/java/technologies/downloads/#java17): Of course you need to have Java installed to run a Java application. I used Java 17, but I think it works fine with the widely-used Java 8 (JDK 1.8), as well.
* [Spring Tool Suite 4](https://spring.io/tools): an Eclipse-based IDE to develop Spring applications. 
It provides a ready-to-use environment to implement, run, deploy, and debug the application. 
It validates your application and provides quick fixes for the applications.
* [Swagger 2](https://swagger.io/): an open source project used to generate the REST API documents for RESTful web services. It provides a user interface to access our RESTful web services via the web browser.
* [MySQL 8.0](https://dev.mysql.com/downloads/installer/): an open source relational database management system that was originally released in 1995. MySQL is popular among all databases, and is ranked as the 2nd most popular database, only slightly trailing Oracle Database. Among open source databases, MySQL is the most popular database in use today and known as one of the most reliable and performative databases out there.
* [H2 Database](https://www.h2database.com/html/main.html): an embedded, open-source, and in-memory database. It is a relational database management system written in Java. It is a client/server application. It is generally used in unit testing. It stores data in memory, not persist the data on disk.
* [Power BI](https://powerbi.microsoft.com/en-us/): a business analytics service provided by Microsoft that lets you visualize your data and share insights. It converts data from different sources to build interactive dashboards and Business Intelligence reports.

## Installation
You can install this application by cloning this repository into your current working directory:
```sh
git clone https://github.com/theEmperorofDaiViet/eat-commerce.git
```
After cloning the repository, you can open the project by Spring Tool Suite.

## Project Setup

### Database Settings
Open the application.properties file in [/src/main/resources](src/main/resources) to change the information about the datasource to fit your own settings. You can use H2 for testing in development, and use MySQL in production.
* Configuration for MySQL:
  ```sh
  spring.datasource.url=jdbc:mysql://localhost:3306/dbname
  spring.datasource.username=root
  spring.datasource.password=password
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.sql.init.mode=ALWAYS
  spring.jpa.hibernate.ddl-auto=update
  ```

* Configuration for H2:
  ```sh
  spring.datasource.dbcp2.driver-class-name=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.h2.console.enabled=true
  ```

Open MySQL or H2 and create a empty database with the name specified above.

### Enable Swagger 2
* First, add the following dependencies in the [pom.xml](pom.xml) file:
  ```xml
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
    </dependency>
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
    </dependency>
  ```
* Create Docket bean in [SwaggerConfig.java](src/main/java/com/educative/ecommerce/config/SwaggerConfig.java) to configure Swagger 2 for the application. We need to define the base package to configure REST API(s) for Swagger 2.
  ```java
    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo())
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.educative.ecommerce"))
          .paths(PathSelectors.any())
          .build();
    }
  ```
* Finally, custom information about the API:
  ```java
    public ApiInfo apiInfo() {
      return new ApiInfoBuilder()
          .title("Ecommerce API")
          .description("API Documentation for Ecommerce")
          .version("1.0.0")
          .license("Apache 2.0")
          .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
          .contact(new Contact("theEmperorofDaiViet", "https://github.com/theEmperorofDaiViet", "Khiet.To.05012001@gmail.com"))
          .build();
    }
  ```
### Connect MySQL Database with Power BI

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# API Documentation


<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Usage

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Contact

You can contact me via:
* [![GitHub][GitHub-shield]][GitHub-url]
* [![LinkedIn][LinkedIn-shield]][LinkedIn-url]
* ![Gmail][Gmail-shield]:&nbsp;<i>Khiet.To.05012001@gmail.com</i>
* [![Facebook][Facebook-shield]][Facebook-url]
* [![Twitter][Twitter-shield]][Twitter-url]

<br/>
<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- Tech stack -->
[Java-shield]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/
[Spring-shield]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Apache Maven-shield]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[Apache Maven-url]: https://maven.apache.org/
[Apache Tomcat-shield]: https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black
[Apache Tomcat-url]: https://tomcat.apache.org/
[Swagger-shield]: https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white
[Swagger-url]: https://swagger.io/
[MySQL-shield]: https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com/
[Stripe-shield]: https://img.shields.io/badge/Stripe-626CD9?style=for-the-badge&logo=Stripe&logoColor=white
[Stripe-url]: https://stripe.com/
[Power BI-shield]: https://img.shields.io/badge/power_bi-F2C811?style=for-the-badge&logo=powerbi&logoColor=black
[Power BI-url]: https://powerbi.microsoft.com/en-us/

<!-- Contact -->
[GitHub-shield]: https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white
[GitHub-url]: https://github.com/theEmperorofDaiViet
[LinkedIn-shield]: https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white
[LinkedIn-url]: https://www.linkedin.com/in/khiet-to/
[Gmail-shield]: https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white
[Facebook-shield]: https://img.shields.io/badge/Facebook-%231877F2.svg?style=for-the-badge&logo=Facebook&logoColor=white
[Facebook-url]: https://www.facebook.com/Khiet.To.Official/
[Twitter-shield]: https://img.shields.io/badge/Twitter-%231DA1F2.svg?style=for-the-badge&logo=Twitter&logoColor=white
[Twitter-url]: https://twitter.com/KhietTo