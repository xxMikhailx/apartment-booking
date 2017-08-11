# APARTMENT BOOKING

## Description
### The main business actions:

- new user could register in the system;
- existing user could authorize in the system;
- existing user could change the password;
- existing user could restore forgotten password;
- user could edit personal profile data;
- user could see the list of available apartments;
- user could filter the list of available apartments;
- user could search for the concrete apartment;

Use Test Driven Development manner, write tests before the main code.

## Implementation and technology requirements:

### Maven


- use Maven to build your project and run tests, use maven multi-module projects mechanism, use maven properties to set dependency versions, add scopes to your dependencies

### Git

- proceed using git.epam.com. Pay attention that you can create only 4 personal repositories at git.epam.com, so for current task please create a new branch in the existing repository;
- use .gitignore file to determine which files and directories to ignore, before you make a commit

### Jenkins

- use Jenkins CI Server in current task;
- create simple Pipeline which includes following steps:

1. checkout code from remote repository;
2. run Unit & Integration tests;
3. build artifacts.

### Testing frameworks

- service layer should be tested using mocking framework - Mockito;
- persistence layer should be tested using DBUnit framework. Default data for testing should be loaded automatically;

### Java frameworks

- use Spring IoC to configure your beans;
- use Spring JDBC (JDBCTemplate) as data access technology;
- use Spring MVC as a controller and view technology

### Hibernate and JPA DAOs implementation

- Introduce hibernate (use xml mapping) and EclipseLink implementations for JPA api for all DAOs;
- Only internal implementation changes are expected in application;
- The implementation of DAO is switched with help of configuration property at Tomcat level;


### Database requirements:

- design database model, put the model image in to the root of your repository;
- create 2 separate db scripts: one for db schema generation and one for loading default data (near 20 items for each table);
- se transactions where it could be needed.

### Source code requirements:

- code according the Java Code Conventions;
- your SQL scripts should be written according to Oracle Naming Conventions;
- proceed using SonarQube to analyze your source code.

### Other requirements:

- Database: Oracle Database 11g or higher;
- IDE: IntelliJ IDEA;
- Java 7 or 8.
