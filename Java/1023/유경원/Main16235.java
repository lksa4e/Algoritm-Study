import java.io.*;
import java.util.*;

public class Main16235 {
	
	/*
	 * 문제 조건 그대로 구현
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, K, A[][], map[][];
	static List<Tree> liveTree = new ArrayList<>();
	static List<Tree> newLiveTree;
	static Queue<Tree> deadTree = new ArrayDeque<>();
	static Queue<int[]> spreadTree = new ArrayDeque<>();
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	
	static class Tree implements Comparable<Tree>{
		int x, y, age;
		
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		A = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 초기 양분 5
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			liveTree.add(new Tree(Integer.parseInt(st.nextToken())-1, 
							 Integer.parseInt(st.nextToken())-1,
							 Integer.parseInt(st.nextToken()) ));
		}
		
		for(int i=0; i<K; i++) {
			spring(); // 나무 나이만큼 양분 먹고 나이 1증가, 나이만큼 못먹는 나무는 즉시 죽음
			summer(); // 봄에 죽은 나무들 양분으로 추가( 죽은 나무 나이/2)
			fall(); // 나무 나이가 5의 배수면 인접 8칸 나이 1인 나무 추가
			winter(); // A배열 값만큼 양분 추가
			
			liveTree = new ArrayList<>(); // 다음 년도를 위한 셋팅
			for(Tree t : newLiveTree) {
				liveTree.add(t);
			}
		}
		System.out.println(liveTree.size());
		
	}
	
	private static void spring() {
		newLiveTree = new ArrayList<>();
		Collections.sort(liveTree); // 나무 나이 오름차순 정렬
		int x=0, y=0, age=0;
		for(Tree t : liveTree) {
			x = t.x;
			y = t.y;
			age = t.age;
			
			if(map[x][y] < age) { // 나무 나이보다 양분 적으면 죽은 나무리스트에 추가
				deadTree.offer(new Tree(x,y,age));
			}else {
				map[x][y] -= age; // 나이만큼 양분 섭취 후 나이 1증가
				newLiveTree.add(new Tree(x,y,age+1));
				if((age+1)%5 == 0) { // 나무 나이가 5의 배수면 번식할 나무 리스트에 추가
					spreadTree.offer(new int[] {x,y});
				}
			}
		}
	}
	
	private static void summer() {
		Tree t;
		while(!deadTree.isEmpty()) { // 죽은 나무 나이/2 만큼 양분 추가
			t = deadTree.poll();
			map[t.x][t.y] += t.age/2;
		}
	}
	
	private static void fall() {
		int t[] = null, x=0, y=0, nx=0, ny=0;
		while(!spreadTree.isEmpty()) { // 나무 번식
			t = spreadTree.poll();
			x = t[0];
			y = t[1];
			
			for(int i=0; i<8; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if(nx<=-1 || ny<=-1 || nx>=N || ny>=N) continue;
				
				newLiveTree.add(new Tree(nx,ny,1));
			}
		}
	}
	
	private static void winter() { // 양분 뿌리기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
	
	
}
