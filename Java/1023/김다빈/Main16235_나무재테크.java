import java.util.*;
import java.io.*;

/**
 * 백준 16235번 나무 재테크
 * 
 * 풀이 : 구현
 * 
 * PQ를 안쓰고 ArrayList로 계속 시도하다가 10퍼대에서 계속 시간 초과가 나서..ㅜㅜ 
 * 
 * 301120KB	1164ms
 */
public class Main16235_나무재테크 {

	static int N, M, K;
	static int[][] yangbun, addYangbun;
	static PriorityQueue<Tree> trees = new PriorityQueue<Tree>();
	
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	
	static class Tree implements Comparable<Tree> {
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		yangbun = new int[N+2][N+2];
		addYangbun = new int[N+2][N+2];
		for (int i = 0; i < N+2; i++) Arrays.fill(yangbun[i], -1);	// 경계값 -1로 설정 
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				yangbun[i][j] = 5;	// 초기 양분 5로 설정 
				addYangbun[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 나무들 저장 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			trees.offer(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// K년동안 반복 
		for (int i = 0; i < K; i++) {
			fourSeasons();
		}
		
		// 남아있는 나무 수 출력 
		System.out.println(trees.size());
	}

	private static void fourSeasons() {
		int[][] deadYangbun = new int[N+2][N+2];
		List<Tree> tempTree = new ArrayList<Tree>();	// 나중에 PQ에 추가해야하는 나무들 모음 
		
		Tree curTree = null;
		while(!trees.isEmpty()) {
			curTree = trees.poll();
			int r = curTree.r;
			int c = curTree.c;
			int age = curTree.age;
			
			if(yangbun[r][c] < age) {	// 나이만큼 양분 섭취 불가하면 즉사.
				deadYangbun[r][c] += age/2;
			} else {
				// 양분 섭취후 나이 + 1 
				yangbun[r][c] -= age;
				tempTree.add(new Tree(r, c, age+1));
				
				if((age+1) % 5 == 0) {	// 나이가 5의 배수이면 번식 
					for (int j = 0; j < 8; j++) {
						int nr = r + dr[j];
						int nc = c + dc[j];
						
						if(yangbun[nr][nc] != -1) {	// 경계를 벗어나지 않으면 번식 가능! 
							tempTree.add(new Tree(nr, nc, 1));
						}
					}
				}
			}
		}
		
		// 살아있는 나무들 추가해주기 
		for (Tree tree : tempTree) trees.offer(tree);
		
		// 겨울에 양분 추가해주고 죽은 친구들 양분도 더해주기 
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				yangbun[i][j] += deadYangbun[i][j] + addYangbun[i][j];
			}
		}
	}
}
