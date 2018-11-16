package main;

import main.Exceptions.ParenthesisMismatchException;
import main.Exceptions.UndefinedOperatorException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Util {
    /**
     * Takes the provided infix expression and turns it into a postfix expression as described at https://en.wikipedia.org/wiki/Reverse_Polish_notation.
     * The algorithm used is the Shunting-yard algorithm as described at https://en.wikipedia.org/wiki/Shunting-yard_algorithm.
     * @param expression the infix expression to translate to postfix.
     * @return postfix representation of the provided expression.
     */
    public static String toReversePolishNotation(String expression){
        if(!isCharacterSetCorrect(expression))throw new UndefinedOperatorException("Unknown character in expression!");
        if(!areParenthesisCorrect(expression))throw new ParenthesisMismatchException("Expression's parenthesis don't match!");

        expression = expression.trim();
        StringBuilder output = new StringBuilder();
        Stack<Operator> operators = new Stack<>();
        String fullRegex;
        {
            StringBuilder regex = new StringBuilder("(");
            Arrays.stream(Operator.values()).forEach(x ->
            {
                if ("+-\\./*^()".contains(x.toString())) regex.append('\\');
                regex.append(x.toString());
                regex.append("|");
            });
            regex.setCharAt(regex.length()-1,')');
            fullRegex = "((?<=" + regex.toString() + ")|(?="+ regex.toString() +"))";
        }
        for(String s:expression.split(fullRegex)){
            if(s.equals(" "))continue;//this is bad practice and (hopefully) a temporary solution until I figure out the cause I'm getting empty strings.
            try {
                //this handles numbers
                output.append(Double.parseDouble(s));
                output.append(" ");
            }catch(NumberFormatException e) {
                Operator op = Operator.fromString(s);
                if(op.getPrecedence() == 0){
                    //this handles parenthesis
                    if(op == Operator.LPR)operators.push(op);
                    else{
                        while(operators.peek() != Operator.LPR){
                            output.append(operators.pop().toString());
                            output.append(" ");
                        }
                        operators.pop();//remove the left parenthesis
                    }
                }
                else {
                    //This handles operators
                    while (operators.size() != 0 &&
                            (operators.peek().getPrecedence() > op.getPrecedence() ||
                                    (operators.peek().getPrecedence()== op.getPrecedence() && operators.peek().isIsLeftAssociative()))) {
                        output.append(operators.pop().toString());
                        output.append(" ");
                    }
                    operators.push(op);
                }
            }
        }
        while(operators.size() != 0){
            output.append(operators.pop().toString());
            output.append(" ");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }
    //TODO: this doesn't work properly in cases where eg mod is an operator and we have a random o on it's own. See tests.
    private static boolean isCharacterSetCorrect(String expression){
        Scanner sc = new Scanner(expression);
        sc.useDelimiter("");
        String charSet;
        {
            StringBuilder charSetBuilder = new StringBuilder("0123456789. ");
            Arrays.stream(Operator.values()).forEach(op -> charSetBuilder.append(op.toString()));
            charSet = charSetBuilder.toString();
        }
        while(sc.hasNext()) {
            if(!charSet.contains(sc.next()))return false;
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
