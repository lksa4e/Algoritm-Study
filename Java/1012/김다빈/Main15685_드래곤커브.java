import java.io.*;
import java.util.*;

/**
 * 백준 15685번 드래곤 커브
 */
public class Main15685_드래곤커브 {
    static boolean[][] map = new boolean[101][101];
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());   // 시작 방향
            int generation = Integer.parseInt(st.nextToken());   // 세대

            dragonCurve(r, c, dir, generation);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void dragonCurve(int r, int c, int dir, int generation) {
        List<Integer> dragonList = new ArrayList<>();
        dragonList.add(dir);

        for (int i = 1; i <= generation; i++) {
            for (int j = dragonList.size() - 1; j >= 0; j--) {
                dragonList.add((dragonList.get(j) + 1) % 4);
            }
        }

        map[r][c] = true;
        for (Integer direction : dragonList) {
            c += dc[direction];
            r += dr[direction];
            map[r][c] = true;
        }
    }
}