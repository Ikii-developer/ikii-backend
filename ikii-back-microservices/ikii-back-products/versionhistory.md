## ikii-back-products

## 1.7.0
* add generic module to upload image

## 1.6.2
* fix getBusinessProductsFilter(/ikii/products/filter) "keyword" null

## 1.6.1
* add parentProductCategoryId to identify the category parent, it will be replaced by productSubcategory in a refactor  with its service ProductSubCategory
* add endpoint to retrieve productCategories by parentCategory

## 1.6.0
* Group product by businessId in "filter product"
* Search product by keyword or find all products that match with keyword business name

## 1.5.0
* adds Required condition for businessId in the filter
* adds favorite attribute to ProductsResponse
* adds customerId as RequestParam to retrieve if a product is favorite or not
* adds setFavorites method to populate the product response for a particular user

## 1.4.0
* adds bulk method to products and changes productCategory to productCategoryId

## 1.3.0
* add services for Product and ProductBusiness

## 1.2.0
* adds endpoint to retrieve productCategories by businessId
* adds endpoint to create productCategories in a bulk way

## 1.1.0

* add productCategory in product model
* add measureUnit in product model
* add bussinessId in product model
* renaming of misnamed attributes

## 1.0.0
* Base ikii proyect


