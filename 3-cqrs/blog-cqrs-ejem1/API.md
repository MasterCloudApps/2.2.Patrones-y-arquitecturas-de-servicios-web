# API REST - PRACTICA 1

Documentación de la API REST de la **Práctica 1 - Tecnologías de Servicios de Internet**

## GET ALL POSTS

_Obtener un listado con el identificador y el título de cada uno de los posts_

* #### MÉTODO GET

* #### URL:  http://localhost:8080/api/post

* #### RESPONSE
	```
	[
		{
			"id": 1,
			"title": "Mi post de prueba"
		}
	]
	```

## GET POST

_Obtener toda la información de un post determinado (comentarios incluidos)_

* #### MÉTODO GET

* #### URL:  http://localhost:8080/api/post/1

* #### RESPONSE
	```
	{
		"id": 1,
		"title": "Mi post de prueba",
		"content": "Esto es un contenido de prueba",
		"comments": [
			{
				"id": 1,
				"author": "Pablo",
				"message": "Esto es un comentario de prueba"
			},
			{
				"id": 2,
				"author": "Juan",
				"message": "Esto es otro comentario"
			}
		]
	}
	```

## NEW POST

_Crear un post_

* #### MÉTODO POST

* #### URL:  http://localhost:8080/api/post

* #### BODY
	```
	{
		"title": "Practica 1",
		"content": "Creacion de post desde API REST"
	}
	```

* #### RESPONSE

	```
	{
		"id": 2,
		"title": "Practica 1"
	}
	```

## NEW COMMENT

_Obtener toda la información de un post determinado (comentarios incluídos)_

* #### MÉTODO POST

* #### URL:  http://localhost:8080/api/post/1/comment

* #### BODY

	```
	{
		"author": "Pablo",
		"message": "Este comentario se ha añadido desde la API REST"
	}
	```

* #### RESPONSE

	```
	{
		"id": 3,
		"author": "Pablo",
		"message": "Este comentario se ha añadido desde la API REST"
	}
	```

## DELETE COMMENT

_Borrar un comentario_

* #### MÉTODO DELETE

* #### URL:  http://localhost:8080/api/post/1/comment/1

* #### RESPONSE

	```
	HTTP STATUS 204
	```
