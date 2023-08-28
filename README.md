# MUSIC APP

Java course final project.

## Requirements

- Java JDK 11 or higher installed.
- Maven installed.

## Setting

1. Clone this repository: `git clone https://github.com/mariagaa993/musicapp-backend.git`.
2. Navigate to the project directory: `cd musicapp-backend`.

## How to run

### From the Command Line

1. Build the project: `mvn clean install`.
2. Run the app: `mvn spring-boot:run`.

### From an IDE

1. Import the project into your IDE (Eclipse, IntelliJ, etc.).
2. Find the main class `MusicappApplication` and execute it.

The app will be available at `http://localhost:9000`.
If you want you can change the port in the file `application.properties` located in `src/main/resources`.

## Endpoints

| Method | Route                           | Description             |
|--------|---------------------------------|-------------------------|
| POST   | /api/user/register              | Register a new user     |
| POST   | /api/user/login                 | Log in                  |
| GET    | /api/movies/recent              | Get recent movies       |
| GET    | /api/movies/popular             | Get popular movies      |
| GET    | /api/movies/topRated            | Get top rated movies    |
| POST   | /api/favoriteMovies/save        | Save movie to favorites |
| GET    | /api/favoriteMovies/list        | Get favorite movies     |
| GET    | /api/favoriteMovies/search      | Search movie by name    |
| DELETE | /api/favoriteMovies/delete/{id} | Delete a movie by ID    |


---
Developed by © [María Gabriela Alarcón](https://github.com/mariagaa993)
