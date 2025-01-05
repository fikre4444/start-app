# start-app
### A Daily Reminder App
##### This is a simple daily reminder app, that starts up when your computer is turned on. Its intended use is that when you turn on your pc it opens and reminds you of the things that you want to be reminded of. These can be things that you want to be reminded of daily or just for that specific day. Which means it allows you add reminders and edit and delete them. This is made in Springboot. And it was made for a postgres database.

### How to Run
##### 1. Since this was made in Springboot, you need to have java (version 17), maven and postgres installed.
##### 2. After installing these things, you need to create a database in the postgres db called start_app. 
##### 3. After this creating this database, if all the settings in your postgres db are default (the => username=postgres and password=admin), then all you need to do is run it.
##### 4. To run this app, go to the root folder of this app (the folder where the pom.xml file is located), and then open cmd in that folder and type the following command. 
  ###### mvn spring-boot:run
##### 5. To view the app, open a web browser and type localhost:8080 and you will see the app. And you can create, edit and delete and view messages there.

#### Note: 
###### Now this is the way to just run it manually, in order for the app to start at startup(when the pc turns on), you need to copy the startapp.bat file to the startup folder which you can get by opening the Run dialog box - (press windows key + R). After that starts type shell:startup and the startup folder will open. After that copy the startapp.bat file there, and you are done. the startapp.bat file is found in the root folder of this app.

#### Second Note:
###### The customizations related to the database and password is found in the application.properties file which you can find by searching for it because i'm to lazy to type it here.
Bye.

