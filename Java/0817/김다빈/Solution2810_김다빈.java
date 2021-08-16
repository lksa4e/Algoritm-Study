package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2810번 컵홀더 
 * 풀이 : 컵홀더를 먼저 체크 (일반좌석이면 양옆, 커플석이면 가운데 하나 빼고 양옆)
 * 좌석 왼쪽부터 오른쪽 순으로 체크하면서 사용했으면 false로 변경하고 +1 
 * 
 * 14228KB	136ms
 */
public class Solution2810_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] chair = br.readLine().toCharArray();
		boolean[] holder = new boolean[N+1];	// 컵홀더는 (좌석+1)개 
		
		for(int i=0;i<N;i++) {
			if(chair[i] == 'S') {	// 일반 좌석 
				holder[i] = true;
				holder[i+1] = true;
			} else {	// 커플석 
				holder[i] = true;
				holder[(i++)+2] = true;
			}
		}
		
		int result = 0;
		for(int i=0;i<N;i++) {
			if(holder[i]) {
				holder[i] = false;
				result++;
			} else if(holder[i+1]) {
				holder[i+1] = false;
				result++;
			}
		}
		System.out.println(result);
	}

}
