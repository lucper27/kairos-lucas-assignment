# Price Finder Application

A Spring Boot application that provides an endpoint to query product prices based on brand, product ID, and application date.

## Features

- REST API endpoint to find applicable product prices
- In-memory H2 database with sample data
- Hexagonal architecture implementation
- Comprehensive test coverage
- MapStruct for DTO mapping
- H2 console available for database inspection

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- MapStruct
- JUnit (for testing)

## Database Configuration

- URL: `jdbc:h2:mem:kairosdb`
- Username: `sa`
- Password: (none)
- H2 Console: http://localhost:8080/h2-console

## API Endpoint

`GET /api/prices`

**Parameters:**
- `date` - DateTime in ISO Instant format (e.g., `"2020-06-14T10:00:00Z"`)
- `productId` - Product identifier (e.g., 35455)
- `brandId` - Brand identifier (e.g., 1)

**Response:**
```json
{
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
}
```

## Running the application

1- Clone the repository

2- Build with Maven: mvn clean install

3- Run the application: mvn spring-boot:run

## Testing

Integration testing for PriceResource and unit testing for PriceService includes the following:

- Request at 10:00 on day 14 for product 35455 and brand 1 (`2020-06-14T10:00:00Z`)  
- Request at 16:00 on day 14 for product 35455 and brand 1 (`2020-06-14T16:00:00Z`)  
- Request at 21:00 on day 14 for product 35455 and brand 1 (`2020-06-14T21:00:00Z`)  
- Request at 10:00 on day 15 for product 35455 and brand 1 (`2020-06-15T10:00:00Z`)  
- Request at 21:00 on day 16 for product 35455 and brand 1 (`2020-06-16T21:00:00Z`)

Additionally, the following error-handling scenarios are covered in the tests:

- Throws `BadRequestException` when `date` is null in the service layer
- Throws `BadRequestException` when `productId` is null in the service layer
- Throws `BadRequestException` when `brandId` is null in the service layer
- Returns HTTP 400 Bad Request when `date` parameter is missing in the endpoint
- Returns HTTP 400 Bad Request when `productId` parameter is missing in the endpoint
- Returns HTTP 400 Bad Request when `brandId` parameter is missing in the endpoint
- Returns HTTP 404 Not Found when no price is found for the given parameters