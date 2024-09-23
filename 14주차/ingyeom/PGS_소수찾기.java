import java.util.*;

public class Solution {
    String s;
    Set<Integer> answer = new HashSet<>();
    boolean[] visited;
    boolean[] isPrime;

    public void f(int size, StringBuilder now){
        if (now.length() == size) {
            int num = Integer.parseInt(String.valueOf(now));
            if (isPrime[num])
                answer.add(num);

            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if(visited[i])
                continue;

            visited[i] = true;
            f(size, new StringBuilder(now.append(s.charAt(i))));
            visited[i] = false;
            now = new StringBuilder(now.substring(0, now.length() - 1));
        }
    }

    public int solution(String numbers) {
        s = numbers;

        isPrime = new boolean[(int)Math.pow(10, s.length())];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i * i < isPrime.length; i++){
            if(!isPrime[i])
                continue;

            for(int j = i * i; j < isPrime.length; j += i)
                isPrime[j] = false;
        }

        for (int i = 1; i <= s.length(); i++) {
            visited = new boolean[s.length()];
            f(i, new StringBuilder());
        }

        return answer.size();
    }
}