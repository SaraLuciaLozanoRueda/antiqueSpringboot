#  ⌨︎ **Push Up Backend**

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Backend realizado con las siguientes tecnologias:

 **Springboot**: Framework del entorno Spring.

**MySQL**: Sistema gestor de bases de datos. 🐬

Y dependencias de *Spring Web*, *Spring Validation*, *Spring Dev Tools*, *Spring Jpa*.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Resumen:**

Este proyecto cuenta con una base de datos relacional, imagen en **resources**, dentro del pom.xml tiene unas dependencias comentadas debido a que se intento integrar Spring Security al desarrollo.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Antes de poner a correr el programa recordar lo siguiente: 🤓

En el archivo application.properties cambiar las credenciales.

```sql
spring.application.name=antique
server.port=8091
spring.datasource.url=jdbc:mysql://localhost:3306/antique
spring.datasource.username=campus2023 #Aqui tu usuario en tu base de datos
spring.datasource.password=campus2023 #Aqui la contraseña del usuario
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

Cuando cambies eso, Pon el siguiente comando en la consola de tu base de datos.

```sql
CREATE DATABASE antique;
USE antique;
```

Y con eso ya deberia correr.

En la primera tiende a lanzar un error puede ser por lo que no esta terminada alguna actualizacion debido a las configuraciones. Asi que ponlo a correr otra vez.

 

