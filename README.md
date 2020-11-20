# ejercicio-usuarios

Desacarlar el api
importar el proyecto al IDE
maven install
run 

Ejecutar los siguientes por servicios:

POST http://localhost:8082/authenticate

BODY
{
    "username": "username",
    "password": "password"
}



GET http://localhost:8082/usuarios
Auth Bearer Token


DELETE http://localhost:8082/usuarios/2
Auth Bearer Token


GET http://localhost:8082/usuarios/1
Auth Bearer Token


GET http://localhost:8082/usuarios/1
Auth Bearer Token


POST http://localhost:8082/usuarios
Auth Bearer Token
{
    "name": "Doris Garcia",
    "email": "createdoris@doris.org",
    "username": "createdorisga",
    "password": "Aholahola02",
    "phones": [
        {
            "number": "123456789",
            "citycode": "11",
            "contrycode": "1"
        }
    ]
}
 

PUT http://localhost:8082/usuarios
Auth Bearer Token
{
    "idUsuario": 5,
    "username": "user3",
    "name": "Doris Doris",
    "email": "email3@doris.com",
    "password": "12344566",
    "phones": [
        {
            "number": "333333333",
            "citycode": "33",
            "contrycode": "3"
        }
    ],
    "isactive": true
}


DELETE http://localhost:8082/usuarios/2
Auth Bearer Token

