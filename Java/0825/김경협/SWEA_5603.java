import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 *  SWEA 5603번 건초더미
 *  
 *  건초더미의 평균을 구해서, 평균보다 큰 더미들이 
 *  평균을 초과한 만큼 구해줬다.
 */

public class SWEA_5603 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] dummies = new int[N];
			int dummyThread = 0;
			for (int i = 0; i < N; i++) {
				dummies[i] = Integer.parseInt(br.readLine());
				dummyThread += dummies[i];
			}
			// 건초더미 평균
			dummyThread /= N;
			
			int move = 0;
			for(int dummy : dummies)
				if(dummy > dummyThread)	// 평균보다 큰 건축 더미들은 평균보다 큰 만큼 다 이동시켜야 함
					move += dummy - dummyThread;
			
			sb.append("#").append(tc).append(" ").append(move).append("\n");
		}
		
		System.out.println(sb);
	}

}
