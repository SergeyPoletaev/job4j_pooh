# job4j_pooh

## О проекте

Этот проект -- аналог асинхронной очереди.

* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента будем использовать cURL.
* В качестве протокола будем использовать HTTP.

Архитектура проекта:

![alt tag](https://job4j.ru/api/images/imageTaskSource?imageId=897)

[![build](https://github.com/SergeyPoletaev/job4j_pooh/workflows/build/badge.svg)](https://github.com/SergeyPoletaev/job4j_pooh/actions)

