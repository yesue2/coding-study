import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            long result = 0;
            int max = arr[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                if(arr[i] < max){
                    result += max - arr[i];
                    continue;
                }
                max = arr[i];
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}