import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 4698 테네스의 특별한 소수
 * 소수, 에라토스테네스의 체 | 10min
 * 
 * sol)
 * 에라토스테네스의 체를 구현하여 소수를 구한 뒤, 해당 소수를 문자열로 변환하여 특별한 수로 만들어주는 D가 포함되는지 확인
 * 
 * 에라토스테네스의 체
 * 개념 - 소수는 1과 자기 자신만을 약수로 가지는 수, 따라서 특정 인덱스로 배수를 만들 수 있는 수는 1과 자기자신을 제외한 특정 인덱스를 약수로 가지는 수이므로 소수 조건에 어긋남
 * 구현
 * - (소수를 구하고자 하는 범위(n)+1) 만큼의 배열을 생성하여 1번부터 n번까지의 인덱스를 소수로 대치
 * - 0, 1번 인덱스는 false로 초기화하고, 2번 인덱스부터 자기 자신을 제외한 자신의 배수 인덱스들은 (소수로부터)제거해나감
 * - 위의 과정을 반복하면 1~n까지의 소수가 저장됨
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_4698_테네스의특별한소수 {
	static boolean[] prime = new boolean[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케 입력 전에 미리 소수를 구해놓고 시작
		primeNum();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// 출력
			StringBuilder sb = new StringBuilder();
			// 미리 구한 소수 중 특별한 소수에 해당하면 개수 카운트
			sb.append("#").append(tc).append(" ").append(specialPrimeNum(D, A, B));
			System.out.println(sb);
		}

	}

	// 소수 구하는 메서드(에라토스테네스의 체)
	private static void primeNum() {
		// 범위 내 모든 수를 확인해야 하므로 우선 true로 초기화
		Arrays.fill(prime, true);
		// 1부터 시작하기 위해 0번은 false로 무시, 소수는 1보다 큰 자연수 중 1과 자기자신만을 소수로 갖는 수이므로 1도 무시
		prime[0] = prime[1] = false;
		
		// 2부터 목표 수 범위까지 반복
		for (int i=2; i<1000001; i++) {
			// 만약 소수인 수이면
			if (prime[i]) {
				// 해당 수는 소수이므로 저장하고 해당 수의 배수들을 제거해나감
				for (int j=2*i; j<1000001; j+=i) prime[j] = false;
			}
		}
	}
	
	// 소수 중 특별한 수가 포함되었는지 확인하는 메서드
	private static int specialPrimeNum(int target, int from, int to) {
		int special = 0;     // 특별한 수 개수
		
		for (int i=from; i<=to; i++) {
			// 소수가 아니면 pass
			if (!prime[i]) continue;
			
			// 소수와 특별한 수(D)를 문자열로 변환한 뒤 소수에 특별한 수가 포함되었는지 contains() 메서드로 확인
			String targetNum = Integer.toString(i);
			if (targetNum.contains(Integer.toString(target))) special++;
		}
		
		return special;
	}

}
