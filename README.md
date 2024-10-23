# simple-quarkus-app

## Requirements

- [jdk21](https://adoptium.net/)
- [maven](https://maven.apache.org/)
- [container management](https://rancherdesktop.io/)

## Build

```shell
mvn clean install
```

## Start

```shell
docker compose up
```

## Stop

```shell
docker compose down
```

## Services

- [customer-service](./customer-service/README.md)
- [reservation-service](./reservation-service/README.md)
- [room-reservation-service](./room-reservation-service/README.md)
- [room-service](./room-service/README.md)

## Useful links

- [The twelve-factor app](https://12factor.net/)
- [layered architecture](https://www.sciencedirect.com/topics/computer-science/layered-architecture)
- [REST API Tutorial](https://restfulapi.net/)
- [Open API](https://www.openapis.org/)
- [Open API Generator](https://openapi-generator.tech/)
- [Quarkus](https://quarkus.io/)
- [Testcontainers](https://testcontainers.com/)
- [Rancher Desktop](https://rancherdesktop.io/)
- [Mermaid](https://mermaid.js.org/)
