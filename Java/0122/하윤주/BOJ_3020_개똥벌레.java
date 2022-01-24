import java.io.*;
import java.util.*;

/**
 * [0122] BOJ 3020 개똥벌레
 * sol) 구현, 누적합
 * 
 */

public class BOJ_3020_개똥벌레 {
	static int N, H, minObstacle = Integer.MAX_VALUE, minCnt;
	static int[] upObstacles, downObstacles;
	static PriorityQueue<Integer> up = new PriorityQueue<Integer>();     // 석순을 높이 순으로 저장
	static PriorityQueue<Integer> down = new PriorityQueue<Integer>();   // 종유석을 높이 순으로 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		upObstacles = new int[H];
		downObstacles = new int[H];
		// 입력
		for (int i=0; i<N; i++) {
			int obstacle = Integer.parseInt(br.readLine());
			if (i%2==0) up.offer(obstacle-1);    // 석순
			else down.offer(obstacle);           // 종유석
		}
		
		countUpObstacles();    // 석순의 각 높이 별 개똥벌레를 만나는 횟수
		countDownObstacles();  // 종유석의 각 높이 별 개똥벌레를 만나는 횟수
		getMin();              // 석순과 종유석을 모두 합쳐 개똥벌레를 최소로 만나는 장애물 높이
		
		System.out.println(minObstacle + " " + minCnt);
	}

	// 석순의 각 높이 별 개똥벌레 만나는 횟수를 저장
	private static void countUpObstacles() {
		int before = Integer.MAX_VALUE;
		int cnt = N/2;
		
		for (int size=up.size(); size>0; size--) {
			int cur = up.poll();               // 가장 낮은 석순부터 N/2번씩 개똥벌레를 만나기 시작
			if (cur>before) cnt = size;        // 석순 높이가 높아지면 개똥벌레 만나는 횟수는 이미 만난만큼 줄어듦
			                                   // 석순 높이가 동일하면 개똥벌레 만나는 횟수도 동일
			for (int i=cur; i>=0; i--) {       // 자신의 높이로 커버할 수 있는 자신보다 높이가 낮았던 구간은 자신이 만난 횟수로 채워줌
				if (upObstacles[i]!=0) break;
				upObstacles[i] = cnt;
			}
			before = cur;
		}
	}

	// 석순의 각 높이 별 개똥벌레 만나는 횟수를 저장
	private static void countDownObstacles() {
		int before = Integer.MAX_VALUE;
		int cnt = N/2;
		
		for (int size=down.size(); size>0; size--) {
			int cur = down.poll();
			if (cur>before) cnt = size;
			for (int i=H-cur; i<H; i++) {
				if (downObstacles[i]!=0) break;
				downObstacles[i] = cnt;
			}
			
			before = cur;
		}
		
	}
	
	// 석순과 종유석 모두 합해 개똥벌레를 가장 적게 만난 경우 찾기
	private static void getMin() {
		for (int i=0; i<H; i++) {
			int height = upObstacles[i] + downObstacles[i];
			if (minObstacle > height) {
				minObstacle = height;      // 높이 합이 최소가 되면 갱신하고 갯수도 갱신
				minCnt = 1;
			} else if (minObstacle == height) minCnt++;
		}
	}

}
