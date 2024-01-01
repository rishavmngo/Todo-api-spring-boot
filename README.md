## Todo rest api in spring boot

### Services

#### Auth

-   Authenticate using `spring security 6.2`
-   Used `Jwt` token for authentication

| Methods | Actions       | Urls           |
| ------- | ------------- | -------------- |
| POST    | Reigster user | /auth/register |
| POST    | Login user    | /auth/login    |

#### Todo

| Methods | Actions        | Urls               |
| ------- | -------------- | ------------------ |
| POST    | Create todo    | /todos/create      |
| GET     | Get all todos  | /todos/getAll      |
| GET     | Get todo by id | /todos/get/{id}    |
| PUT     | Full update    | /todos/update/{id} |
| PATCH   | Partial update | /todos/update/{id} |
| DELETE  | Delete todo    | /todos/delete/{id} |
