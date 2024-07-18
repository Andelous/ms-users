# Microservice: USERS

Microservice for users management and authentication. There are 3 components under this microservice project:

* __[Client](https://github.com/Andelous/ms-users/tree/main/ms-users-client)__ : REST client written in Java to interact with the REST microservice implementation.
* __[Implementation](https://github.com/Andelous/ms-users/tree/main/ms-users-implementation)__ : Fully fledged REST implementation of the microservice that runs in a Servlet container (Jakarta EE 10+).
* __[Definition](https://github.com/Andelous/ms-users/tree/main/ms-users-definition)__ : Defines the contract under which the microservice and client must interact. Both (client and implementation) implement the definition.

Please see the documentation under each component for a more detailed explanation.