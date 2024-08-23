import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new int[]{x, y});
        }
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] prev = new int[]{list.get(0)[0], list.get(0)[1]};
        int result = prev[1] - prev[0];
        for(int i = 1; i < list.size(); i++){
            int[] now = list.get(i);

            if(now[0] <= prev[1]){
                if(prev[1] < now[1]) {
                    result += now[1] - prev[1];
                    prev[1] = now[1];
                }
            }
            else{
                result += now[1] - now[0];
                prev = now;
            }
        }
        System.out.println(result);
    }
}