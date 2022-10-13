# job4j_pooh

## О проекте

Этот проект -- аналог асинхронной очереди.

* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента будет использоваться cURL.
* В качестве протокола будет использоваться HTTP.


####Queue.

Отправитель посылает запрос на добавление данных с указанием очереди (weather) и значением параметра (temperature=18). Сообщение помещается в конец очереди. Если очереди нет в сервисе, то нужно создать новую и поместить в нее сообщение.

Получатель посылает запрос на получение данных с указанием очереди. Сообщение забирается из начала очереди и удаляется.

Если в очередь приходят несколько получателей, то они поочередно получают сообщения из очереди.

Каждое сообщение в очереди может быть получено только одним получателем.

Примеры запросов.

POST запрос должен добавить элементы в очередь weather.

curl -X POST -d "temperature=18" http://localhost:9000/queue/weather

queue указывает на режим «очередь».

weather указывает на имя очереди.

GET запрос должен получить элементы из очереди weather.

curl -X GET http://localhost:9000/queue/weather

Ответ: temperature=18


####Topic.

Отправитель посылает запрос на добавление данных с указанием топика (weather) и значением параметра (temperature=18). Сообщение помещается в конец каждой индивидуальной очереди получателей. Если топика нет в сервисе, то данные игнорируются.

Получатель посылает запрос на получение данных с указанием топика. Если топик отсутствует, то создается новый. А если топик присутствует, то сообщение забирается из начала индивидуальной очереди получателя и удаляется.

Когда получатель впервые получает данные из топика – для него создается индивидуальная пустая очередь. Все последующие сообщения от отправителей с данными для этого топика помещаются в эту очередь тоже.

Таким образом в режиме "topic" для каждого потребителя своя будет уникальная очередь с данными, в отличие от режима "queue", где для все потребители получают данные из одной и той же очереди.

Примеры запросов.

Отправитель.

curl -X POST -d "temperature=18" http://localhost:9000/topic/weather

topic указывает на режим «топик».

weather указывает на имя топика.



Получатель.

curl -X GET http://localhost:9000/topic/weather/1

1 - ID получателя.

Ответ: temperature=18


####Архитектура проекта:

![imageTaskSource](https://user-images.githubusercontent.com/59913283/195352089-7ac6522f-f06a-421f-b20a-2f97eac5cf27.png)

[![build](https://github.com/SergeyPoletaev/job4j_pooh/workflows/build/badge.svg)](https://github.com/SergeyPoletaev/job4j_pooh/actions)

