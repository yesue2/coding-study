import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            long sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int max = 0;
            for(int j = N - 1; j >= 0; j--){
                if(max < arr[j])
                    max = arr[j];
                else{
                    sum += (max - arr[j]);
                }
            }
            sb.append(sum).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
