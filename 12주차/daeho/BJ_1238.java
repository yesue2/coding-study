import java.io.*;
import java.util.*;

public class Main {
    static List<List<Node>> graph;
    static boolean[] visited;
    static int[] dist, distToHome;
    static int N, M, X, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        answer = 0;
        graph = new ArrayList<>();

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        dijkstra(X); // 파티 장소에 갔다가 되돌아와야하기 때문에, 파티 장소로 부터 각자의 집까지 최소거리 구하기 위해 다익스트라 알고리즘을 통해 distToHome 배열에 저장
        for(int i = 1; i <= N; i++){
            if(i == X)
                continue;
            dijkstra(i); // 각자의 집에서 파티장소 까지 최소 거리를 구하기 위해 다익스트라 알고리즘
            answer = Math.max(dist[X] + distToHome[i], answer); // 집 - 파티 - 집 까지 거리를 구해 가장 오래 걸리는 사람의 cost로 업데이트
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost)); // 코스트가 낮을수록 우선순위 높게 설정
        pq.add(new Node(start, 0));
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 1; i < N + 1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(!visited[current.v])
                visited[current.v] = true;

            for(Node next : graph.get(current.v)){
                if(!visited[next.v] && dist[next.v] > current.cost + next.cost){
                    dist[next.v] = current.cost + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        if(start == X){
            distToHome = dist.clone();
        }

    }

    static class Node{ // 노드 클래스 생성 (간선, 코스트)
        int v;
        int cost;
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}