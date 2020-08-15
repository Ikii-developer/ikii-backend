## ikii-back-customers

## 2.1.2
* Removes Serializable interface from request and adds the missing attributes to the DTO

## 2.1.1
* Adds businessFavorites to Response
* Adds id to CustomerDetails model

## 2.1.0
* Adds annotation to CustomerDetailsController related to rest calls (@PathVariable, @RequestBody)
* Adds method to toggle business in favorites
* Adds Helper to CustomerDetails
* Adds method to feing client method to retrieve CustomerDetails by customerId
* Creates CustomerDetails each time a Customer is created

## 2.0.5
* add customerId in customerAddress model and customerDetail model
* renaming of misnamed attributes
* fix update customer when the password is null

## 2.0.4
* Encrypt password when updating the customer

## 2.0.3
* Allow consumptoin without required token in order to allow change password 

## 2.0.2
* Resolves missing dependencies in customerDetailsService

## 2.0.1
* Removes NotNull contraint when the customer is created in order to allow creation when login is by facebook and
* there is no phone number assosciated

## 2.0.0
* Base ikii proyect



