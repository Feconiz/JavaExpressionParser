package main.Exceptions;

public class IllegalExpressionException extends RuntimeException{
    private static final long serialVersionUID = 3052711810537882175L;

    public IllegalExpressionException(String message) {
        super(message);
    }
    public IllegalExpressionException() {
        super();
    }
}
