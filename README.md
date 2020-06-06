
# IKII PROJECT

This project is meant for ikii application
- Author: gstartdevelop@gmail.com


## OVERVIEW

The project was created using the Microservices architectural pattern around the business functionalities  with Spring boot, Spring cloud and docker for developmnet.The lifecycle of the project is managed by maven and a mongodb NoSql database is used to persist the data.

The project was structures using spring cloud guidelines the following way

There are 3 infrastructure microservices

- Gateway-zuul (Spring-cloud provider)
- Discovery-server (Spring-cloud provider)
- Config server

Import Postman Collection link

- https://www.getpostman.com/collections/646c091ad926a0af7edc

### SET UP IN DEVELOPMENT (IMPORTANT-NEEDED TO SET UP IN DEVELOPMENT)

>cd ikii-back-main/
 
 and execute 

>cd ikii-back-commons/ && mvn clean install && cd ../ikii-back-microservices/ && mvn clean install -DskipTests -P development && docker-compose up --build

Note: I used two maven profiles one for production and another one for development because of the dependencies needed for each one of the environments.
Note: Once the environment is up and running you should WAIT for about 3 minutes(It depends on the hardware)so the ZUUL-GATEWAY can register each one of the microservices successfully.Sometimes If ZUUL is not given enough time it keeps throwing an exception till it registers the microservices.Once you have waited the 3 minutes that ZUUL needs to work, you can start using the REST web services and start testing them with POSTMAN or with a Web client

### APPLICATION PROPERTIES ###

CONFIG-SERVER-PROPERTIES:https://gitlab.com/arturovelazquez94/arturo-velazquez-addition-properties.git

### ENVIRONMENT VARIABLES (IMPORTANT)

If you enter the 'ikii-back microservices' there is a .env that contains certain variables that we used to configure the environment and to speed up the development process in a dynamic way.If you want to access and take a look at it just type the next command

>nano ikii-back-microservices/.env

### POSTMAN collection (IMPORTANT)

Postmant variables

- host_gateway=localhost
- port_gateway=7001

You need to add them to the environments 

Note: All the endpoints are configures to the environment variables so they are dynamic and not static.

### MONGODB (DEVELOPMENT)

HOST:localhost
PORT:27020
DATABASE:ikiidb

Establish conection with the mongodb server


## License
[MIT](https://choosealicense.com/licenses/mit/)

test
