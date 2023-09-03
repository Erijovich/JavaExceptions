package homework_2.task_2_and_3;


// Если необходимо, исправьте данный код
// (задание 2 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
// Дан следующий код, исправьте его там, где требуется
// (задание 3 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

public class Main {


    //      Задание 2
    // исправленный код
    /*
    int d = 0;
    if (d != 0 & intArray.length > 8) double catchedRes1 = intArray[8] / d;
    System.out.println("catchedRes1 = " + catchedRes1);
     */

    // -----------------
    //        Задание 3
    // исправленный код:

    public static void main(String[] args) {
        int a = 90;
        int b = 3;
        if (b != 0) System.out.println(a / b);
        printSum(23, 234);
        int[] abc = {1, 2};
        int someNumber = 3;
        if (someNumber < abc.length) abc[someNumber] = 9;
        else System.out.println("индекс указан за пределами массива");
    }
    public static void printSum(Integer a, Integer b) {System.out.println(a + b);}

}

