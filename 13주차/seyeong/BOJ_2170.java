import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] pos = new int[n][2];
        int result=0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            int p1 = Integer.parseInt(line.split(" ")[0]);
            int p2 = Integer.parseInt(line.split(" ")[1]);
            pos[i][0] = p1;
            pos[i][1] = p2;
        }

        Arrays.sort(pos, new Comparator<int[]>() { //x좌표 오름차순 정렬. x좌표 같으면 y좌표 오름차순 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])  return o1[1] - o2[1];
                else  return o1[0] - o2[0];
            }
        });

        int min = pos[0][0];//이전 선의 x좌표
        int max = pos[0][1];//이전 선의 y좌표
        int len = max - min;
        for(int i = 1; i < n; i++) {
            if(min <= pos[i][0] && pos[i][1] <= max) { //현재 선이 이전 선에 포함된다면
                continue;
            } else if(pos[i][0] < max) { //현재 선의 시작점이 이전 선에 포함된다면
                len += pos[i][1] - max;
            } else { //현재 선과 이전 선이 겹치지 않는다면
                len += pos[i][1] - pos[i][0];
            }
            min = pos[i][0];max = pos[i][1];
        }
        System.out.println(len);
    }
}
