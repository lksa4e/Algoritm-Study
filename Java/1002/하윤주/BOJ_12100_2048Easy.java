import java.io.*;
import java.util.*;

/**
 * [1002] BOJ 12100 2048(Easy)
 * 완전탐색, 중복 순열, 시뮬레이션, 구현
 * 
 * sol)
 *  재귀를 이용하여 4가지 방향에 대해 5번 이동해가며 이동할 수 있는 모든 경우의 수를 구하고, 그 과정에서 최대 숫자를 찾는다.
 *  
 * 시행착오)
 * 	각 방향으로 재귀를 탈 때마다 지도를 복사해줘야지 다시 재귀로 돌아왔을 때 원본 지도를 유지할 수 있는데, 이를 빼먹고 이미 이동한 지도로 계속 갖고다니면서 고생했다..
 * 	2차원 배열 시뮬은(시뮬은 거의 2차원 배열이겠지만..) 문제 풀기 전에 지도 복사가 필요한지 한번 더 고민해봐야함.
 * 
 *  중복되는 코드가 너무 보기 싫어서 뜯어 고쳤는데 고치다가 더 틀리더라구욬ㅋㅋ쿠ㅜ easy도 이정도인데 hard는 절대 풀지 말아야겠다고 다짐했습니다.
 *
 */

public class BOJ_12100_2048Easy {
	static int N, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 원본 지도 입력
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 모든 경우의 수 탐색
		dfs(map, 0);
		System.out.println(max);
	}

	// 4방향씩 5번 이동하며 최댓값 갱신
	private static void dfs(int[][] map, int depth) {
		// 기저조건 : 5번 이동 끝나면 리턴
		if (depth==5) return;
		
		// 유도파트 : 각 이동에서 4가지 방향 모두 고려해서 이동
		int[][] copiedMap = play(map, 0, N, 1);				// 좌로 이동, play(지도, 출발인덱스, 도착인덱스, 방향)
		dfs(copiedMap, depth+1);
		 
		copiedMap = play(map, N-1, -1, -1);					// 우로 이동
		dfs(copiedMap, depth+1);
		
		copiedMap = rotateMap(map);							// 위로 이동 (위, 아래 이동은 행렬을 뒤집은 배열을 이용한 뒤 다시 원상복구)
		copiedMap = play(copiedMap, 0, N, 1);
		copiedMap = rotateMap(copiedMap);
		dfs(copiedMap, depth+1);
		 
		copiedMap = rotateMap(map);							// 아래로 이동
		copiedMap = play(copiedMap, N-1, -1, -1);
		copiedMap = rotateMap(copiedMap);
		dfs(copiedMap, depth+1);
	}
	
	// dfs에서 각 방향 별로 이동하여 시뮬레이션 하는 부분
	private static int[][] play(int[][] map, int s, int e, int k) {
		int[][] copiedMap = copyMap(map);			// 지도 복사한 뒤
		concat(copiedMap, s, e, k);					// 지도의 숫자들을 합치고
		rearrange(copiedMap, s, e, k);				// 이동한 방향으로 압축하여 숫자 재배치
		return copiedMap;							// 다음 dfs탐색을 위해 변경된 지도 반환
	}
	
	// 지도의 숫자들을 합치는 함수
	private static void concat(int[][] map, int s, int e, int k) {
		int i, j;										// 2차원 배열 인덱싱을 위한 변수
		i = s-k;										// 바깥 for문을 위한 변수
		
		while((i+=k)!=e) {								// for (int i=0; i<N; i++) 에 해당하는 부분
			int from = s;								// 두개의 점을 이동하며 합칠 구간 찾기
			int to = s;
			
			j = s;										// 안쪽 for문을 위한 변수
			while ((j+=k)!=e) {							// for (int j=1; j<N; j++) 에 해당하는 부분
				to = j;									// 구간의 뒷 포인터는 무조건 증가하며
				
				if (map[i][j]==0) continue;				// 지도가 비어있으면 pass
				
				if (map[i][from]==0) from = j;			// 구간의 앞 포인터가 0이면 앞 포인터부터 채우기
				else {									// from이 0 이상으로 채워져있고 j도 방금 0 이상으로 채웠으므로 구간 비교
					if (map[i][from] == map[i][to]) {	// 같으면 합치기
						map[i][from] *= 2;
						map[i][to] = 0;
					}
					from = j;							// 한번 합친 숫자는 또 합칠 수 없으므로 from 이동
				}
			}
		}
	}
	
	// 이동한 방향으로 지도를 압축하여 숫자를 재배치하는 함수
	private static void rearrange(int[][] map, int s, int e, int k) {
		Queue<Integer> vacant = new ArrayDeque<>();			// 비어있는 칸의 인덱스를 저장할 큐
		int i, j;											// 2차원 배열 인덱싱을 위한 변수
		i = s-k;											// 바깥 for문을 위한 변수
		
		while((i+=k)!=e) {									// for (int i=0; i<N; i++) 에 해당하는 부분
			vacant.clear();
			
			j = s-k;										// 안쪽 for문을 위한 변수
			while ((j+=k)!=e) {								// for (int j=0; j<N; j++) 에 해당하는 부분
				if (map[i][j]==0) vacant.offer(j);			// 지도가 비어있으면 해당 공간으로 압축할 수 있으믈 큐에 저장
				else {										// 지도가 채워져 있으면
					max = Math.max(max, map[i][j]);			// 최댓값 갱신 시도하고
					
					if (vacant.isEmpty()) continue;			// 큐가 비어있으면 이미 압축된 상태이므로 pass
					map[i][vacant.poll()] = map[i][j];		// 큐에 저장된 빈 공간에 압축하고
					map[i][j] = 0;							// 압축되어 숫자가 사라진 곳은 빈 공간 처리하여 다시 큐에 삽입
					vacant.offer(j);
				}
			}
		}
	}
	
	// 위, 아래 이동을 위해 지도를 회전하는 함수
	private static int[][] rotateMap(int[][] map) {
		int[][] rMap = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) rMap[i][j] = map[j][i];
		}
		return rMap;
	}
	
	// 지도를 복사하는 함수
	private static int[][] copyMap(int[][] map) {
		int[][] rMap = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) rMap[i][j] = map[i][j];
		}
		return rMap;
	}

}
