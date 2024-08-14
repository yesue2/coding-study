package com.dijkstra;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_1261 {

    public static int[][] field;
    public static int[][] dist;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine();

        field = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = line.charAt(j) - '0';
            }
        }

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE); 
        }

        int answer = bfs(N, M);
        System.out.println(answer);
    }

    private static int bfs(int n, int m) {
        Deque<Node> dq = new LinkedList<>();
        dq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            Node cur = dq.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cost;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int nc = cur.cost + field[nx][ny];

                    if (nc < dist[nx][ny]) {
                        dist[nx][ny] = nc;

                        if (field[nx][ny] == 1) {
                            dq.addLast(new Node(nx, ny, nc));
                        } else if (field[nx][ny] == 0) {
                            dq.addFirst(new Node(nx, ny, nc));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
