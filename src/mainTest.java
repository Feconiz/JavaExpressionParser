import main.InternalNode;
import main.Leaf;
import main.Operator;
import main.Parser;

public class mainTest {
    public static void main(String[] args){
        Leaf left = new Leaf(1);
        Leaf right = new Leaf(2);
        InternalNode a = new InternalNode(left,right, Operator.SUB);
        InternalNode b = new InternalNode(a,new Leaf(3),Operator.ADD);
        System.out.println(b.toString() + ": " + b.evaluate());
        System.out.println(Parser.parse("(a+b)"));
    }
}
