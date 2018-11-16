package tests;

import main.Exceptions.ParenthesisMismatchException;
import main.Exceptions.UndefinedOperatorException;
import main.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @Test
    void testToReversePolishNotationSimple(){
        assertEquals("4.0 2.0 +", Util.toReversePolishNotation("4 + 2"));
    }
    @Test
    void testToReversePolishNotationMedium(){
        assertEquals("6.0 2.0 * 5.0 3.0 - 3.0 * + 8.0 -",Util.toReversePolishNotation("6 * 2 + (5 - 3) * 3 - 8"));
        assertEquals("3.0 4.0 + 7.0 2.0 * + 1.0 - 9.0 -",Util.toReversePolishNotation("(3 + 4) + 7 * 2 - 1 - 9"));
    }
    @Test
    void testToReversePolishNotationHard(){
        assertEquals("5.0 2.0 - 4.0 8.0 5.0 1.0 + - * + 9.0 +",Util.toReversePolishNotation("5 - 2 + 4 * (8 - (5 + 1)) + 9"));
        assertEquals("8.0 1.0 - 3.0 + 6.0 * 3.0 7.0 + 2.0 * -",Util.toReversePolishNotation("(8 - 1 + 3) * 6 - ((3 + 7) * 2)"));
        assertEquals("3.0 4.0 2.0 * 1.0 5.0 - 2.0 3.0 ^ ^ % +",Util.toReversePolishNotation("(3.0 + 4.0 * 2.0 % ( 1.0 - 5.0 ) ^ 2.0 ^ 3.0)"));
    }
    @Test
    void testToReversePolishNotationParenthesisException(){
        assertThrows(ParenthesisMismatchException.class,()->Util.toReversePolishNotation("(5 - 2 + 4 * (8 - (5 + 1)) + 9"));
        assertThrows(ParenthesisMismatchException.class,()->Util.toReversePolishNotation("5 - 2) + 4 * (8 - (5 + 1)) + 9"));
    }
    @Test
    void testToReversePolishNotationUndefinedOperatorException(){
        assertThrows(UndefinedOperatorException.class,()->Util.toReversePolishNotation("5 - a + 4 * (8 - (5 + 1)) + 9"));
        assertThrows(UndefinedOperatorException.class,()->Util.toReversePolishNotation("5 - 2 + a * (8 - (5 + 1)) + 9"));
    }
}
