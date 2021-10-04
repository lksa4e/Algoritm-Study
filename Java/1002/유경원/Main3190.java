import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main3190 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int N, K, L, map[][], time[], ans = 0;
	static char[] direction;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0};
    static Deque<int[]> q = new ArrayDeque<>();
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;  // 사과
        }

        L = Integer.parseInt(br.readLine());
        time = new int[L];
        direction = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            direction[i] = st.nextToken().charAt(0);
        }

        q.offer(new int[] {1, 1});
        map[1][1] = 1;

        playGame(1, 1, 0);
        System.out.println(ans);
    }

    public static void playGame(int x, int y, int d) {
        int i = 0;
        while (true) {
            if (i < L && ans == time[i]) {
                if (direction[i] == 'D') { // 우
                    d = (d + 1) % 4;
                } else if (direction[i] == 'L') {  // 좌
                    d = (d + 3) % 4;
                }
                i++;
            }

            int nx = x + dx[d];
            int ny = y + dy[d];
            q.addFirst(new int[] {nx, ny});   // 머리를 다음칸에 위치

            if (nx <= 0 || ny <= 0 || nx > N || ny > N) {   // 밖으로 나가는 경우
                ans++;
                break;
            }

            if (map[nx][ny] == 2) { // 사과가 있다면
                map[nx][ny] = 1;
            } else if (map[nx][ny] == 0) {    // 사과가 없다면
                map[nx][ny] = 1;
                map[q.peekLast()[0]][q.peekLast()[1]] = 0;    // 꼬리가 위치한 칸 비워주기
                q.pollLast();
            } else {  // 자기 자신과 부딪힌다면
                ans++;
                break;
            }

            x = nx;
            y = ny;
            ans++;
        }
    }
}
