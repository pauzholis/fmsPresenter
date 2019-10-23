<img src="https://www.orinfo.ru/sites/default/files/avatar_crop/100201-32204.jpg" height="64px"/>
<br><br/>
Приложение для получения и отображения наименования и кода отделений ФМС

# Как использовать
Для данного приложения будет использоваться БД PostgreSQL. Проект написан на Java 8.
## Установка и настройка БД в ОС Windows 10.
Установите PostgreSQL. Для этого перейдите на страницу [загрузки](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads). 
Скачайте версию 11.5, для Windows.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/iBFihQeT.png)
<br><br/>
Запустите скачаный файл. После этого откроется окно приветствия, можете ничего не менять, пока не дойдете до окна с 
вводом пароля.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/6iufKE8f.png)
<br><br/>
Задайте пароль для пользователя postgres. Пароль root введите в 2 поля. Нажмите «Next».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/DVca9s9T.png)
<br><br/>
Оставьте по умолчанию порт на котором будет работать PostgreSQL 5432. Нажимайте «Next» пока не пойдет процесс установки.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/WWPoiHQb.png)
<br><br/>
Подождите окончания установки.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/4bip9L3T.png)
<br><br/>
Установка завершена. Снимите галочку. Нажмите «Finish».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/CVAokXtE.png)
<br><br/>
Запустите pgAdmin. Нажмите «Меню Пуск — > PostgreSQL 11 -> pgAdmin 4».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/QZOLjHxG.png)
<br><br/>
Во всплывающем окне введите root. Нажмите «OK».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/amQnsBRV.png)
<br><br/>
В левой части открывшегося окна нажмите по стрелке слева от иконки «Servers». Если появится всплывающее окно, то введите 
пароль root.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/Ga3sCCzf.png)
<br><br/>
Создайте базу данных. Для этого в развернутом списке серверов нажмите правой кнопкой по пункту PostgreSQL 11 и выберите 
пункт «Create -> Database...».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/GHkDu31P.png)
<br><br/>
В открывшемся окне, в поле Database введите fms. Нажмите кнопку «Save».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/DfuOJX1f.png)
<br><br/>
## Установка и настройка БД в ОС Ubuntu 16.04.
Ключевые параметры при настройке PostgreSQL:<br/>
Пользователь `postgres`. <br>Пароль `root`. <br>БД `fms`. <br>Адрес подключения `localhost:5432/fms`<br/>
Если вы используете Docker и Ubuntu 16.04, можно настроить PostgreSQL по этой 
[инструкции](https://docs.docker.com/engine/examples/postgresql_service/).<br>
Если вы не используете Docker, то PostgreSQL можно настроить по это 
[инструкции](https://computingforgeeks.com/install-postgresql-11-on-ubuntu-18-04-ubuntu-16-04/)
<br><br/>
## Сборка и старт проекта в ОС Windows 10.
<br><br/>
Перейдите в папку куда склонировали проект. Зажмите «Shift» и кликните правой кнопкой мыши на пустом месте. В выпадающем 
меню выберите «Открыть окно команд». 
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/kD9dMsy6.png)
<br><br/>
Далее введите команду  `mvn clean install` - сборка проекта
<br>
Далее `mvn spring-boot:run` - запуск проекта
<br><br/>
## Сборка и старт проекта в ОС Ubuntu 16.04.
Перейдите в папку куда склонировали проект. Кликните правой кнопкой мыши на пустом месте. В выпадающем 
меню выберите «Открыть в терминале». <br>Введите команду `sudo su`. <br>Затем введите пароль от учетной записи 
суперпользователя.
<br>Затем введите команду  `mvn clean install` - сборка проекта
<br>
Далее `mvn spring-boot:run` - запуск проекта
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/fPzoSAD2.png)
<br><br/>
## Как пользоваться
В браузере перейдите по URL [http://localhost:8090/fms-presenter](http://localhost:8090/fms-presenter)
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/t0vRXNIn.png)
<br><br/>
При нажатии на кнопку "Запросить список ФМС" скачивается архив с файлом с данными о ФМС и записывается в БД. При успешном
скачивании справа от кнопки будет сообщение "Список успешно получен", в случае ошибки, будет сообщение "Внутренняя ошибка 
сервера"
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/vaJwLfUh.png)
<br><br/>
При нажатии на кнопку "Отобразить весь список отделений" в таблице отрисовываются данные всех отделений сохраненных в БД.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/kaHnbttc.png)
<br><br/>
При нажатии на кнопку "Отобразить отделения по коду" в таблице отрисовываются данные всех отделений с введенный кодом.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/qxULT8ik.png)
<br><br/>
При вводе кода, по которому не находятся отделения над таблицей выводится сообщение "Отделения ФМС по данному коду не 
найдены".
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/we5dnXhR.png)


