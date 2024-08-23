import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int sum = 0;
        int minus = 0;
        int plus = N;
        boolean zero = false;
        int one = 0;
        for(int i = 0; i < N; i++){
            if(arr[i] < 0)
                minus++;
            else if (arr[i] == 0)
                zero = true;
            else if (arr[i] == 1)
                one++;
            else{
                break;
            }
        }
        for(int i = N - 1;  i >= 0; i--){
            if(arr[i] > 1)
                plus--;
            else
                break;
        }

        if(minus % 2 == 0) {
            for (int i = 0; i <= minus - 2; i += 2){
                sum += arr[i] * arr[i + 1];
            }
        }
        else{
            for(int i = 0; i <= minus - 2; i += 2){
                sum += arr[i] * arr[i + 1];
            }
            if(!zero){
                sum += arr[minus - 1];
            }
        }
        sum += one;


        for(int i = N - 1; i > plus; i -= 2) {
            sum += arr[i] * arr[i - 1];
        }
        if((N - plus) % 2 != 0 && plus != N) {
            sum += arr[plus];
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }
}

