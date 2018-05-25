package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ying on 2018/5/22.
 */
public class NonCompareSort {
    public static void main(String[] args) {
        //        计数排序
//        int[] nums = {2,5,3,0,2,3,0,3};
//        System.out.println(Arrays.toString(countingSort(nums,5)));
//        基数排序
       /* int[] nums = {329,457,657,839,436,720,355};
        radixsort(nums,3);
        System.out.println(Arrays.toString(nums));*/
//       桶排序
        int[] nums = {78,17,39,26,72,94,21,12,23,68};
        bucketSort(nums);
        System.out.println(Arrays.toString(nums));

      /* int i =-1;
       i >>>= 10;
        System.out.println(i);
        long l = -1;
        l>>>=10;
        System.out.println(l);
        short s = -1;
        s >>>= 10;
        System.out.println(s);
        byte b= -1;
        b >>>= 10;
        System.out.println(b);*/


    }
//    计数排序
    public static int[] countingSort(int[] nums,int k){
        int[] result = new int[nums.length];
        int[] count = new int[k+1];
        for (int i = 0; i < count.length ; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        for (int i = 1; i < count.length ; i++) {
            count[i] += count[i-1];
        }
//        从nums.length到0 遍历是稳定的    反过来从0到nums.length遍历则是不稳定的
        for (int i = nums.length-1; i >=0 ; i--) {
            result[count[nums[i]]-1] = nums[i];
            count[nums[i]]--;
         }
        return result;
    }
    /**
     * 基数排序
     * 注意：每一位的排序算法必须是稳定的
     * @param d 数组nums中的数最高位数
     */
    public static  void  radixsort(int[] nums,int d){
//        radix中存放按某一位排序的结果   行0-9表示该位的数字是0-9中的一个
        int[][] radix = new int[10][nums.length];
//        digit数组中存储 每一位上所对应的数的个数
        int[] digit = new int[10];
        int n = 1;
        for (int i = 1; i <=d ; i++) {
            for (int j = 0; j < nums.length ; j++) {
                int m = (nums[j]/n)%10;
                radix[m][digit[m]] = nums[j];
                digit[m]++;
            }
//            把按某一位排好序的数字放回到原数组中，进而进行下一位的排序(注意其稳定性)
            int k = 0;
            for (int j = 0; j <digit.length ; j++) {
                if(digit[j]!=0){
                    for (int l = 0; l <digit[j] ; l++) {
                        nums[k++] = radix[j][l];
                    }
                    digit[j] = 0;
                }
            }
            n *= 10;
        }

    }
//    桶排序
    public static void bucketSort(int[] nums){
        ArrayList[] list = new ArrayList[nums.length];
        for (int i = 0; i < list.length ; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < nums.length ; i++) {
            int index = nums[i]/10;
            list[index].add(nums[i]);
        }
        for (int i = 0; i <list.length ; i++) {
/*            Object[] temp =list[i].toArray();
            Arrays.sort(temp);
//            System.out.println(Arrays.toString(temp));
            list[i].clear();
            for (int j = 0; j <temp.length ; j++) {
                list[i].add(temp[j]);
            }*/
            Collections.sort(list[i]);
        }
        int k = 0;
        for (int i = 0; i <list.length ; i++) {
            if(list[i].size()!=0){
                for(Object number : list[i]){
                    nums[k++] = (Integer)number;
                }
            }
        }
    }
}
