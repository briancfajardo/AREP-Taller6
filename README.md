# Taller 6 AREP - Brian Camilo Fajardo Sanchez

En este taller se realizan peticiones REST, específicamente GET, para una serie de operaciones, calcular el ceno de un
número, el coseno, comprobar si una palabra es palindrome y calcular la magnitud de dos números


## Iniciando

Estas instrucciones le permitirán disponer de una copia del proyecto en su equipo local para fines de desarrollo y pruebas.


### Prerrequisitos

* Git 
* Java
* Maven
* Docket

Si aún no cuenta con las tecnologías necesarias aquí hay unos videotutoriales que pueden ser de utilidad.

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU
* Docker https://www.youtube.com/watch?v=5nX8U8Fz5S0

### Instalando el proyecto

Para correr el proyecto tienes que iniciar primero docker en tu máquina

Luego de eso tienes que correr el siguiente comando que lo que hará es crear una imagen en tu docker local con el proyecto
de mi taller 5

```
docker run -p 8080:46000 -d --name Taller5CamiloFajardo briancfajardo/firstsprkwebapprepo
```

El proyecto se ejecutará en el puerto 8080. Para poder abrirlo ingrese a su navegador de preferencia y abra la siguiente
página.

```
http://localhost:8080/
```

Debería verse de la siguiente manera:

<img width="500" alt="Ejemplo de búsqueda" src="Img/PruebaInicialTaller5.png">

### Test de integración

La prueba que se realizó para verificar el funcionamiento de las operaciones requeridas es la siguiente:

Creación de la clase donde se crearon los servicios REST GET usando spark

<img width="500" alt="Ejemplo de búsqueda" src="Img/GETSTaller5.png">

Luego se probaron cada una de las funciones requeridas como se evidencia a continuación

<img width="500" alt="Ejemplo de búsqueda" src="Img/PruebaIntegracionTaller5.png">

## Documentacion

Para poder ver la documentación del proyecto desde el terminal y ubicado en el directorio del proyecto debe ejecutar el 
siguiente comando:

```
mvn javadoc:javadoc
```

En la siguiente ruta se encuentra un archivo (index.html) generado con la documentación del proyecto, el cual puede ser 
abierto mediante un navegador web 

```
./target/site/apidocs/
```

## Construido con

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [OMDAPI](https://www.omdbapi.com) - API externa de consulta

## Version

1.0-SNAPSHOT

## Autores

Brian Camilo Fajardo Sanchez - [briancfajardo](https://github.com/briancfajardo)

## Licencia

GNU General Public License family

## Diseño

La aplicación contiene solo un paquete el cual contiene las operaciones requeridas y una clase fuera del paquete que
contiene la creación de los servicios REST.

## Arquitectura

En términos de componentes, en este proyecto podría verse como si tuviera 2, el contenedor y el computador físico, en 
donde el contenedor corre la aplicación dentro del computador en un entorno independiente y se mapean puertos del PC físico 
al del contenedor.

## Como crear imágenes y subirlas a DockerHub

En este laboratorio aprenderemos a usar Docker para correr nuestra aplicación desde un contenedor además como publicarlo 
en DockerHub para poder tener el contendor desde cualquier cliente con Docker instalado.

Para lo cual, iniciamos definiendo la estructura y propiedades del contenedor que queremos crear.

<img width="500" alt="Ejemplo de búsqueda" src="Img/dockerFile.png">

En donde cada línea corremos los siguientes comandos:

- FROM: Toma de base la imagen openjdk:17 en nuestro contenedor, esto para no tener que instalar Java desde 0 
- WORKDIR: Asigna el path base del disco duro virtual de nuestro contenedor 
- COPY: Copia los contenidos de la primera carpeta de nuestro computador al directorio de la imagen del contenedor 
- CMD: Corre el comando especificado en la lista, cada entrada en la lista es una porción del comando separado por espacion

Y luego con el siguiente comando creamos la imagen con las especificaciones del archivo Dockerfile.

docker build --tag dockersparkprimer  .

Luego validamos que las imágenes se hayan creado usando este comando

```
docker images
```

Para crear un contenedor con base en la imagen usamos el siguiente comando

```
docker run -d -p [PUERTO]:46000 --name [NOMBRE] dockersparkprimer
```

Donde reemplazamos con lo siguiente:

- [PUERTO] : El puerto físico de la máquina donde queremos que corra 
- [NOMBRE] : El nombre que le damos al contenedor

Las partes del comando son las siguientes:

- -d Continua con la ejecución del contenedor independientemente de la consola donde se corrió el comando 
- -p Especificamos el puerto donde correrá el servidor, en el ejemplo estamos mapeando uno cualquiera de nuestra máquina con el puerto 46000 del contenedor 
- --name Especificamos el nombre de nuestro contenedor

Para revisar que todo salga bien ejecutamos el siguiente comando:

```
docker ps
```

Por último, para subir la imagen a DockerHub seguiremos los siguientes pasos:

1. crear la referencia local del repositorio

```
docker tag [NombreLocal] [Nombre Repositorio]
```

2. Ingresar con las credenciales de la plataforma

```
docker login
```

3. Hacer el push en docker

```
docker push [Nombre Imagen]:tag
```

## Agradecimientos

* Al profesor de Arquitecturas empresariales, Daniel Benavides