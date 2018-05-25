package fenzhipartfour;

import java.util.Arrays;

/**
 * Created by ying on 2018/5/6.
 */
public class Fenzhi {
    public static void main(String[] args) {
        int[] nums = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        int[] nums2 = {2,12,3,-13,-19,-20,-3,3};
        //递归  最大子数组
       // System.out.println(find_max_subarray(nums,0,nums.length-1));
        //暴力破解 最大子数组
       // System.out.println(find_max_subarray_bruteforce(nums,0,nums.length-1));
        //线性时间  最大子数组
      System.out.println(find_max_subarray_liner(nums2));
//        矩阵相乘    分治递归
      /*  int[][] A = {{2,3,1,2},{3,4,6,3},{5,1,2,4},{3,4,2,3}};
        int[][] B = {{2,5,1,2},{3,1,6,9},{1,1,3,2},{3,5,1,1}};
        int[][] C = new int[A.length][A.length];
        square_matrix_multiply_recursive(A,B,C,0,0,0,0,A.length);
        for (int i = 0; i <C.length ; i++) {
            for (int j = 0; j <C[i].length ; j++) {
                System.out.print(C[i][j]+"\t");
            }
            System.out.println();
        }*/

    }

    /*求股票的最大利润 （只能买进卖出一次）
    * 转化为求数组的  非空连续子数组的和 (最大子数组) 递归算法 o(nlog2n)  (log2n为log以2为底的n)
    * */
    public static int find_max_subarray(int[] nums,int low ,int high){
        if(low == high)
            return nums[low];
        int mid = (low+high)/2;
        int max_left = find_max_subarray(nums,low,mid);
        int max_right = find_max_subarray(nums,mid+1,high);
        int max_cross = find_max_cross_subarray(nums,low,high,mid);
        return Math.max(max_left,Math.max(max_cross,max_right));
    }
    public static int find_max_cross_subarray(int[] nums,int low,int high,int mid){
        int sum =0;
        int sum_left = Integer.MIN_VALUE;
        for (int i = mid; i >=low ; i--) {
            sum += nums[i];
            if(sum > sum_left){
                sum_left = sum;
            }
        }
        sum =0;
        int sum_right = Integer.MIN_VALUE;
        for (int i = mid+1; i <= high ; i++) {
            sum += nums[i];
            if(sum > sum_right){
                sum_right = sum;
            }
        }
        return sum_left+sum_right;
    }

    /* 最大子数组    暴力破解方法  o(n的平方）*/
    public static int find_max_subarray_bruteforce(int[] nums,int low,int high){
        int max =Integer.MIN_VALUE;
        int sum= 0;
        for (int i = low; i <=high ; i++) {
            sum = 0;
            for (int j = i; j <=high ; j++) {
                sum += nums[j];
                if(sum > max)
                    max = sum;
            }
        }
       return max;
    }

    /*最大子数组   4.1-5 o(n)*/
    public static int find_max_subarray_liner(int[] nums){
        int max_ending_here = 0,max_so_for = 0;
        int start = 0, end = -1,temp = 0;
        for (int i = 0; i < nums.length ; i++) {
            if(max_ending_here+nums[i] < 0){
                max_ending_here = 0;
                temp = i+1;
            }else
                max_ending_here += nums[i];
            if(max_ending_here > max_so_for){
                max_so_for = max_ending_here;
                start = temp;
                end = i;
            }
        }
        System.out.println("左下标："+start+"右下标："+end);
        return max_so_for;
    }

/*    public static int find_max_subarray_liner(int[] nums,int low,int high){
        int max = 0,lindex = 0,rindex=0,sum=0,temp =0;
        for (int i = low; i <= high ; i++) {
            sum += nums[i];
            if(sum < 0){
                sum = 0;
                temp = i+1;
            }else{
                if(sum > max){
                    max = sum;
                    lindex = temp;
                    rindex = i;
                }
            }
        }
        System.out.println("左下标："+lindex+"右下标："+rindex);
        return max;
    }*/
    /*矩阵乘法  分治算法   矩阵大小：n*n 假设n是2的幂
    *  n:A和B的规模 astarti:数组A的行开始下标   astartj:数组A的列开始下标
    *               bstarti:数组B的行开始下标   bstartj:数组B的列开始下标*/
    public static void square_matrix_multiply_recursive(int[][] A,int[][] B,int[][] C,int astarti,int astartj,
                                                        int bstarti,int bstartj,int n){
        if( n==1 ){
            C[astarti][bstartj] += A[astarti][astartj]*B[bstarti][bstartj];
            return;
        }
        square_matrix_multiply_recursive(A,B,C,astarti,astartj,bstarti,bstartj,n/2);
        square_matrix_multiply_recursive(A,B,C,astarti,astartj+n/2,bstarti+n/2,bstartj,n/2);

        square_matrix_multiply_recursive(A,B,C,astarti,astartj,bstarti,bstartj+n/2,n/2);
        square_matrix_multiply_recursive(A,B,C,astarti,astartj+n/2,bstarti+n/2,bstartj+n/2,n/2);

        square_matrix_multiply_recursive(A,B,C,astarti+n/2,astartj,bstarti,bstartj,n/2);
        square_matrix_multiply_recursive(A,B,C,astarti+n/2,astartj+n/2,bstarti+n/2,bstartj,n/2);

        square_matrix_multiply_recursive(A,B,C,astarti+n/2,astartj,bstarti,bstartj+n/2,n/2);
        square_matrix_multiply_recursive(A,B,C,astarti+n/2,astartj+n/2,bstarti+n/2,bstartj+n/2,n/2);

    }

}
