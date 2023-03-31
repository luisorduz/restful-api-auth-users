
# Proyecto API RESTful de creación de usuarios

Este proyecto implementa una API RESTful para la creación de usuarios con Spring Boot y persistencia en memoria con H2.

Este proyecto sigue la arquitectura hexagonal (también conocida como "puertos y adaptadores"), que es un enfoque de diseño de software que separa la lógica empresarial central de los detalles de implementación. En esta arquitectura, la lógica empresarial se encuentra en el centro (el núcleo), mientras que los detalles de la infraestructura, como la base de datos o la interfaz de usuario, se encuentran en los adaptadores. Los puertos son interfaces que definen la comunicación entre el núcleo y los adaptadores.

La arquitectura hexagonal permite una mayor modularidad y facilita la prueba y el mantenimiento del código. En este proyecto, se utilizó Spring Boot como framework para implementar la arquitectura hexagonal. La capa del núcleo contiene la lógica empresarial, mientras que las capas de los adaptadores contienen la implementación de la API RESTful y la persistencia de datos en la base de datos.

![Logo](https://miro.medium.com/v2/resize:fit:1400/1*yR4C1B-YfMh5zqpbHzTyag.png)


## Tecnologías utilizadas

**Java 11**

**SpringBoot** (web, jpa, security, test)

**Hibernate**

**H2** Database

**Maven**

**Junit** Mockito

**Swagger2**

**Mapstruct**

**Json web token**


## Instalación y ejecución

```bash
Clonar el repositorio: git clone https://github.com/usuario/repo.git 
Navegar al directorio del proyecto: cd proyecto
Compilar el proyecto: mvn clean install
Ejecutar el proyecto Opción 1: mvn spring-boot:run
Ejecutar el proyecto Opción 2: java -jar target/com-nisum-api-users-1.0


```

Verificar que la aplicación está funcionando correctamente accediendo a la siguiente URL en un navegador web:
http://localhost:8081/api/swagger-ui/#/
## Despliegue

Para desplegar este proyecto solo corra el siguiente comando

```bash
  mvn spring-boot:run
```


## Documentation en Postman

A continuación comparto documentacion en Postman de los endpoints de autenticacion y registro.

En el enlace a continuación puedes ver un ejemplo y realizar pruebas.

[Documentacion Postman](https://documenter.getpostman.com/view/6097625/2s93RUuC2a)
## API Endpoints


**Signin** 

**POST /api/auth/signin** 

El proyecto utiliza una base de datos en memoria H2, y tiene un registro previo de un usuario para poder autenticarse la primera vez y obtener un token.

Datos para autenticarse la primera vez:

{
    "email":"andresorduz1@gmail.com",
    "password":"*Nisum2023"
}

Si deseas ver el SQL del registro previo puede acceder al archivo import.sql, ubicado en resources/import.sql

![Logo](http://creaduz.com/wp-content/uploads/2023/03/signin.png)

**Register** 

**POST /user/register** 

```bash
Para Registrar un usuario, se debe incluir el token en el encabezado de la solicitud HTTP utilizando el siguiente formato:

Authorization: Bearer {token}

Si el token no es válido o ha expirado, la API devolverá un mensaje de error y un código de estado HTTP 401.

Es importante mencionar que la validez del token está configurada en el archivo application.yml y se puede cambiar según las necesidades de la aplicación.

a continuacion un ejemplo:

```

![Logo](http://creaduz.com/wp-content/uploads/2023/03/bearerToken.png)

```bash
Crea un nuevo usuario en el sistema con los siguientes campos:

name (requerido): Nombre completo del usuario.
email (requerido): Correo electrónico del usuario. Debe seguir el formato aaaaaaa@dominio.cl.
password (requerido): Contraseña del usuario. Debe seguir el formato especificado en la expresión regular configurada.
phones (opcional): Lista de objetos de teléfono que contiene los siguientes campos:
  number (requerido): Número de teléfono.
  citycode (requerido): Código de ciudad del teléfono.
  countrycode (requerido): Código de país del teléfono.
```
![Logo](http://creaduz.com/wp-content/uploads/2023/03/register.png)

```bash
El endpoint responde con los siguientes campos:

id: ID del usuario generado por el sistema. Se utiliza UUID.
created: Fecha de creación del usuario.
modified: Fecha de última modificación del usuario.
lastLogin: Fecha de último inicio de sesión del usuario.
token: Token de acceso a la API generado por el sistema. Se utiliza JWT.
isActive: Indica si el usuario sigue habilitado en el sistema.

```
En caso de éxito, el endpoint retorna el código de estado HTTP 201 (Creado) y los campos del usuario. 

Si el correo electrónico ya existe en la base de datos, retorna el código de estado HTTP 409 (Conflicto) y un mensaje de error indicando que el correo ya está registrado. 

Si el correo electrónico o la contraseña no cumplen con el formato requerido, retorna el código de estado HTTP 400 (Solicitud incorrecta) y un mensaje de error indicando el problema.

Después de que un usuario se registra exitosamente, se genera un token de acceso que se guarda en la base de datos junto con el usuario. El usuario utiliza este token para usar  peticiones a solicitudes posteriores que realice a la API.
## Pruebas unitarias

El proyecto incluye pruebas unitarias para la clase UserController y AuthController. Se utilizó JUnit y Mockito para realizar las pruebas.

Para ejecutar las pruebas, se puede utilizar el siguiente comando:

```bash
  mvn test
```


## Autor

- Luis Andres Orduz
- Andresorduz1@gmail.com
- Esp. en Ing. De Software

