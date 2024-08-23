import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int result = 0;
        int temp = 0;
        boolean zero = false;
        for(int i = n - 1; i >= 0; i--){
            if(arr[i] > 0){
                if(temp != 0){
                    result += temp * arr[i];
                    if(temp == 1 || arr[i] == 1)
                        result++;

                    temp = 0;
                }
                else
                    temp = arr[i];
            }
            else if(arr[i] < 0){
                result += temp;
                temp = 0;

                for(int j = 0; j <= i; j++){
                    if(temp != 0){
                        result += temp * arr[j];
                        temp = 0;
                    }
                    else
                        temp = arr[j];
                }
                break;
            }
            else{
                result += temp;
                temp = 0;
                zero = true;
            }
        }

        if(temp < 0 && zero)
            temp = 0;
        result += temp;

        System.out.println(result);
    }
}