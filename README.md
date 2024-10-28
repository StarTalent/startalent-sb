# StarTalent Spring Boot V3

## Descripción

StarTalent Spring Boot V3 es un Sistema de Seguimiento de Candidatos (*ATS*) multi-tenant desarrollado con Spring 
Boot. Esta aplicación permite a los reclutadores gestionar vacantes, realizar seguimiento de postulaciones y ofrece a los candidatos la posibilidad de postularse a vacantes personalizadas por cada cliente.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.x
- Spring Security con JWT
- Spring Data JPA
- PostgreSQL
- Flyway para migraciones de base de datos
- Maven

## Requisitos Previos

- JDK 17 o superior
- Maven 3.8+
- PostgreSQL 13+

## Instalación y Configuración

### Clonar el Repositorio

```bash
git clone https://github.com/StarTalent/startalent-sb.git
cd startalent-sb
```

### Configuración de la Base de Datos

1. Crea una base de datos PostgreSQL para el proyecto.
2. Actualiza el archivo `src/main/resources/application.properties` con tus credenciales de base de datos:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/startalent_v3
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Ejecutar el Proyecto

1. Compilar el proyecto:

```bash
mvn clean install
```

2. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

3. Se requiere tener un archivo .env en la raíz del proyecto con las siguientes variables:

```properties
DB_URL={url jdbc de la base de datos}
DB_USERNAME={usuario de la base de datos}
DB_PASSWORD={contraseña de la base de datos}
```

> Sin este archivo, la aplicación no podrá conectarse a la base de datos y no podrá iniciar correctamente.
> 
> Se puede usar el archivo `.env.example` como base para crear el archivo `.env`.
> 
> Se puede modificar el nivel de log de la aplicación a `DEBUG` en el archivo `application.properties`, para ver las 
> propiedades cargadas desde el archivo `.env`. (*ej. `logging.level.com.revenatium.startalent_sb.
> StartalentSbApplication=DEBUG`*)

La aplicación estará disponible en `http://localhost:8080`.

## Estructura del Proyecto

```
startalent-sb/
├── src/
│   ├── main/
│   │   ├── java/com/revenatium/startalent_sb
│   │   │   ├── accounts/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── roles/
│   │   │   ├── userRole/
│   │   │   ├── users/
│   │   │   ├── utils/
│   │   │   └── StartalentSbApplication.java
│   │   └── resources/
│   │       ├── db/migration/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

> El proyecto está utilizando una arquitectura de capas con un enfoque modular. Al seguir esta arquitectura, el equipo de desarrollo podrá mantener un código limpio, modular y fácil de mantener a medida que el proyecto StarTalent V3 crece y evoluciona.

## Componentes de la Aplicación

- **accounts/**: Entidades y lógica de negocio relacionadas con cuentas de usuario.
- **config/**: Configuraciones de Spring, seguridad y multi-tenant.
- **controller/**: Endpoints de la API REST.
- **roles/**: Entidades y lógica de negocio relacionadas con roles de usuario.
- **userRole/**: Entidades y lógica de negocio relacionadas con roles de usuario.
- **users/**: Entidades y lógica de negocio relacionadas con usuarios.
- **utils/**: Clases de utilidad.

## Estructura de la Arquitectura

- **Capa de Presentación**: Representada por el paquete `controller/`.
- **Capa de Lógica de Negocio**: Distribuida en los paquetes de dominio (`accounts/, roles/, userRole/, users/`).
- **Capa de Acceso a Datos**: Implícita dentro de cada paquete de dominio.
- **Capa de Configuración:** Representada por el paquete `config/`.
- **Capa de Utilidades**: Representada por el paquete `utils/`.

## Módulos de Dominio

- **accounts**: Gestión de cuentas de usuario.
- **roles**: Gestión de roles en el sistema.
- **userRole**: Gestión de la relación entre usuarios y roles.
- **users**: Gestión de usuarios del sistema.

## Principios de Diseño

1. **Separación de Preocupaciones (SoC)**: Cada módulo se encarga de un aspecto específico del sistema.
2. **Principio de Responsabilidad Única (SRP)**: Cada clase tiene una única razón para cambiar.
3. **Encapsulamiento**: Los detalles de implementación están ocultos dentro de cada módulo.
4. **Modularidad**: Facilita la extensión y mantenimiento del sistema.

## Buenas Prácticas para seguir con el desarrollo

1. Mantén una clara separación entre las capas de la aplicación.
2. Utiliza inyección de dependencias para lograr un bajo acoplamiento.
3. Sigue los principios SOLID en el diseño de clases y módulos.
4. Implementa pruebas unitarias para cada componente.
5. Documenta las APIs y los cambios significativos en la arquitectura.

## Documentación

La documentación detallada del proyecto se encuentra en el repositorio [startalent-doc](https://github.com/StarTalent/startalent-doc). Allí encontrarás:

- Especificaciones técnicas
- Diagramas de arquitectura
- Guías de desarrollo
- Documentación de la API

## Desarrollo

### Flujo de Trabajo Git

Utilizamos GitFlow como flujo de trabajo. Por favor, es necesario crear ramas feature/ para nuevas funcionalidades y 
bugfix/ para correcciones. Una vez que la funcionalidad esté lista, se debe crear un Pull Request a la rama develop.

#### Ramas

- **main**: Contiene la versión estable del proyecto.
- **develop**: Rama de desarrollo.
- **feature/**: Ramas para nuevas funcionalidades.
- **bugfix/**: Ramas para corrección de errores.
- **hotfix/**: Ramas para corrección de errores críticos en producción.

## Pruebas

Para ejecutar las pruebas:

```bash
mvn test
```

## Despliegue

Las instrucciones detalladas para el despliegue se encuentran en la [documentación de despliegue](https://github.com/StarTalent/startalent-doc/blob/main/deployment.md).

## Contribuir

Este proyecto es de código privado y no aceptamos contribuciones públicas. Si deseas colaborar, por favor contacta a 
los administradores del proyecto. 

El objetivo del proyecto está destinado para la evaluación de un curso de desarrollo de software. Que tiene como 
objetivo la creación de un proyecto de software de principio a fin. El código en este repositorio es solo para fines 
educativos, para demostrar el trabajo realizado por los estudiantes. 

El curso para el que se realiza este proyecto es el [Bootcamp de Backend con Java y Spring - Código Facilito](https://codigofacilito.com/bootcamps/backend-java-spring).

Una vez finalizado el curso, el proyecto se mantendrá privado y no se aceptarán contribuciones públicas.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Soporte

Si encuentras algún problema o tienes alguna pregunta, por favor abre un issue en el [repositorio de GitHub](https://github.com/StarTalent/startalent-doc/issues).

## Roadmap

El roadmap del proyecto se encuentra en el [proyecto startalent-project-springboot-v3](https://github.com/orgs/StarTalent/projects/1)

## Contacto

Para cualquier consulta adicional, por favor contacta a: lgzarturo@gmail.com

## Desarrolladores

- [Ariel Aguirre](https://github.com/AAAGUIRREU)
- [Arturo López](https://github.com/lgzarturo)

---

¡Gracias por tu interés en StarTalent Spring Boot V3!