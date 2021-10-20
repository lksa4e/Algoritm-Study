package BaekOJ.study.date1023;

import java.io.*;
import java.util.*;

/*
 * 백준 16235 나무 재테크
 * 
 * 조건 대로 줄줄 구현만 해주면 되는 문제 
 * 인덱스를 항상 i,j로 관리하다보니  x,y가 주어지면 실수하게됨...
 * 
 * 1 Year Cycle
 * 봄 : 나이만큼 양분먹고(나이가 어릴수록 우선순위), 나이 1 증가. 만약 필요한 양분이 부족하면 양분을 먹지 않고 즉시 고사목행
 * 여름 : 죽은 나무가 양분이 됨. 양분 = 나이//2
 * 가을 : 나이가 5의 배수인 나무의 인접 땅에서 나이가 1인 나무가 자람
 * 겨울 : 양분 추가
 * 
 * 시행착오 : A[x][y]가 i,j로 하면 A[j][i]은데 A[i][j]로 했다가 틀림
 * 해당 테케 :
 * 5 2 7
 * 2 3 2 3 2
 * 2 3 2 3 2
 * 2 3 2 3 2
 * 2 3 2 3 2
 * 2 3 2 3 2
 * 2 1 3
 * 3 2 3
 * 답:71
 * 오답:69
 * 
 * 메모리 	시간
 * 212000  856
 * 
 */

class Tree implements Comparable<Tree>{
	int i, j, age;
	boolean isAlive = true;

	public Tree(int i, int j, int age) {
		super();
		this.i = i;
		this.j = j;
		this.age = age;
	}
	
	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}

public class BaekOJ16235_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, K, map[][], A[][];
	static int near[][] = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
	static List<Tree> tree = new ArrayList<Tree>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
        	A = new int[N][N];
		for (int i = 0; i < N; i++) {
            		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++){
                		map[i][j] = 5;
				// 입력 x,y가 i,j와는 서로 반대임
                		A[j][i] = Integer.parseInt(st.nextToken());
            		}
		}
		
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken())-1;
			int i = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			
			tree.add(new Tree(i, j, age));
		}
		
		for(int year = 1; year <= K; year++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		System.out.println(tree.size());
	}

	private static void spring() {
		Collections.sort(tree);
		
		int size = tree.size();
		for(int i = 0; i < size; i++) {
			// 양분이 더 많으면 양분을 먹고 나이가 1 증가
			if(map[tree.get(i).i][tree.get(i).j] >= tree.get(i).age) {
				map[tree.get(i).i][tree.get(i).j] -= tree.get(i).age;
				tree.get(i).age += 1;
			}
			// 양분이 더 적으면 죽음
			else{
				tree.get(i).isAlive = false;
			}
		}
	}

	private static void summer() {
		int size = tree.size();
		// 죽은 나무는 나이//2 만큼 양분이 됨
		for(int i = 0; i < size; i++) {
			if(!tree.get(i).isAlive) map[tree.get(i).i][tree.get(i).j] += tree.get(i).age/2;
		}
		// 리스트에서 삭제
		tree.removeIf(t -> !t.isAlive);
	}

	private static void autumn() {
		int size = tree.size();
		for(int i = 0; i < size; i++) {
			// 나무의 나이가 5의 배수이면 인접한 땅에 나이가 1인 나무가 생긴다.
			if(tree.get(i).age % 5 == 0) {
				int tI = tree.get(i).i;
				int tJ = tree.get(i).j;
				for(int[] idx : near) {
					int nI = tI + idx[0];
					int nJ = tJ + idx[1];
					// 땅을 벗어나는 칸에는 나무가 생기지 않는다.
					if(isOOB(nI, nJ)) continue;
					tree.add(new Tree(nI, nJ, 1));
				}
			}
		}
	}

	private static void winter() {
		// S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) map[i][j] += A[i][j];
		}
	}
	
	public static boolean isOOB(int i, int j) {
		return i > N - 1 || i < 0 || j > N - 1 || j < 0;
	}
}
