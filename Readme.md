# Sistema de Gestión de Pagos - TopEducation

Este proyecto es una aplicación web monolítica desarrollada en **Java 17** usando **Spring Boot 3**, diseñada para automatizar la gestión de pagos de aranceles en la academia preuniversitaria **TopEducation**. Su despliegue se realiza en un entorno de contenedores con **Docker Compose**, incluye balanceo de carga con **Nginx** y una base de datos en **PostgreSQL**.

---

## 📌 Requisitos

Para ejecutar esta aplicación necesitas tener instalado:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## 🚀 Ejecución del proyecto

1. **Clona el repositorio:**

```bash
git clone https://github.com/MoisesGodoy17/Tingeso-Top-Education.git
cd Tingeso-Top-Education
```

2. **Levanta los contenedores:**

```bash
docker-compose up 
```

3. Accede a la aplicación desde tu navegador:\
   `http://localhost:80`

---

## 🧱 Arquitectura del Sistema

El sistema está compuesto por los siguientes servicios:

- **Backend Spring Boot (Java 17):** API REST con lógica de negocio.
- **PostgreSQL 15:** Base de datos relacional.
- **Nginx:** Balanceador de carga con 2 réplicas de la aplicación backend.
- **Docker Compose:** Orquestador de contenedores para levantar todo el entorno local.

---

## ⚙️ Funcionalidades principales

El sistema automatiza el proceso de pagos y gestiona:

- Registro de estudiantes
- Generación de cuotas de arancel según reglas de negocio
- Registro de pagos
- Importación de resultados de exámenes desde archivos Excel
- Cálculo de descuentos y recargos
- Reporte resumen del estado de pagos

---

## 📄 Historias de Usuario implementadas

- HU1: Ingreso de datos de estudiantes
- HU2: Generación de cuotas de pago
- HU3: Visualización de estado de pagos
- HU4: Registro de pagos
- HU5: Importación de notas desde Excel
- HU6: Cálculo automático de planilla de pagos
- HU7: Reporte detallado de estado de pagos por estudiante


## Ventana principal de la aplicacion. 
<img width="1275" height="905" alt="Captura de pantalla 2025-07-29 225043" src="https://github.com/user-attachments/assets/aea48296-c9ec-412a-b299-b0ba2ee2f6a1" />

## Registro de nuevos Alumnos. 
<img width="1277" height="907" alt="Captura de pantalla 2025-07-29 225100" src="https://github.com/user-attachments/assets/952c65d8-eb02-4c54-b9f5-7c57ca687e33" />

## Listado de Alumnos. 
<img width="1275" height="905" alt="Captura de pantalla 2025-07-29 225129" src="https://github.com/user-attachments/assets/d89a885f-8e5d-457d-9ed1-9becbff2452d" />

## Reporte de un Alumno. 
<img width="1275" height="905" alt="Captura de pantalla 2025-07-29 225154" src="https://github.com/user-attachments/assets/7068ca8c-72a5-4fd4-9114-d23d76cd994d" />

---

## 🧪 Testing y CI/CD

- Pruebas unitarias con **JUnit**
- Análisis de calidad con **SonarQube**
- Pipeline automatizado con **Jenkins**:
  - Clonación desde GitHub
  - Ejecución de tests
  - Análisis estático
  - Generación de imagen Docker y push a DockerHub

---

## 📚 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Lombok
- Docker / Docker Compose
- Nginx
- Jenkins / SonarQube

---

## 🧑‍💻 Autor

- Nombre: Moises Godoy
- Contacto: [Linkedin](https://www.linkedin.com/in/moises-andres-godoy-carre%C3%B1o-58b4a4370/)

---

## Este fue un trabajo para el ramo Técnicas de Ingeniería en Software. 

