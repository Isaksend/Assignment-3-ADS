import java.util.*;
public class Main {
    public static void main(String args[]){
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + id;
            table.put(new MyTestingClass(id, name), "Student" + i);
        }
        table.printChainLengths();
        BST<Integer, String> bst = new BST<>();
        bst.put(5, "value_5");
        bst.put(3, "value_3");
        bst.put(7, "value_7");
        bst.put(2, "value_2");
        bst.put(4, "value_4");
        bst.put(6, "value_6");
        bst.put(8, "value_8");
        for (BST.BSTEntr<Integer, String> elem : bst) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
