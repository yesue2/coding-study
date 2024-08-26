import java.util.*;

class Solution {
    static List<Node>[] graph;
    static boolean[] visited;
    static int[] answer;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<Node>();
        }
        for(int i = 0; i < paths.length; i++){
            graph[paths[i][0]].add(new Node(paths[i][1], paths[i][2], 0, false));
            graph[paths[i][1]].add(new Node(paths[i][0], paths[i][2], 0, false));
        }
        for(int i = 0 ; i < gates.length; i++){
            dijkstra(gates[i], summits, n);
        }
        return answer;
    }

    static void dijkstra(int start, int[] summits, int n){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.max - o2.max);
        visited = new boolean[n + 1];
        queue.add(new Node(start, 0, 0, false));
        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(answer[1] < now.max || now.check)
                return;
            for(int i = 0; i < summits.length; i++){
                if(now.v != summits[i]){
                    continue;
                }
                if(answer[1] > now.max){
                    answer[0] = summits[i];
                    answer[1] = now.max;
                }
                else if(answer[1] == now.max){
                    answer[0] = Math.min(summits[i], answer[0]);
                }
                now.check = true;

            }

            if(visited[now.v])
                continue;
            visited[now.v] = true;
            for(Node next : graph[now.v]){
                if(visited[next.v])
                    continue;
                queue.add(new Node(next.v, 0, Math.max(next.cost, now.max), now.check));
            }
        }
    }

    static class Node{
        int v;
        int cost;
        int max;
        boolean check;
        public Node(int v, int cost, int max, boolean check){
            this.v = v;
            this.cost = cost;
            this.max = max;
            this.check = check;
        }
    }
}