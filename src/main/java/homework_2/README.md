# Домашнее задание №2 по курсу "Исключения и их обработка"

---
## Задача 1
*Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), 
и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения, 
вместо этого, необходимо повторно запросить у пользователя ввод данных.*

Решение реализовано двумя методами - используя блок `try-catch` в первом варианте и используя 
регулярные выражения во втором. Код снабжён комментариями что как и почему.

Так же пыталася использовать метод `isParsable()` из статического класса `NumberUtils`, содержащийся в 
пакете **Apache Commons Lang**, по инструкции из 
[этой статьи](https://www.educative.io/answers/what-is-numberutilsisparsable-in-java),
однако, добавляя в файл `pom.xml` зависимости: 
```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>
```
возникает следующее сообщение:
```
Invalid content was found starting with element '{"http://maven.apache.org/POM/4.0.0":dependency}'. 
One of '{"http://maven.apache.org/POM/4.0.0":parent, 
"http://maven.apache.org/POM/4.0.0":packaging, 
"http://maven.apache.org/POM/4.0.0":name, 
"http://maven.apache.org/POM/4.0.0":description, 
"http://maven.apache.org/POM/4.0.0":url, 
"http://maven.apache.org/POM/4.0.0":inceptionYear, 
"http://maven.apache.org/POM/4.0.0":organization, 
"http://maven.apache.org/POM/4.0.0":licenses, 
"http://maven.apache.org/POM/4.0.0":developers, 
"http://maven.apache.org/POM/4.0.0":contributors, 
"http://maven.apache.org/POM/4.0.0":mailingLists, 
"http://maven.apache.org/POM/4.0.0":prerequisites, 
"http://maven.apache.org/POM/4.0.0":modules, 
"http://maven.apache.org/POM/4.0.0":scm, 
"http://maven.apache.org/POM/4.0.0":issueManagement, 
"http://maven.apache.org/POM/4.0.0":ciManagement, 
"http://maven.apache.org/POM/4.0.0":distributionManagement, 
"http://maven.apache.org/POM/4.0.0":dependencyManagement, 
"http://maven.apache.org/POM/4.0.0":dependencies,
"http://maven.apache.org/POM/4.0.0":repositories, 
"http://maven.apache.org/POM/4.0.0":pluginRepositories,
"http://maven.apache.org/POM/4.0.0":build, 
"http://maven.apache.org/POM/4.0.0":reports, 
"http://maven.apache.org/POM/4.0.0":reporting, 
"http://maven.apache.org/POM/4.0.0":profiles}' is expected.
```
и мне не очень понятно, что с этим делать... хм

---
## Задача 2 и 3
*Если необходимо, исправьте данный код (задание 2 
[ссылка](https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit))*

*Дан следующий код, исправьте его там, где требуется (задание 3
[ссылка](https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit))*

Немного не ясно было, как браться за задание, слишком расплывчато поставлена задача. 
Если воспринимать буквально - то надо попросту всё удалить и стереть из воспоминаний этот недокод...    

Напишу здесь коментарии, что исравляю и почему, а в самом файле - "исправленный" код 
```
    //      Задание 2
    // исключение можно обработать проверкой и блок try-catch не нужен
    try {
        int d = 0;
        double catchedRes1 = intArray[8] / d;
        System.out.println("catchedRes1 = " + catchedRes1);

    } catch (ArithmeticException e) {
        System.out.println("Catching exception: " + e);
    }
```
```
    //        Задание 3
    //  throws Exception не имеет смысла, т.к. все исключения отлавливаются
    public static void main(String[] args) { // throws Exception {
        // в целом, весь блок try-catch лишён смысла, если мы обрабатаем
        // потенциальные проблемы условными блоками
        try {
            int a = 90;
            int b = 3;
            // System.out.println(a / b);
            // если предположить, что числа a и b приходят извне - стоит
            // сделать проверку деления на ноль
            if (b != 0) System.out.println(a / b);

            // здесь всё в порядке, пока подаём в аргументы целые числа
            // a NullPointerException отловит, если будет null в аргументах
            printSum(23, 234);

            // здесь всё в порядке, пока записываем в массив целые числа
            int[] abc = {1, 2};

            // abc[3] = 9; // тут индекс указан за пределами массива
            // объявим вместо цифры 3 переменную со значением 3
            // (допустим, к нам число пришло извне, а не внутри метода)
            int someNumber = 3;
            // и обернём обращение к элементу в условный блок
            if (someNumber < abc.length) abc[someNumber] = 9;
            else System.out.println("индекс указан за пределами массива");

        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
            // поскольку объект Throwable является предком всех исключений,
            // в catch он должен быть самым последним
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) { // throws FileNotFoundException {
                                                        // нет смысла кидать,
                                                        // т.к. не вызываем никакой файл 
        System.out.println(a + b);
    }
```

---
## Задача 4
*Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. 
Пользователю должно показаться сообщение, что пустые строки вводить нельзя.*

чёт как-то слишком просто..