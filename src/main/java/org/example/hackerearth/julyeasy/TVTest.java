package org.example.hackerearth.julyeasy;

import jdk.internal.org.objectweb.asm.tree.FrameNode;

import java.util.*;
import java.io.*;

public class TVTest {

    public static int find(int i, int s, List<Integer> list) {
        if(i == 0 && s == 0) {
//            list.stream().forEach(System.out::print);
//            System.out.println();
            return 1;
        }
        if(s == 0)
            return 0;

        int m = 0;
        list.add(-1);
        m += find(i - 1, s - 1, list);
        list.remove(list.size() - 1);

        list.add(1);
        m += find(i + 1, s - 1, list);
        list.remove(list.size() - 1);

        list.add(0);
        m += find(i , s - 1, list);
        list.remove(list.size() - 1);

        return  m;
    }

    public static void main(String args[]) {
        while(true) {
            Random ran = new Random();
            int a = ran.nextInt() + ran.nextInt();
        }
    }
}