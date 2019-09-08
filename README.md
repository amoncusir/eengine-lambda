# EEngine (Event Engine) #

### ESP

Que quiero crear?

**Message Broker**

Un sistema de deliveración de mensajes entre aplicativos, usando una arquitectura servless.

**Que necesita?**

- Persistencia de todas las transacciones (publicación/subscripción)
- Publicación y subscripción a traves de canales a traves de expresiones regulares o cadenas de texto
- Publicación configurable
- Agrupar subscriptores por servicios (y poder deliverar a 1 o _N_ subscriptores)
- Ultra-rápido de procesar las peticiones (cada milisegundo cuenta!) 
- Deliveración de las publicaciones fallidas de entrega
- Persistencia de los subscriptores
- Poder ser extensible a través de 'Pluggins' y no depender de ningúna implementación
- 
- 

**Posibles Soluciones:**

- Crear un contrato de repositorio que provee el guardado y recuperacion. Implementarlo con S3 de AWS.
- Que cada subscriptor tenga como uno de los parametros los canales a los que esta subscrito.
- Al enviar el mensaje desde el publicador, tendra configuraciones adicionales.
- Cada subscriptor tendra un id de servicio por el cual seran agrupados
- Optimizacion de los procesos y recursos
- Crear un canal especial llamado `eengine/error` el cual se enviaran los mensjes fallidos. Implementar un limite de
  reintentos
- Crear un contrato de respositorio que provee el guardado y recuperacion de los subsciptores. Implementarlo con
  DynamoDB de AWS
- Crear un sistema de contratos e implementaciones que se configurara en el contexto de ejecución.
