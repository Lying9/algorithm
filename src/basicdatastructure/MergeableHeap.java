package basicdatastructure;

/**
 * Created by ying on 2018/5/30.
 */
public class MergeableHeap {
    MyList<Integer> list;

    public MyList<Integer> makeHeap() {
        list = new MyList<>();
        return list;
    }

    public void insert(Integer k) {
        list.insertSort(k);
    }

    public Integer minimum() {
        if (list.head != null)
            return list.head.item;
        return -1;
    }

    public void extractMin() {
        if (list.head != null)
            list.delete(list.head.item);
    }

    public void union(MyList<Integer> myList) {
        MyList.Node<Integer> x = list.head;
        MyList.Node<Integer> y = myList.head;
        MyList.Node<Integer> pre = list.head;
        while (x != null && y != null) {
            if (x.item <= y.item) {
                pre = x;
                x = x.next;
            } else {
                if (x == list.head) {
                    this.list.head = y;
                    y = y.next;
                    list.head.next = x;
                    pre = list.head;
                } else {
                    pre.next = y;
                    y = y.next;
                    pre.next.next = x;
                    pre = pre.next;
                }
            }
        }
        if (y != null) {
            pre.next = y;
        }
    }

    //思考题 10-2 a
    public static void main(String[] args) {
   /*     MyList<Integer> list = new MyList();
        list.insertSort(1);
        list.insertSort(5);
        list.insertSort(10);*/
        MergeableHeap mh = new MergeableHeap();
        mh.makeHeap();
        mh.insert(1);
        mh.insert(5);
        mh.insert(10);

        /*MyList<Integer> list1 = new MyList();
        list1.insertSort(3);
        list1.insertSort(7);
        list1.insertSort(9);

        mh.union(list1);*/


        System.out.println(mh.minimum());
        mh.extractMin();
        mh.list.print();


    }


}

