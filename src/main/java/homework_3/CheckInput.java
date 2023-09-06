package homework_3;

public class CheckInput {
    private static final String wordRegX = "^[a-zA-Zа-яА-Я]+[a-zA-Zа-яА-Я\\-]+[a-zA-Zа-яА-Я]+$";
    private static final String dateRegX = "^\\d+$";
    private static final String phoneRegX = "^\\+?\\d+[\\d\\-\\s/\\\\]*\\d+$";

    // чёт регулярка с датой совсем не получилось: return date.matches(date2Reg) . Буду через сплит делать
    // private static final String dateReg = "^([0][1-9])|([1-9])|([1-2][0-9])|([3][0-1][.])" +
    //                                         "([0][1-9])|([1-9])|([1][0-2][.])" +
    //                                         "([19][0-9][0-9])|([20][0-1][0-9])|([20][2][0-3])$";
    // другой вариант: контролирую размер телефона, но пропускаю с отсутствием цифр
    // String phoneReg = "^\\+?[\\d\\-\\s]{0,12}$";

    public static boolean isWord(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false; // .isBlank() аналогичен .trim().isEmpty() и даже лучше, т.к. проверяет пробелы во всех кодировках
        return name.matches(wordRegX);
    }

    // планировал использовать эту перегрузку для добавления отчества, типа заранее указать будет оно или нет
    // в итоге по-другому решил вопрос. Так что этот метод не используется
    public static boolean isWord(String name, boolean canBeEmpty) {
        if (name == null) return false;
        if (canBeEmpty && name.isBlank()) return true;
        return name.matches(wordRegX);
    }

    public static boolean isDate(String date) {

        String[] dateArray = date.split("[.,-]"); // точка, запятая или тире
        //-------// System.out.print(Arrays.toString(dateArray)+" ");
        if (dateArray.length != 3) return false;
        if (!dateArray[0].matches(dateRegX) ||
                dateArray[0].length() > 2 || // эта проверка нужна, что бы не было, например, 0001.0000015.0000002021
                Integer.parseInt(dateArray[0]) > 31 ||
                Integer.parseInt(dateArray[0]) < 1)
            return false;
        if (!dateArray[1].matches(dateRegX) ||
                dateArray[1].length() > 2 ||
                Integer.parseInt(dateArray[1]) > 12 ||
                Integer.parseInt(dateArray[1]) < 1)
            return false;
        if (!dateArray[2].matches(dateRegX) ||
                dateArray[2].length() > 4 ||
                Integer.parseInt(dateArray[2]) > 2023 ||
                Integer.parseInt(dateArray[2]) < 1900)
            return false;
        return true;
    }

    public static boolean isPhone(String phone) {
        return phone != null && phone.matches(phoneRegX);
    }
}
