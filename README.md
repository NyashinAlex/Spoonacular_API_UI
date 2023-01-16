# Автоматизация проекта Spoonacular с открытым API

## 🚀: Содержание:

- [Технологии и инструменты](#earth_africa-технологии-и-инструменты)
- [Реализованные проверки](#earth_africa-Реализованные-проверки)
- [Jenkins job (Allure report)](#earth_africa-Jenkins-job-(Allure-report))
- [Запуск из терминала](#earth_africa-Запуск-тестов-из-терминала)
- [Примеры использования](#earth_africa-Allure-отчет)
- [Отчет в Telegram](#earth_africa-Уведомление-в-Telegram-при-помощи-бота)
- [Видео примеры прохождения тестов](#earth_africa-Примеры-видео-о-прохождении-тестов)
- [Интеграция с Allure TestOps](#earth_africa-Интеграция-с-Allure-TestOps)
- [Интеграция с Jira](#earth_africa-Интеграция-с-Jira)

## 🧰: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## 🚦: Реализованные проверки

### **Тест-кейс 1 - Успешная авторизация существующего пользователя**

Предусловие:
- Пользователь зарегистрирован на сайте Spoonacular
- https://spoonacular.com/food-api/console#Dashboard

Шаги:
- Открыть страницу с авторизацией
- Заполнить поле Email корректным значением (УЗ из Предусловия)
- Заполнить поле Password корректным значением (УЗ из Предусловия)
- Нажать Log In

ОР:
- Пользователь успешно авторизован на сайте, происходит редирект на страницу Dashboard
- На вкладке Profile, Email равен значению из Предусловия

### **Тест-кейс 2 - Невозможность авторизации несуществующего пользователя**

Предусловие:
- Пользователь НЕ зарегистрирован на сайте Spoonacular
- https://spoonacular.com/food-api/console#Dashboard

Шаги:
- Открыть страницу с авторизацией
- Заполнить поле Email корректным значением (УЗ из Предусловия)
- Заполнить поле Password корректным значением (УЗ из Предусловия)
- Нажать Log In

ОР:
- Авторизация неуспешна
- Появляется информативное сообщение об ошибке: _Login information incorrect._

### **Тест-кейс 3 - Невозможность авторизации - пустое поле Email**

Предусловие:
- https://spoonacular.com/food-api/console#Dashboard

Шаги:
- Открыть страницу с авторизацией
- Оставить поле Email пустым
- Заполнить поле Password корректным значением
- Нажать Log In

ОР:
- Авторизация неуспешна
- Появляется информативное сообщение об ошибке: _Login information incorrect._

### **Тест-кейс 4 - Невозможность авторизации - пустое поле Password**

Предусловие:
- https://spoonacular.com/food-api/console#Dashboard

Шаги:
- Открыть страницу с авторизацией
- Заполнить поле Email корректным значением
- Оставить поле Password пустым
- Нажать Log In

ОР:
- Авторизация неуспешна
- Появляется информативное сообщение об ошибке: _Login information incorrect._

### **Тест-кейс 5 - Невозможность авторизации - поля Email и Password пустые**

Предусловие:
- https://spoonacular.com/food-api/console#Dashboard

Шаги:
- Открыть страницу с авторизацией
- Оставить поле Email пустым
- Оставить поле Password пустым
- Нажать Log In

ОР:
- Авторизация неуспешна
- Появляется информативное сообщение об ошибке: _Login information incorrect._


## <img src="images/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a> <a target="_blank" href="https://jenkins.autotests.cloud/job/Spoonacular/"> Jenkins job (Allure report)</a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/Spoonacular/"><img src="images/screens/jenkins.bmp" alt="Jenkins"/></a>
</p>

### Список пройденных автотестов:
- все успешно пройденные кейсы отмечаются ✅
- тесты с ошибкой помечаются ❌
<img src="images/screens/пройденный_кейсы.bmp"/>

### Вот так выглядит прохождения тест-кейса без ошибок:
В отчет прикладывается:
- скриншот после прохождения всех шагов
- page source
- логи браузера
- видеокаст прохождения всего кейса
<img src="images/screens/успешный_кейс.bmp"/>

### Графики
<img src="images/screens/graf.bmp"/>
<img src="images/screens/graf2.bmp"/>

## 🌟: Запуск тестов из терминала
#### Локальный запуск:
```
gradle clean test -Denv=local
```
где **local** - это конфигурационный файл, который содержит следующие параметры:
```
baseUrl=https://spoonacular.com --- базовый url сайта
browser=chrome --- тестовый браузер
browserVersion=100.0 --- версия тестового браузера
browserSize=1920x1080 --- тестовое разрешение
```

#### Удаленный запуск:
```
gradle clean test -Denv=remote
```
где **remote** - это конфигурационный файл, который содержит следующие параметры:
```
baseUrl=https://spoonacular.com --- базовый url сайта
browser=chrome --- тестовый браузер
browserVersion=100.0 --- версия тестового браузера
browserSize=1920x1080 --- тестовое разрешение
remoteWebDriver=https://user1:1234@selenoid.autotests.cloud/wd/hub --- url Selemoid'а
```

## <img src="images/logo/Telegram.svg" width="25" height="25"  alt="Allure"/></a> Уведомление в Telegram при помощи бота
- статус **broken** означает, что тесты не прошли по техническим причинам. Например, проблемы со стендом 😡
<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/tg.bmp" >
</p>

## <img src="images/logo/Selenoid.svg" width="25" height="25" alt="Jenkins"/></a> Видео <a target="_blank" href="https://selenoid.autotests.cloud/video/595766d262ef75c7d541e35dd75e0c70.mp4"> прохождения тестов </a>

## <img src="images/logo/AllureOps.svg" width="25" height="25"> Интеграция с Allure TestOps
### Отчет о прохождении тест-кейсов:
<img src="images/screens/allureOps.bmp"/>

### "Хранилище" тест-кейсов:
Allure TestOps очень удобный инструмент. В одном месте можно хранить и автоматизированные, а также кейсы, которые проходят в ручную.
<img src="images/screens/allureOps1.bmp"/>

### Общий дашборд:
<img src="images/screens/Ops3.bmp"/>

## <img src="images/logo/Jira.svg" width="25" height="25">Интеграция с Jira
Тут можно наблюдать интеграцию Jira  с нашими автотестами. Можно прилиноквать тест-кейсы и тестовые прогоны к задаче для контроля тестирования в рамках определенной задачи.
<img src="images/screens/jira.bmp"/>
