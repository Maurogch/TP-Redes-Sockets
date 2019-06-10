# TP Final Redes Sockets

- Mauro Guerreiro Chavier
- Ana Laura Nivio

Asynchronous connection between server and various clients using TCP Sockects

## Cuestionario 

### 1.	¿Que es un puerto?

Un puerto es el valor numérico que identifica a una aplicación o servicio que corre en la computadora. Un número de Puerto utiliza 16 bits y su valor varía entre 0 y 65535.

A su vez, los números de puerto se encuentran divididos en tres tipos de rangos:

•	Puertos 0-1023 – Puertos mayormente conocidos: Este tipo de puertos están asignados a los servicios del servidor por la Autoridad de Números Asignados de Internet (IANA; Internet Assigned Numbers Authority). Por ejemplo, los servicios web normalmente utilizan el Puerto 80 y servidores SMTP utilizan el puerto 25.

•	Puertos 1024-49151- Registered Port: Esto pueden ser registrados para los servicios con la IANA y deben ser tratados como semi-reservados. Los programas escritos por el usuario no deben utilizar este tipo de puertos. 

•	Puertos 49152-65535: Conocidos como puertos efímeros, estos son utilizados por los programas de clientes y el usuario es libre de utilizarlos en ellos. Cuando un navegador web se conecta a un servidor web, el navegador se asignará un puerto en este rango. 


### 2.	¿Cómo están formados los endpoints?

Un socket is un endpoint con comunicación bidireccional.
Un socket TCP es una instancia de un endpoint definido por una dirección IP y un puerto en el contexto de una conexión TCP o un estado de escucha.

Los endpoints están formados por:
•	Una dirección que indica donde se encuentra el endpoint.
•	Un enlace que especifica como un cliente puede comunicarse con ese endpoint.
•	Un contrato que identifica las operaciones disponibles.
•	Un conjunto de comportamientos que especifican detalles sobre las implementaciones del endpoint.


### 3.	¿Que es un socket?

Un socket es un método para la comunicación entre un programa del cliente y un programa del servidor en una red. Se define, por lo tanto, como el punto final en una conexión. Una interfaz de programación de socket proporciona las rutinas requeridas para la comunicación entre procesos internos y aplicaciones, ya sea en el sistema local o en un entorno de red distribuido basado en TCP/IP. 

Una conexión socket TCP/IP se encuentra vinculada a un número de puerto para que la capa TCP pueda definir la aplicación a la que está destinada el envío de datos. En sí, es un endpoint que combina una dirección IP  y un número de puerto.
De esta forma para que halla una conexión deben haber dos endpoins (sockets) con ip de origen, ip de destino, puerto de origen y puerto de destino.


### 4.	¿A qué capa del modelo TPC/IP pertenecen los sockets? ¿Porque?

Los sockets pertenecen a la capa de transporte. Esto se debe a que las aplicaciones que emplean sockets son diseñadas para utilizar tanto del modelo UDP o TCP su capa de transporte, siempre y cuando que los protocolos especificados en su capa satisfagan el tipo de conexión que requiere la aplicación.


### 5.	¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets?

Como se dijo antes, un socket se encarga de conectar un programa del cliente y un programa del servidor en una red. En un modelo cliente-servidor con TCP/IP Socket, cada dispositivo debe tener una dirección IP que lo identifique. 

Sin embargo, el uso de una dirección IP no es suficiente para correr aplicaciones de red ya que una computadora puede correr múltiples aplicaciones y/o servicios. Al igual que lo que hace una dirección IP para una computadora, un puerto de red identifica la aplicación o servidor. El uso de este puerto permite que cualquier dispositivo o computadora pueda correr diferentes servicios o aplicaciones.

Esta combinación de la dirección IP junto con el Puerto representa al Socket, permitiendo así que se genere una conexión entre dos dispositivos (cliente-servidor). Esta conexión es única para el cliente, pero el servidor presentara más conexiones con otros Sockets ya varios clientes van a estar conectados al mismo.

Normalmente, el servidor corre en una computadora específica y tiene un socket relacionado con un número de puerto específico. El servidor se encuentra en espera, escuchando al socket del cliente para crear una connection request.

Poniéndonos en el lado del cliente, este sabe la dirección IP de la maquina donde el servidor está corriendo y el número de puerto en donde este se encuentra escuchando. Para crear un connection request, el cliente trata de conectarse con la maquina donde se halle el servidor a través de su puerto. El cliente necesita identificarse ante el servidor para así poder enlazarse con su puerto. Usualmente, este es asignado por el sistema.
 
Si todo está en orden, el servidor aceptara la conexión y, por ende, tendrá un nuevo socket conectado a su puerto local y un endpoint remoto que tiene por valor la dirección IP y puerto del cliente. La razón por la cual se debe crear un nuevo socket es para que el servidor pueda continuar escuchando en el caso de que otro cliente quiera conectarse. Mientras tanto, ira atendiendo las necesidades del cliente ya conectado.
 
Volviendo al cliente, si la conexión es aceptada, creará un socket y el cliente podrá utilizarlo para comunicarse con el servidor.

Ahora que el socket fue creado, el cliente y el servidor pueden comunicarse entre sí por medio de escritura o lectura de datos.


### 6.	¿Cuáles son las causas comunes por la que la conexión entre cliente/servidor falle?

Las causas comunes por la que la conexión entre cliente/servidor falle son:

•	Error 2200: El cliente no recibió una respuesta del servidor en un tiempo determinado (timeout); esto sucede solo si un límite de tiempo fue especificado.

•	Error 2300: El servidor cerró la conexión.

•	Error 2310: El servidor se ha caído mientras intentaba procesar el Handshake Request (conexión con el cliente). La conexión se cerró.

•	Error 2315: El servidor recibió el Handshake Request (conexión con el cliente) y devolvió una respuesta del tipo non-IIOP que el cliente no puede procesar (El cliente recibe una respuesta en un lenguage que no entinede).


### 7.	Diferencias entre sockets UDP y TCP

Por un lado, los sockets TCP son utilizados por aplicaciones orientadas a la conexión. Tiene integrada una verificación de errores en paquetes y retransmite los paquetes perdidos, es decir que no se han podido transmitir.

Por otro lado, los UDP son utilizados por aplicaciones no orientadas a la conexión. No tiene integrada una verificación de errores en paquetes y  no retransmite los paquetes perdidos. UDP no hace un handshake como lo hace TCP


### 8.	Diferencia entre sync & async sockets?

Cuando hablamos de algo sincrónico, nos referimos a algo que sigue un orden. Es decir, que al terminar de ejecutar el paso uno, luego ira el dos, el tres y así sucesivamente. Sin embargo, el paso tres nunca puede ocurrir antes que el paso dos; debe esperar a que este concluya para terminar.

Asincrónico es lo opuesto a esto ya que los pasos pueden ocurrir al mismo tiempo, aleatoriamente o mientras uno de los pasos se está ejecutando.


Volviendo a sockets, decimos que es sincrónico cuando los clientes interactúan con el servidor en orden. Esto significa que cuando el servidor se encuentre interactuando con un cliente y otro cliente desee hacer lo mismo, quedara en espera hasta que el servidor se desocupe. 

Pero un socket asincrónico permite que varios clientes interactúen a la vez con el servidor sin la necesidad de esperar a que este se encuentre desocupado.

















