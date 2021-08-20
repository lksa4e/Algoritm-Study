/**
문제 링크 : https://www.acmicpc.net/problem/11728
뻘짓리스트 
              풀이방법                                                                                                                        | 메모리      | 시간
N+M size Array, N+M 사이즈 한번에 sort                                 | 313988 | 1832
N+M size Array, While문 Merge sort, for문 나머지처리(한쪽 다 담고 남은거 몰아서) | 314992 | 1836
String Array, While문 Merge sort, for문 나머지 처리(st.nextToken제거)     | 417372 | 1636 
Priority Queue 사용, 전부 뽑아서 출력                                                                                | 346256 | 2460
N+M size Array, for문을 통한 merge sort (조건문 개수 줄이기)                | 302380 | 1568

*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11728 {
	static int N, M;
	static int[] arr1;
	static int[] arr2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr1 = new int[N];
		arr2 = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) arr1[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M; i++) arr2[i] = Integer.parseInt(st.nextToken());
		
		//merge sort 풀이
		int left_point = 0, right_point = 0;
		for(int i = 0; i < N + M; i++) {
			// left_point == N -> 왼쪽꺼 다썼다 -> 남은 오른쪽꺼는 전부 뒤에 붙이기만
			if(left_point == N) sb.append(arr2[right_point++]).append(" ");
			// right_point == M -> 오른쪽꺼 다썼다 -> 남은 왼쪽꺼는 전부 뒤에 붙이기만
			else if(right_point == M) sb.append(arr1[left_point++]).append(" ");				
			else {
				// 두 개 배열의 현재선택 원소를 비교해서 작은걸 선택하고 선택 배열의 현재 index 1 증가 
				if(arr1[left_point] < arr2[right_point]) {
					sb.append(arr1[left_point++]).append(" ");
				}else sb.append(arr2[right_point++]).append(" ");				
			}
		}
		System.out.println(sb.toString());
	}
}
