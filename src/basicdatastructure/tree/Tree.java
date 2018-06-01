package basicdatastructure.tree;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by ying on 2018/6/1.
 */
public class Tree {
    Node<Integer> root;

    //  先序递归遍历
    public void preorderTreeWalk() {
        preorder(this.root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node != null) {
            System.out.print(node.key + "\t");
            preorder(node.left);
            preorder(node.right);
        }
    }

    //    先序遍历非递归
    public void preorderTreewalkNonrecursive() {
        Stack stack = new Stack();
        stack.push(this.root);
        while (!stack.empty()) {
            Node node = (Node) stack.pop();
            System.out.print(node.key + "\t");
//            根据栈的特点 右子树先入栈
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    //    中序遍历 递归
    public void inorderTreeWalk() {
        inorder(this.root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + "\t");
            inorder(node.right);
        }
    }

    //    中序遍历 非递归
    public void inorderTreeWalkNonrecursive() {
        Stack stack = new Stack();
        Node node = this.root;
        while (!stack.empty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                Node n = (Node) stack.pop();
                System.out.print(n.key + "\t");
                node = n.right;
            }
        }
    }

    //    后序遍历  递归
    public void postorderTreeWalk() {
        postorder(this.root);
        System.out.println();
    }

    private void postorder(Node<Integer> node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + "\t");
        }
    }
//    后序遍历  非递归

    /**
     * 元素第二次出现在栈顶才出栈
     * 第一次   把右子树和左子树入栈
     * 第二次   出栈输出
     * 用一个hashmap记录其出现在栈顶的次数
     */
    public void postorderTreeWalkNonrecursive() {
        Stack stack = new Stack();
        HashMap<Node, Integer> map = new HashMap();
        stack.push(this.root);
        map.put(this.root, 1);
        while (!stack.empty()) {
            Node n = (Node) stack.peek();
            if (map.get(n).equals(1)) {
                if (n.right != null) {
                    stack.push(n.right);
                    map.put(n.right, 1);
                }
                if (n.left != null) {
                    stack.push(n.left);
                    map.put(n.left, 1);
                }
                map.put(n, 2);
            } else if (map.get(n).equals(2)) {
                Node pn = (Node) stack.pop();
                System.out.print(pn.key + "\t");
            }
        }
        System.out.println();
    }


    public static class Node<E extends Comparable<E>> {
        E key;
        Node<E> p;
        Node<E> left;
        Node<E> right;

        public Node() {

        }

        public Node(E e) {
            this.key = e;
            this.p = null;
            this.left = null;
            this.right = null;
        }
    }
}
