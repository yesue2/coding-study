import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        List<Integer> arr1 = new ArrayList<>();
        List<Character> arr2 = new ArrayList<>();

        HashSet<Character> set = new HashSet<>();
        set.add('+');
        set.add('-');

        StringBuilder sb = new StringBuilder();
        sb.append('0');

        for(int i = 0; i < s.length(); i++){
            if(set.contains(s.charAt(i))) {
                arr1.add(Integer.parseInt(sb.toString()));
                sb.setLength(0);
                arr2.add(s.charAt(i));
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        arr1.add(Integer.parseInt(sb.toString()));
        int idx = -1;
        int sum = arr1.get(0);
        for(int i = 0; i < arr2.size(); i++){
            if(arr2.get(i) == '+')
                sum += arr1.get(i + 1);
            else {
                idx = i + 1;
                break;
            }
        }

        if(idx == -1){
            bw.write(String.valueOf(sum));
        }
        else{
            for(int i = idx; i < arr1.size(); i++){
                sum -= arr1.get(i);
            }
            bw.write(String.valueOf(sum));
        }

        bw.flush();
        bw.close();
    }
}
