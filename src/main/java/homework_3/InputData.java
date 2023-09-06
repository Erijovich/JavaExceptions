package homework_3;

import java.util.Arrays;
import java.util.Scanner;

import static homework_3.CheckInput.*;

public class InputData {

    static Scanner scanner = new Scanner(System.in);
    static boolean[] sequence = new boolean[5];
    static String[] passedInput = new String[5];

    public static void constr() {
        Arrays.fill(sequence, false);
        Arrays.fill(passedInput, null);
    }

    public static void initInput() {
        startInput();
        System.out.println("Это правильные данные? Ничего не вводите и нажмите энтер, что бы пропустить или начните вводить данные заново");
        String answer = scanner.nextLine();
        if (!answer.isBlank()) initInput();
        else {
            writeData();
            System.out.println("Хотите добавить новую запись? Ничего не вводите и нажмите энтер, что бы пропустить или начните вводить новые данные");
            answer = scanner.nextLine();
            if (!answer.isBlank()) initInput();
            else System.out.println("Ввод завершён, адьё, амиго!");
        }
    }

    public static void writeData() {

    }

    public static void readData() {

    }

    public static void startInput() {
        constr();
        while (!sequence[4] || !sequence[3] || !sequence[1] || !sequence[0]) inputSome();
        while (!sequence[2]) {
            System.out.print("Внесите в запись отчество или ничего не вводите и нажмите энтер, что бы пропустить: ");
            String answer = scanner.nextLine();
            if (isWord(answer, true)) {
                sequence[2] = true;
                addWord(answer);
            } else System.out.println("Это не отчество!");
        }
        System.out.print("ура, вот введённая запись: ");
        showResultShort();
    }

    public static void inputSome() {
        // случайно обнаружил, что trim() необходим, иначе с пробелами в самом начале косячно конвертится
        System.out.println("внесите данные > ");
        String[] userInput = scanner.nextLine().trim().split("\\s+");
        boolean flag = false;
//        System.out.println(Arrays.toString(userInput));

        // чёт прям быдлокод какой-то..... :/
        for (String s : userInput) {
            if ((!sequence[2] || !sequence[1] || !sequence[0]) && isWord(s)) {
                for (int i = 0; i <= 2; i++) {
                    if (!sequence[i]) {
                        sequence[i] = true;
                        flag = true;
                        break;
                    }
                }
                addWord(s);
            } else if (!sequence[3] && isDate(s)) {
                sequence[3] = true;
                flag = true;
                addDate(s);
            } else if (!sequence[4] && isPhone(s)) {
                sequence[4] = true;
                flag = true;
                addPhone(s);
            }
        }
        if (!flag) System.out.println("ничего нового не добавили");
        System.out.print("На данный момент введены: ");
        showResult();
    }

    public static void addWord(String word) {
        for (int i = 0; i <= 2; i++) {
            if (passedInput[i] == null) {
                passedInput[i] = word;
                break;
            }
        }
    }

    public static void addDate(String date) {
        passedInput[3] = date;
    }

    public static void addPhone(String phone) {
        passedInput[4] = phone;
    }

    public static void showResult() {
        if (passedInput[0] != null) System.out.print(" (фамилия) " + passedInput[0]);
        if (passedInput[1] != null) System.out.print(" (имя) " + passedInput[1]);
        if (passedInput[2] != null) System.out.print(" (отчество) " + passedInput[2]);
        if (passedInput[3] != null) System.out.print(" (дата) " + passedInput[3]);
        if (passedInput[4] != null) System.out.print(" (телефон) " + passedInput[4]);
        System.out.println();
    }

    public static void showResultShort() {
        System.out.println(Arrays.toString(passedInput));
    }

    // метод послать массив индексы и проверка на наличие чегото
    // типа флаги посылаю и проверить первые три символа есть хотя бы один фолс
    // или строки на наличие нулл  и вернёт индекс
}
