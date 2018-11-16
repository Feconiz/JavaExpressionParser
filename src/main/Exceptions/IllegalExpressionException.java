package main.Exceptions;

abstract class IllegalExpressionException extends RuntimeException{
    IllegalExpressionException(String message){
        super(message);
    }
    IllegalExpressionException(){
        super();
    }
}
