# payconiq-restassure

Java API Testing framework built with JUnit and  and REST-Assured

This project contains automated tests for testing the _booker_ service. The tests are written using Java and executed using Maven with `mvn test`.

## Prerequisites

To run this project, you need the following prerequisites:

- Java Development Kit (JDK) version 17 or later (OpenJDK 17 recommended).
- Apache Maven (version [insert version here]).

## How to Run the Tests

1. Clone the project to your local machine.
2. Navigate to the project directory.
3. Run the tests using Maven. (Or your preferred IDE)

```bash
mvn test
```

## Project Structure

```plain
├── LICENSE
├── README.md
├── pom.xml
└── src
    ├── main
    │   └── java
    │       ├── helpers // this folder contain classes with utilities and other misc functionalities
    │           ├── DataHelper.java
    │       │   └── RequestHelper.java
    │       ├── model // POJO models utilized to encode and decode the Json objects
    │       │   ├── AuthUser.java
    │       │   ├── Booking.java
    │       │   └── PartialBooking.java
    │       └── specifications // provides prebuilt Request specifications that can be reused among tests such as custom headers
    └── test // only test functions will be allocated here
        └── java
            └── booking // tests related to the booking resource
                ├── BaseTest.java // handles the setup and provides functions as test pre-requisites
                ├── Constants.java // constants for this resource
                ├── CreateTest.java
                ├── DeleteTest.java
                ├── GetAllTest.java
                ├── GetByIDTest.java
                └── PartialUpdateTest.java
```

## Notes

- The tests are designed to be clean and independent, minimizing dependencies between them.
- The test scenarios cover basic functionality, but there is potential for improvement by adding more complex data payloads and edge cases.
- Some functions include comments to explain the decision-making process behind the framework used.
- Certain tests were disabled due to issues with the scenarios they test, or they might not fully comply with RESTful API standards. These tests are excluded to maintain the integrity of the test suite.
