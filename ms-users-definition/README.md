# Microservice: USERS - Definition

This project defines the most essential components of either a client or an implementation of the users microservice.

There are 3 packages under this project, each with a specific purpose:

* `com.example.ms.users`: Contains the single most important interface: `MSUsers`, which defines the behavior of the microservice.
* `com.example.ms.users.def.ex`: Defines the exceptions produced by this microservice. They are all intended to take care of human error, not unexpected runtime problems.
* `com.example.ms.users.def.model`: Defines the business objects used in this microservice. Only `User` at the time of writing.