package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ying on 2018/7/25.
 * Single source shortest path
 */
public class SSSP {
    /**
     * 初始化
     *
     * @param g 图
     * @param s 源节点属性    结点中 v.parent 记录单源最短路径上的边
     *          v.d  记录源节点到v的最短路径权重的上界   称为最短路径估计
     *
     *  注意：v.d初始化时不可初始化为integer.MAX_VALUE 因为MAX_VALUE+一个数  将越界  成为一个负数 因此
     *           将小于v.d，进而更新v.d为不正确的值
     */
    public void initializeSingleSource(Graph g, Vertex s) {
//        遍历每个顶点
        for (Vertex v : g.wgraph.keySet()) {
            v.parent = null;
            v.d = 1000000;
        }
        s.d = 0;
    }

    /**
     * 松弛函数
     *
     * @param u 边的起点
     * @param v 边的终点
     * @param w 边上的权重
     */
    public void relax(Vertex u, Vertex v, int w) {
        if (v.d > u.d + w) {
            v.d = u.d + w;
            v.parent = u;
        }
    }

    /**
     * 单源最短路径的bellman-Ford算法
     *
     * @param g 图
     * @param s 源节点
     */
    public Boolean BellmanFord(Graph g, Vertex s) {
        initializeSingleSource(g, s);
        for (int i = 0; i < g.wgraph.size() - 1; i++) {
//            对每条边
            for (Vertex v : g.wgraph.keySet()) {
                for (WeightsVertex wv : g.wgraph.get(v)) {
                    relax(v, wv.getVertex(), wv.getW());
                }
            }
        }
        for (Vertex v : g.wgraph.keySet()) {
            for (WeightsVertex wv : g.wgraph.get(v)) {
                if (wv.getVertex().d > v.d + wv.getW())
                    return false;
            }
        }
        return true;
    }

    /**
     * 单源最短路径的dijkstra算法
     * @param g
     * @param s  源节点
     */
    public void dijkstra(Graph g, Vertex s){
        initializeSingleSource(g,s);
        List<Vertex> q = new ArrayList<>();
        for(Vertex v : g.wgraph.keySet()){
            q.add(v);
        }
        Collections.sort(q);
        while(q.size() > 0){
            Vertex v = q.remove(0);
//            遍历和v相连的每一条边
            for(WeightsVertex wv : g.wgraph.get(v)){
                relax(v,wv.getVertex(),wv.getW());
            }
            Collections.sort(q);
        }

    }

    public void printMinSourceShortestPath(Graph g) {
        for (Vertex v : g.wgraph.keySet()) {
            System.out.println(v.key + "\t" + v.d);
        }
    }
}
