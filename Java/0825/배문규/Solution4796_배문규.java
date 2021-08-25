package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 *  BufferedReader가 9000바이트 정도의 입력 제한이 있다는 것을 알게된 문제
 *  풀기전 미리 알아서 다행이다 ㅎㅎ
 *  종이에 그림을 그려보면서 생각을 해보니까 시작점, 꼭대기, 끝지점을 알아야 문제를 풀 수 있어서
 *  세 지점을 정확히 찾고 구간은 꼭대기를 기준으로 좌우 크기를 곱해서 구했음
 *  그리고 끝지점을 시작점으로 갱신해서 다시 구간을 찾음
 *  
 *  메모리	시간
 *  104,424 677 
 */

public class Solution4796_배문규 {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static int T, N, mtn[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			mtn = new int[N];
			for(int i = 0; i < N; i++) mtn[i] = sc.nextInt();
			
			// 구간 개수, 시작점, 꼭대기, 끝지점
			int cnt, start, top, end;
			cnt = start = top = end = 0;
			
			// 시작점 찾기
			while(start != N-1 && mtn[start] > mtn[start+1]) start++;
			
			// 시작점을 start로 해서 반복문이 끝나면, 시작점 i를 end로 갱신.
			// top과 end가 top+1, end+1까지 체크하니까 i는 N-2까지 반복
			for(int i = start; i < N-1; i = end) {
				top = i;
				while(top != N-1 && mtn[top] < mtn[top+1]) top++; // 꼭대기 찾기
				end = top;
				while(end != N-1 && mtn[end] > mtn[end+1]) end++; // 끝지점 찾기
				cnt += (top-i)*(end-top); // 구간 개수 더하기 (꼭대기를 기준으로 좌우 크기 곱하면 구간의 개수가 됨)
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
