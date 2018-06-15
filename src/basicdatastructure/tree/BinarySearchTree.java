package basicdatastructure.tree;

/**
 * Created by ying on 2018/6/1.
 */
public class BinarySearchTree extends Tree {
    //二叉搜索树插入
    public void insert(int key) {
        if (this.root == null) {
            root = new Node<>(key);
        } else {
            Node<Integer> newNode = new Node<>(key);
            Node parent = null;
            Node x = this.root;
            boolean flag = false;
            while (x != null) {
                parent = x;
                if (x.key.compareTo(key) < 0) {
                    flag = true;
                    x = x.right;
                } else {
                    flag = false;
                    x = x.left;
                }
            }
            if (flag)
                parent.right = newNode;
            else
                parent.left = newNode;
            newNode.p = parent;
        }
    }

    //    删除
    public void delete(Node z) {
        if (z.left == null)
            transplant(z, z.right);
        else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node y = successor(z);
           /* if (y == z.right) {
                transplant(z, y);
                y.left = z.left;
                y.left.p = y;
            } else {
                z.key = y.key;
                transplant(y, y.right);
            }*/
//           y可能是z的右孩子也可能不是z的右孩子
           if(y.p!=z){
               transplant(y,y.right);
               y.right = z.right;
               y.right.p = y;
           }
           transplant(z,y);
           y.left = z.left;
           y.left.p = y;
        }
    }

    /**
     * 用以v为根的树   代替  以u为根的树
     */
    public void transplant(Node u, Node v) {
        if (u == this.root) {
            this.root = v;
        } else if (u.p.left == u) {
            u.p.left = v;
        } else
            u.p.right = v;
        if (v != null)
            v.p = u.p;
    }

    //    查找  递归
    public Node search(Node node, Integer key) {
        if (node == null || node.key == key)
            return node;
        else if (node.key.compareTo(key) < 0) {
            return search(node.right, key);
        } else
            return search(node.left, key);
    }

    //查找    非递归
    public Node searchNonrecursive(Node node, Integer key) {
        while (node != null && node.key != key) {
            if (node.key.compareTo(key) < 0)
                node = node.right;
            else
                node = node.left;
        }
        return node;
    }

    //    返回二叉搜索树中的最小元素
    public Integer minimum() {
        Node node = this.root;
        while (node.left != null)
            node = node.left;
        return (Integer) node.key;
    }

    //返回以node为根的树的最小元素
    public Node minimum(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
   /* public Integer minimum(Node node) {
        while (node.left != null)
            node = node.left;
        return (Integer) node.key;
    }*/

    //    返回二叉搜索树中的最大元素
    public Integer maximum() {
        Node node = this.root;
        while (node.right != null)
            node = node.right;
        return (Integer) node.key;
    }

    //返回以node为根的树的最大元素
    public Integer maximum(Node node) {
        while (node.right != null)
            node = node.right;
        return (Integer) node.key;
    }

//    返回后继节点

    /**
     * @param node 返回node的后继节点的key值
     *             如果node有右子树 则后继节点是其右子树中的最小值
     *             没有右子树   该节点的有左孩子的最底层祖先
     */
   /* public Integer successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        Node y = node.p;
        while(y!=null && y.right==node){
            node = y;
            y = y.p;
        }
        if(y == null)
            return -1;
        return (Integer)y.key;
    }*/
    public Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        Node y = node.p;
        while (y != null && y.right == node) {
            node = y;
            y = y.p;
        }
        return y;
    }

    public Integer predecessor(Node node) {
        if (node.left != null)
            return maximum(node.left);
        Node y = node.p;
        while (y != null && y.left == node) {
            node = y;
            y = y.p;
        }
        if (y == null)
            return -1;
        return (Integer) y.key;
    }
}
