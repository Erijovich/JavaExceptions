package homework_3;

import static homework_3.CheckInput.*;

public class Tests {
    public static void runTestString(){
        System.out.println("---string test(true)---");
        System.out.println(isWord("",true)); // true
        System.out.println(isWord("john")); // true
        System.out.println(isWord("John")); // true
        System.out.println(isWord("john",true)); // true
        System.out.println(isWord("john",false)); // true
        System.out.println(isWord("джон")); // true
        System.out.println(isWord("Джон")); // true
        System.out.println(isWord("Сен-санс")); // true

        System.out.println("---string test(false)---");
        System.out.println(isWord(null)); // false
        System.out.println(isWord(null, true)); // false
        System.out.println(isWord("")); // false
        System.out.println(isWord("",false)); // false
        System.out.println(isWord("123john",false)); // false
        System.out.println(isWord("Камиль Сен-санс")); // false
        System.out.println(isWord("-санс")); // false
        System.out.println(isWord("Сен-")); // false
    }
    public static void runTestPhone() {
        System.out.println("---phone test(true)---");
        System.out.println(isPhone("+71234567890")); // true
        System.out.println(isPhone("71234567890")); // true
        System.out.println(isPhone("+7 123 456-78-90")); // true
        System.out.println(isPhone("103")); // true
        System.out.println(isPhone("911")); // true
        System.out.println(isPhone("01")); // true
        System.out.println(isPhone("023948278978973094827897789789789789798903900")); // true
        System.out.println(isPhone("123/456/7890")); // true
        System.out.println(isPhone("991\\33\\23344")); // true
        System.out.println(isPhone("2------2")); // true

        System.out.println("---phone test(false)---");
        System.out.println(isPhone("+7_123_456-78-90")); // false
        System.out.println(isPhone("911/")); // false
        System.out.println(isPhone("1")); // false
        System.out.println(isPhone("0+1")); // false
        System.out.println(isPhone("")); // false
        System.out.println(isPhone("------")); // false
        System.out.println(isPhone("2------")); // false
    }
    public static void runTestDate() {
        System.out.println("---date test(true)---");
        System.out.println(isDate("01.01.1990")); // true
        System.out.println(isDate("12.12.2000")); // true
        System.out.println(isDate("13.01.2000")); // true
        System.out.println(isDate("1.1.2023")); // true
        System.out.println(isDate("9.01.1999")); // true
        System.out.println(isDate("31.12.2023")); // true
        System.out.println(isDate("1.1.1990")); // true
        System.out.println(isDate("12,12,2023")); // true
        System.out.println(isDate("12-12-2023")); // true

        System.out.println("---date test(false)---");
        System.out.println(isDate("00.01.1990")); // false
        System.out.println(isDate("09.01.1190")); // false
        System.out.println(isDate("10.13.2000")); // false
        System.out.println(isDate("2.29.1900")); // false
        System.out.println(isDate("001.10.1909")); // false
        System.out.println(isDate("01.010.1909")); // false
        System.out.println(isDate("12.12.02023")); // false
        System.out.println(isDate("12.12..2023")); // false
        System.out.println(isDate("12 12 2023")); // false
        System.out.println(isDate("12-12--2023")); // false
        System.out.println(isDate("3в.12.2023")); // false
        System.out.println(isDate("32.1в.2023")); // false
        System.out.println(isDate("32.12.в023")); // false
        System.out.println(isDate("10.00.2023")); // false
        System.out.println(isDate("12.12.02.023")); // false
        System.out.println(isDate("1.1.2000.2000")); // false
    }
}
