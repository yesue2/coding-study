package com.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1504 {

    public static List<List<Node>> graph = new ArrayList<>();
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int E = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c)); // 양방향 간선 추가
        }

        int st = sc.nextInt();
        int ed = sc.nextInt();

        int[] dist1 = dijkstra(1, N);
        int[] dist2 = dijkstra(st, N);
        int[] dist3 = dijkstra(ed, N);

        int route1 = dist1[st] + dist2[ed] + dist3[N];
        int route2 = dist1[ed] + dist3[st] + dist2[N];
        int answer = Math.min(route1, route2);

        // INF와 같거나 큰 값이 있다면 경로가 없는 경우로 처리
        if (dist1[st] == INF || dist2[ed] == INF || dist3[N] == INF ||
                dist1[ed] == INF || dist3[st] == INF || dist2[N] == INF) {
            answer = -1;
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(int st, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);

        dp[st] = 0;
        pq.offer(new Node(st, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int v = node.v;
            int cost = node.cost;

            if (dp[v] < cost) {
                continue;
            }

            for (Node next : graph.get(v)) {
                int nx = next.v;
                int nextCost = cost + next.cost; // 다음 노드까지의 누적 비용 계산

                if (nextCost < dp[nx]) {
                    dp[nx] = nextCost;
                    pq.offer(new Node(nx, nextCost));
                }
            }
        }

        return dp;
    }

    private static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
