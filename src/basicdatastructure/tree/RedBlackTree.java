package basicdatastructure.tree;

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
    @Override
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
}
