package homework_3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static homework_3.CheckInput.*;

public class Record {
    private final Scanner scanner;
    // Хранит одобренные данные: ФИО, дату и телефон. Вообще, думаю кортежем это сделать надо
    private final String[] acceptedData;
    private static final String help = """
            \tФИО - символы латиницы и кириллицы, минимум 2 символа, допустимо тире
            \tДата - дд.мм.гггг, в качестве разделителя точка, запятая или тире
            \tТелефон: цифры без пробелов, минимум 2 символа, в начале допустим знак плюса, в качестве разделителя - тире, прямые и обратные слэши""";

    public Record() {
        scanner = new Scanner(System.in);
        acceptedData = new String[5];
        Arrays.fill(acceptedData, null);
    }

    /**
     * @param checkFullName true, значит нужно проводить проверку по полному ФИО, false - только имя и фамилию
     * @return true, если не все данные введены, false - всё введено
     */
    private boolean dataNotDone(boolean checkFullName) {
        return namePending(checkFullName) ||
                datePending() ||
                phonePending();
    }

    /**
     * @return true, если дата не добавлена, false - дата есть
     */
    private boolean datePending() {
        return acceptedData[3] == null;
    }

    /**
     * @return true, если телефон не добавлен, false - телефон добавлен
     */
    private boolean phonePending() {
        return acceptedData[4] == null;
    }

    /**
     * @param checkFullName true, значит нужно проводить проверку по полному ФИО, false - только имя и фамилию
     * @return  true, если в имени не хватает данных, false - всё введено
     */
    private boolean namePending(boolean checkFullName) {
        // красиво ИДЕЯ первую проверку подсказала! я через тернарник написал, а она исправила
        // (checkFullName ? passedInput[2] != null : true)
        return (checkFullName && acceptedData[2] == null) || // отчество проверим первым, т.к. скорее всего остальное уже добавлено
                (acceptedData[1] == null) || // имя
                (acceptedData[0] == null);   // фамилия
    }

    /**
     * Запуск цикла ввода новой записи для проверки и парсинга данных
     * @param userInput пользовательский ввод
     */
    public void initInput(String userInput) {
        Arrays.fill(acceptedData, null);
        startInput(userInput);
        System.out.print("Это правильные данные? Ничего не вводите и нажмите энтер, что бы сохранить запись " +
                "или начните вводить данные заново\nВнесите данные >");
        String answer = scanner.nextLine();
        if (!answer.isBlank()) initInput(answer);
        else writeData();
    }

    /**
     * Первый ввод данных пробрасывается с метода initInput(). Если данных не достаточно, запускаются циклы
     * проверки и парсинга данных. Тут же реализована возможность пропустить ввод отчества для тех, у кого его нет
     * @param userInput пользовательский ввод
     */
    private void startInput(String userInput) {
        if (!userInput.isBlank()) parseData(userInput.trim().split("\\s+"));
        while (dataNotDone(false)) nextInput();
        while (dataNotDone(true)) {
            System.out.print("Внесите в запись отчество или ничего не вводите и нажмите энтер, что бы пропустить\nОтчество > ");
            String answer = scanner.nextLine();
            if (isWord(answer, true)) {
                addWord(answer);
            } else System.out.println("Это не отчество!");
        }
        System.out.print("Ура! Вот полная введённая запись: ");
        showResultShort();
    }

    /**
     * Ввод следующей строки с потенциальными данными. Если строка не пустая - отправляется на парсинг
     */
    private void nextInput() {
        System.out.print("внесите данные > ");
        String userInput = scanner.nextLine();
        if(userInput.isBlank()) {
            System.out.println("Вы ничего не написали!");
        } else {
            // случайно обнаружил, что trim() необходим, иначе ввод с пробелами в самом начале косячно конвертится
            parseData(userInput.trim().split("\\s+"));
        }
    }

    /**
     * Введённая строка, сплитом разбитая по пробелам, записывается в массив стрингов и по каждому идёт проверка,
     * во-первых, нужно ли добавлять новое значение в соответствующую ячейку, во-вторых, является ли элемент валидным.
     * В случае, если были добавлены не все необходимые данные выводится инфо о том, какие части уже добавлены.
     * <p> я добавил эксепшены, понимаю, что они тут избыточны, но ооочень хотелось для тренировки
     */
    private void parseData(String[] userInput) {
        if (userInput.length > 5) System.out.println("Вы ввели " + userInput.length + " единиц данных - больше, " +
                "чем требуются. В запись будут внесены первые, прошедшие проверку по формату.");

        boolean flag = false;

        for (String s : userInput) {
            try {
                if (namePending(true) && isWord(s)) {
                    addWord(s);
                    flag = true;
                } else if (datePending() && isDate(s)) {
                    addDate(s);
                    flag = true;
                } else if (phonePending() && isPhone(s)) {
                    addPhone(s);
                    flag = true;
                }
            } catch (CantAddWordException e) {
                System.out.println("Не удалось записать слово " + s);
            } catch (CantAddDateException e) {
                System.out.println("Не удалось записать дату " + s);
            } catch (CantAddPhoneException e) {
                System.out.println("Не удалось записать телефон " + s);
            }
        }
        if (!flag) System.out.println("Уникальных вхождений не добавили.\nПроверьте формат:\n" + help);
        System.out.println("На данный момент введены");
        showResultDetailed();
    }

    /**
     * Добавляет слово в массив. Положение слова (фамилия, имя или отчество) выбирается методом по порядку
     * Следующие методы добавляют соответственно дату и телефон
     * <p> Эксепшены кидаются просто так, на всякий случай... скорее потренироваться хотелось
     * boolean somethingWrong принимает значение true, если слово отправилось на добавление (хотя оно и не должно
     * было пройти проверку в предыдущем методе), но цикл for пройдя по элементам ничего не добавил
     * @param word слово для добавления.
     */
    private void addWord(String word) throws CantAddWordException {
        boolean somethingWrong = false;
        for (int i = 0; i <= 2; i++) {
            somethingWrong = false;
            if (acceptedData[i] == null) {
                acceptedData[i] = word;
                break;
            }
            somethingWrong = true;
        }
        if (somethingWrong) throw new CantAddWordException();
    }

    private void addDate(String date) throws CantAddDateException {
        try {
            acceptedData[3] = String.join(".", date.split("[,-]"));
        } catch (Exception e) {
            throw new CantAddDateException();
        }
    }

    private void addPhone(String phone) throws CantAddPhoneException {
        try {
            acceptedData[4] = String.join("", phone.split("[-/\\\\]"));
        } catch (Exception e) {
            throw new CantAddPhoneException();
        }
    }

    /**
     * полный и короткий вывод уже добавленных данных
     */
    public void showResultDetailed() {
        if (acceptedData[0] != null) System.out.println("\tфамилия: " + acceptedData[0]);
        if (acceptedData[1] != null) System.out.println("\tимя: " + acceptedData[1]);
        if (acceptedData[2] != null) System.out.println("\tотчество: " + acceptedData[2]);
        if (acceptedData[3] != null) System.out.println("\tдата: " + acceptedData[3]);
        if (acceptedData[4] != null) System.out.println("\tтелефон: " + acceptedData[4]);
    }

    public void showResultShort() {
        System.out.println(String.join(" ", acceptedData));
    }

    /**
     * Запись в файл. Имя файла берётся из первого элемента массива (то есть фамилии), причём для имени файла
     * идёт первая буква всегда преобразуется в заглавную, остальные - в строчные. Это не обязательно , конечно, т.к.
     * имя файла вроде бы не чувствительно к регистру, но опять же, интересно потрениться было
     */
    private void writeData() {
        String fileName = acceptedData[0].toUpperCase().charAt(0) + acceptedData[0].substring(1).toLowerCase();
        String path = "X:\\Devs\\GeekBrains\\JavaExceptions\\homework\\src\\main\\java\\homework_3\\" + fileName + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            // человечески читаемая запись =))
            // bw.write(String.join(" ", acceptedData) + "\n");
            bw.write("<" +
                    acceptedData[0] + "><" +
                    acceptedData[1] + "><" +
                    acceptedData[2] + "><" +
                    acceptedData[3] + "><" +
                    acceptedData[4] + ">\n");
            System.out.println("Запись успешно добавлена!\n");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при попытке добавить запись в файл: " + e.getMessage());
        }
    }
}
