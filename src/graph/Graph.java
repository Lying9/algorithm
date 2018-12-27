package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ying on 2018/7/9.
 */
public class Graph {
    Map<Vertex, List<Vertex>> graph;

    Map<Vertex, List<WeightsVertex>> wgraph;

    public Graph() {
        graph = new HashMap<>();
        wgraph = new HashMap<>();
    }

    public Graph(Map<Vertex, List<WeightsVertex>> graph) {
        this.wgraph = graph;
    }

    public void initGraph() {
        graph = new HashMap<>();
        wgraph = new HashMap<>();
//单源最短路径
        Vertex s = new Vertex('s');
        Vertex t = new Vertex('t');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');
        Vertex z = new Vertex('z');

        List<WeightsVertex> lists = new ArrayList<>();
        lists.add(new WeightsVertex(t,10));
        lists.add(new WeightsVertex(y,5));

        List<WeightsVertex> listt = new ArrayList<>();
        listt.add(new WeightsVertex(x,1));
        listt.add(new WeightsVertex(y,2));
//        listt.add(new WeightsVertex(z,-4));

        List<WeightsVertex> listx = new ArrayList<>();
        listx.add(new WeightsVertex(z,4));

        List<WeightsVertex> listy = new ArrayList<>();
        listy.add(new WeightsVertex(z,2));
        listy.add(new WeightsVertex(x,9));
        listy.add(new WeightsVertex(t,3));

        List<WeightsVertex> listz = new ArrayList<>();
        listz.add(new WeightsVertex(x,6));
        listz.add(new WeightsVertex(s,7));

        wgraph.put(s,lists);
        wgraph.put(t,listt);
        wgraph.put(x,listx);
        wgraph.put(y,listy);
        wgraph.put(z,listz);

//强联通分量
        /*Vertex a = new Vertex('a');
        Vertex b = new Vertex('b');
        Vertex c = new Vertex('c');
        Vertex d = new Vertex('d');
        Vertex e = new Vertex('e');
        Vertex f = new Vertex('f');
        Vertex g = new Vertex('g');
        Vertex h = new Vertex('h');

        List<Vertex> lista = new ArrayList<>();
        lista.add(b);
        List<Vertex> listb = new ArrayList<>();
        listb.add(e);
        listb.add(c);
        listb.add(f);
        List<Vertex> listc = new ArrayList<>();
        listc.add(g);
        listc.add(d);
        List<Vertex> listd = new ArrayList<>();
        listd.add(h);
        listd.add(c);
        List<Vertex> liste = new ArrayList<>();
        liste.add(a);
        liste.add(f);
        List<Vertex> listg = new ArrayList<>();
        listg.add(f);
        listg.add(h);
        List<Vertex> listh = new ArrayList<>();
        listh.add(h);
        List<Vertex> listf = new ArrayList<>();
        listf.add(g);

        graph.put(c,listc);
        graph.put(d,listd);
        graph.put(e,liste);
        graph.put(f,listf);
        graph.put(g,listg);
        graph.put(h,listh);
        graph.put(a,lista);
        graph.put(b,listb);*/


//搜索
      /*  Vertex s = new Vertex('s');
        Vertex r = new Vertex('r');
        Vertex t = new Vertex('t');
        Vertex u = new Vertex('u');
        Vertex v = new Vertex('v');
        Vertex w = new Vertex('w');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');

        List<Vertex> listr = new ArrayList<>();
        listr.add(v);
        listr.add(s);
        List<Vertex> lists = new ArrayList<>();
        lists.add(r);
        lists.add(w);
        List<Vertex> listt = new ArrayList<>();
        listt.add(w);
        listt.add(x);
        listt.add(u);
        List<Vertex> listu = new ArrayList<>();
        listu.add(t);
        listu.add(x);
        listu.add(y);
        List<Vertex> listv = new ArrayList<>();
        listv.add(r);
        List<Vertex> listw = new ArrayList<>();
        listw.add(s);
        listw.add(t);
        listw.add(x);
        List<Vertex> listx = new ArrayList<>();
        listx.add(w);
        listx.add(t);
        listx.add(y);
        listx.add(u);
        List<Vertex> listy = new ArrayList<>();
        listy.add(u);
        listy.add(x);

        graph.put(r, listr);
        graph.put(s, lists);
        graph.put(t, listt);
        graph.put(u, listu);
        graph.put(v, listv);
        graph.put(w, listw);
        graph.put(x, listx);
        graph.put(y, listy);*/
    }

    /**
     * 把图中所有边的方向反过来 成为一个 新图
     *
     * @return 返回新图
     */
//    没有权重
/*    public Graph graphTranspose() {
        Map<Vertex, List<Vertex>> map = new HashMap<>();
        for (Vertex v : graph.keySet()) {
            map.put(v, new ArrayList<>());
        }
        for (Vertex v : graph.keySet()){
            for(Vertex u : graph.get(v)){
                map.get(u).add(v);
            }
        }
        return new Graph(map);
    }*/
//有权重
    public Graph graphTranspose() {
        Map<Vertex, List<WeightsVertex>> map = new HashMap<>();
        for (Vertex v : wgraph.keySet()) {
            map.put(v, new ArrayList<>());
        }
        for (Vertex v : wgraph.keySet()) {
            for (WeightsVertex u : wgraph.get(v)) {
                Vertex node = u.getVertex();
                map.get(node).add(new WeightsVertex(v,u.getW()));
            }
        }
        return new Graph(map);
    }

    //    无权重
   /* public void print(){
        for (Vertex v : graph.keySet()){
            System.out.print("顶点："+v.key+"\t临界链表：");
            for(Vertex u : graph.get(v)){
                System.out.print(u.key);
            }
            System.out.println();
        }
    }*/
//   有权重
    public void print() {
        for (Vertex v : wgraph.keySet()) {
            System.out.print("顶点：" + v.key + "\t临界链表：");
            for (WeightsVertex u : wgraph.get(v)) {
                System.out.print(u.getVertex().key);
            }
            System.out.println();
        }
    }
}
