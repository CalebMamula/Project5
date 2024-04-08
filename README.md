# Catalog Service

This application is part of the Polar jobshop system and provides the functionality for managing
the jobs in the jobshop catalog. It's part of the project built in the
[Cloud Native Spring in Action](https://www.manning.com/jobs/cloud-native-spring-in-action) job
by [Thomas Vitale](https://www.thomasvitale.com).

## REST API

| Endpoint	      | Method   | Req. body  | Status | Resp. body     | Description    		   	     |
|:---------------:|:--------:|:----------:|:------:|:--------------:|:-------------------------------|
| `/jobs`        | `GET`    |            | 200    | Job[]         | Get all the jobs in the catalog. |
| `/jobs`        | `POST`   | Job       | 201    | Job           | Add a new job to the catalog. |
|                 |          |            | 422    |                | A job with the same JobID already exists. |
| `/jobs/{isbn}` | `GET`    |            | 200    | Job           | Get the job with the given JobID. |
|                 |          |            | 404    |                | No job with the given JobID exists. |
| `/jobs/{isbn}` | `PUT`    | Job       | 200    | Job           | Update the job with the given JobID. |
|                 |          |            | 200    | Job           | Create a job with the given JobID. |
| `/jobs/{isbn}` | `DELETE` |            | 204    |                | Delete the job with the given JobID. |

## Useful Commands

| Gradle Command	         | Description                                   |
|:---------------------------|:----------------------------------------------|
| `./gradlew bootRun`        | Run the application.                          |
| `./gradlew build`          | Build the application.                        |
| `./gradlew test`           | Run tests.                                    |
| `./gradlew bootJar`        | Package the application as a JAR.             |
| `./gradlew bootBuildImage` | Package the application as a container image. |

After building the application, you can also run it from the Java CLI:

```bash
java -jar build/libs/catalog-service-0.0.1-SNAPSHOT.jar
```
