import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = Integer.parseInt(br.readLine());
        int arr[] = new int[cnt];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int sum1 = 0;
        int sum2 = 0;

        for(int i = 0; i < cnt; i++){
            sum1 += arr[i];
            sum2 += sum1;
        }

        bw.write(String.valueOf(sum2));
        bw.flush();
        bw.close();
    }
}