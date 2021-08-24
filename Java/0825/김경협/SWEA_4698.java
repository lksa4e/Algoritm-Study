import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  SWEA 4698번 테네스의 특별한 소수
 *  
 *  최대 1,000,000 이하의 소수를 구하는 문제,
 *  원래 하던 대로 sqrt를 이용해 각 수에 대해 소수를 구해주려 하니 시간초과가 떴다.
 *  
 *  그래서 최대수인 1,000,000까지 2부터 시작해서 소수에 대한 배수는 전부 지워가면서
 *  미리 소수를 구해놓고 시작했다.
 *  
 */

public class SWEA_4698 {
	static boolean[] nonPrimeNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		nonPrimeNum = new boolean[1000001]; 
		setPrime(1000000);
		
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			nonPrimeNum[1] = true;	// 1은 소수 아님
			nonPrimeNum[2] = false;	// 2는 이미 소수

			int cnt = getSpecialPrime(D,start,end);
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void setPrime(int end) {
		for(int n = 2; n <= end; n++) {
			if(!nonPrimeNum[n]) { // 소수일 때
				for(int m = 2; n*m <= end; m++) // 소수의 배수는 모두 소수가 될 수 없음
					nonPrimeNum[n*m] = true;
			}
		}		
		// 원래 하던 대로 구하려던 소수 코드
//		double sqrt = Math.sqrt(num);
//		for (int i = 2; i <= sqrt; i++)
//			if(num % i == 0)
//				return false;	
//		return true;
	}
	
	static int getSpecialPrime(int D, int start, int end) {
		int cnt = 0;
		for (int i = start; i <= end; i++)
			if(!nonPrimeNum[i] && isSpecial(i, D))
				cnt++;
		return cnt;
	}
	
	static boolean isSpecial(int num, int D) {
		while(num > 0) {
			if(num % 10 == D)
				return true;
			num /= 10;
		}
		return false;
	}
}
