package tests;


import main.Exceptions.IllegalExpressionException;
import main.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testAreParenthesisCorrect(){
        assertThrows(IllegalExpressionException.class,()->Parser.parse("(())("));
        assertThrows(IllegalExpressionException.class,()->Parser.parse("(())())"));
        assertThrows(IllegalExpressionException.class,()->Parser.parse("(())(()"));
    }
    @Test
    public void isCharacterSetCorrect(){
        assertThrows(IllegalExpressionException.class,()->Parser.parse("(1+2a)"));
        assertThrows(IllegalExpressionException.class,()->Parser.parse("b"));
        assertThrows(IllegalExpressionException.class,()->Parser.parse("2,3"));
    }
}
