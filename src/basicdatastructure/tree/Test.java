package basicdatastructure.tree;

/**
 * Created by ying on 2018/6/1.
 */
public class Test {
    public static void main(String[] args) {

//        BinarySearchTree tree = new BinarySearchTree();
   /*     tree.insert(6);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);
        tree.insert(2);
        tree.insert(10);
        tree.insert(9);*/
//        tree.preorderTreewalkNonrecursive();
//        tree.inorderTreeWalkNonrecursive();
//        tree.postorderTreeWalkNonrecursive();
//        查找测试
       /* Tree.Node n = tree.searchNonrecursive(tree.root,2);
        if(n!=null)
            System.out.println(n.key);
        else
            System.out.println("没有该元素");*/
//       查找最大、小元素测试
      /*  System.out.println(tree.minimum());
        System.out.println(tree.maximum());*/
//      后继节点测试
       /* System.out.println(tree.successor(tree.search(tree.root,10)));
        System.out.println(tree.successor(tree.search(tree.root,2)));
        System.out.println(tree.successor(tree.search(tree.root,6)));*/
//       删除测试
       /* tree.insert(12);
        tree.insert(5);
        tree.insert(18);
        tree.insert(2);
        tree.insert(9);
        tree.insert(15);
        tree.insert(19);
        tree.insert(13);
        tree.insert(17);
        tree.delete(tree.search(tree.root,13));
        tree.delete(tree.search(tree.root,15));
        tree.delete(tree.search(tree.root,12));*/

//       红黑树测试
        RedBlackTree rb = new RedBlackTree();
       int[] nums = {11,2,14,7,8,15,5,4,1};
        for (int i = 0; i < nums.length; i++) {
            rb.insert(nums[i]);
        }
    }
}
