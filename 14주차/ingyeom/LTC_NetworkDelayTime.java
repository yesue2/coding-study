class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            list.add(new ArrayList<>());

        for(int i = 0; i < times.length; i++)
            list.get(times[i][0]).add(new int[]{times[i][2], times[i][1]});

        int[] time = new int[n + 1];
        Arrays.fill(time, 10001);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, k});
        time[k] = 0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(time[now[1]] < now[0])
                continue;

            for(int i = 0; i < list.get(now[1]).size(); i++){
                int[] next = new int[]{now[0] + list.get(now[1]).get(i)[0], list.get(now[1]).get(i)[1]};

                if(time[next[1]] <= next[0])
                    continue;

                pq.add(next);
                time[next[1]] = next[0];
            }
        }

        int result = 0;
        for(int i = 1; i < time.length; i++)
            result = Math.max(result, time[i]);
        return result == 10001 ? -1 : result;
    }
}