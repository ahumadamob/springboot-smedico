# EstadoTurno API

## POST /EstadoTurnoE

Crea un nuevo EstadoTurno.

### Respuestas

- **200 OK**: EstadoTurno creado y devuelto en el cuerpo de la respuesta.

## GET /EstadoTurnoE/{id}

Obtiene un EstadoTurno por su identificador.

### Respuestas

- **200 OK**: EstadoTurno encontrado y devuelto en el cuerpo de la respuesta.
- **204 No Content**: No existe un EstadoTurno con el `id` especificado.
