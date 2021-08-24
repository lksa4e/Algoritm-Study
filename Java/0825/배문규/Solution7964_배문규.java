package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 문제 보자마자 O(N)으로 안끝내면 안되겠다고 생각
 * 8/28일까지 제출하는 백준 2110 공유기 문제와 비슷한 부분이 있음
 * 최소거리안에 차원관문이 없으면 최대거리 지점에 차원관문을 설치하고감
 * 메모리		시간
 * 84,844	257
 */

public class Solution7964_배문규 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, D, cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {

		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			int[] city = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) city[i] = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			int idx = -1;
	loop:	while(++idx < N) {
				// 차원관문이 존재하면 넘어감
				if(city[idx] == 1) continue;
				// 차원관문이 존재하지 않으면 해당지점에서 1 ~ D-1 거리의 지점까지 차원관문이 존재하는지 확인 한다
				for(int i = idx+1; i < idx+D; i++) {
					if(city[i] == 1) { // 존재하면 다시 탐색할 인덱스를 존재하는 지점으로 갱신하고 while문 continue
						idx = i;
						continue loop;
					}
				}
				// 코드가 여기까지 도달했다면 차원관문을 설치해야 함. 차원관문을 설치하고 인덱스도 갱신 그리고 설치 수 카운트
				cnt++;
				city[idx+D-1] = 1;
				idx += D-1;
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
