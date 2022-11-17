# Car rental system - Exercise

For a car rental system, we want to create a piece of software for managing the rental administration with three primaries functions:
- Have an inventory of cars
- Calculate the price for rental
- Keep the track of the customer loyalty points

## First thoughts

I think for our API we should have more or less this operations:
- Get, Add, modigy and delete a car
- Get, simulate, create, update, finish or remove a rental
- Get, create, edit or delete customers

Firstly operations about cars, this are needed if we want to manage it from the API but obviusly this part could be managed directly from DB although it is more interesting to manage it using the API.

Secondly, the core of the system that are the rentals, we should need to create it, but maybe we could be asked for how much could cost some rent without execute it. That is why I think it is interesting to have a simulate operation. And then we have a finish operation to establish the day that the car is delivered. The simulate service could also calculate what happens at the finish of a rental related to the surcharges and loyalty points.

Finally, the operations related to the customers, that are needed to keep the track of the customer loyalty points, although we could achieve the same getting the information from the rentals directly, but I think in real life when you are doing a rent, some relevant information needed to rent a car that could be reused in your next rental.

## Modeling thoughts

We should need a car, a rental and a customer. I think we could have more than one car with the same information, so we could divide cars in related information and concrete information.
Then about rental, it is a relation of a car, and a customer, we could know if a car is rented looking that a rental have not a finished date.
Finally, the customer it is only is information that could be reused like is ID, and we could know his loyalty points doing a sum of all his obtained points at the rentals.

![model](https://github.com/ecn-code/car-rental-system-demo/blob/main/schema.png)

## API
I started defined a yaml to have an idea of the api of the system but it is not updated
[api](https://github.com/ecn-code/car-rental-system-demo/blob/main/api.yaml)

![rentals](https://raw.githubusercontent.com/ecn-code/car-rental-system-demo/main/rentals_api.png)
#
![cars](https://raw.githubusercontent.com/ecn-code/car-rental-system-demo/main/cars_api.png)
#
![customers](https://raw.githubusercontent.com/ecn-code/car-rental-system-demo/main/customers_api.png)

## Technologies
Application developed using Spring boot 2.6.7 with Java 11, testing features with test containers 
and using to save data a H2 database that keeps data while it is running and it is filled at start by the file data.sql

## How to run

Requirements to run are Java 11 and Maven, then it should run doing `mvn spring-boot:run`.
Then you can test it from http://localhost:8080/swagger-ui/index.html#/rentals/

## Implemented

- GET /rentals - to obtain all the rentals saved
- POST /rentals/simulate - where we can send an array of rents that are not saved and return the rentals with the price
  - Example: [{
      "carRegistrationNumber": "1111PPP",
      "customerIdNumber": "55555555Z",
      "rentedFrom": "2022-06-14",
      "rentedTo": "2022-06-24"
    }]
- POST /rentals - same as simulate but it save rents
- POST /rentals/{id}/finish - we can finish the rent, sending a date in the body and we will save the rent with loyaltyPoints and surcharges
- /customers/{id} - To retrieve the customer and see his loyaltyPoints

## TODO

- Define a security system with users in order to do operations only for people allowed
- Define a role system to not allow all operations to all employs
- Define a standard response for errors
- Stablish only the yaml as the API documentation point, in order to have only one source that can be used also for other purposes
- Validate the inputs and probably have more concrete objects or mappings to respond depending on what is needed