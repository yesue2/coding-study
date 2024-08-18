import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static int N, M, answer;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }
        BFS();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void BFS(){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        queue.add(new Node(0, 0, 0));
        visited[0][0] = true;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(now.r == N - 1 && now.c == M - 1){
                answer = now.count;
                return;
            }
            for(int i = 0; i < 4; i++){
                int nr = now.r + dx[i];
                int nc = now.c + dy[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc])
                    continue;
                if(map[nr][nc] == 1){
                    queue.add(new Node(nr, nc, now.count + 1));
                }
                else {
                    queue.add(new Node(nr, nc, now.count));
                }
                visited[nr][nc] = true;
            }
        }
    }

    static class Node{
        int r;
        int c;
        int count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}