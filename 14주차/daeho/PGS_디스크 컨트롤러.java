import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int answer = 0;
        int addIdx = 0;
        int idx = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        Arrays.sort(jobs, (j1, j2) -> {
            if(j1[0] == j2[0])
                return j1[1] - j2[1];
            else
                return j1[0] - j2[0];
        }); // 요청시간 -> 소요시간 순으로 정렬

        while(idx < jobs.length){
            while(addIdx < jobs.length && jobs[addIdx][0] <= time){
                queue.add(new Process(jobs[addIdx][0], jobs[addIdx][1]));
                addIdx++; // 현재 시간에서 이미 요청이 들어온 경우 해당하는 작업을 모두 큐에 추가
            }
            if(queue.isEmpty()){
                time = jobs[addIdx][0] + jobs[addIdx][1];
                answer += jobs[addIdx][1];
                addIdx++; // 큐가 비어 있다면, 현재 시간에서 이미 요청이 들어온 작업이 없는 경우이므로,
                // 다음에 나올 작업 요청시간과 소요시간을 통해 시간 업데이트
            }
            else{
                Process now = queue.poll();
                time += now.time;
                answer += (time - now.await); // 큐가 비어 있지 않다면 이미 요청이 들어온 작업 중 가장 소요시간이 짧은 작업시간 만큼 시간 업데이트
            }
            idx++;
        }

        return answer / jobs.length;
    }

    static class Process{
        int await;
        int time;
        public Process(int await, int time){
            this.await = await;
            this.time = time;
        }
    }
}