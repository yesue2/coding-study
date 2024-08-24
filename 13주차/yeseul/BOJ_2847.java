import java.io.*;
import java.util.*;

public class BOJ_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N - 2; i >= 0; i--) {
            if (num[i] >= num[i + 1]) {    //다음 레벨보다 클 때
                answer += num[i] - (num[i + 1] - 1);    //감소 횟수
                num[i] = num[i + 1] - 1;    //감소 진행
            }
        }
        System.out.println(answer);
    }
}
