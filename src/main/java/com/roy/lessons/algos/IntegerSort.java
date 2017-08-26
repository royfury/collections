package com.roy.lessons.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description: IntegerSort
 *
 * 把数组排成最大的数（数组、算法）。
 * 题目：输入一个正整数数组，将它们连接起来排成一个数，输出能排出的所有数字中最大的一个。
 * 例如输入数组{32,  321}，则输出这两个能排成的最大数字32321.。
 * 或者输入数组{10,9,33,1000}输出这四个能排列的最大数字933101000.
 *
 *
 * 思路：排序！
 *
 * 三个数
 * a   x位
 * b   y位
 * c   z位
 *
 * 已知
 * ab > ba
 * bc > cb
 * 求证
 * ac > ca
 *
 * 证明
 *  a * 10^y + b  >  b * 10^x + a
 *  b * 10^z + c  >  c * 10^y + b
 *
 * 得到
 * a * (10^y - 1) > b * (10^x - 1)
 * b * (10^z - 1) > c * (10^y - 1)
 *
 * 即
 * b / c * (10^z - 1) > (10^y - 1) > b / a * (10^x - 1)
 * 相当于
 * a * 10^z + c  >  c * 10^x + a
 *
 * 证毕
 *
 * @author lei.zhu
 * @version 2017-08-23 20:28
 */
public class IntegerSort {


    public static List<Integer> target = new ArrayList<Integer>();
    public static List<Integer> target2 = new ArrayList<Integer>();

    static {
        target.add(1);
        target.add(19);
        target.add(101);

        target2.add(10);
        target2.add(9);
        target2.add(33);
        target2.add(1000);
    }

    public static void sort(List<Integer> target) {
        Collections.sort(target, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return map(o2, o1) - map(o1, o2);
            }
        });
    }

    public static int map(int first, int second) {
        return Double.valueOf(first * Math.pow(10, size(second))).intValue() + second;
    }

    public static int size(int val) {
        int result = 0;
        while (val > 0) {
            val = val / 10;
            ++result;
        }
        return result;
    }

    public static void main(String[] args) {
        sort(target);
        sort(target2);
        System.out.println(target);
        System.out.println(target2);
    }
}
