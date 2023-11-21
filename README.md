# Prueba Java : api-usuarios-java-sp

Aplicacion APIRestful para creacion de usuarios con autenticacion de Token.
Realizado con Java 17, SpringBoot, Spring Security, JWT (Json web token), Banco de datos de memoria H2, Bases de Datos MySQL y build Maven.

# Instalacion

Seguir los siguientes pasos para la instalacion y compilacion del proyecto:

1. Clonar el repositorio con el comando: git clone https://github.com/kgisselledev/api-usuarios-java-sp.git
2. Acceder al directorio con: cd api-usuarios-java-sp
3. Compilar el proyecto con la consola en Maven: mvn clean install
4. Compilar el proyecto con mvn spring-boot:run
5. Probar el API en postman con http://localhost:8080

# Creacion de llaves privada y publica (Opcional)

En el proyecto vienen incluidas las llaves app.pub y app.key para la securizacion de la API, es opcional crearlas con los siguientes pasos:

1. Crear la llave publica: openssl rsa -in keypair.pem -pubout -out app.pub
2. Crear la llave privada: openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out app.key

Al clonar el proyecto viene incluido el script para la creacion de la Bases de Datos, el Swagger y la coleccion de peticiones de Postman.
