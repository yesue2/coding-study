package PGM_디스크컨트롤러;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        PriorityQueue <int []> minHeap = new PriorityQueue<>(
                new Comparator<int []>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if (o1[1]==o2[1]) return o1[0]-o2[0];
                        return o1[1]-o2[1];
                    }
                }
        );

        int time = 0;
        int sum = 0;

        for(int i = 0; i < jobs.length || !minHeap.isEmpty();){
            if(i != jobs.length && time >= jobs[i][0]){
                minHeap.add(jobs[i++]);
            }
            else if(minHeap.isEmpty()) {
                time = jobs[i][0];
            }
            else {
                int[] tmp = minHeap.poll();


                sum += tmp[1] + (time - tmp[0]);
                time += tmp[1];
            }
        }

        return sum/jobs.length;
    }
}

