# ms-sql

[Configure and customize SQL Server Linux containers](https://learn.microsoft.com/en-us/sql/linux/sql-server-linux-docker-container-configure?view=sql-server-ver16&pivots=cs1-bash)

```shell
docker build -t simple-quarkus-app/mssql:latest .
```

or

```shell
./build.sh
```

## sql script

```
    volumes:
      - ./setup.sql:/usr/src/app/setup.sql:ro
```
