# Practice 2. Databases

## Usage

```sh
docker run -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=test -e -d mysql:latest
mvn spring-boot:run
```

## API Root URL

```sh
http://localhost:8080/api/v1/<resource>
```

## API Docs URL

```sh
http://localhost:8080/api-docs
http://localhost:8080/api-docs.yaml
```

## API Swagger URL

```sh
http://localhost:8080/swagger-ui.html
```

## Author

👤 **Álvaro Martín Martín**

* Github: [@amartinm82](https://github.com/amartinm82)

Note that you should have to [install JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), [Maven](https://maven.apache.org/install.html) and [Docker](https://docs.docker.com/engine/install/) as prerequisite.
