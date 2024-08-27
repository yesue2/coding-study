import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i = 0; i < jobs.length; i++)
            pq.add(new int[]{jobs[i][1], jobs[i][0]});

        int now = 0;
        PriorityQueue<int[]> temp = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        while(!pq.isEmpty()){
            boolean check = false;
            while(!pq.isEmpty()){
                int[] d = pq.poll();
                if(d[1] > now){
                    temp.add(d);
                    continue;
                }

                answer += d[0] + now - d[1];
                now += d[0];
                check = true;
                break;
            }

            if(!check && !temp.isEmpty()){
                int[] d = temp.poll();
                answer += d[0];
                now = d[0] + d[1];
            }

            while(!temp.isEmpty())
                pq.add(temp.poll());
        }

        return answer / jobs.length;
    }
}