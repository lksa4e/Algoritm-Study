import java.util.Scanner;

/*
 * BOJ 2023번 신기한 소수
 * 
 * 시행착오,
 * 에라토스테레스의 체인줄 알고 풀었다가 메모리 초과
 * 
 * 질문란에서 아이디어를 얻어서 dfs로 가지치기하면서 풀었음
 * 
 * 17636KB	208ms
 */

public class BOJ_2023 {

	static int N, max;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		max = (int) Math.pow(10, N);
		
		primeDfs(2, 1);	// 맨 앞자리들은 2,3,5,7로만 시작
		primeDfs(3, 1);
		primeDfs(5, 1);
		primeDfs(7, 1);
		
		System.out.println(sb);
		sc.close();
	}

	static void primeDfs(int num, int cnt) {
		if(cnt == N) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			int next = num * 10 + i;
			if(isPrime(next)) primeDfs(next, cnt+1);
		}
	}

	static boolean isPrime(int num) {	// prime 판별, 0과 1은 안들어오니 패스
		for (int i = 2; i*i <= num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
