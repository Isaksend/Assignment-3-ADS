import java.lang.Comparable;
import java.util.LinkedList;

public class MyHashTable<K extends Comparable<K>, V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K,V> next;
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K,V>[] chainArray;
    private int M = 11;
    private int size;
    public MyHashTable(){
        chainArray = new HashNode[M];
    }
    public MyHashTable(int M){
        chainArray = new HashNode[M];
    }
    private int hash(K key){
        return (key.hashCode() % M);
    }
    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
        if ((1.0 * size) / M >= 0.7) {
            resize();
        }
    }
    private void resize() {
        LinkedList<HashNode<K, V>> temp = new LinkedList<>();
        for (HashNode<K, V> headNode : chainArray) {
            while (headNode != null) {
                temp.add(headNode);
                headNode = headNode.next;
            }
        }

        M = 2 * M;
        chainArray = new HashNode[M];
        size = 0;

        for (HashNode<K, V> node : temp) {
            put(node.key, node.value);
        }
    }
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            chainArray[index] = head.next;
        }

        return head.value;
    }
    public boolean contains(V value){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }
    public K getKey(V value){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }
}
