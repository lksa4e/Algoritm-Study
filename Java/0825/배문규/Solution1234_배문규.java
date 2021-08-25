package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 새벽에 풀어서 머리가 더 안돌아가서 스택을 생각못함..
 * 처음엔 대칭이 되는 부분을 찾아서 양옆으로 퍼져나가면서 계속 값이 같은지 추적하고
 * 달라지면 오른쪽으로 탐색인덱스를 갱신해주면 된다고 생각함.
 * 그래서 while문으로 이미 제거됐으면 건너뛰고 제거 안된놈들 끼리 대칭을 확인하면서 문제를 해결함
 * 
 * 메모리		시간
 * 16,084 	99 
 */
public class Solution1234_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N;
	static char[] num;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			num = st.nextToken().toCharArray();
			check = new boolean[N];
			
			// 좌우 탐색 인터벌 인덱스
			int left, right;
			left = right = 0;
			for(int i = 0; i < N-1; i++) {
				// 대칭이면
				if(num[i] == num[i+1]) { 
					// 좌우를 퍼뜨리며 같은지 확인
					while(!oob(i-left, i+right+1) && num[i-left] == num[i+right+1]) {
						// 같으면 제거
						check[i-left] = true;
						check[i+right+1] = true;
						// 좌우 모두 다음 인덱스로 옮겨주는데 다음 인덱스가 이미 제거된거면 건너 뜀
						while(!oob(i-left) && check[i-left]) left++;
						while(!oob(i+right+1) && check[i+right+1]) right++;
					}
					// 탐색인덱스 갱신
					i += right;
					// 좌우 탐색 인터벌 0으로 초기화
					left = right = 0;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < N; i++) {
				if(!check[i]) sb.append(num[i]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// 바운더리 체크
	public static boolean oob(int i, int j) {
		if(i>N-1 || i<0 || j>N-1 || j<0) return true;
		else return false;
	}
	public static boolean oob(int i) {
		if(i>N-1 || i<0) return true;
		else return false;
	}
}
