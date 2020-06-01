# Alcohols

## Show alcohol

Fetch a specific alcohol

### HTTP Request

`GET /api/v1/alcohols/{id}`

### Parameters

Path params:

Parameter | Type   | Required? | Description
----------|:------:| :-------: | :---------:
id        | string | true      | Id of the alcohol (in Long format)

### Responses

#### 200 OK

Response body:



#### 404 Not Found

```
{ }
```

## Alcohols index

Fetch list of alcohols

### HTTP Request

`GET /api/v1/alcohols`

### Parameters

JSON parameters:

Parameter                | Type    | Required? | Description
-----------              |:------: | :-------: | :---------:
-----------              | ------  |  -------  |  ---------

### Responses

#### 200 OK

Response body:

```
[
    {
        "id": 1,
        "name": "Tatra",
        "cost": 2.0,
        "amount": 10
    },
    {
        "id": 2,
        "name": "Tatraa",
        "cost": 2.0,
        "amount": 10
    }
]
```

## Update person record

Update a alcohol record

### HTTP Request

`PUT /api/v1/alcohols/{id}`

### Parameters

JSON parameters:

Parameter       | Type    | Required? | Description
----------------|:-------:| :-------: | :---------:
id              | string  | true      | Id of the alcohol (in Long format)
name            | string  | false     | Alcohol name
cost            | double  | false     | Alcohol cost
amount          | int     | false     | Alcohol amount

