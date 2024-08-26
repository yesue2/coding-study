import java.io.*;
import java.util.*;

public class BOJ_1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();

        String str = br.readLine();
        // '-' 기준으로 식을 나눔
        String[] split = str.split("-");

        // 나눈 식의 덧셈을 수행
        for (String s : split) {
            int sum = 0;
            String[] split1 = s.split("\\+");
            for (String sp : split1) {
                sum += Integer.parseInt(sp);
            }
            // 결과를 리스트에 add
            list.add(sum);
        }
        // 리스트의 가장 첫 수는 덧셈을 해야 하므로 미리 더해줌
        long answer = list.get(0);
        // 그 다음 수부터 뺄셈을 하여 계산을 완료
        for (int i = 1; i < list.size(); i++) {
            answer -= list.get(i);
        }
        System.out.println(answer);
        br.close();
    }

}
