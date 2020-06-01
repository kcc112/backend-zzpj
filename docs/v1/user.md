# User

## Show user

Fetch a specific user

### HTTP Request

`GET /api/v1/users/{id}`

### Parameters

Path params:

Parameter | Type   | Required? | Description
----------|:------:| :-------: | :---------:
id        | string | true      | Id of the user (in Long format)

### Responses

#### 200 OK

#### 404 Not Found

Response body:

```
{ }
```

## Users index

Fetch list of users

### HTTP Request

`GET /api/v1/users`

### Responses

#### 200 OK

Response body:

```
[
    {
        "id": 1,
        "login": "Szymon",
        "password": "kfshdkfsdjgbsjbgjb5r43y52tr673476",
        "type": "ADMIN"
    }
]
```

## Update user

Update user record

### HTTP Request

`PUT /api/v1/users/{id}`

### Parameters

JSON parameters:

Parameter       | Type    | Required? | Description
----------------|:-------:| :-------: | :---------:
id              | string  | true      | Id of the user (in Long format)
login           | string  | false     | User login
password        | string  | false     | User password
type            | string  | false     | User type

### Responses

#### 200 OK

#### 404 Not Found

Response body:

```
{ }
```


## Delete user

Delete user record

### HTTP Request

`DELETE /api/v1/users/{id}`

### Parameters

Path parameters:

Parameter       | Type    | Required? | Description
----------------|:-------:| :-------: | :---------:
id              | string  | true      | Id of the user (in Long format)


### Responses

#### 200 OK

#### 404 Not Found

Response body:

```
{ }
```

