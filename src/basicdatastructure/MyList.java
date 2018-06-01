package basicdatastructure;

/**
 * Created by ying on 2018/5/30.
 */
public class MyList<E extends Comparable<E>> {
    Node<E> head;

    MyList() {
        head = null;
    }

    // 查找  若没有返回空
    public Node<E> search(E k) {
        Node<E> x = this.head;
        while (x != null && k != x.item)
            x = x.next;
        return x;
    }

    //    插入   头结点插入
    public void insert(E k) {
        Node<E> node = new Node<E>(k, null);
        node.next = this.head;
        head = node;
    }

    //    有序插入
    public void insertSort(E k) {
        Node<E> newnode = new Node<E>(k, null);
        if (this.head == null) {
            this.head = newnode;
            return;
        }
        Node<E> node = this.head;
        Node<E> prev = this.head;
        while (node != null && node.item.compareTo(k) < 0) {
            prev = node;
            node = node.next;
        }
        if (node != null) {
            if (node == this.head) {
                newnode.next = node;
                this.head = newnode;
            } else {
                prev.next = newnode;
                newnode.next = node;
            }
        } else {
            prev.next = newnode;
        }
    }

    //    删除
    public void delete(E k) {
        if (this.head == null)
            return;
        Node<E> node = this.head;
        Node<E> prev = this.head;
        while (node != null && node.item != k) {
            prev = node;
            node = node.next;
        }
        if (node != null) {
            if (node == this.head) {
                this.head = this.head.next;
            } else prev.next = node.next;
        }
    }

    //    遍历
    public void print() {
        Node<E> x = this.head;
        while (x != null) {
            System.out.print(x.item + "\t");
            x = x.next;
        }
        System.out.println();
    }


    static class Node<E extends Comparable<E>> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

}
