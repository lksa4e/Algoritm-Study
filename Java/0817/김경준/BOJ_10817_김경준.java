import java.io.*;
import java.util.*;
/**
 * BOJ 10817 세 수 : https://www.acmicpc.net/problem/10817
 * 풀이할거없음
 */
public class BOJ_10817_김경준 {
	static int[] arr = new int[3];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		System.out.print(arr[1]);
	}
}
