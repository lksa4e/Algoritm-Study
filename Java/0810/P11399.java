/**
문제 링크 : https://www.acmicpc.net/problem/11399
모든 사람이 돈을 인출하는데 걸리는 시간을 최소로 하려면, 인출하는데 필요한 시간이 적은 순서대로 인출을 해야한다.
N번째 사람까지 인출하는데 걸린 시간은 N-1번째 사람이 인출하는데 걸린 시간 + N번째 사람이 인출하는데 걸린 시간이다.
arr[] 배열을 통해 모든 사람이 인출하는데 걸린 시간을 계산하고, 합을 구하면서 모든 사람이 돈을 인출하는데 필요한 시간의 합을 구할 수 있다.

*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11399 {
	static int N;
	static int arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int answer = arr[0];
		for(int i = 1; i < N; i++) {
			// arr[i] -> i 번째 사람의 인출 완료까지 걸린 시간 = i번째 사람의 순수 인출 시간 + 대기시간
			//                                    = i번째 사람의 순수 인출 시간 + i-1번째 사람의 인출 완료까지 걸린 시간
			arr[i] += arr[i-1];  
			answer += arr[i];    // 모든 사람의 인출 시간 더하기
		}
		System.out.println(answer);
	}
}
