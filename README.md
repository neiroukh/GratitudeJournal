# GratitudeJournal

WIP!

A web service to keep track of your well being and the things you are grateful for, written in Java using the Spring Framework.

## Dependencies

The following dependencies are necessary:

- Spring Web
- Spring HATEOAS
- Spring Data JPA
- MySQL Driver

The following dependencies are only used for fast deployment of an MySQL Server via Docker Compose:

- Docker Compose Support
- Testcontainers

For production it is recommended to setup the MySQL Server externally and set up `application.properties` accordingly.
