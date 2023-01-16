# Автоматизация проекта Spoonacular с открытым API

<img src="src/test/resources/images/logo/Spoonacular-Logo.png"/>

> <a href="https://spoonacular.com/"> Spoonacular</a> - это сайт, на котором можно управлять своим питанием.

> На данном сайте можно котролировать питание, за счет:
> - контроля продуктов, купленных в магазине. Можно на их основе подбирать необходимые рецепты с подробным описанием. 
> - систематизировать рестораны, чтобы контрольировать свое питание, если нет возможности готовить самостоятельно
> - помочь в достижении ваших целей в области питания и т.п.

> <a href="https://spoonacular.com/food-api"> Spoonacular API</a> - ссылка на документацию, посвященное API данного продукта (в начале необходимо авторизоваться).

 В рамках данного репозитория, были реализованы проверки апи + часть проверок комбинированные: проверяем что-то на уровне backend'а с последующей перепроверкой на UI.

## 🎃: Содержание Readme
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
<a href="https://www.jetbrains.com/idea/"><img src="src/test/resources/images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="src/test/resources/images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="src/test/resources/images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="src/test/resources/images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="src/test/resources/images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="src/test/resources/images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="src/test/resources/images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="src/test/resources/images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="src/test/resources/images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## 🚦: Реализованные проверки ##

Проверками покрыт следующие функциональные объекты:
- Ингредиенты
  - Вычисление гликемической нагрузки
- Планирование питания
  - Удаление существующего плана питания
- Поиск (рецептов)
  - Проверка рандомного рецепта
  - Ошибка при поиске рецепта - несуществующий id рецепта
  - Поиск всех рецептов блюда и проверка их наличия на сайте


## <img src="src/test/resources/images/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a> <a target="_blank" href="https://jenkins.autotests.cloud/job/Spoonacular/"> Jenkins job (Allure report)</a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/Spoonacular/"><img src="src/test/resources/images/screens/jenkins.bmp" alt="Jenkins"/></a>
</p>

### Список пройденных автотестов:
- все успешно пройденные кейсы отмечаются ✅
- тесты с ошибкой помечаются ❌
<img src="src/test/resources/images/screens/пройденный_кейсы.bmp"/>

### Вот так выглядит прохождения тест-кейса без ошибок:
В отчет прикладывается:
- скриншот после прохождения всех шагов
- page source
- логи браузера

### Графики
<img src="src/test/resources/images/screens/graf.bmp"/>
<img src="src/test/resources/images/screens/graf2.bmp"/>

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

## <img src="src/test/resources/images/logo/Telegram.svg" width="25" height="25"  alt="Telegram"/></a> Уведомление в Telegram при помощи бота
- статус **broken** означает, что тесты не прошли по техническим причинам. Например, проблемы со стендом 😡
<p align="center">
<img title="Allure Overview Dashboard" src="src/test/resources/images/screens/tg.bmp" >
</p>

## <img src="src/test/resources/images/logo/AllureOps.svg" width="25" height="25"> Интеграция с Allure TestOps
### Отчет о прохождении тест-кейсов:
<img src="src/test/resources/images/screens/allureOps.bmp"/>

### "Хранилище" тест-кейсов:
Allure TestOps очень удобный инструмент. В одном месте можно хранить и автоматизированные, а также кейсы, которые проходят в ручную.
<img src="src/test/resources/images/screens/allureOps1.bmp"/>

### Общий дашборд:
<img src="src/test/resources/images/screens/Ops3.bmp"/>

## <img src="src/test/resources/images/logo/Jira.svg" width="25" height="25">Интеграция с Jira
Тут можно наблюдать интеграцию Jira  с нашими автотестами. Можно прилиноквать тест-кейсы и тестовые прогоны к задаче для контроля тестирования в рамках определенной задачи.
<img src="src/test/resources/images/screens/jira.bmp"/>
