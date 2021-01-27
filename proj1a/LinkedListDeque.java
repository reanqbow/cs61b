import javax.swing.plaf.synth.SynthTextAreaUI;

class LinkedListDeque<AnyType> {
    // Node class.
    public class Node {
        public AnyType item;
        public Node next;
        public Node prev;

        //The constructor.
        public Node() {
        }
        public Node(AnyType i) {
            this.item = i;
        }
        public AnyType getNode() {
            return this.item;
        }
    }

    // Members of the LinkedListDeque Class.
    private int size = 0;
    private Node sentinel;

    //The constructor without parameters.
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(AnyType t) {
        Node newStuff = new Node(t);
        if (this.size == 0) {
            sentinel.prev = newStuff;
        }
        newStuff.next = sentinel.next;
        newStuff.next.prev = newStuff;
        newStuff.prev = sentinel;
        sentinel.next = newStuff;
        size += 1;
    }

    public  void addLast(AnyType t) {
        Node newStuff = new Node(t);
        newStuff.next = sentinel;
        newStuff.prev = sentinel.prev;
        sentinel.prev.next = newStuff;
        sentinel.prev = newStuff;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ref = sentinel.next;
        if (ref.item == null) {
            System.out.println("Empty List!!!");
        }
        while (ref.item != null) {
            System.out.println(ref.item);
            ref = ref.next;
        }
    }

    public AnyType removeFirst() {
        Node first = sentinel.next;
        if (this.isEmpty()) {
            return first.item;
        }
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        //System.out.println("now we have " + size + " item(s).");
        if (size == 0) {
            System.out.println("It has been cleared out.");
            return null;
        }
        return first.item;
    }

    public AnyType removeLast() {
        Node last = sentinel.prev;
        if (this.isEmpty()) {
            System.out.println("It's cleared.");
            return last.item;
        }
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        //System.out.println("now we have " + size + " item(s).");
        if (size == 0) {
            System.out.println("It has been cleared out.");
            return null;
        }
        return last.item;
    }

    public AnyType get(int index) {
        Node ref = sentinel.next;
        while (index != 0) {
            ref = ref.next;
            index -= 1;
        }
        return ref.item;
    }


    public AnyType getRecursive(int index) {
        return helper(sentinel.next, index);
    }

    public AnyType helper(Node k, int n) {
        if (n == 0) {
            return k.item;
        } else {
            return helper(k.next, n - 1 );
        }
    }

    public static void main(String[] args) {
        //DEBUGGING
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addFirst(2);
        System.out.println(lld1.sentinel.next.item);
        lld1.addFirst(3);
        System.out.println(lld1.sentinel.next.item);
        lld1.addLast(4);
        System.out.println(lld1.sentinel.next.next.item);
        lld1.addLast(5);
        System.out.println(lld1.sentinel.next.next.item);
        int i = lld1.get(2);
        System.out.println(i);
        int iq = lld1.getRecursive(2);
        System.out.println(iq);
        lld1.printDeque();
    }
}

