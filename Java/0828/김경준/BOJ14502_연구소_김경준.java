import java.io.*;
import java.util.*;
/**
 * BOJ 14502 연구소 : https://www.acmicpc.net/problem/14502
 * 메모리 : 75216KB, 시간 : 460ms
 * 벽을 3개 세워야 한다 -> 한번에 벽을 세우면서 최대 안전영역을 구하려고 하면 너무 복잡함
 * 
 * 조합을 통해 3개의 벽을 구하고 해당 위치에 벽을 세운 후 BFS를 통해 바이러스를 퍼뜨리고, 안전 영역을 카운트한다.
 * 3개 벽 고르기 -> next_permutation
 * 바이러스 퍼뜨리기 -> BFS
 * 
 * Time Complexity)
 * N * M 개의 빈칸에서 벽 3개 고르기 -> nmC3
 * 각 경우에 대해서 BFS -> N*M 
 * O(nmC3 * N * M)
 * 
 * worst case -> 공간이 최대인 경우
 * N, M의 범위가 3 <=N, M <= 8 이므로 빈 공간의 최대 개수는 64를 넘지 않는다.
 * 따라서 전체 벽을 세우는 경우의 수를 고려하여도 64C3 보다 작은 수가 된다.
 * 벽을 세운 각 경우에 BFS를 통해 탐색하여도 최대 이동 가능한 경우는 64밖에 없다.(실제는 벽때문에 얼마 못감)
 * ==> 대충계산 64^4 < 16,000,000 (1600만)
 */

public class BOJ14502_연구소_김경준 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, answer = 0;
	static int map[][], idn[], temp[][], wall[][] = new int[3][2];
	static List<Pair> virus = new ArrayList<Pair>();
	static List<Pair> blank = new ArrayList<Pair>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];   // origin map
		temp = new int[N][M];   // BFS용 map
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new Pair(i,j));     // BFS 탐색시켜줄 바이러스 list
				else if(map[i][j] == 0) blank.add(new Pair(i,j)); // combination 수행 위한 빈 칸 list
			}
		}
		combi();
		System.out.println(answer);
	}
	
	static void combi(){
		// next_permutation 위한 idn배열, nC3이므로 뒤에서 3개만큼 1로 채우기
		int[] idn = new int[blank.size()];
		for(int i = blank.size()-1; i > blank.size() -4; i--) {  
			idn[i] = 1;
		}
		// 조합 만들기
		do {
			int cnt = 0;
			for(int i = 0; i < blank.size(); i++) {  // 전체 빈칸중에서 조합으로 걸린 애들(1) 설정
				if(idn[i] == 1) {
					wall[cnt][0] = blank.get(i).x;
					wall[cnt++][1] = blank.get(i).y;  // wall[cnt++]을 통해 wall[0],wall[1],wall[2] 채움
				}
			}
			bfs(); // 조합 완성되면 BFS 수행
			
		}while(next_permutation(idn));
	}
	
	static void bfs() {
		copy();  // BFS 탐색용으로 맵 초기화
		for(int i = 0; i < 3; i++) {
			temp[wall[i][0]][wall[i][1]] = 1;   // 벽 세우기
		}
		Queue<Pair> q = new ArrayDeque<Pair>();
		for(int i = 0; i < virus.size(); i++)   // 한 BFS에서 모든 바이러스 다 집어넣어도 동일 결과
			q.offer(virus.get(i));
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + "2101".charAt(i) -'1';  // dx, dy 배열없이 4방도는 방법
				int ny = cur.y + "1210".charAt(i) -'1';
				if(!oob(nx,ny) && temp[nx][ny] == 0) {
					temp[nx][ny] = 2;                   // BFS 돌면서 그냥 바이러스로 마킹해버리기
					q.offer(new Pair(nx,ny));
				}
			}
		}
		getanswer();
	}
	
	static void copy() {               // BFS 탐색할 맵 초기화
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++) 
				temp[i][j] = map[i][j];
	}
	
	static void getanswer() {   // 탐색하면서 빈칸 개수 세주기
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j <M; j++)
				if(temp[i][j] == 0) cnt++;
		
		answer = Math.max(answer, cnt);
	}
	
	static boolean oob(int x, int y) {
		return x < 0 || x>= N || y < 0 || y >= M;
	}
	
	static boolean next_permutation(int[] arr) {
		int N = arr.length;
		
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i])--i;
		if (i == 0) return false;

		int j = N - 1;
		while (arr[i - 1] >= arr[j]) --j;
		
		swap(arr, i - 1, j);
		
		int k = N - 1;
		while (i < k) swap(arr, i++, k--);
		return true;
	}

	static void swap(int[] arr, int fst, int snd) {
		int temp = arr[fst];
		arr[fst] = arr[snd];
		arr[snd] = temp;
	}
}
class Pair {
	int x, y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
