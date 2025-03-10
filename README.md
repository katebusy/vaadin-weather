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
### Запуск приложения
Вы можете запустить приложение с помощью следующих команд:
```sh
cd local // Переход в папку local
docker-compose up // Запуск docker-compose.yml
java -jar vaadin-weather.jar // Запуск исполняемого jar файла
```
После этого вы сможете открыть веб-браузер и чтобы увидеть интерфейс, перейти по адресу:
http://localhost:8080/weather

![screenshot1](https://github.com/katebusy/vaadin-weather/raw/assets/src/main/resources/screenshots/screenshot1.png)

На страннице вы сможете ввести широту и долготу места, чтобы узнать погоду и нажать кнопку "Показать погоду" или нажатием клавиши "Enter":

![screenshot2](https://github.com/katebusy/vaadin-weather/raw/assets/src/main/resources/screenshots/screenshot2.png)