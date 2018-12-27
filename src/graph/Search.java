package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ying on 2018/7/9.
 *
 * 注意：虽然初始化工作在构造函数中已经做了，但是在搜索时还是要进行初始化
 * 因为要搜索的图可能不是刚初始化的图 或者  进行了搜索后的图再次搜索
 */

public class Search {
    int time;


    //    广度优先搜索
    public void bfs(Graph g, Vertex s) {
//        初始化所有顶点的color bd parent 属性 这个工作在构造图时已经做了
        for (Vertex v : g.graph.keySet()) {
            v.color = 0;
            v.parent = null;
            v.bd = Integer.MAX_VALUE;
        }

        s.color = 1;
        s.bd = 0;
        Deque<Vertex> queue = new ArrayDeque();
        queue.add(s);
        while (queue.size() > 0) {
            Vertex u = queue.poll();
            List<Vertex> list = g.graph.get(u);
//            遍历与该结点相连的所有边
            for (Vertex v : list) {
                if (v.color == 0) {
                    v.color = 1;
                    v.parent = u;
                    v.bd = u.bd + 1;
                    System.out.print(v.key + "\t");
                    queue.add(v);
                }
            }
            u.color = 2;
        }
    }

    //深度优先搜索
/*    public void dfs(Graph g) {
//       初始化所有顶点的color parent d  f  属性 这个工作在构造图时已经做了
        time = 0;
        for (Vertex v : g.graph.keySet()) {
            if (v.color == 0)
                dfsVisit(g, v);
        }
    }*/
    public List<Vertex> dfs(Graph g) {
//       初始化所有顶点的color parent d  f  属性 这个工作在构造图时已经做了
        for (Vertex v : g.graph.keySet()) {
            v.color = 0;
            v.parent = null;
        }

        time = 0;
        List<Vertex> list = new ArrayList<>();
        for (Vertex v : g.graph.keySet()) {
            if (v.color == 0)
                dfsVisit(g, v, list);
        }
        return list;
    }


    /* private void dfsVisit(Graph g, Vertex v) {
         v.color = 1;
         v.d = ++time;
         System.out.print(v.key + "\ttime:" + v.d + "\t");
         for (Vertex vertex : g.graph.get(v)) {
             if (vertex.color == 0) {
                 vertex.parent = v;

                 dfsVisit(g, vertex);
             }
         }
         v.color = 2;
         v.f = ++time;
     }*/
    //    list 拓扑排序  按完成时间 正序存储
    private void dfsVisit(Graph g, Vertex v, List<Vertex> list) {
        v.color = 1;
        v.d = ++time;
//       System.out.print(v.key + "\ttime:" + v.d + "\t");
        for (Vertex vertex : g.graph.get(v)) {
            if (vertex.color == 0) {
                vertex.parent = v;

                dfsVisit(g, vertex, list);
            }
        }
        v.color = 2;
        v.f = ++time;
        list.add(v);
    }

    public void dfsOrder(Graph g, List<Vertex> list) {
//       初始化所有顶点的color parent d  f  属性 这个工作在构造图时已经做了
        for (Vertex v : g.graph.keySet()) {
            v.color = 0;
            v.parent = null;
        }

        time = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Vertex v = list.get(i);
            if (v.color == 0) {
                System.out.println(v.key);
                dfsVisit(g, v, list);
            }
        }
    }

    //计算强连通分量
    public void stronglyConnectedComponents(Graph g) {
        List<Vertex> list = dfs(g);
        Graph graph = g.graphTranspose();
        dfsOrder(graph, list);
    }

}
