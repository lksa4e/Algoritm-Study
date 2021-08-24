import java.io.*;
import java.util.*;
/**
 * SWEA 6485 삼성시의 버스 노선 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo
 * 
 * 모든 건초더미를 같은 크기(Size)로 만들때 한 건초더미의 사이즈 => 모든 건초더미의 사이즈 / N
 * 각 건초더미를 Size 크기로 만들기 위해 필요한 연산량 => abs(Size - 건초사이즈)
 * 모든 연산량을 더한 뒤 2로 나눈다. => 어떤건 빼고 어떤건 더하는데 실제로는 1번의 움직임으로 인한 결과이기 때문
 *  
 */

public class SWEA_5603_건초더미_김경준 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			int sum = 0, answer = 0;
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				sum += arr[i];
			}
			sum /= N;
			for(int num : arr) answer += Math.abs(num - sum);
			sb.append("#").append(tc).append(" ").append(answer / 2).append("\n");
		}
		System.out.print(sb);
	}	
}
