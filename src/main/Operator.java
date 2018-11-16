package main;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum Operator {
    ADD("+", 1, true,(x)-> {
        if(x.length==2)return x[0]+x[1];
        else throw new IllegalArgumentException();
    }),
    SUB("-",1,  true,(x)-> {
        if(x.length==2)return x[0]-x[1];
        else throw new IllegalArgumentException();
    }),
    MUL("*",2, true, (x)->{
        if(x.length==2)return x[0]*x[1];
        else throw new IllegalArgumentException();
    }),
    DIV("/",2, true,(x)->{
        if(x.length==2)return x[0]/x[1];
        else throw new IllegalArgumentException();
    }),
    MOD("%",2, false,(x)->{
        if(x.length==2)return x[0]%x[1];
        else throw new IllegalArgumentException();
    }),
    MAX("max",4, false,(x)->{
        if(x.length==2)return Math.max(x[0],x[1]);
        else throw new IllegalArgumentException();
    }),
    POW("^",3, false,(x)->{
        if(x.length==2)return Math.pow(x[0],x[1]);
        else throw new IllegalArgumentException();
    }),
    LPR("("),
    RPR(")");
    private String representation;
    private Function<Double[],Double> function;
    private int precedence;
    private boolean isLeftAssociative;
    Operator(String representation, int precedence, boolean isLeftAssociative, Function<Double[],Double> function) {
        this.representation = representation;
        this.function = function;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
    }
    Operator(String representation) {
        this.representation = representation;
        this.function = (x)->0.0d;
        this.precedence = 0;
        this.isLeftAssociative = true;
    }

    /**
     * Returns the Operator that is symbolized by the specified string.
     * @param op the string to search for.
     * @return the Operator that is symbolized by the specified string.
     */
    public static Operator fromString(String op){
        Optional optionalOperator = Arrays.stream(values()).filter(x->x.representation.equals(op)).findFirst();
        if(optionalOperator.isPresent())return (Operator) optionalOperator.get();
        throw new IllegalArgumentException("Operator matching string: " + op + " wasn't found!");
    }

    public Function<Double[],Double> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return representation;
    }
    public int getPrecedence(){
        return precedence;
    }


    /**
     * Gets isLeftAssociative.
     *
     * @return Value of isLeftAssociative.
     */
    public boolean isIsLeftAssociative() {
        return isLeftAssociative;
    }
}
