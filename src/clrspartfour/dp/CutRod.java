package clrspartfour.dp;

import java.util.*;

/**
 * Created by ying on 2018/6/12.
 */
public class CutRod {
    public static void main(String[] args) {
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};//p[0]没用
      /*  int n = 9;
        System.out.println(memoizedCutRod(p,n));*/
       /* for (int i = 0; i < 11; i++) {
            System.out.println("长度为" + i + "的钢条，最大收益：" + bottomUpCutRod(p, i));
        }*/

    }


    /**
     * 备忘录方法   自顶向下
     *
     * @param p p里存放不同长度的钢条所对应的价格
     * @param n 长度为n的钢条
     * @return 最大收益
     */
    public static int memoizedCutRod(int[] p, int n) {
        int[] r = new int[p.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        return memoizedCutRodAux(p, n, r);
    }

    /**
     * @param p p里存放不同长度的钢条所对应的价格
     * @param n 长度为n的钢条
     * @param r r[n]表示长度为n的钢条可获得的最大收益
     * @return 最大收益
     */
    public static int memoizedCutRodAux(int[] p, int n, int[] r) {
        if (r[n] >= 0)
            return r[n];
        int max;
        if (n == 0)
            max = 0;
        else {
            max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                max = Math.max(max, p[i] + memoizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = max;
        return r[n];
    }

    /**
     * 自底向上   记录了最优解
     *
     * @param p p里存放不同长度的钢条所对应的价格
     * @param n 钢条长度
     * @return 最大收益
     */
    public static int extendedBottomUpCutRod(int[] p, int n) {
        int[] r = new int[p.length];
        int[] s = new int[p.length];//s记录最优值
        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
//                max = Math.max(max, p[j] + r[i - j]);
                if (max < p[j] + r[i - j]) {
                    s[i] = j;
                    max = p[j] + r[i - j];
                }
            }
            r[i] = max;
        }
//        输出最优解
        int m = n;
        while (m > 0) {
            System.out.print(s[m] + "\t");
            m -= s[m];
        }
        System.out.println();

        return r[n];
    }

    /**
     * 自底向上  只返回最优值
     *
     * @param p p里存放不同长度的钢条所对应的价格
     * @param n 钢条长度
     * @return 最大收益
     */
    public static int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[p.length];
        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                max = Math.max(max, p[j] + r[i - j]);
            }
            r[i] = max;
        }
        return r[n];
    }
}
