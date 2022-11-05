package main;

public class Problem4 {

    /**
     * Метод flattenMatrix преобразует матрицу размера nxm в одномерный массив, записывая сперва элементы первого столбца,
     * затем элементы второго столбца и т.д.
     *
     * @param matrix матрица размера nxm
     * @return одномерный массив
     * <p>
     * ПРИМЕР:
     * Вход: matrix = [[1, 2, 3],
     * [4, 5, 6],
     * [7, 8, 9]]
     * Выход: [1, 4, 7, 2, 5, 8, 3, 6, 9]
     */
    public static int[] flattenMatrix(int[][] matrix) {
        int length = matrix.length * matrix[0].length;
        int[] flattenMatrix = new int[length];
        int el = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int[] ints : matrix) {
                flattenMatrix[el] = ints[i];
                el++;
            }
        }
        return flattenMatrix;
    }
}

