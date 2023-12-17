# Рефакторинг баз данных и приложений

### Сценарий 2: рефакторинг уже существующего проекта

**Проект: курсовая работа по ИСБД** (выполняется совместно со студентом P34111 Бордун Анастасией)

В данном репозитории находится копия курсача на момент завершения работы над ним(первым *init* коммитом).

*Коротко об курсовой работе на момент её завершения:* агрегатор спортивных секций, клубов, залов и т.д.
Реализованы REST операции, операции с изменением баланса клиента спорт.активностей и соответствующего поставщика(запись на занятие)

Подробнее с курсовой работой можно ознакомиться [тут](description-of-coursework.pdf)

# Выполнение, реализация

Мы провели три итеракции изменений, в результате которых исправили недостатки системы, приложения;

Подробнее о них:

### Первая группа изменений:

Описания коротко:

- [x] добавление обработки перехвата и вывода ошибок → exceptionHandler
- [x] изменение названий REST ручек для соответствия стандарту
- [x] добавление валидации(Spring validation)
- [x] добавление DTO'шек + подправка отношений в Hibernate, sequence подправка.
- [x] добавление Sl4j logger
- [x] добавление lombok аннотаций в сущности.

Отчёт по работе, с подробным описанием, что и для чего было изменено [тут](docs/РБДиП_1_Иванов_Бордун.pdf).

Или в ветке [refactoring-1](https://github.com/3ilib0ba/ITMO-RDBaA/pull/1)

### Вторая группа изменений:

Описания коротко:

- [x] Добавить менеджер транзакций в систему. Настроить readonly на все сервисные методы с обращениями к базе на чтение. На обращения с записью оставить возможность записи в транзакции(значение по умолчанию -- readonly = false).

Отчёт по работе, с подробным описанием, что и для чего было изменено [тут](docs/РБДиП_2_Иванов_Бордун.pdf).

Или в ветке [refactoring-1](https://github.com/3ilib0ba/ITMO-RDBaA/pull/2)

### Третья группа изменений:

Описания коротко:

- [x] Добавить unit-тесты на всех сервисах на методы.

Отчёт по работе, с подробным описанием, что и для чего было изменено [тут](docs/РБДиП_3_Иванов_Бордун.pdf).

Или в ветке [refactoring-1](https://github.com/3ilib0ba/ITMO-RDBaA/pull/3)



