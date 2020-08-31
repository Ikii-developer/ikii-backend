## ikii-back-customers

## 2.1.4
* changes the keywords queryparam to a @RequestBody
* adds JsonInclude non null in the near by me response
* formats the average to 1 decimal in the near by me response
* adds totalRates and status to the near by me DTO
* changes the near by be response attributes in order to homologate with Business response

## 2.1.3
* add request to find bussines "NearByMe" and optional filter by keyword 

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



