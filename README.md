# REST com Spring Boot e Java
API REST desenvolvida para fins de estudo utilizando Spring Boot e Java.  
A API expõe operações CRUD para o recurso __Person__.

## Tecnologias
- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- MySQL
- MySQL Workbench
- Maven

## Endpoints - Person
### Create Person
POST /person
### Get All Persons
GET /person
### Get Person by Id
GET /person/{id}
### Update Person
PUT /person
### Delete Person
DELETE /person/{id}

## Executando o projeto
1. Crie um banco de dados no MySQL
2. Copie o arquivo `application.yml.example` para `application.yml`
3. Atualize as credenciais do banco de dados
4. Execute a aplicação

## Observações
- As credenciais do banco de dados não são versionadas no repositório.