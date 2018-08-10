# Book Library
BookLibraryService and BookLibraryFrontend.

## Getting Started
- [**Java 8**](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [**npm**](https://www.npmjs.com/get-npm)
- [**docker**](https://docs.docker.com/install/)
- [**docker-compose**](https://docs.docker.com/compose/install/)

## Installing (still development)
**1. To run PostgreSQL**
```
cd ./book-library-service/deployment
docker-compose up -d
```
Database management by **Adminer**:
- Port: 8080
- Host: localhost

**2. To run BookLibraryService**
```
cd ./book-library-service
./run.sh build
```
- Port: 8010
- Host: localhost

**3. To run BookLibraryFrontend**
```
cd ./book-library-frontend

npm install

npm start
```
- Port: 4001
- Host: localhost

## Build with
* [**Gradle**](https://gradle.org/) - Dependency Management
* [**Webpack**](https://webpack.js.org/) - Module Bundler

## Author
* **Trong Manh Tu**
