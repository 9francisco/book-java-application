# API Development Assignment

## Introduction

This is a short coding assignment, in which you will implement a REST API that calls an external API service to get information about books. Additionally, you will implement a simple CRUD (Create, Read, Update, Delete) API with a local database of your choice.

The external API that will be used here is the Ice And Fire API. This API requires no sign up / authentication on your part. 

## Requirements

Prerequisites: Any IDE(e.g Eclipse), Gradle , GIT, Java 8, MySQL 5.6+

## Steps

1. Clone the application

	git clone https://github.com/9francisco/book-java-application.git

2. Change MySQL username and password as per your installation**

	Application require MySQL database to store its data. Database  "book_db" will be automatically 	created if not exist. Update application.properties with spring.datasource.URL, 	spring.datasource.username, and spring.datasource.password

3. Build and run the app using gradle

	./gradlew build && java -jar build/libs/book-java-application-0.1.0.jar
	
	or ./gradle bootRun
	
	The app will start running at <http://localhost:8080>.
