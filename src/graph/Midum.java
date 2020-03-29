package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ying on 2018/8/3.
 */
public class Midum {
    public static int sum = 0;
    public static float fsum = 0.0f;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>();
        midum(nums, list, 0);
        System.out.println("---------");
        System.out.println(fsum+sum);
        System.out.println(fsum);


    }

    public static void midum(int[] nums, List<Integer> list, int start) {
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
//            打印list
            for (Integer element : list)
                System.out.print(element + "\t");
            System.out.println();
//            求中值 并求和
//            元素个数是偶数  暂时假定元素有序
            if (list.size() % 2 == 0) {
                int temp = list.get(list.size() / 2) + list.get(list.size() / 2 - 1);
                if (temp % 2 != 0)
                    fsum += temp % 2;
                sum += temp / 2;
            } else
                sum += list.get(list.size() / 2);
            midum(nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
