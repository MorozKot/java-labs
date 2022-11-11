package lab1.main;

import java.util.Locale;

public class Problem1 {

    /**
     * Метод containsDigitAInHexadecimalRepresentation определяет, содержится ли символ A в шестнадцатиричном
     * представлении числа number.
     *
     * @param number целое положительное число
     * @return true, если шестнадцатиричная запись numbers содержит A
     * false, если шестнадцатиричная запись numbers не содержит A
     * <p>
     * ПРИМЕР1:
     * Вход: number = 10
     * Выход: true (10 = 0xA, содержит A)
     * <p>
     * ПРИМЕР2:
     * Вход: number = 9
     * Выход: false (9 = 0x9, не содержит A)
     */

    public static boolean containsDigitAInHexadecimalRepresentation(int number) {
        return Integer.toHexString(number).toUpperCase(Locale.ROOT).contains("A");
    }
}

