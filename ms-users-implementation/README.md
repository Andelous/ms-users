# Microservice: USERS - Implementation
This is the REST implementation of the users microservice.

Prior to running the microservice, you need to complete the next steps:
1. [Have your own DB!](#db-configuration)
2. [Provide the JDBC URL and Driver to the app](#jdbc-url-and-driver)
3. [Configure a user in the Servlet Container](#configure-user)
4. Run the microservice `:)`

### DB Configuration
The file `/src/main/resources/db.sql` specifies the database structure that this microservice requires. It also inserts some default data.

Specifically in this implementation, the database used is Azure SQL (or SQL Server, but in Azure `XD`). __However__, that can be easily changed if you want to use a different database. You would only have to change the JDBC connector specified in the `pom.xml`.

### JDBC URL and Driver
Now that you have created your DB, and the previous SQL file has been executed in it, you need to provide the JDBC URL and Driver name to the microservice. Simply create a properties file named `ms-users.properties` and put it in the classpath, with the following contents:

```
msusers.jdbc.url=...
msusers.jdbc.driver=...
```

Of course, you'd have to change the URL and Driver according to the database you're using.

### Configure user
Basically, you need to configure a user in your Servlet container, in order to provide the authentication needed by the microservice.

For example, in Tomcat, you can add the following lines in the `tomcat-users.xml` file:

``` xml
	<role rolename="ms-users-admin"/>
  	<user username="user" password="pass" roles="ms-users-admin"/>
```

Please note that the role `ms-users-admin` is the one required in the `web.xml` of the microservice. So, that's why we are specifying it. Now, in order to use the microservice, you'll need to always provide the username and password through the BASIC authentication header.

---

After all the previous steps have been completed, you should be able to use the microservice `:)`.
