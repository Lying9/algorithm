package sort;

import java.util.ArrayList;

/**
 * Created by ying on 2019/3/2.
 */
public class MergeList {
    public static void main(String []args) {

        ArrayList<Integer> list = new ArrayList<>();
        // System.out.println("Hello World!");
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        ListNode q = new ListNode(3);
        ListNode p = new ListNode(5);
        ListNode m=	new ListNode(10);
        ListNode n =	new ListNode(16);
        q.next = p;
        p.next = m;
        m.next = n;
        ListNode result =Merge(head,q);
        while(result!= null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
    public static   ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null)
            return list2;
        else if(list2 == null)
            return list1;
        ListNode head1 = list1,head2 = list2,pre,head;
        if(list1.val > list2.val){
            head = list2;
            head2 = head2.next;
        }
        else{
            head = list1;
            head1 = head1.next;
        }
        pre = head;
        while(head1 !=null && head2 !=null ){
            if(head1.val > head2.val){
                pre.next = head2;
                pre = pre.next;
                head2 = head2.next;
            }else{
                pre.next = head1;
                pre = pre.next;
                head1 = head1.next;
            }
        }
        if(head1 != null){
            pre.next = head1;
        }
        if(head2 != null){
            pre.next = head2;
        }
        return head;
    }


}


 class ListNode {
     int val;
     ListNode next = null;

     ListNode(int val) {
         this.val = val;
     }
 }



