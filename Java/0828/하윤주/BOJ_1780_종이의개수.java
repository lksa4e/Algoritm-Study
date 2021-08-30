import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [0828] BOJ 1780 종이의 개수
 * 분할정복
 * 
 * sol)
 * 현재 종이가 같은 수들로 채워졌는지 확인하고 아니라면 길이를 3등분하여 재귀적으로 재탐색
 * 
 * tc)
 * N을 9개 등분하고 9번 재귀 돌며 각각 다시 N의 세제곱근만큼 탐색하므로
 * 9^(log3N) = N^2
 * 맞을까요..?
 *
 */

public class BOJ_1780_종이의개수 {
	
	static int N;
	static int[] papers = new int[4];     // 기재된 숫자 저장된 배열, 0:-1, 1:0, 2:1, 3:수가섞인경우
	static int[][] matrix;
   
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
   	
		// 종이 정보를 저장한 2차원 배열 생성
		matrix = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
      
		// 종이를 자르며 같은 수만 기재된 종이 개수 카운트
		cutPaper(0, 0, N);
      
		for (int i=0; i<3; i++) System.out.println(papers[i]);
	}

	// 종이를 조건에 맞게 자르는 메서드
	private static void cutPaper(int x, int y, int len) {
		// 종이가 어떤 숫자들로 채워졌는지 확인
		int filled = countNum(x, y, len);       
		papers[filled]++;           // (채워진 숫자+1) 인덱스로 접근하여 카운트 증가
      
		// 더이상 자를 수 없으면 반환
		if (len == 1) return;
      
		// 만약 종이가 서로 다른 수들로 채워졌다면 현재 함수를 반복적으로 호출하여 다시 자르기 시도
		if (filled == 3) {
			for (int i=0; i<3; i++) {             // 행 3등분
				for (int j=0; j<3; j++) {         // 열 3등분
					int cut = len/3;
					cutPaper(x+(i*cut), y+(j*cut), cut);      // 1/3길이로 다시 자르기 시도
				}
			}
		}
	}
   
	// 종이가 어떤 숫자들로 채워졌는지 확인하는 메서드
	private static int countNum(int x, int y, int len) {
		int base = matrix[x][y];
		
		for (int i=x; i<x+len; i++) {
			for (int j=y; j<y+len; j++) {
				if (matrix[i][j] != base) return 3;      // 다른 수들로 채워져있다면 (0~2)이외의 인덱스 반환
			}
		}
		return base+1;                                   // 같은 수들로 채워졌다면 채워진 수에 해당하는 인덱스 반환
	}

}