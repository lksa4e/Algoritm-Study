import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [0825] SWEA 2005 파스칼의 삼각형
 * 수학, 구현 | 20min
 * 
 * sol)
 * 각 삼각형의 단계를 1차원 배열로 갖는 2차원 배열을 만들어 파스칼의 삼각형 공식대로 원소 저장
 * 미리 10단계의 삼각형을 만들어두고 테케별로 출력만 하도록 함
 * 
 * time_complex)
 * 삼각형의 단계별 접근 : N
 * 이전 단계의 수들을 공식대로 더해 현재 단계 수를 완성 : 거의 * N
 * O(N^2)
 * 
 */

public class SWEA_2005_파스칼의삼각형 {
	static int[][] triangle = new int[10][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 미리 10단계의 파스칼 삼각형 배열을 완성하고 테케에서는 출력만 함
		makePascal();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append("\n");
			
			// 테케에서 주어진 N만큼의 삼각형 출력
			for (int i=0; i<N; i++) {
				for (int n : triangle[i]) sb.append(n).append(" ");
				sb.setLength(sb.length()-1);
				sb.append("\n");
			}
			
			System.out.print(sb);
		}
	}
	
	// 크기가 10인 파스칼 삼각형 만들기
	private static void makePascal() {
		triangle[0] = new int[] {1};           // 꼭대기 1로 초기화
		triangle[1] = new int[] {1, 1};        // 크기가 1인 삼각형까지 1로 초기화
		
		// 크기가 2인 삼각형부터 파스칼 삼각형 원리 적용
		for (int i=2; i<10; i++) {
			triangle[i] =  new int[i+1];
			Arrays.fill(triangle[i], 1);       // 우선 1로 초기화 하고
			
			// 자신의 왼쪽 위와 오른쪽 위에 숫자가 존재할 때만 두 수를 더해 자신을 변경
			for (int j=1; j<i; j++) triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
		}
	}

}
