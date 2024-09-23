import java.util.*;

class Solution {
    List<List<int[]>> list;
    int[][] fee;

    public void f(int s, int index){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.add(new int[]{0, s});
        fee[index][s] = 0;

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(fee[index][now[1]] < now[0])
                continue;

            for(int i = 0; i < list.get(now[1]).size(); i++){
                int[] next = new int[]{now[0] + list.get(now[1]).get(i)[0], list.get(now[1]).get(i)[1]};

                if(fee[index][next[1]] < next[0])
                    continue;

                pq.add(next);
                fee[index][next[1]] = next[0];
            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 60000000;
        fee = new int[3][n + 1];

        for(int i = 1; i <= n; i++){
            fee[0][i] = 20000000;
            fee[1][i] = 20000000;
            fee[2][i] = 20000000;
        }

        list = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            list.add(new ArrayList<>());

        for(int i = 0; i < fares.length; i++){
            list.get(fares[i][0]).add(new int[]{fares[i][2], fares[i][1]});
            list.get(fares[i][1]).add(new int[]{fares[i][2], fares[i][0]});
        }

        f(a, 0);
        f(b, 1);
        f(s, 2);

        for(int i = 1; i <= n; i++)
            answer = Math.min(answer, fee[2][i] + fee[0][i] + fee[1][i]);
        return answer;
    }
}