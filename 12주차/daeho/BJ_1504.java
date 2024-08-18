import java.io.*;
import java.util.*;

public class Main{
    static int[] dist, distVertex1, distVertex2;
    static int N, E, vertex1, vertex2, answer, min1, min2;
    static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        vertex1 = Integer.parseInt(st.nextToken());
        vertex2 = Integer.parseInt(st.nextToken());

        dist = dijkstar(1);
        distVertex1 = dijkstar(vertex1);
        distVertex2 = dijkstar(vertex2);

        min1 = -2;
        min2 = -2;

        if(dist[vertex1] != Integer.MAX_VALUE && distVertex1[vertex2] != Integer.MAX_VALUE && distVertex2[N]!= Integer.MAX_VALUE)
            min1 = dist[vertex1] + distVertex1[vertex2] + distVertex2[N];

        if(dist[vertex2] != Integer.MAX_VALUE && distVertex2[vertex1] != Integer.MAX_VALUE && distVertex1[N] != Integer.MAX_VALUE)
            min2 = dist[vertex2] + distVertex2[vertex1] + distVertex1[N];

        if(min1 < 0 && min2 < 0)
            bw.write("-1");
        else if (min1 > 0 && min2 < 0)
            bw.write(String.valueOf(min1));
        else if (min1 < 0 && min2 > 0)
            bw.write(String.valueOf(min2));
        else
            bw.write(String.valueOf(Math.min(min1, min2)));
        bw.flush();
        bw.close();

    }

    static int[] dijkstar(int start){
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for(int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(!visited[now.v])
                visited[now.v] = true;

            for(Node next : graph[now.v]){
                if(!visited[next.v] && dist[next.v] > next.cost + now.cost){
                    dist[next.v] = next.cost + now.cost;
                    queue.add(new Node(next.v, dist[next.v]));
                }
            }

        }
        return dist;
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