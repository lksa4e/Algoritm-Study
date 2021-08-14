package P0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 9019번 DSLR 
 * 풀이 : BFS를 이용하여 최종값이 되는 최단 경로 찾기
 * 
 */

public class Solution9019_김다빈 {
	
	static String[] commandList = {"D", "S", "L", "R"};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=0;test_case<T;test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// 최단 경로를 저장해줄 String 배열 
			// 처음엔 방문했는지 여부를 경로가 저장되어있는지로 한번에 판단하려했지만
			// 맨 처음 path의 방문 여부를 판단할 수 없어 결과가 틀렸다고 나온다.. 
			String[] path = new String[10000];
			
			// 방문 여부를 저장해줄 boolean 배열 
			boolean[] visited = new boolean[10000];
			visited[start] = true;
			
			Arrays.fill(path, "");
			
			Queue<Integer> pos = new ArrayDeque<Integer>();
			pos.offer(start);
			
			while(!pos.isEmpty()) {
				int position = pos.poll();
				
				if(position == end) break;
				
				for(int i=0;i<4;i++) {
					int num = position;
					
					// 명령어 수행 
					switch (i) {
					case 0:	// D
						num = (2*num) % 10000;
						break;
					case 1:	// S
						num -= 1;
						if(num < 0) num = 9999;
						break;
					case 2:	// L
						num *= 10;	// d1 d2 d3 d4 0
						num = (num+num/10000) % 10000;	// (d1) d2 d3 d4 d1
						break;
					case 3:	// R
						int d4 = num - (num/10)*10; // d1 d2 d3 d4 - d1 d2 d3 0 = d4
						num = d4*1000 + num/10; // d4 0 0 0 + d1 d2 d3 = d4 d1 d2 d3
					}
					
					if(!visited[num]) {
						pos.offer(num);
						visited[num] = true;
						path[num] = path[position] + commandList[i];
					}
				}
			}
			sb.append(path[end]+"\n");
		}
		
		System.out.println(sb);
	}
}
