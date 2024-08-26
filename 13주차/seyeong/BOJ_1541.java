package com.greedy;

import java.util.Scanner;

public class BOJ_1541 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        sc.close();

        String[] arr = s.split("-");
        int answer = 0;

        for(int i = 0; i<arr.length; i++){
            String[] check = arr[i].split("\\+");
            int sum = 0;

            for(String str : check){
                sum+=Integer.parseInt(str);
            }

            if(i == 0){
                answer += sum;
            }else{
                answer -= sum;
            }


        }

        System.out.println(answer);

    }
}
