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
- _Port: 8080_
- _Host: localhost_

**2. To run BookLibraryService**
```
cd ./book-library-service

./run.sh build
```
- _Port: 8010_
- _Host: localhost_

**3. To run BookLibraryFrontend**
```
cd ./book-library-frontend

npm install

npm start
```
- _Port: 4001_
- _Host: localhost_

## Build with
* [**Gradle**](https://gradle.org/) - Dependency Management
* [**Webpack**](https://webpack.js.org/) - Module Bundler

## Author
* **Trong Manh Tu**
