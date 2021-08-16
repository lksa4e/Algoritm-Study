package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 백준 2798번 블랙잭 
 * 풀이 : NC3의 조합 중 M을 넘지 않으면서 M에 최대한 가까운 합 구하기 
 * 
 * 14196KB	152ms
 */
public class Solution2798_김다빈 {

	static int N, M;
	static int result;
	static int[] cards;
	static int[] order;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		cards = new int[N];
		order = new int[3];
		for(int i=0;i<N;i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0,0);
		
		// 찾을 수 없다면 입력 X 
		if(result <= M) System.out.println(result);
	}

	private static void combination(int start, int r) {
		if(r==3) {
			int sum = 0;
			for(int num : order) {
				sum += num;
			}
			// 합이 M보다 같거나 같고 이전에 찾은 수보다 가까우면 업데이트 
			if(sum <= M && Math.abs(M-sum) < Math.abs(M-result)) result = sum;
			return;
		}
		for(int i=start;i<N;i++) {
			order[r] = cards[i];
			combination(i+1, r+1);
		}
	}

}
