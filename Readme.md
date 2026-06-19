# 🚗 Rent-A-Car Express API - v1.0

Este es un microservicio desarrollado en **Java con Spring Boot** para la gestión de una flota de vehículos. El proyecto aplica una arquitectura de **3 capas** (Controller, Service, Repository) y utiliza una lista en memoria para la persistencia de datos (Simulación de Base de Datos).

---

## 🛠️ Tecnologías Utilizadas

* **Java 21** o superior.
* **Spring Boot 4.x** (Spring Web).
* **Lombok**: Para la reducción de código boilerplate (Getters/Setters).
* **Jakarta Validation**: Para asegurar la integridad de los datos de entrada.
* **Maven**: Gestor de dependencias.

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue el flujo de responsabilidad única para facilitar el mantenimiento y la escalabilidad:

1. **Model**: Define la estructura del objeto `Auto` y sus validaciones.
2. **Repository**: Gestiona el acceso a los datos (Lista `ArrayList`).
3. **Service**: Contiene las reglas de negocio (Validación de patentes, lógica de arriendo).
4. **Controller**: Expone los endpoints REST para el consumo externo.

---

## 🚀 Instalación y Ejecución

1. **Clonar el repositorio:**

    ```bash
        git clone [https://github.com/tu-usuario/rentacar-v1.git](https://github.com/tu-usuario/rentacar-v1.git)
    ```

2. **Importar en el IDE:**
Abrir como proyecto Maven en IntelliJ IDEA, Eclipse o VS Code.
3. **Ejecutar la aplicación:**
Ejecutar la clase `RentacarApplication.java` o usar el comando:

```bash
    mvn spring-boot:run
```

El servicio estará disponible en: `http://localhost:8080`

---

## 🛣️ Endpoints de la API

| Método | Endpoint | Descripción | Código Exitoso |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/autos` | Lista toda la flota de vehículos. | `200 OK` |
| **GET** | `/api/autos/disponibles` | Lista solo vehículos con `disponible: true`. | `200 OK` |
| **POST** | `/api/autos` | Registra un nuevo vehículo (Valida patente única). | `201 Created` |
| **PUT** | `/api/autos/arrendar/{id}` | Cambia el estado de un vehículo a NO disponible. | `200 OK` |
| **DELETE** | `/api/autos/{id}` | Elimina un vehículo por su ID. | `204 No Content` |

---

## 🧪 Pruebas en Postman (Reglas de Negocio)

Para validar la lógica v1, asegúrese de probar los siguientes escenarios:

1. **Creación Duplicada**: Intentar un `POST` con una patente que ya existe. Debe retornar `400 Bad Request`.
2. **Arriendo Doble**: Intentar un `PUT` en un auto ya arrendado. Debe retornar `400 Bad Request`.
3. **ID Inexistente**: Intentar un `DELETE` o `PUT` con un ID que no esté en la lista. Debe retornar `404 Not Found`.

---

## 👤 Autor

* **Jose Campos - Docente FullStack1**
* **Versión:** 1.0 (Marzo 2026)
