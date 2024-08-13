package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {
    static class Node implements Comparable<Node> {
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            if (this.distance < other.distance) {
                return -1;
            } else return 1;
        }
    }

    private static final int INF = (int) 1e9;

    private static void dijkstra(int start,ArrayList<ArrayList<Node>> graph, int distance[]) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        distance[start] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int now = node.index;
            int dist = node.distance;
            //현재 노드가 이미 방문한 노드라면 지나가기
            if (distance[now] < dist) continue;

            //현재노드와 인접 노드 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = distance[now] + graph.get(now).get(i).distance;
                //현재 노드 방문후 다른노드 방문하는게 짧은경우
                if (cost < distance[graph.get(now).get(i).index]) {
                    distance[graph.get(now).get(i).index] = cost;
                    queue.offer((new Node(graph.get(now).get(i).index, cost)));
                }
            }
        }

        return;

    }

    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        ArrayList<ArrayList<Node>> backgraph = new ArrayList<>();
        int max = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int city = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken()) - 1;

        //각 노드 리스트 초기화
        for (int i = 0; i < city; i++) {
            graph.add(new ArrayList<Node>());
            backgraph.add(new ArrayList<Node>());
        }
        int[] godistance = new int[city];
        int[] backdistance = new int[city];

        Arrays.fill(godistance,INF);
        Arrays.fill(backdistance,INF);


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, d));
            backgraph.get(e).add(new Node(s, d));
        }


        dijkstra(point,backgraph,godistance);
        dijkstra(point,graph,backdistance);

        for (int i = 0; i < city; i++) {
            max = Math.max(godistance[i] + backdistance[i], max);
        }
        System.out.println(max);
    }
}
