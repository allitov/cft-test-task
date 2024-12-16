# Тестовое задание. Курс Java
**Утилита фильтрации содержимого файлов.**

При запуске утилиты в командной строке подается несколько файлов, содержащих вперемешку целые числа, 
строки и вещественные числа. В качестве разделителя используется перевод строки. 
Строки из файлов читаются по очереди в соответствии с их перечислением в командной строке.

Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt.
## Используемые инструменты
- Java 21
- [Apache Maven Wrapper 3.3.2](https://maven.apache.org/wrapper/download.cgi)
- [Lombok 1.18.36](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.36)
- [Apache Commons CLI 1.9.0](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0)
## Локальный запуск
### Клонирование репозитория и переход в рабочую директорию
```shell
git clone https://github.com/allitov/cft-test-task.git
cd cft-test-task
```
### Сборка приложения
```shell
.\mvnw package -DskipTests
```
### Запуск приложения
Для запуска приложения достаточно выполнить следующую команду:
```shell
java -jar .\target\cft-test-app.jar in1.txt
```
Здесь 'in1.txt' название файла, который необходимо обработать. 
Количество передаваемых файлов не ограничено.
#### Дополнительные параметры
Создать файлы результата по пути '.\path':
```shell
java -jar .\target\cft-test-app.jar in1.txt -o .\path
```
Создать файлы результата с префиксом 'result_':
```shell
java -jar .\target\cft-test-app.jar in1.txt -p result_
```
Включить возможность добавления данных в существующие файлы:
```shell
java -jar .\target\cft-test-app.jar in1.txt -a
```
Включить возможность сбора минимальной статистики:
```shell
java -jar .\target\cft-test-app.jar in1.txt -s
```
Включить возможность сбора всей статистики:
```shell
java -jar .\target\cft-test-app.jar in1.txt -f
```
Примечание: параметры можно комбинировать. Параметры -f -s взаимоисключают друг дуга.
#### Пример запуска приложения
```shell
java -jar .\target\cft-test-app.jar -s -a -p sample- in1.txt in2.txt
```
