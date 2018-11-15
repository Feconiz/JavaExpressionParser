package main;

import main.Exceptions.IllegalExpressionException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Parser {
    public static Node parse(String expression){
        if(!isCharacterSetCorrect(expression))throw new IllegalExpressionException("Unknown character in expression!");
        if(!areParenthesisCorrect(expression))throw new IllegalExpressionException("Expressions parenthesis don't match!");

        return null;
    }
    public static String toReversePolishNotation(String expression){
        StringBuilder output = new StringBuilder();
        Stack<String> operators = new Stack<>();
        StringBuilder regex = new StringBuilder("( |");
        Arrays.stream(Operator.values()).forEach(x->
        {
            regex.append("|");
            if("+-\\./".contains(x.toString()))regex.append('\\');
            regex.append(x.toString());
        });
        regex.append("|(|))");
        throw new NotImplementedException();
    }
    private static boolean isCharacterSetCorrect(String expression){
        Scanner sc = new Scanner(expression);
        sc.useDelimiter("");
        StringBuilder charSet = new StringBuilder("0123456789.");
        for(Operator op : Operator.values())charSet.append(op.toString());
        while(sc.hasNext()) {
            if(!charSet.toString().contains(sc.next()))return false;
        }
        return true;
    }

    /**
     * Checkes if the parenthesis used in the expression make sense (e.g. "((1+2)(" would be wrong)
     * @param expression the expression to check.
     * @return true if the parenthesis make sense, false otherwise.
     */
    private static boolean areParenthesisCorrect(String expression){
        Scanner sc = new Scanner(expression);
        sc.useDelimiter("");
        int counter = 0;
        while(sc.hasNext()) {
            char ch = sc.next().charAt(0);
            if(ch == '(')counter++;
            else if(ch==')'){
                if(--counter < 0){//if we are closing a parenthesis that wasn't opened
                    return false;
                }

            }

        }
        return counter == 0;
    }
}
