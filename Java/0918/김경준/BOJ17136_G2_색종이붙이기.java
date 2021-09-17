import java.io.*;
import java.util.*;

/**
 * G2 BOJ 17136 괄호 추가하기 : 메모리 : 184132kb 시간 : 528ms
 * 
 * 1로 마크된 좌표에서 1*1 , 2*2,,, 5*5 색종이를 붙이는 모든 경우의 수를 고려하는 풀이
 * 매번 copymap을 새로 만든 후 넣었더니 메모리, 시간 효율이 안나는듯..
 *  
 * 
 */

public class BOJ17136_G2_색종이붙이기 {
	static int N, answer = Integer.MAX_VALUE, length;
	static List<Pair> one_pos;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[10][10];
		one_pos = new ArrayList<Pair>();
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) one_pos.add(new Pair(i, j)); // 1 좌표를 저장함
			}
		}
		length = one_pos.size();
		if(length == 0) System.out.println(0);
		else {
			int[] papers = new int[] {-1,5,5,5,5,5}; // 1,2,3,4,5 사이즈 종이의 개수
			dfs(0, papers, 0, map);
			if(answer == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(answer);
		}
	}
	// one_num : 현재 탐색하는 좌표의 list index
	static void dfs(int one_num, int[] papers, int use_num, int[][] map) {
		if (one_num == length) { // 모든 1 좌표를 처리한 경우
			answer = Math.min(answer, use_num); // 최소값 갱신
			return;
		}
		
		if(use_num > answer) return;  // 만약 지금까지 사용한 색종이 개수가 정답보다 커진 경우 백트래킹
		
		int x = one_pos.get(one_num).x;
		int y = one_pos.get(one_num).y;
		
		if(map[x][y] == 0) {          // 만약 이전에 색종이를 덮어씌운 작업을 통해 기존 1 좌표가 사라졌다면, 다음 1 좌표를 찾아서 이동
			int new_one_num = length;
			for(int i = one_num; i < length; i++) {
				if(map[one_pos.get(i).x][one_pos.get(i).y] == 1) {  // 1이 나올때까지 계속 좌표 이동
					new_one_num = i;
					break;
				}
			}
			dfs(new_one_num, papers, use_num, map);  // 다음 1 좌표를 찾았다면 dfs 진행
		}else {
			for(int i = 5; i >= 1; i--) {  // 1,2,3,4,5 색종이 붙이기 가능한지?
				int remain_paper = papers[i];
				if(remain_paper > 0 && x + i <= 10 && y + i <= 10) {
					if(check(x,y,i,map)) {  // size * size만큼 1로 채워져 있는지?
						int[][] copied_map = copymap(map);
						for (int k = x; k < x + i; k++) {
							for (int q = y; q < y + i; q++) {
								copied_map[k][q] = 0;
							}
						}
						papers[i]--;
						dfs(one_num + 1, papers, use_num + 1, copied_map);
						papers[i]++;
					}
				}
			}
		}
	}

	static int[][] copymap(int[][] origin) {
		int[][] temp = new int[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				temp[i][j] = origin[i][j];
		return temp;
	}
	
	static boolean check(int x, int y, int size, int[][] map) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
