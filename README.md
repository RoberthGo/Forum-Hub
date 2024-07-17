# Forum-Hub

![Java JDK](https://img.shields.io/badge/Java_JDK-v17.0-blue)
![IDE](https://img.shields.io/badge/IDE-Intellij_IDEA-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-v3.3.0-blue)
![Maven](https://img.shields.io/badge/Proyect-Maven-blue)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL-v14.12-blue)
![Jackson](https://img.shields.io/badge/jackson-v2.17.2-blue)


Project developed for the ONE-Backend program. This api simulates the behavior of a basic Forum where there is a CRUD for courses, topics, also has JWT for authentication and does not allow unregistered users.
</br></br>



## ⚙️ Application functions

</br></br>



## Prerequisites 📋
To run the program, you need the following technologies used during its development:
* Java JDK: You must have Java Development Kit version 17 or higher installed. You can download it [here](https://www.oracle.com/java/technologies/downloads/#java17).
*  MySQL:  You can download it [here](https://www.mysql.com/downloads/)
* Maven: version 4 or higher, for dependency management and project construction.
* IntelliJ IDEA: It is the IDE used for the development of the project. Although it is not strictly necessary to run the program, its use is recommended to facilitate the development and code management.
  development and code management. You can download IntelliJ IDEA [here](https://www.jetbrains.com/es-es/idea/).
  </br></br>



## Installation 🔧
1. Clone the application
```  
git clone https://github.com/RoberthGo/ReadSearch
```
2. Create MySQL database
```
CREATE DATABASE forum_hub;
```
3. Configure the file [application.properties](src/main/resources/application.properties)
<p align="center">
  <img src="https://github.com/user-attachments/assets/95628a83-1989-4d0c-b3f7-30f620d17cda">
</p>
Replace the environment variables with the corresponding data from your MySQL.
Or create environment variables with the same names and assign the appropriate values. </br>
Note: If you run the above SQL command as is, ${DB_NAME} will be forum_hub otherwise put the name of your database.</br>
${JWT_SECRET:123456} is the key to sign the encryption. If not edited, it will remain as 123456 by default.</br>
5. ready, now you can run the APIREST and consume it
</br></br>



## Built with 🛠️
* Java JDK - Programming Language Used
* Spring - Java Framework used
* Spring boot, Spring Securiry, Flyway, Lombock - used spring technologies
* Hibernate: Persistence provider used by JPA.
* Spring Data JPA: Used for object-relational mapping and data persistence.
* Maven - Dependency handler.
* Intellij - Integrated Development Environment (IDE) used for the project.
* MySQL - Database management system used in the project.
* Jackson Databind: Convert data between JSON and Java objects
  </br></br>



## Author ✒️
* **Roberto Gordillo Herrera** - [Roberth_G](https://github.com/RoberthGo)
  </br>


## Licencia 📄
This project is under the MIT License - see the [LICENSE](LICENSE) file for details.
