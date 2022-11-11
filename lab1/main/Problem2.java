package lab1.main;

import java.util.Arrays;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param array массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     * <p>
     * ПРИМЕР:
     * Вход: array = [2, 1, 5, 6, 8]
     * Выход: [2, 6, 8, 1, 5]
     */
    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int[] chet = new int[array.length];
        int[] neChetn = new int[array.length];
        int chetnArray = 0;
        int neChetnArray = 0;
        for (int i : array) {
            if (i < 0) {
                return null;
            }
            if (i % 2 == 0) {
                chet[chetnArray++] = i;
            } else {
                neChetn[neChetnArray++] = i;
            }
        }
        int[] sort = Arrays.copyOf(chet, chet.length);
        if (neChetnArray >= 0) System.arraycopy(neChetn, 0, sort, chetnArray, neChetnArray);
        return sort;
    }
}
