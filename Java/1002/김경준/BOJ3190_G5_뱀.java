import java.io.*;
import java.util.*;

/**
 * G5 BOJ 3190 뱀
 * 메모리 : 14328kb 시간 : 136ms
 * 
 * 터널 문제와 비슷하다
 * 트럭이 1초에 1씩 이동한다고 했을 때  길이 N의 트럭이 터널에서 완전히 나오려면 N초만큼의 시간이 필요하다.
 * 
 * 뱀 문제에 적용하면 특정 좌표에 도달했을 때
 * ==> (현재 시각 - 뱀 길이 <= 이전에 해당 좌표에 도착한 시간)인 경우 뱀의 몸뚱아리가 아직 해당 좌표에 남아있다는 것이 된다. 
 */
public class BOJ3190_G5_뱀 {
	static int N,K,L;
	static int map[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,-1,0,1};
	static Map<Integer, Character> m = new HashMap<Integer, Character>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int apple_x = Integer.parseInt(st.nextToken());
			int apple_y = Integer.parseInt(st.nextToken());
			map[apple_x - 1][apple_y - 1] = -1;
		}
		
		L = Integer.parseInt(br.readLine());
		// int char 관리하기 귀찮아서 그냥 map에 넣음
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			char op = st.nextToken().charAt(0);
			m.put(count,op);
		}
		
		int dir = 3, cnt = 1, body_size = 1;
		int x = 0, y = 0;
		while(true) {
			x += dx[dir];
			y += dy[dir];
			
			// 벽에 부딪히는지 체크
			if(oob(x,y)) break;
			
			// 머리랑 몸뚱아리랑 부딪히는지 체크
			if(map[x][y] != 0 && cnt - body_size <= map[x][y]) break;
			
			// 사과 먹으면 몸길이 증가
			if(map[x][y] == -1) body_size++;
			
			// 좌표에 현재시각 마킹
			map[x][y] = cnt;
			
			// 머리 돌리기
			if(m.get(cnt) != null) {
				char op = m.get(cnt);
				if(op == 'L') dir = (dir + 1) % 4;
				else dir = dir > 0 ? dir - 1 : dir + 3;
			}
			cnt++;
		}
		System.out.println(cnt);
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
