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

    public Integer minimum(Node node) {
        while (node.left != null)
            node = node.left;
        return (Integer) node.key;
    }

    //    返回二叉搜索树中的最大元素
    public Integer maximum() {
        Node node = this.root;
        while (node.right != null)
            node = node.right;
        return (Integer) node.key;
    }

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
    public Integer successor(Node node) {
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
    }
    public Integer predecessor(Node node){
        if(node.left!=null)
            return maximum(node.left);
        Node y = node.p;
        while(y!=null && y.left == node){
            node = y;
            y = y.p;
        }
        if(y == null)
            return -1;
        return (Integer)y.key;
    }
}
