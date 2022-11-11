package lab1.test;

import lab1.main.Problem4;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Problem4Tests {

    @Test
    void Test1() {
        int[][] noFlattenMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        int[] result = Problem4.flattenMatrix(noFlattenMatrix);
        Arrays.equals(result, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    @Test
    void Test2()  {
        int[][] noFlattenMatrix = {
                {10, 11, 12},
                {13, 14, 15},
                {16, 17, 18},
        };
        int[] result = Problem4.flattenMatrix(noFlattenMatrix);
        Arrays.equals(result, new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
    }
}
