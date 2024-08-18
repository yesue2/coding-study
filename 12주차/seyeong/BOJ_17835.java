package com.dijkstra;

import java.util.*;

public class BOJ_17835 {

    public static List<List<Node>> graph = new ArrayList<>();
    public static long[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(b).add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.cost));

        for (int i = 0; i < K; i++) {
            int interviewCity = sc.nextInt();
            dist[interviewCity] = 0;
            pq.add(new Node(interviewCity, 0));
        }


        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curCity = current.v;
            long curCost = current.cost;

            if (curCost > dist[curCity]) continue;

            for (Node neighbor : graph.get(curCity)) {
                if (dist[neighbor.v] > curCost + neighbor.cost) {
                    dist[neighbor.v] = curCost + neighbor.cost;
                    pq.add(new Node(neighbor.v, dist[neighbor.v]));
                }
            }
        }


        long maxDistance = -1;
        int maxCity = -1;

        for (int i = 1; i <= N; i++) {
            if (dist[i] > maxDistance) {
                maxDistance = dist[i];
                maxCity = i;
            }
        }

        System.out.println(maxCity);
        System.out.println(maxDistance);
    }

    public static class Node {
        int v;
        long cost;

        public Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
