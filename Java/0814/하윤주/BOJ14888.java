import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0814] 백준 14888 연산자 끼워넣기
 * 순열 - 같은 것이 있는 순열
 * 
 * sol)
 * 연산자로 순열을 만든 뒤 모든 경우의 수에 대해 연산을 시도
 * 재귀를 통한 순열 구현
 * 
 * 시행착오)
 * 같은 것이 있는 순열에 사로잡혀서 순열 재귀함수 구현 시 start 매개변수를 설정하여 값을 달리주고 별 시도를 다해봤는데요,
 * 그냥 현재 남아있는 연산자를 선택했다 선택하지 않았다 하니까 올바른 순열이 만들어지네요.휴.
 * 
 * time_complex)
 * O(N*N!)(하지만 같은 것이 있는 순열은 n!보다 작음).
 */

public class BOJ14888 {
	static int N, M;
	static int maxCal = Integer.MIN_VALUE;     // 최댓값 설정, 0으로 초기화 주의
	static int minCal = Integer.MAX_VALUE;     // 최솟값 설정, 0으로 초기화 주의
	static int[] nums;                         // 피연산자 숫자 배열
	static int[] operators;       // 0:덧셈(+), 1:뺄셈(-), 2:곱셈(×), 3:나눗셈(÷)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = N-1;      // 연산자 수
		
		// 피연산자 배열
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());

		// 연산자 등장 횟수 배열
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) operators[i] = Integer.parseInt(st.nextToken());
		
		// 연산자 순열 생성 메서드 호출
		lineUpOperators(0, nums[0]);    // (재귀 깊이, 연산 결과)
		
		StringBuilder sb = new StringBuilder();
		sb.append(maxCal).append("\n").append(minCal);
		System.out.println(sb);
		
	}

	// 재귀를 통한 연산자 순열 생성하는 메서드
	private static void lineUpOperators(int depth, int answer) {
		// 기저 조건(연산자를 모두 사용했을 경우)
		if (depth == M) {
			maxCal = maxCal > answer ? maxCal : answer;    // 최댓값 갱신
			minCal = minCal < answer ? minCal : answer;    // 최솟값 갱신
			return;
		}
		
		// 유도 파트(연산자를 하나씩 선택해보고 선택해보지 않으며 순열 생성)
		for (int i=0; i<4; i++) {             // 0:덧셈(+), 1:뺄셈(-), 2:곱셈(×), 3:나눗셈(÷)
		    // 연산자가 더이상 남아있지 않다면 무시
			if (operators[i]<= 0) continue;
			
			// 실제 연산을 수행하는 메서드를 호출하여 현재 깊이까지의 연산 결과 도출
			int curCalc = calculate(answer, nums[depth+1], i);
			operators[i]--;     // 순열을 위한 방문 체크
			
			// 재귀로 더 이상 깊게 들어갈 수 없을때까지 반복
			lineUpOperators(depth+1, curCalc);
			
			operators[i]++;
		}
	}
	
	// 실제 연산을 수행하는 메서드
	private static int calculate(int x, int y, int oper) {
		int calcResult = 0;
		
		switch (oper) {
		case 0:   // 덧셈
			calcResult = x + y;
			break;
		case 1:   // 뺄셈
			calcResult = x - y;
			break;
		case 2:   // 곱셈
			calcResult = x * y;
			break;
		case 3:   // 나눗셈
			calcResult = x / y;
			break;
		}
		return calcResult;
	}

}