# job4j_pooh

## О проекте

Этот проект -- аналог асинхронной очереди.

* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента будем использовать cURL.
* В качестве протокола будем использовать HTTP.

Архитектура проекта:

![imageTaskSource](https://user-images.githubusercontent.com/59913283/195352089-7ac6522f-f06a-421f-b20a-2f97eac5cf27.png)

[![build](https://github.com/SergeyPoletaev/job4j_pooh/workflows/build/badge.svg)](https://github.com/SergeyPoletaev/job4j_pooh/actions)

