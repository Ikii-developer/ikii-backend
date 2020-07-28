## ikii-back-business

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