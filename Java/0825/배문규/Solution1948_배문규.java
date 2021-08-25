package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 지극히 평범한 달력 날짜계산
 * 여기에 더해서 year를 추가해서 윤년까지 고려해서 날짜나 요일 계산하는 문제가 im시험에 나오면 좋겠다
 * 메모리		시간
 * 18,672 	108 
 */
public class Solution1948_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, calender[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 1월 ~ 12월 요일

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int M1 = Integer.parseInt(st.nextToken());
			int D1 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			int D2 = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(calc(M1, D1, M2, D2)).append("\n");
		}
		System.out.println(sb);
	}
	
	// 날짜 계산
	public static int calc(int m1, int d1, int m2, int d2) {
		int cnt = 0;
		cnt += calender[m1]-d1+1; // 해당 달의 남은 요일
		for(int i = m1+1; i < m2; i++) cnt += calender[i]; // 온전히 사이에 낀 달들 날짜 추가
		if(m1 != m2) cnt += d2; // 달이 서로 다르면 d2도 추가  
		return cnt;
	}
}
