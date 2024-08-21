import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine() + '+';

        int result = 0, index = 0;
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                int temp = Integer.parseInt(s.substring(index, i));
                index = i + 1;

                result += flag ? -temp : temp;

                if(s.charAt(i) == '-')
                    flag = true;
            }
        }
        System.out.println(result);
    }
}