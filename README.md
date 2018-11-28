# Book Library
Web application for managing book library
## Documentation
- entities are in entity-relationship model (`publisher`, `book-image`, `author`, `category`, `book`)
- `publisher`, `category`, `author`, `book` name is unique
- `book` does not require `book-image`
- `book` can contain many `authors` and `categories`
- database PostgresSQL

## Launching
- PostgreSQL
```bash
cd ./book-library-service/

docker-compose -f deployment/docker-compose.yml up -d
```
- book-library-service
```bash
cd ./book-library-service
./gradlew clean build
./run.sh
```

- book-library-frontend
```bash
cd ./book-library-frontend

npm install

npm start
```
