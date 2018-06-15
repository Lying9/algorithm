package clrspartfour.dp;

/**
 * Created by ying on 2018/6/14.
 */
public class LCS {
    public static void main(String[] args) {
        char[] X = {'z', 'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] Y = {'z', 'B', 'D', 'C', 'A', 'B', 'A'};
        LCSLength(X, Y);
    }

    /**
     * 计算最长公共子序列的长度  （最长公共子序列并不唯一）
     *
     * @param X X序列存储在X1...Xm  下标从1开始
     * @param Y Y序列存储在Y1...Ym  下标从1开始
     */
    public static void LCSLength(char[] X, char[] Y) {
//        记录公共子序列的长度
        int[][] c = new int[X.length][Y.length];
//        方便构造最优值   0表示↖   1表示↑  2表示←
        int[][] s = new int[X.length][Y.length];
        for (int i = 0; i < c.length; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < c[0].length; i++) {
            c[0][i] = 0;
        }
        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[0].length; j++) {
                if (X[i] == Y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    s[i][j] = 0;
                } else {
                    if (c[i - 1][j] > c[i][j - 1]) {
                        c[i][j] = c[i - 1][j];
                        s[i][j] = 2;
                    } else {
                        c[i][j] = c[i][j - 1];
                        s[i][j] = 1;
                    }
                }
            }
        }
//        输出最长公共子序列的长度
        System.out.println(c[X.length - 1][Y.length - 1]);
//        输出最长公共子序列
        printLcs(s, X, X.length - 1, Y.length - 1);
    }

    /**
     * 输出 最长公共子序列
     */
    public static void printLcs(int[][] s, char[] X, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (s[i][j] == 0) {
            printLcs(s, X, i - 1, j - 1);
            System.out.print(X[i] + "\t");
        } else if (s[i][j] == 1)
            printLcs(s, X, i, j - 1);
        else
            printLcs(s, X, i - 1, j);
    }
}
