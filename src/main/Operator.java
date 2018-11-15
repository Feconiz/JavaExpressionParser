package main;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum Operator {
    ADD("+",(x)-> {
        if(x.length==2)return x[0]+x[1];
        else throw new IllegalArgumentException();
    }),
    SUB("-",(x)-> {
        if(x.length==2)return x[0]-x[1];
        else throw new IllegalArgumentException();
    });
    private String representation;
    private Function<Double[],Double> function;
    Operator(String s, Function<Double[],Double> function) {
        representation = s;
        this.function = function;
    }

    /**
     * Returns the Operator that is symbolized by the specified string.
     * @param op the string to search for.
     * @return the Operator that is symbolized by the specified string.
     */
    public Operator fromString(String op){
        Optional optionalOperator = Arrays.stream(values()).filter(x->x.representation.equals(op)).findFirst();
        if(optionalOperator.isPresent())return (Operator) optionalOperator.get();
        return null;
    }

    public Function<Double[],Double> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return representation;
    }

}
