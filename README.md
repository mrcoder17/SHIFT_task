<h1>Тестовое задание Back-End</h1>

<h3>Запуск программы</h3>

Для запуска программы необходимо сделать git clone <repo> и запустить удобным способом <br>

<h3>Принцип работы</h3>

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
