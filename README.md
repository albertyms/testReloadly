# Reloadly Challenge


### Stack used
- Java 8
- PosgreSQL 
- Apache Maven 3.5
- Spring Boot 2.6.4
- Docker
- Docker Compose

### Instructions for installation with docker-compose

+ **With Docker:**
    + Compile each component with maven using the command: mvn clean install -DskipTests
    + Run the command: docker-compose up --build (Docker must be installed).
    

### Uso
Once the application is up and running, the API endpoints can be tested locally using a client
REST [Postman](https://www.getpostman.com/) or from the console if you have cURL installed.

+ **Note:** For all endpoints except singup and singin it is necessary to implement the JWT token in the execution of the endpoints.

API Auth
-----
#### SingUp
```
POST localhost:8081/api/auth/process/signup
{
    "identificationNumber": "1234567",
    "holderName": "Albert",
    "holderLastName": "Medina",
    "holderSurName": "Sanchez",
    "address": "calle 33",
    "username": "usermod",
    "email": "usermod@email.com",
    "password": "123456789",
    "role": ["mod", "user"]
}
```
```
Response: 200 - OK
{
    "message": "User registered successfully!"
}
```
#### SingIn
```
POST localhost:8081/api/auth/process/signin
{
    "username": "usermod",
    "password": "123456789"
}
```
```
Response: 200 - OK
{
    "id": 1,
    "username": "usermod",
    "email": "usermod@email.com",
    "roles": [
        "ROLE_USER",
        "ROLE_MODERATOR"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybW9kIiwiaWF0IjoxNjUxMTcwOTU0LCJleHAiOjE2NTEyNTczNTR9.39A1xqBqH0pKj3yrODLWDZ_wyUPWZOq_P6tVi7JlmHPR6Ir-d5JqAueweHAUIGrQh8yC29l7cnCBu8oACZN6Lg",
    "tokenType": "Bearer"
}
```
#### Edit Profile
```
PUT localhost:8081/api/auth/edit/1
{
    "holderName": "Albert Yoel",
    "holderLastName": "Medina",
    "holderSurName": "Sanchez",
    "address": "calle 33333"
}
```
```
Response: 200 - OK
{
    "id": 1,
    "username": "usermod",
    "email": "usermod@email.com",
    "password": "$2a$10$8tRf9sNlYgbKzrqliWKBde5BJ9hS478ZvizuIaHa8.hLKGk1dZ.xq",
    "roles": [
        {
            "id": 2,
            "name": "ROLE_MODERATOR"
        },
        {
            "id": 3,
            "name": "ROLE_USER"
        }
    ],
    "identificationNumber": 1234567,
    "holderName": "Albert Yoel",
    "holderLastName": "Medina",
    "holderSurName": "Sanchez",
    "address": "calle 33333"
}
```
#### Get Account for ID
```
GET localhost:8081/api/account/1
```
```
Response: 200 - OK
{
    "id": 1,
    "numberAccount": 1390410632,
    "amount": 2400.0,
    "user": {
        "id": 1,
        "username": "usermod",
        "email": "usermod@email.com",
        "password": "$2a$10$8tRf9sNlYgbKzrqliWKBde5BJ9hS478ZvizuIaHa8.hLKGk1dZ.xq",
        "roles": [
            {
                "id": 2,
                "name": "ROLE_MODERATOR"
            },
            {
                "id": 3,
                "name": "ROLE_USER"
            }
        ],
        "identificationNumber": 1234567,
        "holderName": "Albert Yoel",
        "holderLastName": "Medina",
        "holderSurName": "Sanchez",
        "address": "calle 33333"
    },
    "typeAccount": "SAVING",
    "creationDate": "2022-04-28T18:36:07.451+00:00"
}
```
#### Get All for Username
```
GET localhost:8081/api/account?userName=usermod
```
```
Response: 200 - OK
[
    {
        "id": 1,
        "numberAccount": 1390410632,
        "amount": 2400.0,
        "user": {
            "id": 1,
            "username": "usermod",
            "email": "usermod@email.com",
            "password": "$2a$10$8tRf9sNlYgbKzrqliWKBde5BJ9hS478ZvizuIaHa8.hLKGk1dZ.xq",
            "roles": [
                {
                    "id": 2,
                    "name": "ROLE_MODERATOR"
                },
                {
                    "id": 3,
                    "name": "ROLE_USER"
                }
            ],
            "identificationNumber": 1234567,
            "holderName": "Albert Yoel",
            "holderLastName": "Medina",
            "holderSurName": "Sanchez",
            "address": "calle 33333"
        },
        "typeAccount": "SAVING",
        "creationDate": "2022-04-28T18:36:07.451+00:00"
    }
]
```
API Transactions
-----
#### Create transactions
```
POST localhost:8083/api/transaction
{
    "accountId": 1,
    "amount": 100.00,
    "typeTransaction": "WITHDRAWAL"
}
```
```
Response: 200 - OK
{
    "id": 39,
    "account": {
        "id": 1,
        "numberAccount": 1390410632,
        "amount": 2300.0,
        "user": {
            "id": 1,
            "username": "usermod",
            "email": "usermod@email.com",
            "password": "$2a$10$8tRf9sNlYgbKzrqliWKBde5BJ9hS478ZvizuIaHa8.hLKGk1dZ.xq",
            "roles": [
                {
                    "id": 2,
                    "name": "ROLE_MODERATOR"
                },
                {
                    "id": 3,
                    "name": "ROLE_USER"
                }
            ],
            "identificationNumber": 1234567,
            "holderName": "Albert Yoel",
            "holderLastName": "Medina",
            "holderSurName": "Sanchez",
            "address": "calle 33333"
        },
        "typeAccount": "SAVING",
        "creationDate": "2022-04-28T18:36:07.451+00:00"
    },
    "amount": 100.0,
    "typeTransaction": "WITHDRAWAL",
    "transactionDate": "2022-04-29T14:15:48.768+00:00"
}
```




