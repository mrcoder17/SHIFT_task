<h1>Тестовое задание Back-End для SHIFT-lab</h1>

<h3>Необходимые компоненты и зависимости</h3>
Java 17<br>
Spring Boot<br>
in-memory H2 database<br>
JUnit<br>
Mockito<br>


<h3>Запуск программы</h3>
Для начала склонируйте репозиторий и перейдите в директорию проекта<br>
<code> git clone https://github.com/mrcoder17/SHIFT_task.git </code><br>
<code> cd SHIFT_task </code><br>

После этого вы можете запустить программу удобным способом:<br>
<h4> 1. С помощью IDE </h4>
Открыть проект в используемой среде разработки и запустить ShiftTaskApplication <br>
<br>
<h4> 2. С помощью терминала </h4>
Соберите проект: <code> mvn clean install </code><br>
Запустите приложение: <code> java -jar target/SHIFT_task-0.0.1-SNAPSHOT.jar </code><br>
<br>
<h4> 3. С помощью Docker </h4>
<code> mvn clean package </code> <br>
<code> docker build -t shift-task:1.0 . </code><br>
<code> docker run -p 8080:8080 shift-task:1.0 </code><br>


<h3>Принцип работы</h3>
Программа получает на вход несколько интервалов из букв или цифр, после чего<br>
объединяет пересекающиеся и сохраняет их в базу данных H2<br>
С помощью запроса можно получить минимальный интервал


Для тестирования использовалось ПО Postman. <br>

<h4>Запросы</h4>

Для выполнения POST запроса используется json формата: <br>

[<br>
    [1, 4], <br>
    [3, 6], <br>
    [8, 10] <br>
] <br>

По адресу http://localhost:8080/api/v1/intervals/merge?kind=digits <br>

[<br>
    ["a","c"], <br>
    ["b","d"],<br>
    ["e","f"] <br>
] <br>

Для http://localhost:8080/api/v1/intervals/merge?kind=letters <br>

<h4>На основе данных выше:</h4>

GET http://localhost:8080/api/v1/intervals/min?kind=digits выведет [1, 6],<br>
а GET http://localhost:8080/api/v1/intervals/min?kind=letters выведет  ["a", "d"]
