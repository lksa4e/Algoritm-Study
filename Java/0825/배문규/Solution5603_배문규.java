package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 아무 생각없이 minheap, maxheap 만들어서 풀었더니 바로 시간초과났음
 * 전체 건초의 합에서 N만큼 나눠서 평균을 구한 뒤
 * 평균 이상과 평균을 차를 구하여 문제를 해결함
 * 메모리 	시간
 * 23,848 	128 
 */
public class Solution5603_배문규 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, mow[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		 	for(int tc = 1; tc <= T; tc++) {
	            N = Integer.parseInt(br.readLine());
	            mow = new int[N];
	            // 건초더미를 입력받으면서 전체 건초더미의 합을 구함
	            int total = 0;
	            for(int i = 0; i < N; i++) {
	                mow[i] = Integer.parseInt(br.readLine());
	                total += mow[i];
	            }
	            // 건초더미가 평균이상이면 평균과의 차이를 구함
	            int cnt = 0;
	            for(int i = 0; i < N; i++) {
	            	if(mow[i] >= total/N) cnt += mow[i]-total/N;
	            }
	            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
	        }
		System.out.println(sb);
	}
}
