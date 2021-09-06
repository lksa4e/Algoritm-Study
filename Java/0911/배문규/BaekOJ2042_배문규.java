package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 * 지난 일요일날 CS스터디에서 윤주님께서 세그먼트 트리를 정리해주셔서 나름 수월(?)하게 풀었다.
 * 문제 풀이 과정은 아래와 같다.
 * 
 * initTree();
 * 1. 미리 입력받은 N 값으로 세그먼트 트리 사이즈를 구해서 초기화함
 * 2. 리프노드에 값들을 순차적으로 입력함.
 * 3. 부모노드 인덱스 = 리프노드 인덱스/2 인것을 이용해서 자식노드들의 합들을 부모 노드에 저장 ==> 루트까지
 * 
 * 4. 그 다음 option, a, b를 입력받는다. 
 * 
 * change(int a, long b);
 * 5. option이 1이면, a번 째 리프노드를 b값으로 바꾼다.
 * 여기서 b가 long값으로 들어오기 때문에 long형으로 선언해서 파싱도 long으로 해야 함 => 안하면 런타임 에러 (NumberFormat)
 * 
 * prefix_Sum(int treeStart, int treeEnd, int queryStart, long queryEnd, int root);
 * 6. option이 2이면, a부터 b까지의 구간합을 구한다.
 * 이 때, b는 int까지만 들어와서 헷갈렸음...
 * 구간합을 구하기 위해서는 분할정복을 해야 함. 
 * 전체 구간, 찾으려는 구간, 해당 구간 트리 루트 인덱스를 입력함
 * 1) 전체 구간에 찾으려는 구간이 아예 포함이 안되면, return 0
 * 2) 찾으려는 구간이 전체 구간에 완전히 포함되면, return 구간 트리 루트값 
 * 3) 위 두 조건을 만족하지 많으면 전체구간을 절반 분할하여 분할 정복.
 * 이 때, 분할 된 구간 트리의 루트도 맞춰서 입력해야 함.
 * 
 * - 시행착오 : prefix_Sum 메소드를 void형으로 선언하고, static 변수로 값을 더해주니 테케는 잘나오던데 틀렸음
 * 			디버깅이 잘 안돼서 long타입으로 값을 리턴해주는 방식으로 피보팅하니까 통과하였음.
 * 
 * 메모리 	시간
 * 86600	548
 */

public class BaekOJ2042_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, K, x, max_size;
	static long sTree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		initTree();
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int option = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			if(option == 1) change(a, b);
			else sb.append(prefix_Sum(1, max_size/2, a, b, 1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void initTree() throws NumberFormatException, IOException {
		// 윤주님 CS 스터디 세그먼트 트리 정리에서 참고
		// 트리 전체 사이즈 구하기
		x = (int) (Math.ceil(Math.log(N) / Math.log(2)));
		max_size = 2 * (int) Math.pow(2, x);
		sTree = new long[max_size];		
		
		for(int i = max_size/2; i < max_size/2+N; i++) // 리프 노드 입력
			sTree[i] = Long.parseLong(br.readLine());
		for(int i = max_size/2-1; i > 0; i--) // 루트 까지 부모노드에 자식노드값의 합 저장
			sTree[i] = sTree[i*2] + sTree[i*2+1];
	}
	
	// option 1, b는 long형 주의
	public static void change(int a, long b) {
		long gap = sTree[a + max_size/2 - 1] - b; // 이전 값과, 변경값의 차이
		
		sTree[a + max_size/2 - 1] = b; // 리프노드 값 변경
		for(int i = (a + max_size/2 - 1)/2; i > 0; i /= 2) sTree[i] -= gap; // 이전에 저장된 값과 변경값의 차이만큼 빼 줌
	}
	
	// option 2, 구간합 구하기.
	static long prefix_Sum(int treeStart, int treeEnd, int queryStart, long queryEnd, int root) {
		if(treeStart > queryEnd || treeEnd < queryStart) return 0; // 찾으려는 구간 완전 밖
		if(queryStart <= treeStart && queryEnd >= treeEnd) return sTree[root]; // 찾으려는 구간 완전 포함

		int mid = (treeStart+treeEnd)/2; // 트리 중앙 분할
		return prefix_Sum(treeStart, mid, queryStart, queryEnd, 2*root) // 좌 우 분할정복
				+ prefix_Sum(mid+1, treeEnd, queryStart, queryEnd, 2*root+1);
	}
}
