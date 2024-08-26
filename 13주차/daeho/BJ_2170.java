import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] line = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(line, (j1, j2) -> j1[0] - j2[0]);
        int sum = 0;
        int X = line[0][0];
        int Y = line[0][1];

        for(int i = 1; i < N; i++){
            int count = 0;

            if(line[i][0] >= X && line[i][0] <= Y){
                Y = Math.max(line[i][1], Y);
            }
            else
                count++;

            if(line[i][1] >= X && line[i][1] <= Y){
                X = Math.min(line[i][0], X);
            }
            else
                count++;

            if(count == 2){
                sum += (Y - X);
                X = line[i][0];
                Y = line[i][1];
            }
        }
        sum += (Y - X);
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }
}

