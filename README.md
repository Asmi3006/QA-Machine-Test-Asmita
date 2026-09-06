# Field Force Connect - QA Automation

## Automation
- Selenium WebDriver
- Java
- TestNG
- Maven

## API Request & Setup

### Environment
Postman environment: FieldForce Test

Variables:
- baseUrl
- username
- password

### Login API
Method: POST
Endpoint: {{baseUrl}}/api/account/authenticate

### Stats API
Method: GET
Endpoint: {{baseUrl}}/api/CRM/Stats

### Add Customer API
Method: POST
Endpoint: {{baseUrl}}/api/CRM/Lead

### Validation
- Valid Login → Success
- Invalid Login → Login failure response
- Stats → Successful response
- Add Customer → Customer created successfully

Postman collection and environment are included in the repository.
