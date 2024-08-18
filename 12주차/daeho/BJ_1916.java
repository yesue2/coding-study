import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static int[] dist;
    static boolean[] visited;
    static List<Node>[] graph;
    static int start, end, N, M, answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        answer = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        bw.write(String.valueOf(dist[end]));
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dist[start] = 0;
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(visited[current.v])
                continue;

            visited[current.v] = true;
            for(Node next : graph[(current.v)]){
                if(visited[next.v] || dist[next.v] <= current.cost + next.cost)
                    continue;
                dist[next.v] = current.cost + next.cost;
                queue.add(new Node(next.v, dist[next.v]));

            }
        }
    }

    static class Node{
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}