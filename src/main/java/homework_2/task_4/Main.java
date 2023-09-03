package homework_2.task_4;

// Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
// Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

import java.util.Scanner;

public class Main {
    // создаём поток для ввода
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.print("Введите хоть что-нибудь: ");
            emptyChecker(scanner.nextLine());
            System.out.print("Повторим? 1 - да, любой другой ввод - выход из программы: ");
        } while (scanner.nextLine().equals("1"));
    }

    /**
     * простая кидалка unchecked исключения в случае, если на проверку поступает пустая строка
     * @param userInput - любая строка
     */
    public static void emptyChecker(String userInput ) {
        if (userInput.length() == 0)
            throw new RuntimeException("Нельзя вводить пустую строку!!! " +
                    "Программа терминирует и Шварцнегер уже выехал по вашему адресу \uD83D\uDC7F\uD83D\uDCA5");
    }
}
