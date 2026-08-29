# play-test01

A small Java form and Ebean CRUD example for Play Framework 3.

## Requirements

- JDK 17 or newer
- sbt 1.13

## Checks

```sh
sbt test
sbt stage
```

The application uses an in-memory H2 database. Set a strong `play.http.secret.key` through runtime configuration before starting a production build.
