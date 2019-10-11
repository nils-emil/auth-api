# Auth api

Required software

    * docker-compose
    * jdk 1.8

## Postgres
Setup Postgres database container via docker-compose:
```
 docker-compose up -d
```
When Postgres container is deleted then so is the data. To create persistent data use volumes in docker-compose file.

Example:

```
 version: '3'
 services:
   postgres:
     build: docker/db/
     volumes:
       - db-data:/var/lib/postgresql/data/
     environment:
       - POSTGRES_PASSWORD=postgres
     ports:
       - "5432:5432"
 
 volumes:
   db-data:

```

## Application
Run application:
```
 ./gradlew bootRun
```