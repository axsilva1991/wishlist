# Wishlist-api-rest

<p>
    <a href="https://github.com/axsilva1991/wishlist/actions">
        <img alt="Build" src="https://github.com/axsilva1991/wishlist/actions/workflows/build.yml/badge.svg" />
    </a>
    <a href="https://codecov.io/github/axsilva1991/wishlist" >
        <img src="https://codecov.io/github/axsilva1991/wishlist/graph/badge.svg?token=2wPBRvdK5P" alt="codecov"/>
    </a>
</p>

This is software responsible for managing wishlist of products for Axsilva MarketPlace customers.

# Api-Rest

* [Some Concepts off the Clean Architecture](https://www.amazon.com.br/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164)
* [Mongo DB](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-community-with-docker/)
* [SpringBoot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Junit for Unit Tests](https://junit.org/junit5/docs/current/user-guide/)
* [Open API 3.0 specification](https://swagger.io/specification/)

# Requirements for Run Application
* Docker
* Docker Compose
* RestAPI Client's for example: (Postman/Insomnia)
* Access Internet for gradlew dependency's download
* Communication port <b>8081</b> released on the computer

# Usage
## Run the app anyway

## Docker

### Run
```bash
$ docker-compose up --build -d
```

### Shut down

```bash
$ docker-compose down
```

After run this commands access [swagger](http://localhost:8081/api-docs/swagger-ui/index.html) to validate application it's ok.


## Test

This project use [Karate Framework](https://github.com/karatelabs/karate) to integration tests and excecute scenarios you need to start application and run commands start a integration tests image.

>**Atention** <br>Because it is a integration test, it's work's if you run one time! <br>
>if you need to re-execute it, it is recommended to clean the database and restart the application.
> 
### Run
```bash

$ cd ./integration-test
$ docker-compose up --build

```
After finishing the tests, this image will be interrupted.

## Documentation

This project Using OpenAPI 3.0 specification if you whant to see a doc open [swagger-ui](http://localhost:8081/api-docs/swagger-ui/index.html) in your Browser

## Continuous integration

This application used GitHub actions to inspect and improve code. Your steps are:
* Build Gradle (Build application and run unit tests)
* [Upload code to Codecov](https://codecov.io/github/axsilva1991/wishlist)
* Build Docker Machine
* Dependency Check Vulnerability's of dependencies.