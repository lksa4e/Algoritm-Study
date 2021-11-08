import java.io.*;
import java.util.*;

/**
 * 백준 21608번 상어 초등학교 
 * 
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 
 * 시행착오 :
 * 	처음에 자리 배치 전 초기화 값을 maxValue = maxPos = maxEmpty = 0으로 주니까
 * 	maxValue, maxEmpty가 모두 0이 최적이 되는 경우를 체크하지 못해서 시작과 동시에 틀렸다..
 * 	maxValue = maxPos = maxEmpty = -1로 초기화하니 잘 풀렸다!
 * 
 * 18564KB	184ms
 */
public class Main21608_상어초등학교 {

	static int N;
	static int maxValue, maxPos, maxEmpty;
	static int[][] map;
	static List<Integer> order = new ArrayList<Integer>();
	static int[][] preference;
	
	static int[] dr = {-1,1,0,0};	// 상하좌우 
	static int[] dc = {0,0,-1,1};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        preference = new int[N*N+1][4];
        
        // 선호하는 학생 입력 받기
        for (int i = 0; i < N*N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken());
        	order.add(num);
        	
			for (int j = 0; j < 4; j++) {
				preference[num][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        // 순차적으로 자리 선정하기
        for (int i = 0; i < N*N; i++) {
        	maxValue = maxPos = maxEmpty = -1;	// 자리 초기화
        	setSeat(order.get(i), 0);
		}
        
        // 만족도 총합 계산하기
        int result = 0;
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int value = 0, cur = map[i][j];
				int nr, nc;
				
				for (int dir = 0; dir < 4; dir++) {
					nr = i + dr[dir];
					nc = j + dc[dir];
					
					if(isOOB(nr, nc)) continue;	// 경계를 벗어나면 패스
					if(checkPreference(cur, map[nr][nc])) {	// 좋아하는 학생을 만나면 만족도 ++ 
						value++;
					}
				}
				
				if(value != 0) {	// 만족도 10^(주변 사람 수 - 1) 더하기 
					result += (int)Math.pow(10, value-1);
				}
			}
		}
        
        System.out.println(result);
    }

    // curStudent : 현재 자리 선정하는 학생
    // curPos : 현재 위치
	private static void setSeat(int curStudent, int curPos) {
		if(curPos == N*N) {	// 모든 좌석을 확인했으면 맵에 표시 후 종료 
			map[maxPos/N][maxPos%N] = curStudent;
			return;
		}
		
		int curR = curPos / N, curC = curPos % N;
		
		// 빈칸이 아니면 다음 칸으로 이동 
		if(map[curR][curC] != 0) {
			setSeat(curStudent, curPos+1);
			return;
		}
		
		int curValue = 0, curEmpty = 0;
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = curR + dr[i];
			nc = curC + dc[i];
			
			if(isOOB(nr, nc)) continue;	// 경계를 벗어나면 패스 
			if(map[nr][nc] == 0) {	// 빈칸을 만나면 emptySeat ++
				curEmpty++;
				continue;
			}
			if(checkPreference(curStudent, map[nr][nc])) {	// 좋아하는 학생을 만나면 만족도 ++ 
				curValue++;
			}
		}
		
		// 좋아하는 학생 수가 많거나,
		// 수가 같고 비어있는 칸이 많다면 자리 업데이트 
		if(curValue > maxValue || (curValue == maxValue && curEmpty > maxEmpty)) {
			maxValue = curValue;
			maxPos = curPos;
			maxEmpty = curEmpty;
		}
		
		// 다음 칸으로 이동 
		setSeat(curStudent, curPos+1);
	}

	// student가 좋아하는 학생에 nextStudent가 포함되는지 확인하는 함수 
	private static boolean checkPreference(int student, int nextStudent) {
		for(int num : preference[student]) {
			if(num == nextStudent) return true;
		}
		return false;
	}

	// 경계값 체크 함수 
	private static boolean isOOB(int r, int c) {
		return (r<0 || r>=N || c<0 || c>=N);
	}
    
}