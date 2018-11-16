package main.Exceptions;

public class ParenthesisMismatchException extends IllegalExpressionException {
    public ParenthesisMismatchException(String message){
        super(message);
    }
    public ParenthesisMismatchException(){
        super();
    }
}
