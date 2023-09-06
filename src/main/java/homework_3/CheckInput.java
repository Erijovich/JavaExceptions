package homework_3;

public abstract class CheckInput {
    private static final String wordRegX = "^[a-zA-Zа-яА-Я]+[a-zA-Zа-яА-Я\\-]+[a-zA-Zа-яА-Я]+$";
    private static final String dateRegX = "^\\d+$";
    private static final String phoneRegX = "^\\+?\\d+[\\d\\-/\\\\]*\\d+$";

    // чёт регулярка с датой совсем не получилось: return date.matches(date2Reg) . Буду через сплит делать
    // private static final String dateReg = "^([0][1-9])|([1-9])|([1-2][0-9])|([3][0-1][.])" +
    //                                         "([0][1-9])|([1-9])|([1][0-2][.])" +
    //                                         "([19][0-9][0-9])|([20][0-1][0-9])|([20][2][0-3])$";
    //
    // с телефоном ещё другой вариант: контролирую размер телефона, но пропускаю ввод с отсутствием цифр:
    // String phoneReg = "^\\+?[\\d\\-\\s]{0,12}$";

    /**
     * Проверка на то, что строка является словом, состоящим из букв латиницы и/или кириллицы. Так же допустимо
     * имя с дефисом Сан-Мартин, например
     * @param name строка на проверку
     * @return true, если проверку прошло, false, если не прошло
     */
    public static boolean isWord(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false; // .isBlank() аналогичен .trim().isEmpty() и даже лучше, т.к. проверяет пробелы во всех кодировках
        return name.matches(wordRegX);
    }
    public static boolean isWord(String name, boolean canBeEmpty) {
        if (name == null) return false;
        if (canBeEmpty && name.isBlank()) return true;
        return name.matches(wordRegX);
    }

    /**
     * Проверка на то, что строка является относительно валидной датой формата дд.мм.гггг
     * <p> в качестве разделителя допустимы символы точки, запятой или тире
     * <p> есть ограничения - дату проверяет до 31 числа, так что может быть 31 февраля, например
     * @param date строка на проверку
     * @return true, если проверку прошло, false, если не прошло
     */
    public static boolean isDate(String date) {
        String[] dateArray = date.split("[.,-]"); // точка, запятая или тире
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

    /**
     * Проверка на то, что строка является телефонным номером. Количество знаков не ограничено.
     * После опционального символа плюс в начале строки, а так же в конце допускаются только цифры.
     * в середине строки допустимы дефисы, прямой и обратный слэши
     * @param phone строка на проверку
     * @return true, если проверку прошло, false, если не прошло
     */
    public static boolean isPhone(String phone) {
        return phone != null && phone.matches(phoneRegX);
    }
}
