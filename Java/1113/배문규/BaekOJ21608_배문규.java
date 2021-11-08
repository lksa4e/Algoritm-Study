package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 21608 상어 초등학교
 * 
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 
 * 3개의 조건에 대한 결과를 각각 저장할 수 있는 배열을 이용해서 
 * 조건에 맞게 정렬하여 결과를 구함
 * 직관적이긴하나 효율적이진 않은듯..
 * 
 * 메모리 	시간
 * 48056	648
 */

public class BaekOJ21608_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int LIKE = 0, EMPTY = 1, IDX = 2;
	static int N, map[][], conditions[][], order[];
	static int delta[][] = {{-1,0}, {0,-1}, {0,1}, {1,0}};
	static HashMap<Integer, int[]> hashMap = new HashMap<Integer, int[]>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		conditions = new int[N*N][3];
		order = new int[N*N];
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			int tempArr[] = new int[4];
			for(int j = 0; j < 4; j++) tempArr[j] = Integer.parseInt(st.nextToken());
			hashMap.put(order[i], tempArr);
		}
		
		// 순서에 따라 자리찾기
		for(int idx = 0; idx < N*N; idx++) {
			setMap();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 해당 자리 주변확인
					for(int[] d : delta) {
						int di = i + d[0];
						int dj = j + d[1];
						if(isOOB(di, dj)) continue;
						
						// 해당 자리에 좋아하는 친구가 몇명있는지 카운트
						for(int k = 0; k < 4; k++) {
							if(map[di][dj] == hashMap.get(order[idx])[k]) {
								conditions[i*N+j][LIKE]++;
								break;
							}
						}
						
						// 해당 자리에 빈자리가 몇자리가 있는지 카운트
						if(map[di][dj] == 0) conditions[i*N+j][EMPTY]++;
					}
				}
			}
			
			// 조건에 따라 정렬하고 최적의 자리를 찾음
			Arrays.sort(conditions, (a,b) -> a[0] != b[0] ? b[0]-a[0] : a[1] != b[1] ? b[1] - a[1] : a[2] != b[2] ? a[2]-b[2] : a[3]-b[3]);
			for(int i = 0; i < N*N; i++) {
				if(map[conditions[i][IDX]/N][conditions[i][IDX]%N] == 0) {
					map[conditions[i][IDX]/N][conditions[i][IDX]%N] = order[idx];
					break;
				}
			}
		}
		
		System.out.println(getResult());
	}
	
	// 각 조건들의 결과를 담을 배열
	public static void setMap() {
		for(int i = 0; i < N*N; i++) {
			conditions[i][LIKE] = 0; 
			conditions[i][EMPTY] = 0; 
			conditions[i][IDX] = i; 
		}
	}
	
	public static int getResult() {
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int cnt = 0;
				for(int[] d : delta) {
					int di = i + d[0];
					int dj = j + d[1];
					if(isOOB(di, dj)) continue;
					for(int k = 0; k < 4; k++) {
						if(map[di][dj] == hashMap.get(map[i][j])[k]) {
							cnt++;
							break;
						}
					}
				}
				result += Math.pow(10, cnt-1);
			}
		}
		return result;
	}
	
	public static boolean isOOB(int i, int j) {
		return i > N - 1 || i < 0 || j > N - 1 || j < 0;
	}
}
