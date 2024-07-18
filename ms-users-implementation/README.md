# Microservice: USERS - Implementation
This is the REST implementation of the users microservice.

Prior to running the microservice, you need to complete the next steps:
1. [Have your own DB!](#db-configuration)
2. [Provide the JDBC URL and Driver to the app](#jdbc-url-and-driver)
3. Run the microservice `:)`

### DB Configuration
The file `/src/main/resources/db.sql` specifies the database structure that this microservice requires. It also inserts some default data.

Specifically in this implementation, the database used is Azure SQL (or SQL Server, but in Azure `XD`). __However__, that can be easily changed if you want to use a different database. You would only have to change the JDBC connector specified in the `pom.xml`.

### JDBC URL and Driver
Now that you have created your DB, and the previous SQL file has been executed in it, you need to provide the JDBC URL and Driver name to the microservice. Simply pass the following JVM System properties:

```
-Dmsusers.jdbc.url=...
-Dmsusers.jdbc.driver=...
```

Of course, you'd have to change the URL and Driver according to the database you're using.