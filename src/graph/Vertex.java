package graph;

/**
 * Created by ying on 2018/7/9.
 */
public class Vertex implements  Comparable<Vertex>{
    char key;
    //    0 白色  1灰色  2 黑色
    int color;
    //    记录前一个结点
    Vertex parent;
    //    bfs距离源的距离
    int bd;
    //  dfs发现时间
    int d;
    //    完成时间
    int f;

    public Vertex(char key) {
        this.key = key;
        color = 0;
        parent = null;
        bd = Integer.MAX_VALUE;
        d = 0;
        f = 0;
    }


    @Override
    public int compareTo(Vertex o) {
        if(this.d < o.d)
            return -1;
        else if(this.d > o.d)
            return 1;
        else
           return 0;
    }
}
class  WeightsVertex{
    Vertex vertex;
//    权重
    int w;
    public WeightsVertex(Vertex vertex,int w){
        this.vertex = vertex;
        this.w = w;
    }
    public Vertex getVertex(){
        return vertex;
    }
    public int getW(){
        return w;
    }
}

