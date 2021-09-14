package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 *  M이 200,000이라서 M^2는 40,000,000,000까지 가능해서 무조건 M log M의 방법을 사용해야함
 *  수많은 시행착오를 거치며 결국 알고리즘 분류를 보니 희소 배열이라는 알고리즘을 사용하는 문제였다
 *  
 *  희소배열의 개념을 좀 찾아보니 비트마스킹과 같은 개념으로 linear 타임을 log 타임으로 바꿔줄 수 있는 놀라운 알고리즘이 있더라...
 *  1000을 2인수로 표현하면 1111101000이고, 높은 비트자리부터 시작해서 비트가 1인 녀석들을 희소 테이블에서 추적하면 빠르게 결과를 얻을 수 있다.
 *  ...[9][값 = [10][값]] 이런식으로 이전 값을 가지고 다음 값을 추적함
 *  결국엔 1000을 탐색할 것을 각 비트의 10자리만 탐색하면 된다.
 *  개념을 이해하기까지 상당히 오래 걸렸다...사실 지금도 확실하게 이해한게 맞는건가 싶음
 * 
 *  * 시행착오:
 * 	각 함수 인덱스마다 반복되기 전까지의 인덱스 흐름을 기록함. 
 * 	그리고 반복되지 않는 부분을 구해서
 * 	n이 인덱스 흐름보다 클 때, 반복되지 않는 부분만큼 빼고 모드연산을 실시하여 값을 도출함
 *  ==> M이 200,000이라, 함수들을 인덱스 흐름의 찾을 때 워스트케이스 O(M^2)까지 가능
 *  ==> M log M이상 이면 시간초과
 *  
 *  메모리 	시간
 *  94836	976
 */
public class BaekOJ17435_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int M, Q, log, func[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		M = Integer.parseInt(br.readLine());
		// M의 로그값을 구하여서 희소 테이블을 선언
		log = (int)(Math.ceil(Math.log(M) / Math.log(2)));
		func = new int[log+1][M+1];

		// 먼저 0번째 자리는 기본 입력을 받는다.
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) func[0][i] = Integer.parseInt(st.nextToken());
		
		// M log M을 반복하며 희소 테이블을 생성
		for(int i = 1; i <= log; i++) {
			for(int j = 1; j <= M; j++) {
				func[i][j] = func[i-1][func[i-1][j]]; 
				// func[i][j]는 j번째 원소를 2^i번 만큼 이동시켰을 때의 결과값
				// 즉, j번째 원소를 2^(i-1)번 만큼 이동시켰을 때의 결과값을 2^(i-1)번 만큼 이동시킨 것과 같다.
				// 희소 테이블을 추적하는 [8][값 = [9][값 = [10][값]]]을 역순으로 생각하면 됨. 2^8과 2^9의 차이는 2^8
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			// n을 2진수로 변환하였을 때, 비트가 1인 녀석들만 골라서 희소 테이블을 점프 
			for (int j = log; j >= 0; j--) {
                int sub = (int) Math.pow(2, j);
                if (n >= sub) {
                    n -= sub;
                    x = func[j][x];
                    if(n == 0) break;
                }
            }
			
			sb.append(x).append("\n");
		}
		
		System.out.println(sb);
	}
}
