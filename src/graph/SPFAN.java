package graph;

/**
 * Created by ying on 2018/8/3.
 * Shortest path for all nodes
 */
public class SPFAN {
    /**
     * 最短路径  扩展一条边
     *
     * @param L 含有m-1条边的最短路径数组
     * @param W 权重数组
     * @return 含有m条边的最短路径数组
     * 数组下标从0开始
     */
    public int[][] extendShortestPaths(int[][] L, int[][] W) {
        int n = L.length;
        int[][] newL = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newL[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    newL[i][j] = Math.min(newL[i][j], L[i][k] + W[k][j]);
                }
            }
        }
        return newL;
    }

    /**
     * 计算所有节点之间的最短路径
     *
     * @param W 表示图的邻接矩阵
     * @return 各节点的最短路径矩阵
     * 时间复杂度   n的4次幂
     */
    public int[][] slowAllPairsShortestPaths(int[][] W) {
        int n = W.length;
        int[][] L = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                L[i][j] = W[i][j];
            }
        }
        for (int i = 2; i < n; i++) {
            L = extendShortestPaths(L, W);
        }
        return L;
    }

    /**
     * 改进： 相当于 边数成倍的增加
     * @param W
     * @return
     * 时间复杂度  n的3次幂×lgn
     */
    public int[][] fasterAllPairsShortestPaths(int[][] W) {
        int n = W.length;
        int[][] L = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                L[i][j] = W[i][j];
            }
        }
        int m = 1;
        while (m < n - 1) {
            L = extendShortestPaths(L, L);
            m = 2 * m;
        }
        return L;
    }

    /**
     * 输出矩阵L
     * @param L
     */
    public void print(int[][] L) {
        for (int i = 0; i < L.length; i++) {
            for (int j = 0; j < L[0].length; j++) {
                System.out.print(L[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 各结点对的最短路径
     * @param W  表示图的邻接矩阵
     * @return    返回 表示最短路径的矩阵
     * 时间复杂度  n的3次幂
     */
    public int[][] floydWarshall(int[][] W){
        int n = W.length;
        int[][] L = new int[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                L[i][j] = W[i][j];
            }
        }
        for (int k = 0; k <n ; k++) {
             int[][] newL = new int[n][n];
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n ; j++) {
                    newL[i][j] = Math.min(L[i][j],L[i][k]+L[k][j]);
                }
            }
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j < n; j++) {
                    L[i][j] = newL[i][j];
                }
            }

        }
        return L;
    }
}
