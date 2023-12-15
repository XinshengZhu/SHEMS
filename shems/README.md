# shems

## Back-End Setup

### Requirements

- Java 18.0.2
- Maven 3
- MySQL 8.2.0

### Installation

Execute our dumped sql file `shems.sql` in your database through [https://drive.google.com/file/d/1uVjZLjNxY98P01vBsewHrWK6TUObhdmO/view?usp=drive_link](https://drive.google.com/file/d/1uVjZLjNxY98P01vBsewHrWK6TUObhdmO/view?usp=drive_link).

Change the database credentials in `src/main/resources/application.properties` to match your database.

### Build

Build the project with `mvn clean package`.

### Run

Run the project with `mvn spring-boot:run` or `java -jar target/shems-0.0.1-SNAPSHOT.jar`.
