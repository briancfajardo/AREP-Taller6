# Taller 6 AREP - Brian Camilo Fajardo Sanchez

En este taller creamos un servicio de logs que trae los 10 últimos mensajes recibidos de una base de datos de mongodb
con una usando Docker y una instancia EC2 de AWS


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

Luego, clona el proyecto usando el siguiente comando

```
git clone https://github.com/briancfajardo/AREP-Taller6.git
```

Luego muevete al directorio creado y desde ahi ejecuta este comando

```
docker-compose up -d
```
Podrás entrar a http://localhost:8080/ y deberías ver lo siguiente:

![pagInicial](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/c94489e7-6bd4-42d7-80ce-41d34c1ec3ec)

### Test de integración

Para probar que el desarrollo de la aplicación fuera correcto sé probo cada funcionalidad en ella corriendo, para ello 
enviamos un log y verificamos que saliera junto con los últimos creados.

![EjemplodeUso](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/5f332551-de53-4b22-8072-2ce3786935bf)


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

Para simular la arquitectura especificada en el taller dentro de un mismo proyecto se separaron los componentes en paquetes
diferentes, uno para los LogService, otro para la conexión y pruebas de la base de datos mongo y el último para el balanceador 
RRInvoker. Además, se tienen los archivos Dockerfile y docker-compose que especifica como se construirán los contenedores 
y que harán cada uno de ellos.

## Arquitectura

La arquitectura de este taller es presentada con el siguiente diagrama:

![arquitectura](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/e58e18bb-79aa-4286-b4d2-69d17a13ddda)


En donde se especifican los siguientes componentes:

Security Group: Grupo de seguridad de la instancia en AWS que lo protege los puertos de la máquina (firewall de nivel 1) 
- AWS-EC2: Instancia básica de una máquina virtual en la nube de Amazon 
- Docker Engine: Motor de los contenedores instalado en la instancia 
- APP-LB-RoundRobin: Balanceador de cargas que implementa un algoritmo estático de RoundRobin donde se rotan los servidores 
por cada petición 
- LogService: Servidor que almacenara en la base de datos el log, y devolverá los últimos 10 
- MongoDB: Contenedor con la base de datos de mongo


## Como crear las imágenes y desplegar el proyecto

El proceso que hay que seguir para crear las imágenes y desplegar el proyecto es esencialmente el mismo, solo cambia un 
comando y que obviamente se debe de ejecutar en la máquina virtual donde se desea desplegar.

### Creacion de las imagenes

Para automatizar la creación de las imágenes y los contenedores se creó el archivo docker-compose.yml con l¡a siguiente 
información:

![docker-compose1](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/bf9a300f-30ed-4549-b856-bfbc3370ff90)

![docker-compose2](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/972075a0-c22d-4885-a9b8-0ef03be06d7d)


En este archivo especificamos como se crean las imágenes, en él hay 2 métodos de creación, especificando la imagen con un 
Dockerfile y otro, trayendo la imagen de docker hub, aquí no se ahondara mucho, ya que se explicó en detalle en el taller anterior.

Lo nuevo que aparece en el archivo es la sección de "network" en esta estamos especificando la red virtual que crearemos 
donde estarán los contenedores, en este especificamos el tipo de red (atributo driver), el id de la red y el gateway de esta. 
La especificación de la red se hace para que los contenedores puedan tener una IP estática y sea fácilmente desplegable en cualquier PC.

Ya con el archivo lo único que debemos hacer es correr el siguiente comando:

```
docker-compose up -d
```

En la máquina virtual de Amazon Linux es:

```
docker compose up
```

Y al entrar en la URL http://localhost:8080 encontrarás el app corriendo.

### Despliegue

Para crear una instancia EC2 en AWS puedes solo seguir el siguiente [tutorial](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EC2_GetStarted.html). 
Ya cuando la hayas creado y te hayas conectado, simplemente clona el proyecto en la instancia y sigue el tutorial del 
apartado anterior para desplegar. Puede que haga falta instalar git, docker y el plugin de compose en la instancia para 
poder correr el lab, pero para ello puedes seguir los tutoriales del inicio del README.

## Video

https://github.com/briancfajardo/AREP-Taller6/assets/80064378/17de1a0a-99b9-4221-9874-f14981df3a10

## Agradecimientos

* Al profesor de Arquitecturas empresariales, Daniel Benavides
