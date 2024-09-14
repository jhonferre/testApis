# Proyecto de Automatización de APIs

Este proyecto contiene pruebas automatizadas para verificar el correcto funcionamiento de los endpoints de una API de clientes.

## Pruebas Realizadas

### 1. Prueba de Automatización de Búsqueda de Cliente (GET)

#### Descripción
- **Endpoint:** `/customers/{id}`
- **Objetivo:** Verificar que el endpoint devuelve el estado correcto y los datos del cliente según su existencia.

#### Casos de Prueba
- **Cliente Existente:**
    - Verificar que el endpoint devuelve el estado `200 OK` y los datos del cliente.
- **Cliente No Existente:**
    - Verificar que el endpoint devuelve el estado `404 NOT FOUND` y la descripción del error.

### 2. Prueba de Automatización de Creación de Cliente (POST)

#### Descripción
- **Endpoint:** `/customers`
- **Objetivo:** Verificar que el endpoint maneja correctamente la creación de clientes con datos válidos e inválidos.

#### Casos de Prueba
- **Datos Válidos:**
    - Verificar que el endpoint devuelve el estado `201 Created` y el ID del cliente creado.
- **Datos Inválidos:**
    - Verificar que el endpoint devuelve el estado `400 Bad Request` y un mensaje de error descriptivo.

## Requisitos

- **Java:** 17
- **Gradle:** 7.2 o superior

## Ejecución de Pruebas

Para ejecutar las pruebas, utiliza el siguiente comando:

```sh
gradle clean test aggregate
