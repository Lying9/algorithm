package find;

/**
 * Created by ying on 2018/4/27.
 */
public class Find {
    public static void main(String[] args) {
     /*   int[] nums ={1,2,3,4,5,6,7,8,9,10,11,12,13};
        System.out.println(binary(nums,0,nums.length-1,10));*/
//       返回第i小的元素
       /* int[] nums = {3, 9, 10, 6, 8, 16, 36};
        System.out.println(randomizedSelectCycle(nums, 0, nums.length - 1, 7));*/
        int[] nums = {3, 9, 10, 6, 8, 36, 16};
        System.out.println(select(nums, 0, nums.length - 1, 7));
    }

    //    二分查找
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

    /**
     * 返回nums中p到r之间的第i小的元素
     * 最坏情况运行时间为O(n^2)  期望运行时间为O（n）
     *
     * @param nums
     * @param p
     * @param r
     * @param i
     * @return
     */
    public static int randomizedSelect(int[] nums, int p, int r, int i) {
        if (p == r)
            return nums[p];
        int q = randomizedPartiton(nums, p, r);
        int k = q - p + 1;
        if (i == k)
            return nums[q];
        else if (i < k)
            return randomizedSelect(nums, p, q - 1, i);
        else
            return randomizedSelect(nums, q + 1, r, i - k);
    }

    private static int randomizedPartiton(int[] nums, int p, int r) {
//        产生p到r之间的随机数
        int random = (int) (p + Math.random() * (r - p));
        int x = nums[random];
        int i = p - 1, j;

        nums[random] = nums[r];
        for (j = p; j < r; j++) {
            if (nums[j] <= x) {
                int temp = nums[j];
                nums[j] = nums[++i];
                nums[i] = temp;
            }
        }
        nums[r] = nums[++i];
        nums[i] = x;
        return i;
    }

    //          基于循环
    public static int randomizedSelectCycle(int[] nums, int p, int r, int i) {
        while (p < r) {
            int q = randomizedPartiton(nums, p, r);
            int k = q - p + 1;
            if (i == k)
                return nums[q];
            else if (i < k) {
                r = q - 1;
            } else {
                p = q + 1;
                i -= k;
            }
        }
        return nums[p];
    }

    /**
     * 最坏情况为线性时间的选择算法  算法导论9.3
     * @param nums
     * @param p
     * @param r
     * @param m
     * @return  nums在p-r之间的第m小的数
     */
    public static int select(int[] nums, int p, int r, int m) {
        if (p == r)
            return nums[p];
        int i;
        for (i = p; i < 5 * ((r - p + 1) / 5) + p - 1; i += 5) {
            insertionsort(nums, i, i + 4);
        }
        int count = (r - p + 1) / 5;  //共分为count组
        if ((r - p + 1) % 5 != 0) {
            count++;
            insertionsort(nums, i, r);
        }
        int[] mid = new int[count];
        int k = p + 2;
        for (int j = 0; j < mid.length - 1; j++) {
            mid[j] = nums[k];
            k += 5;
        }
        if ((r - p + 1) % 5 != 0) {
            mid[mid.length - 1] = nums[r - ((r - p + 1) % 5) / 2];
        } else
            mid[mid.length - 1] = nums[k];

        int x = select(mid, 0, mid.length - 1, count / 2);
        int q = partition(nums, p, r, x);
        int n = q - p + 1;
        if (m == n)
            return x;
        else if (m < n)
            return select(nums, p, q - 1, m);
        else
            return select(nums, q + 1, r, m - n);
    }

    //    插入排序
    public static void insertionsort(int[] nums, int start, int end) {
        int key, j;
        for (int i = start + 1; i <= end; i++) {
            key = nums[i];
            j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }
    private static int partition(int[] nums, int p, int r, int x) {
        int k;
        for (k = p; k <= r; k++) {
            if (nums[k] == x)
                break;
        }
        int i = p - 1, j;
        nums[k] = nums[r];
        for (j = p; j < r; j++) {
            if (nums[j] <= x) {
                int temp = nums[j];
                nums[j] = nums[++i];
                nums[i] = temp;
            }
        }
        nums[r] = nums[++i];
        nums[i] = x;
        return i;
    }
}
