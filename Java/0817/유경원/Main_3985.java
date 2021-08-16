import java.io.*;
import java.util.*;

public class Main_3985 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		boolean[] visited = new boolean[L+1];
		int start = 0, end = 0, preSum = 0, preMax = 0, postSum = 0, postMax = 0;
		int preIdx = 0, postIdx = 0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			preSum = end - start;
			// 가장 많이 갈 것으로 기대하는 방청객
			if(preMax < preSum) {
				preMax = preSum;
				preIdx = i;
			}
			postSum = 0;
			// 실제 가장 많이 가져가는 방청객
			for(int j=start; j<=end; j++) {
				if(!visited[j]) {
					visited[j] = true;
					postSum++;
				}
			}
			if(postMax < postSum) {
				postMax = postSum;
				postIdx = i;
			}
		}
		sb.append(preIdx).append("\n").append(postIdx);
		System.out.println(sb);
	}
}