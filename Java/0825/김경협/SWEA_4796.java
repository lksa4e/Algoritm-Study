import java.util.Scanner;

/*
 *  SWEA 4796번 의석이의 우뚝 선 산
 *  
 *  연속으로 증가하는 왼쪽 산들과 연속으로 감소하는 오른쪽 산을 끼는 우뚝 선 산을 구하는 문제
 *  BufferdReader 사용 시 runtime error 발생
 * 
 *  총 3개의 인덱스: 왼쪽과 우뚝 선 산, 오른쪽으로 
 *  왼쪽 ~ 산 까지는 오름차순으로
 *  산 ~ 오른쪽 까지는 내림차순으로 인덱스를 찾았다.
 *  그리고 왼쪽 산의 개수 * 오른쪽 산의 개수로 이들을 포함하는 집합의 개수를 구했다.
 *  
 */

public class SWEA_4796 {
	static int N;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();
		
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			int[] mountains = new int[N + 1];
			
			for (int i = 0; i < N; i++)
				mountains[i] = sc.nextInt();
			
			mountains[N] = mountains[N-1]; // 맨 끝과 동일한 값을 하나 더 줘서, 내림차순 찾을 때 범위를 벗어나지 않게 한다.
			sb.append("#").append(tc).append(" ").append(cntHighest(mountains)).append("\n");
			
		}
		System.out.println(sb);
		sc.close();
	}
	
	static int cntHighest(int[] map) {
		int left = 0, center = 0, right = 0, cnt = 0;
		while(left < N - 1) {	// N에는 N-1과 동일한 값이 있으므로, 더 이상 탐색할 수 없을 때 left == N-1이 된다.
			center = left; 
			while(map[center] < map[center+1])	// left가 오름차순일때까지
				center++;
			
			right = center;
			while(map[right] > map[right+1])	// right가 내림차순일때까지
				right++;
			
			cnt += (center-left) * (right - center);
			left = right; // right를 다음 집합에서 또 사용할 수도 있으므로 다음 left는 right 부터 시작
		}
		return cnt;
	}

}
