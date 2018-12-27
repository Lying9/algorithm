package clrspartfour.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ying on 2018/6/19.
 */
public class ActivitySelect {
    public static void main(String[] args) {
     /*   int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        List<Integer> list = new ArrayList();
        recursiveActivitySelector(s, f, 0, s.length - 1, list);
        for (Integer i : list) {
            System.out.print("a" + i + "\t");
        }*/
       /* int[] s = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        List<Integer> list = greedyActivitySelector(s,f);
        for (Integer i : list) {
            System.out.print("a" + (i+1) + "\t");
        }*/
     /*   int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12, Integer.MAX_VALUE};
        int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16, 0};
        dpActivitySelector(s, f);*/
    }

    /**
     * 贪心算法   自顶向下  递归
     *
     * @param s    开始时间数组  从1开始
     * @param f    结束时间数组  从1开始  已排好序
     * @param k    要求解的子问题为sk 即ak活动结束之后的最大兼容活动集
     * @param n    问题的规模
     * @param list 存储最大兼容活动集
     */
    public static void recursiveActivitySelector(int[] s, int[] f, int k, int n, List list) {
        int m = k + 1;
        while (m <= n && s[m] < f[k]) {
            m++;
        }
        if (m <= n) {
            list.add(m);
            recursiveActivitySelector(s, f, m, n, list);
        }
    }

    /**
     * 贪心算法  迭代
     *
     * @param s 开始时间  下标从0开始
     * @param f 结束时间  下标从0开始   已排好序
     */
    public static List greedyActivitySelector(int[] s, int[] f) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int pre = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] >= f[pre]) {
                list.add(i);
                pre = i;
            }
        }
        return list;
    }

    /**
     * 动态规划求解
     *
     * @param s 活动开始时间  下标从1开始
     * @param f 活动结束时间  下标从1开始
     */
    public static void dpActivitySelector(int[] s, int[] f) {
//        c[i][j]表示集合Sij的最优解的大小   sij表示在ai结束之后开始，且在aj开始之前结束
        int[][] c = new int[s.length][s.length];
//        记录最优值
        int[][] m = new int[s.length][s.length];
        for (int l = 3; l <= s.length; l++) {
            for (int i = 0; i < s.length - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i + 1; k < j; k++) {
                    if (s[k] >= f[i] && f[k] <= s[j]) {
                        int count = c[i][k] + c[k][j] + 1;
                        if (count > c[i][j]) {
                            c[i][j] = count;
                            m[i][j] = k;
                        }
                    }
                }
            }
        }
        System.out.println(c[0][s.length - 1]);
    }
}
