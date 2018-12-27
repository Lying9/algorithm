package sort;

import java.util.Arrays;

/**
 * Created by ying on 2018/4/25.
 */
public class CompareSort {
    public static void main(String[] args) {
       /* int[] nums = {12,56,23,41,25,16,52,25};
        insertionsortdesc(nums);
        System.out.println(Arrays.toString(nums));*/
      /* int[] A = {0,1,1,1,0,1};
       int[] B = {1,1,0,0,1,1};
       add(A,B);*/
//        int[] nums = {12, 56, 23, 41, 25, 16, 52, 25};
        // int[] nums = {2,3,8,6,1};
        // selectionSort(nums);
        // mergeSort(nums,0,nums.length-1);
        // bubbleSort(nums);
      /*  System.out.println(reversemergeSort(nums, 0, nums.length - 1));
        System.out.println(Arrays.toString(nums));*/
//      测试快排
        /*int[] nums = {13,19,9,5,12,8,7,4,11,2,6,21};
        quicksort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));*/
//        习题7-2 b
        int[] nums = {2, 6, 7, 6, 3, 5, 8, 6, 6};
        int[] result = partition_2(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(nums));


    }

    /*求逆序对的个数
    *  注意：reversemerge中相等情况的处理
    *   疑问： A还必须是有序的   必须对数组进行排序    这一点没太清楚*/
    public static int reversemergeSort(int[] nums, int p, int r) {
        int reverse = 0;
        if (p < r) {
            int q = (p + r) / 2;
            reverse += reversemergeSort(nums, p, q);
            reverse += reversemergeSort(nums, q + 1, r);
            reverse += reversemerge(nums, p, q, r);
        }
        return reverse;
    }

    private static int reversemerge(int[] nums, int p, int q, int r) {
        int[] left = new int[q - p + 1];
        int[] right = new int[r - q];
        for (int i = p; i <= q; i++) {
            left[i - p] = nums[i];
        }
        for (int i = q + 1; i <= r; i++) {
            right[i - q - 1] = nums[i];
        }
        int indexi = 0;
        int indexj = 0;
        int k = p;
        int reverse = 0;
        while (indexi < q - p + 1 && indexj < r - q) {
            if (left[indexi] <= right[indexj])
                nums[k] = left[indexi++];
            else {
                nums[k] = right[indexj++];
                reverse += q - p + 1 - indexi;
            }
            k++;
        }
        if (indexi == q - p + 1) {
            for (int i = indexj; i < r - q; i++) {
                nums[k++] = right[i];
            }
        }
        if (indexj == r - q) {
            for (int i = indexi; i < q - p + 1; i++) {
                nums[k++] = left[i];
            }
        }
        return reverse;
    }


    /*冒泡排序*/
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j >= i + 1; j--) {
                if (nums[j] < nums[j - 1]) {
                    //交换
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    /*归并排序
    *   重点是归并算法   归并方法的时间复杂度为o(n)*/
    public static void mergeSort(int[] nums, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(nums, p, q);
            mergeSort(nums, q + 1, r);
            merge(nums, p, q, r);
        }

    }

    private static void merge(int[] nums, int p, int q, int r) {
        int[] left = new int[q - p + 1];
        int[] right = new int[r - q];
        for (int i = p; i <= q; i++) {
            left[i - p] = nums[i];
        }
        for (int i = q + 1; i <= r; i++) {
            right[i - q - 1] = nums[i];
        }
        int indexi = 0;
        int indexj = 0;
        int k = p;
        while (indexi < q - p + 1 && indexj < r - q) {
            if (left[indexi] < right[indexj])
                nums[k] = left[indexi++];
            else
                nums[k] = right[indexj++];
            k++;
        }
        if (indexi == q - p + 1) {
            for (int i = indexj; i < r - q; i++) {
                nums[k++] = right[i];
            }
        }
        if (indexj == r - q) {
            for (int i = indexi; i < q - p + 1; i++) {
                nums[k++] = left[i];
            }
        }

    }


    /*
    * 选择排序
    * 找到最小的放到A[0]
    * 找到第二小的放到A[1]
    * 。。。。。        第一层for循环可以只到nums.length-1  因为只需对前n-1个元素 运行*/
    public static void selectionSort(int[] nums) {
        int min = 0, minindex = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minindex = j;
                }
            }
            int temp = nums[minindex];
            nums[minindex] = nums[i];
            nums[i] = temp;
        }

    }

    /* AB 表示某个数的 位 存储    求其和*/
    public static void add(int[] A, int[] B) {
        int[] C = new int[A.length + 1];
        int carry = 0, sum = 0;
        for (int i = A.length; i > 0; i--) {
            sum = A[i - 1] + B[i - 1] + carry;
            C[i] = sum % 2;
            carry = sum / 2;
        }
        C[0] = carry;
        System.out.println(Arrays.toString(C));
    }

    /*
    * 插入排序   （扑克牌）
    * */
    public static void insertionsort(int[] nums) {
        int key, j;
        for (int i = 1; i < nums.length; i++) {
            key = nums[i];
            j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    //二分查找
    public static int binary(int nums[], int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target)
                return binary(nums, mid + 1, right, target);
            else if (nums[mid] > target)
                return binary(nums, left, mid, target);
            else
                return mid;
        }
        return -1;
    }

    /*
    * 插入排序（逆序）*/
    public static void insertionsortdesc(int[] nums) {
        int key, j;
        for (int i = 1; i < nums.length; i++) {
            key = nums[i];
            j = i - 1;
            while (j >= 0 && nums[j] < key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    /**
     * 快速排序  数组被划分为两个子数组，[p q-1]  数组里每个数比 nums[q] 小或等于
     * [q+1,r]  数组里每个数比 nums[q] 大或等于
     *
     * @param nums 要排序的数组
     * @param p    开始下标
     * @param r    结束下标
     */
    public static void quicksort(int[] nums, int p, int r) {
        int q;
        if (p < r) {
//            q = partition(nums, p, r);
            q = hoare_partition(nums, p, r);
            quicksort(nums, p, q - 1);
            quicksort(nums, q + 1, r);
        }
    }

    private static int partition(int[] nums, int p, int r) {
//        选择nums[r]作为标兵
        int x = nums[r];
        int i = p - 1;  //i：i之前（包括i）的数都是小于标兵的
        for (int j = i + 1; j < r; j++) {
            if (nums[j] <= x) {
//                交换nums[i+1] nums[j]
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[r];
        nums[r] = temp;

        return i + 1;
    }

    private static int[] partition_2(int[] nums, int p, int r) {
        int[] result = new int[2];
//        选择nums[r]作为标兵
        int x = nums[r];
        int count = 0;
        int i = p - 1;  //i：i之前（包括i）的数都是小于标兵的
        for (int j = i + 1; j < r; j++) {
            if (nums[j] < x) {
//                交换nums[i+1] nums[j]
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        result[0] = i + 1;
        int temp = nums[i + 1];
        nums[i + 1] = nums[r];
        nums[r] = temp;
        i++;
        int t;
        for (t = i + 1; t <= r; t++) {
            if (nums[t] == x) {
                i++;
                temp = nums[i];
                nums[i] = nums[t];
                nums[t] = temp;
            }
        }
        result[1] = i;
        return result;
    }

    public static int hoare_partition(int[] nums, int p, int r) {
        int x = nums[p];
        int i = p;
        int j = r;
        while (true) {
            while (nums[j] > x)
                j--;
            while (nums[i] < x)
                i++;
            if (i < j) {
//                交换 i j
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            } else
                return j;
        }
    }

    //    希尔排序
    public static void shell(int[] a) {
        int dk = a.length / 2;
        while (dk >= 1) {
            shellInsertsort(a, a.length, dk);
            dk = dk / 2;
        }
    }

    public static void shellInsertsort(int a[], int n, int dk) {
        for (int i = dk; i < n; i++) {
            if (a[i] < a[i - dk]) {
                int x = a[i];
                int j = i - dk;
                while (j >= 0 && x < a[j]) {
                    a[j + dk] = a[j];
                    j -= dk;
                }
                a[j + dk] = x;
            }
        }
    }


    //    二元选择排序   一趟排序找出最大和最小值
    public static void selectSort(int r[], int n) {
        int i, j, min, max, temp;
        for (i = 0; i < n / 2; i++) {
            // 做不超过n/2趟选择排序
            min = i;
            max = i; //分别记录最大和最小关键字记录位置
            for (j = i + 1; j <= n - i - 1; j++) {
                if (r[j] > r[max]) {
                    max = j;
                    continue;
                }
                if (r[j] < r[min]) {
                    min = j;
                }
            }
//            最小值是否已经在正确的位置上了
            if (min != i) {
                temp = r[min];
                r[min] = r[i];
                r[i] = temp;
            }
//            可能出现最大值存储在r[i]的位置经过前面的变换，最大值换到了r[min]的位置
            if (max == i)
                max = min;
//            最大值是否已经在正确的位置上
            if (max != n - i - 1) {
                temp = r[max];
                r[max] = r[n - i - 1];
                r[n - i - 1] = temp;
            }

        }

    }
}
