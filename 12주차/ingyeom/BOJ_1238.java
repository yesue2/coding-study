import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static List<List<int[]>> g;

    static int f(int s, int e){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        pq.add(new int[]{0, s});

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++)
            dp[i] = 1000000;
        dp[s] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int w = now[0];
            int v = now[1];

            if(v == e)
                return dp[e];
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
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        g = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            g.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            g.get(s).add(new int[]{t, e});
        }

        int result = 0;
        for (int i = 1; i <= n; i++)
            result = Math.max(result, f(i, x) + f(x, i));
        System.out.println(result);
    }
}