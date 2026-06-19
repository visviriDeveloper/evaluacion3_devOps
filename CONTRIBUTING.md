# Guía de Contribución - Rent-A-Car Express v1.0

¡Gracias por sumarte al desarrollo de este microservicio! Para mantener el código limpio, seguimos las siguientes normas:

---

## 🌿 Flujo de Trabajo (Git Flow)

1. No subir cambios directamente a `main`.
2. Crear una rama de trabajo: Usa nombres descriptivos con los siguientes prefijos:
   - `feature/` para nuevas funcionalidades.
   - `fix/` para corregir errores.
   - `docs/` para documentación.
   - `refactor/` para mejoras de código.

---

## 🏗️ Estándares de Código (Clean Code)

Para asegurar la calidad del microservicio, respete la arquitectura de **3 capas**:

- **Controller**: Solo recibe peticiones y entrega respuestas.
- **Service**: Aquí reside toda la **Lógica de Negocio**.
- **Repository**: Solo gestiona el acceso a los datos.

### Reglas de Oro

- **Lombok**: Use `@Data`, `@AllArgsConstructor` y `@NoArgsConstructor`.
- **Validaciones**: Use `@NotBlank` y `@Min`.
- **Idiomas**: Mantenga los nombres de variables y métodos en español.

---

## 📝 Formato de Commits

Usamos mensajes de commit claros:

- ✅ `feat: agregar validación de patente duplicada en Service`
- ✅ `fix: corregir error 500 al eliminar ID inexistente`
- ❌ `update: cambios varios` (Evite mensajes genéricos).

---

## 🧪 Pruebas antes de enviar

Antes de solicitar la integración de su código (Pull Request), asegúrese de:

1. Que el proyecto **compile** correctamente (`mvn clean compile`).
2. Probar los endpoints en **Postman**.
3. Verificar que el archivo `README.md` esté actualizado.

---

**¡A programar con lógica!**🚀
