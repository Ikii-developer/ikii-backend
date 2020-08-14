## ikii-back-business

## 1.6.0
* Removes security from business microservice in order to allow request between microservices
* Adds mapper methor to map between List<ObjectId> to List<String>

## 1.5.0
* Adds updateProperties helper method in order to update specific properties
* Adds customerId RequestParam to getAll business in order to retrieve whether it is favorite or not

## 1.4.0
* Adding BusinesRate
* Adding Rate
* Adding Repository, Service, Wrapper and Controller 

## 1.3.0
* Adds overviewTitle, sectionTitle to BusinessCategory Model

## 1.2.1
* Adds Batch to create business
* Adds BusinessStatus as enum
* Adds deliveryTime, closeTime, isOpen and status to business Model
* Sets ACTIVE when the business is created
* Updates business dump with additional values

## 1.2.0
* adds customerId in businessRequest and validates whether the customer and the category exist and creates the business
* changes Categories end-point to categories 
* modify BusinessCategory.getById to throw correct resource (BusinessCategory) if not found
* adds jsonInclude NOT_NULL annotation to BusinessResponse

## 1.1.0
* add customerId attribute in model business
* add BusinessCategory crud
* renaming of misnamed attributes
* fix path endpoint getByBusinesName
* fix update business
* fix update business category

## 1.0.0
* Base ikii proyect