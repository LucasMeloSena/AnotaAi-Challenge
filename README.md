# AnotaAi Backend Challenge

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

This project is an API built using **Java, Java Spring, AWS Simple Queue Service, Mongo DB and AWS Simple Storage Service.**

The application was developed to demonstrate how to solve the [AnotaAi Backend Challenge](https://github.com/githubanotaai/new-test-backend-nodejs).

## API Endpoints
The API provides the following endpoints:

**API PRODUCT**
```markdown
POST /api/product - Create a new product
GET /api/product - Retrieve all products
PUT /api/product/{id} - Updates a product
DELETE /api/product/{id} - Delete a product
```

**BODY**
```json
{
  "title": "Produto para postar no tópico",
  "description": "",
  "ownerId": "4444444",
  "categoryId": "659d558b0304df732ddd4587",
  "price": 10000
}
```

**API CATEGORY**
```markdown
POST /api/category - Create a new category
GET /api/category - Retrieve all categories
PUT /api/category/{id} - Updates a category
DELETE /api/category/{id} - Delete a category
```

**BODY**
```json
{
  "id": "393948882828",
  "title": "Teste",
  "description": "",
  "ownerId": "4444444"
}
```
