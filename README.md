## Todo rest api in spring boot

### Services

#### Auth

-   Authenticate using spring security 6.2
-   Used Jwt token for authentication

| Function      | Route          | Type | Return    |
| ------------- | -------------- | ---- | --------- |
| Reigster user | /auth/register | POST | JWT token |
| Login user    | /auth/login    | POST | JWT token |

#### Todo

| Function       | Route              | Type   | Return        |
| -------------- | ------------------ | ------ | ------------- |
| Create todo    | /todos/create      | POST   | todo object   |
| Get all todos  | /todos/getAll      | GET    | list of todos |
| Get todo by id | /todos/get/{id}    | GET    | todo object   |
| Full update    | /todos/update/{id} | PUT    | todo object   |
| Partial update | /todos/update/{id} | PATCH  | todo object   |
| Delete todo    | /todos/delete/{id} | DELETE | todo object   |
