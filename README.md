# central-phone-simulation
Apliación que simula el comportamiento de llamadas de una central de telefonía. 

## Stack de tecnologías
 - Lenguaje de programación: JAVA
 - Framework: Spring Boot
 - ORM: Hibernate
 - Administrador de dependencias: Maven
 - Base de datos: PosgreSQL

## Variables de ejecución
Las variables son configuradas en el archivo applicaction.yml ubicado en: 
*src/main/resources/application.yml*

### Descripción de Variables
```
#Puerto para ejecutar el proyecto
server:
  # Puerto en que se ejecutará la aplicación
  port : 8090
spring:

  #Coneccion a base de datos
  datasource:
    #nombre del servidor de base de datos
    platform: postgres
    #url hacia la base de datos
    url: jdbc:postgresql://localhost:5432/centralDB
    #credenciales de acceso a la base datos
    username: postgres
    password: postgres
    
  #Base de datos (por defecto)
  # Configuración del ORM
  jpa:
    database: POSTGRESQL
    generate-ddl: true
    show-sql: false
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQL95Dialect
        jdbc:
          lob:
            non_contextual_creation: true
            
  #Thymeleaf para el motor de plantillas
  #-------------------------------------
  thymeleaf:
    cache: false
    #Codificacion de los archivos de la plantilla
    encoding: UTF-8
    #Modo de plantilla a aplicar
    mode: HTML
    #Modo de enviar la plantilla
    servlet:
      content-type: text/html
    #Extension de archivos
    suffix: .html

```


