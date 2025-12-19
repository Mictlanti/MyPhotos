##📸 MyPhotos App

MyPhotos es una aplicación Android desarrollada con Jetpack Compose que consume una API remota alojada en GitHub para mostrar una galería de imágenes con descripciones en Markdown, e incorpora Room para persistencia local y uso offline.

##✨ Características

-📡 Consumo de API remota (JSON desde GitHub)

-🖼️ Carga de imágenes con Coil

-📝 Renderizado de descripciones en Markdown

-💾 Persistencia local con Room

-🔌 Soporte offline

-🧭 Navegación con Navigation Compose

-🧱 Arquitectura limpia (Repository pattern)

-💉 Inyección de dependencias con Hilt

-🎨 UI moderna con Jetpack Compose

-🧱 Arquitectura

#🛠️ Tecnologías y librerías

- Kotlin

- Jetpack Compose

- Navigation Compose

- Coil 3

- Retrofit

- Room

- Hilt

- KSP

- Markdown Renderer

#📦 Versionado

El proyecto utiliza Git tags para manejar versiones.

Versión	Descripción
v1.0	Consumo de API remota únicamente
v2.0	API remota + persistencia local con Room

Puedes cambiar de versión usando:

git checkout v1.0


o volver a la versión más reciente:

git checkout main

#📁 Fuente de datos

La app consume un JSON alojado en GitHub que contiene:

URL de imagen

Título

URL con descripción en Markdown

📄 Ejemplo de estructura del JSON:

[
  {
    "id": 1,
    "title": "Kratos",
    "contentUrl": "https://...",
    "descriptionUrl": "https://raw.githubusercontent.com/..."
  }
]

#📴 Modo Offline

Al primer uso con internet, los datos se guardan en Room

Si no hay conexión, la app carga la información desde la base de datos local

Se utiliza fallbackToDestructiveMigration() para manejar cambios de esquema durante el desarrollo

#🚀 Cómo ejecutar el proyecto

Clona el repositorio:

git clone https://github.com/tu-usuario/MyPhotos.git


Abre el proyecto en Android Studio

Ejecuta el proyecto en un emulador o dispositivo físico

👨‍💻 Autor

Daniel Rosas
Android Developer
📍 México 🇲🇽

📄 Licencia

Este proyecto se distribuye bajo la licencia MIT.
Puedes usarlo, modificarlo y aprender de él libremente.
