import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<Node>[] graph;
    static int[] place;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[V].add(new Node(U, C));
        }

        place = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            place[i] = Integer.parseInt(st.nextToken());
        }

        dist = new long[N + 1];
        for(int i = 1; i <= N; i++){
            dist[i] = Long.MAX_VALUE;
        }

        dijkstra();

        int answerNum = -1;
        long answerDist = -1;
        for (int i = N; i > 0; i--) {
            if (dist[i] != Integer.MAX_VALUE && dist[i] >= answerDist) {
                answerNum = i;
                answerDist = dist[i];
            }
        }

        bw.write(answerNum + "\n" + answerDist + "\n");
        bw.flush();
        bw.close();
    }

    static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (int) o1.cost - (int) o2.cost);
        for (int p : place) {
            dist[p] = 0;
            queue.add(new Node(p, 0));
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (dist[now.v] < now.cost) continue;
            for (Node next : graph[now.v]) {
                if (dist[next.v] > now.cost + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    queue.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    static class Node {
        int v;
        long cost;
        public Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
