import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());

            arr[i][0] = start;
            arr[i][1] = end;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];
            return o1[0] - o2[0];
        });

        int result = 0, index = 0;
        int start = 301, end = 301;
        while (start <= 1130) {
            boolean flag = false;

            while (index < n && arr[index][0] <= start) {
                if (arr[index][1] > end) {
                    end = arr[index][1];
                    flag = true;
                }
                index++;
            }

            if (!flag) {
                result = 0;
                break;
            }

            result++;
            start = end;
        }

        System.out.println(result);
    }
}