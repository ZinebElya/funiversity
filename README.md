# Funiversity

You're going to create a fully working application that offers a REST(ful) Web API to clients.

Funiversity is the most fun university in the world. For now, the application you'll be building will only offer an
API for basic professor and course queries and manipulations.

## Technical requirements

Please adhere to the following technical requirements:
- Create a web application using Spring boot
- Create an in memory data structure that functions as a 'database'.
- Perform proper exception handling
- Perform proper logging
- Create a single or multi-module project using Maven.
    - Make sure to have a clear distinction between the controllers/api, the domain and the services
      (either by means of packages or by means of Maven modules). Make use of the so-called **layered architecture / structure**:
        ```
            -------
            | api |     
            -------
                - ProfessorController
                
            -----------
            | service |     
            -----------
                - ProfessorService (used by api/ProfessorController)
            
            ----------
            | domain |     
            ----------
                - Professor (used by service/ProfessorService (and also api/ProfessorController))
        ```
    - Activate / Enable Swagger (UI)
    - Try to write tests, try to write them first!

_Make sure to take a look at the Moviebase setup for some inspiration on the technical requirements and setup._

## Professors

Implement the following requirements:
- Create a controller specific for the professors resource
- Make it so that we can:
    - Create a new professor.
    - Get all the professors.
    - Get the professor for a given id.
    - Update an existing professor (an id is provided) with a new set of data.
    - Delete an existing professor for a given id.
- Create a specific service and the required domain objects to succeed in these requirements
    - A professor consists of an:
        - id
        - firstname
        - lastname

Try to make use of DTO's (and dedicated mappers to map those DTO's from and to your domain objects).

Furthermore, think about edge-cases, e.g.:
- What if we try to update a professor, but could not find a professor for the provided id...?

## Courses

Implement the following requirements:
- Create a controller specific for the courses resource
- Make it so that we can:
    - Create a new course.
    - Get all courses
        - Additionally, using a query parameter, we should be able to filter on study points of a course.
            - E.g. 'get the courses that have 6 study points'
            - When the query parameter is not defined, all courses should be returned
- Create a specific service and the required domain objects to succeed in these requirements
    - A course consists of an:
        - id
        - name
        - amount of study points (integer values ranging from 1 to 6)
        - an (existing) professor (either include only the professor's id, or include the full professor object)
            - object-based:
                ```
                    Professor profFreddy = profRepo.getById(providedProfessorId);
                    new Course ("IT essentials", 6.0, profFreddy);
                ```
            - identity-based:
               ```
                   new Course ("IT essentials", 6.0, 100); // where 100 is the id of a professor
               ```

Try to make use of DTO's (and dedicated mappers to map those DTO's from and to your domain objects).

Furthermore, think about edge-cases, e.g.:
- What if we try to create a course, but the professor id could not be found...?

------------------------------------------------------------------------------------------------------------------------

# funiversity

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/funiversity-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.


