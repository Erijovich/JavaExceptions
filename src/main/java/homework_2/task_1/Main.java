package homework_2.task_1;

// Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение.
// Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить
// у пользователя ввод данных.

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    // создаём поток для ввода
    static Scanner scanner = new Scanner(System.in);
    // создаём шаблон регулярного выражения
    static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) {
        System.out.print("Введите дробное число (тип float): ");
        System.out.println("Вы ввели: " + floatConverterUsingException(scanner.nextLine()));

        System.out.print("Введите дробное число (тип float): ");
        System.out.println("Вы ввели: " + floatInputUsingRegExp());
    }

    /**
     * пример рекурсивного вызова метода из блока catch в случае ошибки ввода со стороны пользователя
     * тут непосредственно в метод направляем искому строку и не создаём объект String
     * @param userInput - сюда направляем myScanner.nextLine()
     * @return - возвращаем сконверченное во флоат число
     */
    public static float floatConverterUsingException(String userInput) {
        float convertedNum;
        try {
            convertedNum = Float.parseFloat(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Необходимо ввести хотя бы оду цифру, без пробелов и, опционально, " +
                    "с одной точкой, разделяющую целую и дробную части ☝️");
            System.out.print("Введите дробное число (тип float): ");
            convertedNum = floatConverterUsingException(scanner.nextLine());
        }
        return convertedNum;
    }

    /**
     * пример с использование регулярных выражений
     * сам метод предназначен для ввода корректного дробного числа
     * но есть минус - число типа double метод тоже попытается сконвертировать
     * используется следующее регвыражение - "-?\\d+(\\.\\d+)?", где
     * -? –- проверяет наличие минуса в начале введённого числа, знак вопроса указывает на необязательность наличия символа "тире"
     * \d+ –- ищем одну или более цифру
     * (\.\d+)? – выражение взято в скобки и после стоит знак вопроса - опять говорит о необязательности этой составляющей в введённом числе
     * ищем же мы одну или более цифру сразу после точки.
     * @return - возвращаем сконверченное во флоат число
     */
    public static float floatInputUsingRegExp() {
        float convertedNum = 0;
        String userInput;
        while (convertedNum == 0) {
            System.out.print("Введите дробное число (тип float): ");
            userInput = scanner.nextLine();
            if (pattern.matcher(userInput).matches())
                convertedNum = Float.parseFloat(userInput);
            else
                System.out.println("Необходимо ввести хотя бы оду цифру, без пробелов и, опционально, " +
                        "с одной точкой, разделяющую целую и дробную части ☝️");
        }
        return convertedNum;
    }
}
