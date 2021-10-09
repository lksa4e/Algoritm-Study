import java.io.*;
import java.util.*;

/*
 * SWEA 4014번, BOJ 14890번 활주로 건설
 * 
 *  시뮬레이션
 *  가로로 긋는 경사로는 배열 그대로 사용하고
 *  세로로 긋는 경사로는 배열을 90도 회전시켜서 탐색했다.
 *  
 *  경사로 만들 때, 위로 가는 경우, 평평한 경우, 아래로 가는 경우로 나눠서 풀었다.
 */

public class BOJ_14890 {
	static int N, X, map[][], rotatedMap[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		rotatedMap = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++)
				rotatedMap[c][r] = map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			cnt += buildRoad(map[i]);
			cnt += buildRoad(rotatedMap[i]);
		}
		System.out.println(cnt);
	}
	
	static int buildRoad(int[] arr) {
		int currRoad = arr[0], cntRoad = 0;	// 시작점에 0번째 값 넣고 시작
		
		for (int i = 0; i < N; i++) {
			int diff = currRoad - arr[i];	// 이전 칸과 현재 칸의 높이 차이
			if(diff == 0) cntRoad++;	// 평지
			else if(diff == 1) {	// 아래로 가는 경사
				
				currRoad -= diff; // 아래로 한 칸 내리기
				cntRoad = 1;
				
				while(cntRoad < X) {	// 경사로 짓기
					if(++i >= N) return 0;	// 경사로 만들다가 맵 밖으로 나가서 실패
					if(arr[i] == currRoad)	
						cntRoad++;
					else
						return 0;	// 경사로 만들고 있는데 또 다른 언덕 만났음
				}
				cntRoad = 0;
				
			} else if(diff == -1) {	// 위로 가는 경사
				if(cntRoad < X) return 0;
				currRoad -= diff;	// 위로 한 칸 올리기
				cntRoad = 1;
				
			} else	// 그 외의 경우 (높이 차이가 2 이상 날 때)
				return 0;
			
		}
		return 1;
	}
}
