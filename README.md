# 📱 App S9 - SharedPreferences Demo

Aplicación Android de ejemplo que demuestra el uso práctico de `SharedPreferences` para guardar datos persistentes como perfil de usuario, contador de visitas y preferencias de tema.

---

## 🧾 Descripción

Esta app permite simular una configuración personalizada en una sola pantalla. Guarda datos del usuario como nombre, correo y edad, detecta si es la primera vez que se abre la app, cuenta cuántas veces se ha ejecutado, y ofrece cambio de tema (claro/oscuro) en tiempo real, todo utilizando `SharedPreferences`.

---

## 🚀 Funcionalidades

✅ **Contador de visitas**
- Muestra cuántas veces se ha abierto la app
- Botón para resetear el contador

✅ **Perfil de usuario**
- Guarda nombre, correo y edad
- Botones para guardar, cargar y limpiar los datos

✅ **Modo oscuro/claro**
- Cambia en tiempo real al activar el switch
- Guarda automáticamente la preferencia
- Modo Claro: fondo blanco, texto negro  
- Modo Oscuro: fondo gris oscuro, texto blanco

✅ **SharedPreferencesHelper**
- Clase reutilizable que gestiona todos los tipos de datos:
  - `String`, `Boolean`, `Int`, `Float`, `Long`

---

## 🛠️ Tecnologías y herramientas

| Herramienta                | Uso                                |
|----------------------------|-------------------------------------|
| `Kotlin`                   | Lenguaje principal de desarrollo    |
| `Android Studio`           | IDE utilizado                       |
| `ConstraintLayout`         | Estructura base del layout          |
| `ScrollView + LinearLayout`| Para scroll vertical del contenido  |
| `SharedPreferences`        | Almacenamiento de datos persistente |

---

## 📋 Requisitos

- Android Studio Giraffe o superior
- SDK mínimo: API 21 (Android 5.0)
- SDK objetivo: API 34 (Android 14)
- Kotlin 1.9+

---

## 🧪 Cómo usar

1. **Guardar datos**: Escribe tu nombre, correo y edad → pulsa “Guardar”
2. **Cargar datos**: Pulsa “Cargar” y se mostrará tu perfil
3. **Limpiar**: “Limpiar Todo” elimina todos los datos guardados
4. **Contador**: Al iniciar, se incrementa automáticamente. Puedes reiniciarlo
5. **Tema**: Activa el switch para cambiar entre modo claro/oscuro

---

## 🗂️ Estructura del Proyecto

