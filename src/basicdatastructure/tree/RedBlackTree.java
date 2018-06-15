package basicdatastructure.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by ying on 2018/6/5.
 */
public class RedBlackTree extends BinarySearchTree {
    //    哨兵 color属性是黑色
    Node nil = new Node();

    public RedBlackTree() {
        super();
        this.root = nil;
        nil.color = 0;//黑色
    }

    //插入方法
    public void insert(int key) {
        Node newnode = new Node(key);
        Node y = this.nil;
        Node x = this.root;
        while (x != this.nil) {
            y = x;
            if (x.key.compareTo(key) < 0)
                x = x.right;
            else
                x = x.left;
        }
        newnode.p = y;
        if (y == this.nil)
            this.root = newnode;
        else if (y.key.compareTo(key) < 0)
            y.right = newnode;
        else y.left = newnode;
        newnode.left = this.nil;
        newnode.right = this.nil;
        newnode.color = 1;
        rbInsertFixup(newnode);
    }
//对插入节点  进行调整

    /**
     * 初始：z是红色的
     *
     * @param z
     */
    private void rbInsertFixup(Node z) {
        while (z.p.color == 1) {    //1 红色
            if (z.p.p.left == z.p) {
                Node y = z.p.p.right;
                if (y.color == 1) {
                    z.p.p.color = 1;
                    z.p.color = 0;
                    y.color = 0;
                    z = z.p.p;
                } else if (z == z.p.right) {
                    z = z.p;
                    leftRotate(z);
                } else {
                    z.p.color = 0;
                    z.p.p.color = 1;
                    rightRotate(z.p.p);//z不变
                }
            } else {
                Node y = z.p.p.left;
                if (y.color == 1) {
                    z.p.p.color = 1;
                    z.p.color = 0;
                    y.color = 0;
                    z = z.p.p;
                } else if (z.p.left == z) {
                    z = z.p;
                    rightRotate(z);
                } else {
                    z.p.color = 0;
                    z.p.p.color = 1;
                    leftRotate(z.p.p);
                }
            }
        }
        this.root.color = 0;
    }


    //    左旋 函数    x的右子树不空
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (x.right != this.nil)
            x.right.p = x;

        y.p = x.p;
        if (x.p == this.nil)
            this.root = y;
        else if (x.p.left == x)
            x.p.left = y;
        else x.p.right = y;

        y.left = x;
        x.p = y;
    }

    public void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != this.nil)
            x.right.p = y;

        x.p = y.p;
        if (y.p == this.nil)
            this.root = x;
        else if (y.p.left == y)
            y.p.left = x;
        else y.p.right = x;

        x.right = y;
        y.p = x;
    }


    //    删除的辅助函数   v代替u
    public void transplant(Node u, Node v) {
        if (u.p == this.nil) {
            this.root = v;
        } else if (u == u.p.left)
            u.p.left = v;
        else u.p.right = v;
        v.p = u.p;
    }

    public void delete(Node z) {
        Node y = z;
        Node x;
        int color = y.color;
        if (z.left == this.nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == this.nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = successor(z);
            color = y.color;
            x = y.right;
            if (y == z.right) {
                x.p = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (color == 0)//0黑色
            rbDeleteFixuup(x);
    }

    public void rbDeleteFixuup(Node x) {
        while (x != this.root && x.color == 0) {
            if (x == x.p.left) {
                Node w = x.p.right;
                if (w.color == 1) {   //1 red
                    w.color = 0;
                    x.p.color = 1;
                    leftRotate(x.p);
                    w = x.p.right;
                }
                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.p;
                } else {
                    if (w.right.color == 0) {
                        w.left.color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = 0;
                    w.right.color = 0;
                    leftRotate(x.p);
                    x = this.root;
                }
            } else {
                Node w = x.p.left;
                if (w.color == 1) {
                    w.color = 0;
                    x.p.color = 1;
                    rightRotate(x.p);
                    w = x.p.left;
                }
                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.p;
                } else {
                    if (w.left.color == 0) {
                        w.right.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = 0;
                    w.left.color = 0;
                    rightRotate(x.p);
                    x = this.root;
                }
            }
        }
        x.color = 0;
    }

    public Node successor(Node node) {
        if (node.right != this.nil) {
            return minimum(node.right);
        }
        Node y = node.p;
        while (y != this.nil && y.right == node) {
            node = y;
            y = y.p;
        }
        return y;
    }

    //返回以node为根的树的最小元素
    public Node minimum(Node node) {
        while (node.left != this.nil)
            node = node.left;
        return node;
    }

    //  先序递归遍历
    public void preorderTreeWalk() {
        preorder(this.root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node != this.nil) {
            System.out.print(node.key + "[" + node.color + "]" + "\t");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void cengciTreeWalk() {
        Queue queue = new ArrayDeque();
        if (this.root != this.nil)
            queue.add(this.root);
        while (!queue.isEmpty()) {
            Node x = (Node) queue.poll();
            if (x.left != this.nil)
                queue.add(x.left);
            if (x.right != this.nil)
                queue.add(x.right);
            System.out.print(x.key + "[" + x.color + "]" + "\t");
        }
        System.out.println();
    }
}
