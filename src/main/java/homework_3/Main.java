package homework_3;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        Tests.runTestWord();
//        Tests.runTestPhone();
//        Tests.runTestDate();

        Record record = new Record();
        String userInput;
        do {
            System.out.print("Хотите добавить новую запись? Ничего не вводите и нажмите энтер, что бы выйти " +
                    "или начните вводить новые данные\nВнесите данные > ");
            userInput = scanner.nextLine();
            if (!userInput.isBlank()) record.initInput(userInput);
        } while (!userInput.isBlank());
        System.out.println("Ввод записей завершён, адьё, амиго!");
    }
}
