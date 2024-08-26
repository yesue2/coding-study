import java.io.*;
import java.util.*;

public class Main {
    static int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static List<Day> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer st  =new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            for(int j = 0; j < startMonth - 1; j++){
                startDay += month[j];
            }
            for(int j = 0; j < endMonth - 1; j++){
                endDay += month[j];
            }
            list.add(new Day(startDay, endDay, 1));
        }
        int answer = flower();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    static int flower() {
        PriorityQueue<Day> queue = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);
        Iterator<Day> iterator = list.iterator();
        while (iterator.hasNext()) {
            Day now = iterator.next();
            if (60 >= now.start) {
                queue.add(now);
                iterator.remove();
            }
        }
        while (!queue.isEmpty()) {
            Day now = queue.poll();
            if (now.end >= 335)
                return now.count;

            int count = 0;
            iterator = list.iterator();
            while (iterator.hasNext()) {
                Day next = iterator.next();
                if (next.start <= now.end) {
                    queue.add(new Day(next.start, next.end, now.count + 1));
                    iterator.remove();
                    count++;
                }
            }
            if (count == 0)
                return 0;
        }
        return 0;
    }
    static class Day{
        int start;
        int end;
        int count;

        public Day(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }
    }


}