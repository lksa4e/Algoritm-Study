package BaekOJ.study.date1228;

import java.io.*;
import java.util.*;

/*
 * 백준 1946 신입 사원
 * 
 * 그리디를 하기위해 정렬을 하고 한가지 기준을 가지고 선형으로 합격자 추가시켜줌 
 * 메모리랑 시간이 엄청나게 나옴...
 * 
 * 메모리 	시간
 * 308480	3256
 */

public class BaekOJ1946_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int T, N, applicant[][], result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			applicant = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				applicant[i][0] = Integer.parseInt(st.nextToken());
				applicant[i][1] = Integer.parseInt(st.nextToken());
			}
			// 그리디를 위해 정렬
			Arrays.sort(applicant, (a, b)->a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
			
			
			result = 1; // 일단 1과목 1등은 무조건 입사
			int rank = applicant[0][1]; 	// 2과목 기준으로 탐색
			for(int i = 1; i < N; i++) {
				if(rank > applicant[i][1]){	// 등수가 안좋은 사람을 찾아서 합격자 추가하고 등수 갱신
					result++;
					rank = applicant[i][1];
				}
			}
			
			System.out.println(result);
		}

	}
}