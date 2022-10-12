# job4j_pooh

## О проекте

Этот проект -- аналог асинхронной очереди.

* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента будем использовать cURL.
* В качестве протокола будем использовать HTTP.

Архитектура проекта:

![](../../Загрузки/imageTaskSource.png)

[![build](https://github.com/SergeyPoletaev/job4j_pooh/workflows/build/badge.svg)](https://github.com/SergeyPoletaev/job4j_pooh/actions)

