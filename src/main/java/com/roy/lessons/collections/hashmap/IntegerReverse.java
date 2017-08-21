package com.roy.lessons.collections.hashmap;

/**
 * Description: IntegerReverse
 *
 * @author lei.zhu
 * @version 2017-08-20 15:44
 */
public class IntegerReverse {

    public static int reverse(int target) {
        return target >= 0 ?
                reversePositive(target) :
                reverseNegative(target);
    }

    public static int reversePositive(int target) {
        if (target == 0) {
            return 0;
        }
        int result = 0;
        while (target > 0) {
            if (result > Integer.MAX_VALUE / 10) {
                return -1;
            }
            int a = target % 10;
            if (result * 10 > Integer.MAX_VALUE - a) {
                return -1;
            }
            result = result * 10 + a;
            target = target / 10;
        }
        return result;
    }

    public static int reverseNegative(int target) {
        if (target == 0) {
            return 0;
        }
        int result = 0;
        while (target < 0) {
            if (result < Integer.MIN_VALUE/ 10) {
                return -1;
            }
            int a = target % 10;
            if (result * 10 < Integer.MIN_VALUE - a) {
                return -1;
            }
            result = result * 10 + a;
            target = target / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(reverse(Integer.MAX_VALUE));
        System.out.println(reverse(Integer.MAX_VALUE - 6));
        System.out.println(reverse(Integer.MAX_VALUE / 10));

        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverse(Integer.MIN_VALUE));
        System.out.println(reverse(Integer.MIN_VALUE + 7));
        System.out.println(reverse(Integer.MIN_VALUE / 10));
    }
}
