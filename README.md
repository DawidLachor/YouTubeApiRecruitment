# YouTube API Recruitment

The application downloads information about the companies sought from Youtube API and saves it to the database. When we ask for the same phrase within 24 hours, we will get the result from the database. 

## Technologies
* Java 11
* Spring Boot 2.4.8
* PostgreSQL
* Hibernate

## Start-Up

To run, we need: 
* Intellij Idea
* Java 11
* PostgreSQL
* Postman

We download the project from GitHub. We run it in Intellij idea. We create a database in PostegreSQL called "youtube_api". 
In the "application.properties" file, we change the login and password to the database to our own. We run applications.
We launch Postman. Enter url: http://localhost:8080/api/youtube/search?searchVideo=car.
Where "searchVideo" is the parameter by which we search for companies. 
