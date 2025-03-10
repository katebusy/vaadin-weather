# Приложение для просмотра погоды
Это приложение предоставляет информацию о текущей погоде на основе географических координат (широта и долгота). Данные о запросах хранятся в PostgreSQL.

## Содержание
- [Технологии](#технологии)
- [Запуск приложения](#запуск-приложения)


## Технологии
- Java 17
- Spring Boot
- Vaadin
- Maven
- PostgreSQL
- Docker

## Запуск приложения
### Требования
Для установки и запуска проекта, необходимы Java 17+, Docker.
### 1. Настройка PostgreSQL с помощью Docker
Вы можете запустить PostgreSQL в контейнере Docker с помощью следующей команды:
```sh
docker run --name postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=weather_db -d -p 5432:5432 postgres
```
### 2. Запуск приложения
Вы можете запустить приложение необходимо ввести с помощью следующей команды:
```sh
java -jar target/vaadin-weather-1.0-SNAPSHOT.jar
```
После этого вы сможете открыть веб-браузер и чтобы увидеть интерфейс, перейти по адресу:
http://localhost:8080/weather

![screenshot1](https://github.com/katebusy/vaadin-weather/raw/assets/src/main/resources/screenshots/screenshot1.png)

На страннице вы сможете ввести широту и долготу места, чтобы узнать погоду и нажать кнопку "Показать погоду" или нажатием клавиши "Enter":

![screenshot2](https://github.com/katebusy/vaadin-weather/raw/assets/src/main/resources/screenshots/screenshot2.png)