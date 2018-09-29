# OOPChatBot
Чат-бот 
Авторы: - Пашков Евгений(https://github.com/Sanchezz17) - Логинов Александр(https://github.com/lgnv)
1)Разделить ответственность управления пользователями из телеграм-бота +
2)Сделать, чтобы реализации не зависели от других реализаций, а зависели от абстракций +

2 задача:
Бот по запросу пользователя выдает ему анекдот. 
Логика парсинга анекдота со сторонних ресурсов отделена от инфраструктурного кода бота.
В чём сложность и интерес:
1. Бот будет работать с абстракцией jokeDownloader
2. Анекдоты можно будет загружать с сайта или из файла
3. Один и тот же анекдот не будет отправлен данному пользователю дважды.