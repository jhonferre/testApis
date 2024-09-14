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

## Herramientas y Frameworks Utilizados

- **Serenity BDD:** Framework para pruebas automatizadas que proporciona informes detallados y fáciles de entender.
- **Cucumber:** Herramienta que permite escribir pruebas en un lenguaje natural (Gherkin), facilitando la colaboración entre desarrolladores y no desarrolladores.
- **RestAssured:** Librería para pruebas de servicios RESTful en Java.
- **JUnit:** Framework para pruebas unitarias en Java.

## Requisitos

- **Java:** 17
- **Gradle:** 7.2 o superior

## Ejecución de Pruebas

Para ejecutar las pruebas, sigue estos pasos:

1. Clona el repositorio del proyecto:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd <NOMBRE_DEL_PROYECTO>
    ```
3. Ejecuta las pruebas utilizando Gradle:
    ```sh
    gradle clean test aggregate
    ```

## Visualización de Resultados

Después de ejecutar las pruebas, los resultados estarán disponibles en un informe generado por Serenity BDD. Para visualizar el informe:

1. Navega al directorio `target/site/serenity/`.
2. Abre el archivo `index.html` en tu navegador preferido.

Este informe proporcionará una vista detallada de cada prueba ejecutada, incluyendo los pasos realizados, los resultados esperados y los resultados obtenidos.