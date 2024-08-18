import java.util.*;
import java.io.*;

public class Main {
    static List<List<int[]>> g = new ArrayList<>();

    static void f(int[] dist, int s){
        Arrays.fill(dist, 200000000);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        pq.add(new int[]{0, s});
        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(now[0] > dist[now[1]])
                continue;

            for(int i = 0; i < g.get(now[1]).size(); i++){
                int[] next = new int[]{now[0] + g.get(now[1]).get(i)[0], g.get(now[1]).get(i)[1]};

                if (dist[next[1]] <= next[0])
                    continue;

                dist[next[1]] = next[0];
                pq.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++)
            g.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            g.get(a).add(new int[]{c, b});
            g.get(b).add(new int[]{c, a});
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        f(dist1, v1);
        f(dist2, v2);

        int result = Math.min(dist1[1] + dist1[v2] + dist2[n], dist2[1] + dist2[v1] + dist1[n]);
        System.out.println(result >= 200000000 ? -1 : result);
    }
}