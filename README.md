# Hospital Management Platform

## Starting the application

From the command line, run: `./gradlew application:bootRun` from the project root. Alternatively, from IntelliJ you can open the Gradle sidebar and locate the bootRun task under 

## Accessing the application
http://localhost:9994

From the command line, run: `./gradlew application:bootRun` from the project root. Alternatively, from IntelliJ you can open the Gradle sidebar and locate the bootRun task under


application -> Tasks -> application

## The Database

When the application starts it spins up an in memory database called H2. Note that since it is an in memory database, every time you stop the application, the data in the database will be lost.

You can connect and query the db via the h2 tool in the browser once the application is running.

This tool will be found at: http://localhost:9992/h2-console

Upon arriving at the login page

Change the JDBC Url to `jdbc:h2:mem:myDb`

use the username and password `admin`

Click connect

Once logged in, you should be able to run SQL commands in the query box.

