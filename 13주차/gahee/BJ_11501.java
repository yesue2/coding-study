import java.io.*;
import java.util.*;

public class BJ_11501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long answer = 0;
            int max = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (max < arr[i]) {
                    max = arr[i];
                } else if (max > arr[i]) {
                    answer += max - arr[i];
                }
            }
            System.out.println(answer);
        } // [E] test_case
    } // [E] main
}
