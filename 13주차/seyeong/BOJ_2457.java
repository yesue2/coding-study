package com.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2457 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] flowers = new int[n][2];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int k = sc.nextInt();
            flowers[i][0] = x * 100 + y;
            flowers[i][1] = z * 100 + k;
        }

        Arrays.sort(flowers, (a, b) ->{
            if(a[0] == b[0]){
                    return b[1] - a[1];
            }
            return a[0] - b[0];
        });

//        System.out.println(flowers[0][1]);

        int count = 0;
        int idx = 0;
        int maxEnd = 301;
        int currentEnd = 301;

        while(currentEnd <= 1130){
            boolean flag = false;

            while(idx < n && flowers[idx][0] <= currentEnd){
                if(flowers[idx][1] > maxEnd){
                    maxEnd = flowers[idx][1];
                    flag = true;
                }
                idx++;
            }

            if(flag){

                count++;
                currentEnd = maxEnd;
            }else{
                System.out.println(0);
                return;
            }
        }

        System.out.println(count);

    }
}
