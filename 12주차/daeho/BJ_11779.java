import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static int n, m, start, end, answer;
    static List<List<Node>> graph;
    static boolean[] visited;
    static int[] dist;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;

        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<Node>());
        }
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, start + " "));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start, int end){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        for(int i = 1; i <= n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        queue.add(new Node(start, 0, 1, start + " "));
        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(current.v == end && current.cost < answer){
                answer = current.cost;
                sb = new StringBuilder();
                sb.append(current.cost).append("\n").append(current.count).append("\n").append(current.route);
            }
            if(!visited[current.v]) {
                visited[current.v] = true;
                for(Node next : graph.get(current.v)){
                    if(!visited[next.v] && dist[next.v] > current.cost + next.cost){
                        dist[next.v] = current.cost + next.cost;
                        queue.add(new Node(next.v, dist[next.v], current.count + 1, current.route + next.v + " "));
                    }
                }
            }
        }
    }

    static class Node{
        int v;
        int cost;
        int count;
        String route;

        public Node(int v, int cost, int count, String route) {
            this.v = v;
            this.cost = cost;
            this.count = count;
            this.route = route;
        }
    }
}