# Microservice: USERS

Microservice for users management and authentication. There are 3 components under this microservice project:

* __[Client](https://github.com/Andelous/ms-users/tree/main/ms-users-client):__ Client written in Java to interact with the REST implementation of the microservice. Implemented with the Jakarta RESTful Web Services 3.1 spec.
* __[Implementation](https://github.com/Andelous/ms-users/tree/main/ms-users-implementation):__ Fully fledged REST implementation of the microservice. Implemented with Jakarta Servlet 6.0 and Jakarta RESTful Web Services 3.1 specs.
* __[Definition](https://github.com/Andelous/ms-users/tree/main/ms-users-definition):__ Defines the functionality that the microservice must provide. It can also be seen as the contract under which the microservice and clients must interact. Both of the previous components (client and implementation) implement the definition.

Please see the documentation under each component for a more detailed explanation.