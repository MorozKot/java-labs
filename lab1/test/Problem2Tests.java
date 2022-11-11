package lab1.test;

import lab1.main.Problem2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Problem2Tests {

    @Test
    void Test1() {
        int[] array = {5, 2, 7, 4, 6};
        int[] result = Problem2.segregateEvenAndOddNumbers(array);
        Arrays.equals(result, new int[]{2, 4, 6, 5, 7});
    }

    @Test
    void Test2() {
        int[] array = {2, 1, 5, 6, 8};
        int[] sort = Problem2.segregateEvenAndOddNumbers(array);
        Arrays.equals(sort, new int[]{2, 6, 8, 1, 5});
    }
}
