package clrspartfour.dp;

/**
 * Created by ying on 2018/6/15.
 */
public class BST {
    public static void main(String[] args) {
        double[] p = {0, 0.15, 0.10, 0.05, 0.10, 0.20};
        double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
        optimalBST(p, q, 5);
    }

    /**
     * 最优二叉搜索树 期望搜索代价最小
     *
     * @param p n个已排好序的不同关键字  的搜索频率   下标从1开始
     * @param q “伪关键字”的搜索频率     伪关键字di表示在ki和ki+1 的值
     * @param n 关键字的个数
     */
    public static void optimalBST(double[] p, double[] q, int n) {
//        e存储 期望搜索代价   w 为简便计算  root存储最优解构造信息
        double[][] e = new double[n + 2][n + 1];
        double[][] w = new double[n + 2][n + 1];
        int[][] root = new int[n + 1][n + 1];

        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                e[i][j] = Integer.MAX_VALUE;
                double temp;
                for (int k = i; k <= j; k++) {
                    temp = e[i][k - 1] + e[k + 1][j] + w[i][j];
                    if (temp < e[i][j]) {
                        e[i][j] = temp;
                        root[i][j] = k;
                    }
                }
            }
        }
//        输出期望搜索代价
        System.out.println(e[1][n]);
        print(root,1,n,0);
    }

    /**输出最优解
     *
     * @param root 存储最优解信息的数组
     * @param i    开始关键字下标
     * @param j    结束关键字下标
     * @param flag 标志  0：表示是根  1：表示是左孩子   2：表示是右孩子
     */
    public static void print(int[][] root, int i, int j,int flag) {
  /*      if (i > j)
            return;
        print(root, i, root[i][j] - 1);
        System.out.println(root[i][j]);
        print(root, root[i][j] + 1, j);*/
        if(i<=j){
            if(flag == 0)
                System.out.println("k"+root[i][j]+"是根");
            else if(flag == 1)
                System.out.println("k" + root[i][j] + "是k" + (j + 1) + "的左孩子");
            else
                System.out.println("k" + root[i][j] + "是k" + (i - 1) + "的右孩子");
            print(root,i,root[i][j]-1,1);
            print(root,root[i][j]+1,j,2);
        }
        else if(j == i-1){
            if(flag == 1)
                System.out.println("d"+j+"是k"+(j+1)+"的左孩子");
            else
                System.out.println("d"+j+"是k"+(j)+"的右孩子");
        }
    }
}
