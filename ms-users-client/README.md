# Microservice: USERS - Client

This project contains the client for the REST implementation of the users microservice.

The main class of this client is `com.example.ms.users.MSUsersClient`. It has two constructors. The first constructor doesn't take any arguments, and takes the default connection values (URI, user and password) from the JVM system properties. The second constructor will ask for arguments. Both constructors are shown below:

``` java
/*

The following client instance will search the JVM system properties for the default connection values (URI, user and password). The JVM system properties are:

-Dmsusers.client.uri=...
-Dmsusers.client.username=...
-Dmsusers.client.password=...

*/
MSUsersClient client01 = new MSUsersClient();


/*

The following client is given the connection values at instantiation time.

*/
MSUsersClient client02 = new MSUsersClient("http://localhost:8080/ms-users-implementation", "user", "pass");
```

Internally, the client uses Jersey as REST client, and SLF4J as logging interface.