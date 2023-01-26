# ProductList
This is a learning project which deals with connecting to RDBMs storage and provide REST operations. 

Project needs JDK17
### Building the service:
```mvnw package```

### Creating docker image:
```docker build -t productlist:0.0.1 .```
### (Optional) Creating database docker image
```docker build -f Dockerfile.DB -t product-list-db:0.0.1 .```

### Running the service locally
Make sure to provide actual DB connection parameters:

```docker run -p 8080:8080 -e DB_URL=jdbc:postgresql://localhost:5432/postgres -e DB_USERNAME=postgres -e  DB_PASSWORD=db-password -t product-list:0.0.1```

### Service assembly via docker-compose.yml:
```docker compose build```

### Running the service together with postgresql db container via docker-compose.yml:
```docker compose up```

### Print the list of containers
```docker compose ps```

### Display a list of images
```docker compose images```

