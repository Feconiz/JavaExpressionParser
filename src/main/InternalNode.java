package main;

public class InternalNode implements Node {
    private Node left, right;
    private Operator operator;

    public InternalNode(Node left, Node right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Gets left.
     *
     * @return Value of left.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets new left.
     *
     * @param left New value of left.
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Gets right.
     *
     * @return Value of right.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets new right.
     *
     * @param right New value of right.
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Gets operator.
     *
     * @return Value of operator.
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Sets new operator.
     *
     * @param operator New value of operator.
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public double evaluate() {
        Double[] args = new Double[2];
        if(right != null) {
            args[0] = right.evaluate();
            if (left != null) args[0] = left.evaluate();//this is a nested if because functions like sin take the right element as input without having a left input.
        }
        return operator.getFunction().apply(args);
    }
    @Override
    public String toString() {
        return '(' + left.toString() + operator.toString() + right.toString() + ')';
    }
}
