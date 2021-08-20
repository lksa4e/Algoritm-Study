import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0821] 백준 1080 행렬
 * 2차원 배열, 그리디 | 30min
 * 
 * sol)
 * 정말 그리디하게 (0,0) 좌표부터 (N, M)까지 탐색하며 다른 것이 있으면 스위치 시도
 * 단, 경계 체크가 중요한데, 최초 입력받은 배열이 3x3 이하라면 같은지 다른지만 확인하고 스위치는 하지 않음
 * 또한 3x3 이상의 배열일지라도 다른 값이 존재하는 좌표가 (N-2 ~ N) 또는 (M-2 ~ M) 사이에 존재한다면
 * N-3 혹은 M-3 구간을 스위치하기
 * 
 * time_complex)
 * 다른 것이 있는지 탐색 : N^2 (최대 N : 50)
 * 다른 것이 있다면 교환 : * N^2
 * 마지막으로 뒤바뀐 배열이 타겟 배열하고 같은지 검사 : + N*2
 * 
 */

public class BOJ_1080_행렬 {
	static int N, M, cnt;       // 몇번 교환 일어나는지
	static boolean flag;        // 최종본에서도 다른 원소가 존재하는지 여부
	static char[][] before;     // 원본 배열
	static char[][] after;      // 타겟 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 생성
		before = new char[N][M];
		after = new char[N][M];
		
		for (int i=0; i<N; i++) before[i] = br.readLine().toCharArray();
		for (int i=0; i<N; i++) after[i] = br.readLine().toCharArray();
		
		// 행렬이 3x3 이상인 경우에만 스위치 시도
		if (N>=3 && M>=3) findDifference();
		
		// 행렬이 3x3 이하인 경우에는 입력 자체가 최종본이므로(교환 불가) 타겟배열과 대조만 시도
		finalCheck();
		
		// 출력
		cnt = flag ? -1 : cnt;      // 최종본에도 타겟 값과 다른 값이 존재하면 예외처리
		System.out.println(cnt);
		
	}

	// 3x3 이상의 배열을 대상으로 교환할 좌표 탐색(타겟 배열과 값이 다른 좌표 찾기)
	private static void findDifference() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (before[i][j] != after[i][j]) {    // 값이 다른 경우를 찾으면 
					switchMatrix(i, j);               // 값을 교환
					cnt++;                            // 교환한 시도 증가
				}
			}
		}
	}

	// 값이 다른 부분이 존재하여 값을 교환하는 메서드
	private static void switchMatrix(int x, int y) {
		// 만약 교환할 좌표 위치가 N-2 혹은 M-2 이하라면
		if (N-x < 3) x = N-3;      // 경계 벗어나지 않도록 N-3 혹은 M-3으로 설정하여 접근
		if (M-y < 3) y = M-3;
		
		// 하나씩 접근하여 값을 교환
		for (int i=x; i<x+3; i++) {
			for (int j=y; j<y+3; j++) before[i][j] = before[i][j] == '0' ? '1' : '0';
		}
	}
	
	// 교환을 마친 최종본을 타겟 배열과 비교하는 메서드
	private static void finalCheck() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {   
				if (before[i][j] != after[i][j]) {    // 최종본임에도 타겟과 동일하지 않은 경우
					flag = true;                      // 예외 처리를 위해 플레그 설정
					return;                           // 뒤에는 더 보지 않고 반환
				}
			}
		}
	}

}
