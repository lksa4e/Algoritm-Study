import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * BOJ 17140번 이차원 배열과 연산
 * 
 * 시뮬레이션, pq, 배열 돌리기
 * 
 * R연산만 구현하고, C연산은 배열을 뒤집고 R연산하고 다시 배열을 뒤집는 식으로 구현했다.
 * R연산은 숫자와 카운트를 저장하는 클래스를 만들고, 그 클래스를 담는 Priority Queue를 사용해서 우선순위 대로 정렬하게 했다.
 * PQ에서 하나씩 빼면서 새로운 배열에 넣어주고 기존 map에다가 덮어 씌웠다.
 * 
 * 18932KB	176ms
 */

public class BOJ_17140 {
	static class CountNum implements Comparable<CountNum>{
		int num;	// 현재 숫자와
		int cnt;	// 그 숫자가 몇 개 있는지 저장하는 cnt
		
		public CountNum(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(CountNum o) {
			return this.cnt != o.cnt ? this.cnt - o.cnt: this.num - o.num;
		}
	}
	
	static int rowSize = 3, colSize = 3, targetRow, targetCol, targetVal, map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		targetRow = Integer.parseInt(st.nextToken()) - 1;
		targetCol = Integer.parseInt(st.nextToken()) - 1;
		targetVal = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		for (; time <= 100; time++) {	// 시간은 100 이하로,
			if(targetRow < rowSize && targetCol < colSize) {	// 범위 체크
				if(map[targetRow][targetCol] == targetVal) break;
			}
			
			if(rowSize >= colSize) {	// R연산
				arrSort();
			} else {					// C연산
				reverse();				// 배열 뒤집고
				arrSort();
				reverse();				// 다시 원래대로 뒤집기
			}
		}
		if(time == 101) time = -1;
		System.out.println(time);
	}
	
	
	static void reverse() {	// 배열 뒤집기
		int tmp = rowSize;
		rowSize = colSize;
		colSize = tmp;
		int[][] cpMap = new int[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				cpMap[i][j] = map[j][i];
			}
		}
		map = cpMap;
	}
	
	static void arrSort() {
		@SuppressWarnings("unchecked")		
		PriorityQueue<CountNum>[] pq = new PriorityQueue[rowSize];	// CountNum으로 row에 존재하는 숫자들과, 카운트를 저장함
		boolean visited[] = new boolean[colSize];
		
		int maxCnt = 0;	// 다음 ColSize가 몇 개인지 알기 위해 따로 변수 설정
		for (int i = 0; i < rowSize; i++) {
			pq[i] = new PriorityQueue<CountNum>();
			Arrays.fill(visited, false);	// visited 초기화
			
			for (int j = 0; j < colSize; j++) {
				if(map[i][j] == 0) continue;	// 0 무시
				if(visited[j]) continue;
				
				visited[j] = true;
				int curr = map[i][j];
				int cnt = 1;
				for (int k = j; k < colSize; k++) {	// 같은 수 집어넣기
					if(visited[k]) continue;
					if(map[i][k] == curr) {			// 같은 수면 cnt 늘리고, visited 갱신
						cnt++;
						visited[k] = true;
					}
				}
				pq[i].offer(new CountNum(curr, cnt));	// 같은 수 다 찾고 나서 pq에 넣어주기
			}
			maxCnt = Math.max(maxCnt, pq[i].size() * 2);	// 배열은 num과 cnt가 하나씩 들어가니 size * 2
		}
		
		colSize = maxCnt;
		if(colSize >= 100) colSize = 100;		// 100 개 이상이면 100개 까지만
		int[][] cpMap = new int[rowSize][colSize];	// 새로운 카피맵 설정
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize && !pq[i].isEmpty(); j+=2) {		// pq에서 하나씩 빼면서 pq가 빌 때 까지 혹은 pq에서 빼내면서 100개 이상 가져올 수 있으니, colSize까지 빼오면서 저장
				CountNum tmp = pq[i].poll();
				cpMap[i][j] = tmp.num;
				cpMap[i][j+1] = tmp.cnt;
			}
		}
		map = cpMap;	// 맵에 덮어씌우기
	}
	
}
