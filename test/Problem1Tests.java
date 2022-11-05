package test;

import main.Problem1;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class Problem1Tests {

    @Test
    void shouldReturnTrue_ifNumberContainsAInHexRepresentation() {
        // given
        int number = 10;

        // when
        boolean actual = Problem1.containsDigitAInHexadecimalRepresentation(number);

        // then
        assertTrue(actual);
    }

    @Test
    void shouldReturnFalse_ifNumberDoesNotContainAInHexRepresentation() {
        // given
        int number = 9;

        // when
        boolean actual = Problem1.containsDigitAInHexadecimalRepresentation(number);

        // then
        assertFalse(actual);
    }

}
