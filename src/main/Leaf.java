package main;

public class Leaf implements Node {
    private double value;
    public Leaf(double value){
        this.value = value;
    }

    /**
     * Gets the value of this leaf.
     *
     * @return the value of this leaf.
     */
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
    @Override
    public double evaluate() {
        return value;
    }
}
