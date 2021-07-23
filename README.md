# Tuition Reimbursement Management System - README
### Christopher Mendoza

> The client was looking for a Web Application with which to track reimbursements for their employees, who are each given a $1,000 
stipend to use for Professional Development. The resulting application allows employees to login, submit requests for reimbursements, and 
check the status of their requests. It also allows management to approve or deny requests, as well as track the reimbursement payments.  

### Package Structure
**Models:** Java Beans that represent tables in the PostgreSQL Database  
**DAOs:** Data Access Objects used to query the Database  
**Services:** Any necessary business logic for doing work on the models (CRUD)  
**Servlets:** Application logic for handling HttpRequests and formatting HttpResponses  
**Utils:** JDBC Connection Class to configure credentials and make connections to the AWS RDS instance

### Features
- AJAX Calls
  - JavaScript is implemented for frent end design in order to send and receive asynchronous HTTP Requests and Responses  
- HTML Design
  - Bootstrap used for basic CSS  
  - JavaScript used for DOM Manipulation  
  
### Testing

**JUnit**  
Test cases for all services implemented to ensure proper database connection and retrieval of information.  

### Further Development  
Some appropriate imrpovements would be to implement a messaging service tied to employees, supervisors, and their development resources.  
Implementation of file uploading is not functional yet.    

### Required Dependencies
##### The below dependencies are required in the pom.xml file in order to ensure proper execution.
- JUnit
- PostgreSQL
- Log4J
- Javax Servlet
- Gson
