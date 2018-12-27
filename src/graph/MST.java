package graph;

import java.util.*;

/**
 * Created by ying on 2018/7/12.
 */
public class MST {
    /**
     * 最小生成树算法
     *
     * @param vertices 图的所有顶点的集合
     * @param nodes    图的所有边的集合（包含权重）
     */
    public void kruskal(List<WVertex> vertices, List<WNode> nodes) {
        List<Set<WVertex>> list = new ArrayList<>();
//        每一个顶点单独在一个集合
        for (WVertex c : vertices) {
            Set<WVertex> set = new HashSet<>();
            set.add(c);
            list.add(set);
        }
        Collections.sort(nodes);
        for (WNode node : nodes) {
//            判断边的两个顶点是否在一个集合里
            int uset = findVertices(list, node.u);
            int vset = findVertices(list, node.v);
            if (uset != vset) {
                System.out.println("把边" + node.u.key + "\t" + node.v.key + "加入最小生成树中");
//                合并 集合
                list.get(uset).addAll(list.get(vset));
                list.remove(vset);
            }
        }
    }

    public int findVertices(List<Set<WVertex>> list, WVertex target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(target))
                return i;
        }
        return -1;
    }

    /**
     * @param vertices
     * @param nodes
     * @param r        PriorityQueue在加入时排序，poll（）后排序
     *                 而本方法中poll（）后对队列中的值做了改变，而这时队列并没有重新排序，所以在poll()时得到的是没有改变之前的，而不是改变之后的
     */
    public void prim(List<WVertex> vertices, List<WNode> nodes, WVertex r) {
//        PriorityQueue<WVertex> queue = new PriorityQueue();
        List<WVertex> queue = new ArrayList<>();
        for (WVertex v : vertices) {
            v.parent = null;
            v.minw = Integer.MAX_VALUE;
            queue.add(v);
        }
        r.minw = 0;
        r.parent = r;
        while (!queue.isEmpty()) {
            Collections.sort(queue);
            WVertex w = queue.get(0);
            queue.remove(0);
            System.out.println("把边" + w.key + "\t" + w.parent.key + "加入最小生成树中");
//            遍历所有与w相连的边
            for (WNode node : nodes) {
                if (w.equals(node.u)) {
                    if (queue.contains(node.v) && node.v.minw > node.w) {
                        node.v.minw = node.w;
                        node.v.parent = w;
                    }
                } else if (w.equals(node.v)) {
                    if (queue.contains(node.u) && node.u.minw > node.w) {
                        node.u.minw = node.w;
                        node.u.parent = w;
                    }
                }
            }
        }
    }

}

class Vertexcompare implements Comparator<WVertex> {

    @Override
    public int compare(WVertex o1, WVertex o2) {
        if (o1.minw > o2.minw)
            return 1;
        else if (o1.minw < o2.minw)
            return -1;
        else
            return 0;
    }
}

class WVertex implements Comparable<WVertex> {
    WVertex parent;
    //    最小权重
    int minw;
    char key;

    public WVertex(char key) {
        this.key = key;
        parent = null;
        minw = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(WVertex o) {
        if (this.minw > o.minw)
            return 1;
        else if (this.minw < o.minw)
            return -1;
        else
            return 0;
    }
}

class WNode implements Comparable<WNode> {
    int w;
    WVertex u;
    WVertex v;

    public WNode(int w, WVertex u, WVertex v) {
        this.w = w;
        this.u = u;
        this.v = v;
    }

    @Override
    public int compareTo(WNode node) {
        if (this.w > node.w)
            return 1;
        else if (this.w < node.w)
            return -1;
        else return 0;
    }
}
