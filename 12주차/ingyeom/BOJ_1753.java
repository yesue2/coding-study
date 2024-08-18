import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vv = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        int[] dp = new int[vv + 1];
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i <= vv; i++) {
            dp[i] = 3000000;
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(v1).add(new int[]{w, v2});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        pq.add(new int[]{0, k});
        dp[k] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int w = now[0];
            int v = now[1];

            if(dp[v] < w)
                continue;

            for(int i = 0; i < g.get(v).size(); i++){
                int nw = w + g.get(v).get(i)[0];
                int nv = g.get(v).get(i)[1];

                if(dp[nv] <= nw)
                    continue;

                dp[nv] = nw;
                pq.add(new int[]{nw, nv});
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= vv; i++) {
            if(dp[i] != 3000000)
                sb.append(dp[i]);
            else
                sb.append("INF");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}