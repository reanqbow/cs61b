import java.awt.desktop.SystemEventListener;

public class ArrayDeque<AnyType> {

    private AnyType[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        item = (AnyType[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }
    private void resize(int cap) {
        AnyType[] a = (AnyType[]) new Object[cap];
        System.arraycopy(item, 0, a, 0, size);
        item = a;
    }

    public void addFirst(AnyType i) {
        if (size == item.length) {
            resize(size * 2);
            nextFirst = 0;
            nextLast = size;
        }
        if (this.isEmpty()) {
            nextLast = 1;
        }
        item[nextFirst] = i;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = item.length - 1;
        }
        size += 1;
    }

    public void addLast(AnyType i) {
        if (size == item.length) {
            resize(size * 2);
            nextFirst = 0;
            nextLast = size;
        }
        if (this.isEmpty()) {
            nextFirst = item.length - 1;
        }
        item[nextLast] = i;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < item.length; i++) {
            System.out.println(item[i]);
        }
    }

    public AnyType removeFirst() {
        AnyType i = item[nextFirst + 1];
        if (size / item.length < 0.25) {
            resize(size / 2);
        }
        item[nextFirst + 1] = null;
        nextFirst += 1;
        return i;
    }

    public AnyType removeLast() {
        AnyType i = item[nextLast - 1];
        if (size / item.length < 0.25) {
            resize(size / 2);
        }
        item[nextLast - 1] = null;
        nextLast -= 1;
        return i;
    }

    public AnyType get(int index) {
        return item[index];
    }

    //DEBUGGING

    public static void main(String[] args) {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(3);
        ad1.addFirst(5);
        ad1.printDeque();
    }


}
