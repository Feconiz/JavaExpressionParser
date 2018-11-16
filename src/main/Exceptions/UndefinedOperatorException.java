package main.Exceptions;

public class UndefinedOperatorException extends IllegalExpressionException {
    public UndefinedOperatorException(String message){
        super(message);
    }
    public UndefinedOperatorException(){
        super();
    }
}
