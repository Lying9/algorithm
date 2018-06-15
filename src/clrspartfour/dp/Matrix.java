package clrspartfour.dp;

/**
 * Created by ying on 2018/6/13.
 */
public class Matrix {
    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
//        System.out.println(matrixChainOrder(p));
        System.out.println(memoizedMatrixChain(p));
    }

    /**
     * 矩阵相乘  自底向上
     *
     * @param p 矩阵的规模  假定矩阵Ai的规模为p(i-1)*p(i)
     * @return 计算A1...An的最少相乘次数
     */
    public static int matrixChainOrder(int[] p) {
//        存储相乘次数
        int[][] m = new int[p.length][p.length];
//        存储最优解
        int[][] s = new int[p.length][p.length];
        for (int i = 0; i < p.length; i++) {
            m[i][i] = 0;
        }
//        l表示 长度
        for (int l = 2; l < p.length; l++) {
            for (int i = 1; i <= p.length - l; i++) {
                int j = i + l - 1;
                int temp = 0;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    temp = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (temp < m[i][j]) {
                        m[i][j] = temp;
                        s[i][j] = k;
                    }
                }
            }
        }
        printOptimalParens(s, 1, p.length - 1);
        return m[1][p.length - 1];
    }

    /**
     * 输出最小矩阵相乘的最优值
     *
     * @param s s表记录了最优解信息
     * @param i 起始位置
     * @param j 结束为止
     */
    public static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j)
            System.out.print("A" + i);
        else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    /**
     * 备忘录方法   自顶向下
     *
     * @param p 矩阵的规模  假定矩阵Ai的规模为p(i-1)*p(i)
     * @return 计算A1...An的最少相乘次数
     */
    public static int memoizedMatrixChain(int[] p) {
        int m[][] = new int[p.length][p.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[1].length; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        return lookupChain(m, p, 1, p.length - 1);
    }

    /**
     *
     * @param m  存储相乘的最少次数
     * @param p  矩阵规模
     * @param i
     * @param j
     * @return   返回m[i][j]即Ai...Aj的相乘最少次数
     */
    public static int lookupChain(int[][] m, int[] p, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE)
            return m[i][j];
        if(i==j)
            m[i][j] = 0;
        else {
            int temp = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                temp = Math.min(temp, lookupChain(m, p, i, k) + lookupChain(m, p, k + 1, j) + p[i - 1] * p[k] * p[j]);
            }
            m[i][j] = temp;
        }
        return m[i][j];
    }
}
