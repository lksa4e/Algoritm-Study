import java.io.*;
import java.util.*;

/**
 * G2 BOJ 15685 드래곤 커브
 * 메모리 : 14440kb 시간 : 140ms
 * 
 * n세대 드래곤 커브는 n-1 세대 드래곤 커브의 끝점에서 시작해서 역으로 출발점까지의 방향을 타고 간다.
 * 이때 반대로 뒤집은 방향은 90도를 회전시켰을 때 n세대 배열의 dir 값이 나와야 한다.
 * 이를 반대로 생각하면 n세대 배열을 -90도 회전시켰을 때 n+1 세대의 dir 값이 나오게 된다.
 * 
 * 수식으로 표현하면 
 * n 세대 dir 배열 = n-1 세대 드래곤 배열 + reverse(n-1 세대 드래곤 배열).rotate(-90) 가 된다. 
 * 
 */
public class BOJ15685_G4_드래곤커브 {
	static int N;
	static boolean map[][] = new boolean[101][101];
	static int dx[] = {0,-1,0,1};
	static int dy[] = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int dragon_gen = Integer.parseInt(st.nextToken());
			process(y, x, dir, dragon_gen);
		}
		System.out.println(count());
	}
	static int count() {
		// 상 하 좌 우 4방향이 모두 색칠되어있으면 answer++
		int answer = 0;
		for(int i = 0; i <= 99; i++) {
			for(int j = 0; j <= 99; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
					answer++;
			}
		}
		return answer;
	}
	static void process(int x, int y, int start_dir, int dragon_gen) {
		List<Integer> list[] = new ArrayList[dragon_gen + 1];
		for(int i = 0; i <= dragon_gen; i++) list[i] = new ArrayList<Integer>();
		
		list[0].add(start_dir);
		
		// 1세대 ~ n세대 드래곤 커브 만들기 위한 dir 배열값 만들기
		for(int i = 1; i <= dragon_gen; i++) {
			// 이전 세대 dir 배열 그대로 붙이기
			for(int j = 0; j < list[i-1].size(); j++)
				list[i].add(list[i-1].get(j));
			
			// 이전 세대 dir 배열의 역방향 탐색 + -90도 회전
			for(int j = list[i-1].size() - 1; j >= 0; j--)
				list[i].add((list[i-1].get(j) + 1) % 4);
		}
		
		
		// 드래곤 커브 따라가면서 맵 색칠해주기
		map[x][y] = true;
		for(int i = 0; i < list[dragon_gen].size(); i++) {
			x += dx[list[dragon_gen].get(i)];
			y += dy[list[dragon_gen].get(i)];
			map[x][y] = true;
		}
	}
}
