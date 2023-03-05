# О проекте job4j_todo

## Описание

Приложение "TODO список" предназначено для управления задачами.
Присутствуют следующие возможности:
* создание, чтение, обновление, удаление задач
* просмотр всех задач, только выполненных, только новых

## Стек технологий
* Java 17
* PostgreSql 14
* Lombok
* Thymeleaf
* Bootstrap
* Liquibase
* Hibernate 5
* Spring boot 2

## Требования к окружению
* Должны быть установлены следующие программы: Java 17, GIT, Maven 3.8, PostgreSQL 14

## Запуск проекта
1. Скачать проект
~~~
git clone https://github.com/pavilion1900/job4j_todo
~~~
2. Выполнить первую команду 
~~~
mvn clean install -Pproduction
~~~
3. Выполнить вторую команду
~~~
java -jar target/job4j_todo-1.0.jar
~~~
4. Перейти в браузере по ссылке http://localhost:8080/tasks

## Скриншоты запущенного приложения
![This is an image](https://i.ibb.co/ZcqhKFK/2023-03-05-10-59-02.png)
![This is an image](https://i.ibb.co/k5MDtcF/2023-03-05-10-58-26.png)