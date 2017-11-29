# tumler-archive-images-fetch
Includes code to fetch archive images from first 50 posts of any blog on Tumblr

I was learning Spring Boot few days back since it creates a standalone application & to practice something, i created this simple project which fetches photos from first 50 posts of any blog present on tumblr. 

Whole project is in Spring Boot & Maven. Download & run 
1> TumblrApplication.java via IDE as Java Application or through 
2> command line as -> java -jar Tumblr-0.0.1-SNAPSHOT.jar

Port is configured to 9001 so the url to access will be http://localhost:9001/tumblr/photos/{blog-name}

Also, make sure to create your test application on Tumblr to get the api-key which you will need to put in BlogPhotosController.java

Additions are welcome!
