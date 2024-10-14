FROM mcr.microsoft.com/mssql/server:2022-latest

USER root

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Bundle config source
COPY . /usr/src/app

RUN chmod +x /usr/src/app/entrypoint.sh
RUN chmod +x /usr/src/app/configure-db.sh

USER mssql

ENTRYPOINT ["./entrypoint.sh"]
