import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ying on 2018/6/28.
 */
public class test {
    interface ihello {
        void sayHello();
    }

    static class Hello implements ihello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originalobj;

        Object bind(Object originalobj) {
            this.originalobj = originalobj;
            return Proxy.newProxyInstance(originalobj.getClass().getClassLoader(), originalobj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
//            return method.invoke(originalobj,args);
            return null;
        }
    }

    public static void main(String[] args) {
     /*   ihello hello = (ihello) new DynamicProxy().bind(new Hello());
        hello.sayHello();*/

   /*   List<Integer> list = new ArrayList<>();
      list.add(2);
      list.add(1);
      list.add(2);
      for(Integer i : list){
          System.out.println(i);
      }*/


        /*float f = -0.99f;
        System.out.println(f);
        float f2  = 0.99f;
        System.out.println(Float.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);

        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(Float.NaN)));
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(Float.MAX_VALUE)));*/
//        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(1f)));
//        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(-1.000000f)));
        //System.out.println(Float.floatToRawIntBits(Float.MIN_VALUE));
        // System.out.println(Float.floatToRawIntBits(0.09555f));
        //    System.out.println(f+f2);

      /*  PriorityQueue<Integer> queue =  new PriorityQueue<>();
        queue.add(122);
        queue.add(23);
        queue.add(93);
        queue.add(3);
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }*/


        /*Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        int sum = a+b;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == sum);
        System.out.println(c.equals(sum));
        System.out.println(g == sum);
        System.out.println(g.equals(sum));*/
       /* System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));*/

        /*int[] nums = {3,5,7,2,6,7,8,3,7,6,1,9,0,7};
        quickSort(nums,0,nums.length-1);
        for(int i : nums){
            System.out.println(i);
        }*/

        String s1 = "100";
        String s2 = "1" + new String("00");
        String s3 = "10"+"0";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);


    }

    public static void quickSort(int[] nums,int start,int end){
        if(start < end){
            int pos = position1(nums,start,end);
            quickSort(nums,start,pos-1);
            quickSort(nums,pos+1,end);
        }
    }

    public static int position1(int[] nums,int start,int end){
        int x = nums[start];
        while(start < end){
            while(start < end && nums[end]>= x)
                end--;
            nums[start] = nums[end];
            while(start < end && nums[start]<=x)
                start++;
            nums[end] = nums[start];
        }
        nums[start] = x;
        return start;
    }


}



