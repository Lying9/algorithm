package sort;

import java.util.Arrays;

/**
 * Created by ying on 2018/5/14.
 */
public class HeapSort {
    public static void main(String[] args) {
//        最大堆维护  nums[0]里存储堆的元素个数  堆元素从1开始
       /* int[] nums = {10,16,4,10,14,7,9,3,2,8,1};
        max_heapify(nums,2);
        for (int i = 1; i < nums.length ; i++) {
            System.out.print(nums[i]+"\t");
        }*/
//      最小堆维护
        /*int[] nums = {10,3,21,9,12,15,16,19,20,19};
        min_heapify(nums,2);
        for (int i = 1; i < nums.length ; i++) {
            System.out.print(nums[i]+"\t");
        }*/
//        建堆
   /*     int[] nums = {11,3,5,7,15,2,36,25,14,8,3,5};
         bulid_max_heap(nums);
        System.out.println(Arrays.toString(nums));*/
//        堆排序
      /*  int[] nums = {11,3,5,7,15,2,36,25,14,8,3,5};
        heap_sort(nums);
        System.out.println(Arrays.toString(nums));*/
//      优先队列(最大堆)    nums[0]里存储 堆的heap_size
      /*  int[] nums = {11, 3, 5, 7, 15, 2, 36, 25, 14, 8, 3, 5, 0, 0, 0, 0, 0};
        bulid_max_heap(nums);
        System.out.println("建好的优先队列：" + Arrays.toString(nums));
        int max = maximums(nums);
        System.out.println("最大关键字：" + max);
        max = extract_max(nums);
        System.out.println("删除最大关键字：" + Arrays.toString(nums));
        increase_key(nums, 5, 87);
        System.out.println("增加关键字：" + Arrays.toString(nums));
        max_heap_insert(nums, 93);
        System.out.println("插入关键字：" + Arrays.toString(nums));
        max_heap_delete(nums,2);
        System.out.println("删除下标为2的关键字：" + Arrays.toString(nums));*/
//     优先队列（最小堆）
    /*    int[] nums = {11, 3, 5, 7, 15, 2, 36, 25, 14, 8, 3, 5, 0, 0, 0, 0, 0};
        build_min_heap(nums);
        System.out.println("建好的优先队列：" + Arrays.toString(nums));
        int min = minimum(nums);
        System.out.println("最小关键字：" + min);
        min= extract_min(nums);
        System.out.println("删除最小关键字：" + Arrays.toString(nums));
        decrease_key(nums, 5, 1);
        System.out.println("减小关键字：" + Arrays.toString(nums));
        min_heap_insert(nums,12);
        System.out.println("插入关键字：" + Arrays.toString(nums));*/

//    合并k个有序链表
/*
        int[] num1 = {2,4,6,7,12,15,17,29};
        int[] num2 = {3,6,8,15,25};
        int[] num3 = {3,7,9,12,15,17,19,25};
        merge_k(num1,num2,num3);
*/

    }




  /*  public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }*/

    /**
     * 堆排序  （正序）
     * @param nums
     */
    public static void heap_sort(int[] nums) {
        bulid_max_heap(nums);
        int heap_size = nums[0];
        for (int i = nums[0]; i >= 2; i--) {
            //交换nums[1] 和 nums[i]
            int temp = nums[1];
            nums[1] = nums[i];
            nums[i] = temp;
            nums[0]--;
            max_heapify(nums, 1);
        }
        nums[0] = heap_size;
    }

    public static void merge_k(int[]...args){
        int[] index = new int[args.length];
        int sumlegth = 0;
        for (int i = 0; i < args.length; i++) {
            sumlegth += args[i].length;
        }
        int[] result = new int[sumlegth];
        int endcount = 0;
        int minindex = 0;
        int[] nums = new int[args.length+1];
        nums[0] = args.length;
        for (int i = 1; i < nums.length ; i++) {
            nums[i] = args[i-1][index[i-1]];
        }
        build_min_heap(nums);
//        System.out.println(Arrays.toString(nums));
        while(endcount < args.length-1){
//            System.out.println(Arrays.toString(nums));
             int min=extract_min(nums);
             result[minindex++] =min;
             int i = 0;
            for (i = 0; i < args.length ; i++) {
                if(index[i]!=-1 && args[i][index[i]] == min){
                    index[i]++;
                    break;
                }
            }
            if(index[i] < args[i].length)
                min_heap_insert(nums,args[i][index[i]]);
            else
                index[i] = -1;
            endcount = 0;
            for ( i = 0; i < index.length ; i++) {
                if(index[i] == -1)
                    endcount++;
            }

        }
        int i;
        for (i = 0; i < index.length; i++) {
            if(index[i] != -1){
                break;
            }
        }
        for (int j = index[i]; j < args[i].length; j++) {
            result[minindex++] = args[i][j];
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 建堆  最大堆
     * @param nums
     */
    public static void bulid_max_heap(int[] nums) {
        int leftbegin = (nums[0]) / 2;
        for (int i = leftbegin; i > 0; i--) {
            max_heapify(nums, i);
        }
    }

    /**
     * 最大堆  维护堆方法  递归实现
     * @param nums   要维护的堆   以数组形式存储  （数组下标从1开始）
     * @param index
     */
  /*  public static void max_heapify(int[] nums, int index){
        int left = index << 1;
        int right = left + 1 ;
//        System.out.println(left);
        int maxindex = index;
        if(left<nums.length && nums[left] > nums[maxindex]){
            maxindex = left;
        }
        if(right<nums.length && nums[right] > nums[maxindex]){
            maxindex = right;
        }
        if(index != maxindex){
            int temp = nums[index];
            nums[index] = nums[maxindex];
            nums[maxindex] = temp;
            max_heapify(nums,maxindex);
        }
    }*/

    /**
     * 最大堆  维护堆方法  循环实现
     * @param nums  要维护的堆   以数组形式存储  （数组下标从1开始）
     * @param index
     */
    public static void max_heapify(int[] nums, int index) {
        int left = index << 1;
        int right = left + 1;
//        System.out.println(left);
        while (left <= nums[0] || right <= nums[0]) {
            int maxindex = index;
            if (left <= nums[0] && nums[left] > nums[maxindex]) {
                maxindex = left;
            }
            if (right <= nums[0] && nums[right] > nums[maxindex]) {
                maxindex = right;
            }
            if (index != maxindex) {
                int temp = nums[index];
                nums[index] = nums[maxindex];
                nums[maxindex] = temp;
            } else
                break;
            left = maxindex << 1;
            right = left + 1;
            index = maxindex;
        }

    }
    /**
     * 返回优先队列里的最大关键字元素
     * @param nums
     * @return
     */
    public static int maximums(int[] nums) {
        if (nums[0] < 1) {
            System.out.println("优先队列里没有元素");
            return -1;
        }
        return nums[1];
    }

    /**
     * 返回并且删除 优先队列里的最大关键字元素
     * @param nums
     * @return
     */
    public static int extract_max(int[] nums) {
        if (nums[0] < 1) {
            System.out.println("优先队列里没有元素");
            return -1;
        }
        int max = nums[1];
        nums[1] = nums[nums[0]];
        nums[0]--;
        max_heapify(nums, 1);
        return max;
    }

    /**
     * 把下标为i的值改为key   key > 原来的下标为i的值
     */
    public static void increase_key(int[] nums, int i, int key) {
        if (i > nums[0]) {
            System.out.println("不合法的下标");
        }
        if (key < nums[i]) {
            System.out.println("不合法的key值");
        }
        nums[i] = key;
       /* while (i > 1 && nums[i / 2] < nums[i]) {
            int temp = nums[i];
            nums[i] = nums[i / 2];
            nums[i / 2] = temp;
            i = i / 2;
        }*/
        while (i > 1 && nums[i / 2] < key) {
            nums[i] = nums[i / 2];
            i = i / 2;
        }
        nums[i] = key;
    }

    /**
     * 插入一个元素
     * @param nums
     * @param key
     */
    public static void max_heap_insert(int[] nums, int key) {
        nums[0]++;
        nums[nums[0]] = key;
        int i = nums[0];
        while (i > 1 && nums[i / 2] < nums[i]) {
            int temp = nums[i];
            nums[i] = nums[i / 2];
            nums[i / 2] = temp;
            i = i / 2;
        }
    }

    /**
     * 删除下标为index 的元素
     * @param nums
     * @param index
     */
    public static void max_heap_delete(int[] nums,int index){
        if(index > nums[0]) {
            System.out.println("不合法的下标");
            return;
        }
        nums[index] = nums[nums[0]];
        nums[0]--;
        max_heapify(nums,index);
    }


    /**
     * 建立最小堆   时间复杂度为o(n)
     */
    public static void build_min_heap(int[] nums) {
        int leaf = nums[0] / 2;
        for (int i = leaf; i >= 1; i--) {
            min_heapify(nums, i);
        }
    }

    /**
     * 最小堆  维护堆方法
     * @param nums
     * @param index
     */
    public static void min_heapify(int[] nums, int index) {
        int left = index << 1;
        int right = left + 1;
        int minindex = index;
        if (left <= nums[0] && nums[left] < nums[minindex])
            minindex = left;
        if (right <= nums[0] && nums[right] < nums[minindex])
            minindex = right;
        if (minindex != index) {
            int temp = nums[index];
            nums[index] = nums[minindex];
            nums[minindex] = temp;
            min_heapify(nums, minindex);
        }
    }

    /**
     * 返回最小元素
     * @param nums
     * @return
     */
    public static int minimum(int[] nums) {
        if (nums[0] < 1) {
            System.out.println("优先队列为空");
            return -1;
        }
        return nums[1];
    }

    /**
     * 返回并删除最小元素
     * @param nums
     * @return
     */
    public static int extract_min(int[] nums) {
        if (nums[0] < 1) {
            System.out.println("优先队列为空");
            return -1;
        }
        int min = nums[1];
        nums[1] = nums[nums[0]];
        nums[0]--;
        min_heapify(nums, 1);
        return min;
    }

    /**
     * 改变某个值  使其变小
     * @param nums
     * @param index
     * @param key
     */
    public static void decrease_key(int[] nums, int index, int key) {
        if (index > nums[0]) {
            System.out.println("不合法的下标");
            return;
        }
        if (key > nums[index]) {
            System.out.println("不合法的数字");
            return;
        }
        nums[index] = key;
        while (index > 1 && nums[index / 2] > nums[index]) {
            int temp = nums[index];
            nums[index] = nums[index / 2];
            nums[index / 2] = temp;
            index = index / 2;
        }
    }

    /**
     * 在最小堆中插入 一个数
     * @param nums
     * @param key
     */
    public static void min_heap_insert(int[] nums, int key) {
        nums[0]++;
        nums[nums[0]] = Integer.MAX_VALUE;
        decrease_key(nums, nums[0], key);
    }


}
