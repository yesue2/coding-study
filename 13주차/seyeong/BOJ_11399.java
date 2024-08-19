package com.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11399 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = sc.nextInt();

        for(int i = 0; i<n; i++){
            int j = sc.nextInt();
            pq.add(j);
        }

        int time = 0;
        int answer = 0;

        while(!pq.isEmpty()){

            int j = pq.poll();
            time += j;
            answer += time;

        }

        System.out.println(answer);
    }
}
