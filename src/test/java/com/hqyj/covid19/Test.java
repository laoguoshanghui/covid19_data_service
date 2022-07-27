package com.hqyj.covid19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guojing
 * @Date:2022/7/4 15:07
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        //要求把5和6数字过滤出来
        List<Integer> collect = list.stream().filter(a -> a == 5 || a == 6).collect(Collectors.toList());

        System.out.println(collect);
    }


}
