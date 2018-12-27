package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ying on 2018/7/9.
 */
public class Test {
    public static void main(String[] args) {
    /*    Graph g = new Graph();
        g.initGraph();*/

//        测试转置
  /*      Graph gt = g.graphTranspose();
        gt.print();*/
//测试搜索
     /*   Search search = new Search();
        Vertex vertex = null;
        for (Vertex v : g.graph.keySet()) {
            if (v.key == 's') {
                vertex = v;
                break;
            }
        }
//        search.bfs(g,vertex);
        search.dfs(g);*/
//测试强连通
     /*   Search search = new Search();
        search.stronglyConnectedComponents(g);*/

//     最小生成树 kruskal算法
      /*  List<WVertex> vertices = new ArrayList<>();
        WVertex a = new WVertex('a');
        WVertex b = new WVertex('b');
        WVertex c = new WVertex('c');
        WVertex d = new WVertex('d');
        WVertex e = new WVertex('e');
        WVertex f = new WVertex('f');
        WVertex g = new WVertex('g');
        WVertex h = new WVertex('h');
        WVertex i = new WVertex('i');
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);
        vertices.add(f);
        vertices.add(g);
        vertices.add(h);
        vertices.add(i);

        List<WNode> nodes = new ArrayList<>();
        nodes.add(new WNode(4, a, b));
        nodes.add(new WNode(8, a, h));
        nodes.add(new WNode(11, h, b));
        nodes.add(new WNode(1, h, g));
        nodes.add(new WNode(7, c, d));
        nodes.add(new WNode(7, h,i));
        nodes.add(new WNode(8,c,b));
        nodes.add(new WNode(2,c,i));
        nodes.add(new WNode(4,c,f));
        nodes.add(new WNode(6,i,g));
        nodes.add(new WNode(2,f,g));
        nodes.add(new WNode(10,f,e));
        nodes.add(new WNode(14,f,d));
        nodes.add(new WNode(9,e,d));


        MST mst = new MST();
//        mst.kruskal(vertices, nodes);
        mst.prim(vertices,nodes,a);*/
//      单源最短路径 测试
     /*   Graph g = new Graph();
        g.initGraph();
        SSSP sssp = new SSSP();
        Vertex vertex = null;
        for (Vertex v : g.wgraph.keySet()) {
            if (v.key == 's') {
                vertex = v;
                break;
            }
        }
//        g.print();
//      System.out.println(sssp.BellmanFord(g,vertex));
        sssp.dijkstra(g,vertex);
        sssp.printMinSourceShortestPath(g);*/


//     所有节点的最短路径 测试
        int max= 100000;
        int[][] W = {{0,3,8,max,-4},
                {max,0,max,1,7},
                {max,4,0,max,max},
                {2,max,-5,0,max},
                {max,max,max,6,0}
        };
        SPFAN spfan = new SPFAN();
//        spfan.print(spfan.slowAllPairsShortestPaths(W));
//        spfan.print(spfan.fasterAllPairsShortestPaths(W));
        spfan.print(spfan.floydWarshall(W));
    }
}
