package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 2252번 줄세우기와 동일한 접근방식으로 풀었음.
 * 대신 한가지 조건이 더 있었는데, 문제를 풀어도 되면 쉬운문제를 우선적으로 풀어야하는 조건이 있었다.
 * 그래서 Comparable 인터페이스를 상속받아 쉬운문제에 대한 기준을 세팅해주었다.
 * 
 * 메모리 	시간
 * 51968	652
 */
class Exam implements Comparable<Exam>{
	int num;
	List<Integer> out = new ArrayList<Integer>(); // 해당 vertex가 진출하는 vertex
	Queue<Integer> in = new ArrayDeque<Integer>(); // 해당 vertex에 진입하는 vertex
	
	Exam(int num){
		this.num = num;
	}
	
	// PQ를 위한 쉬운문제 기준 세팅
	@Override
	public int compareTo(Exam o) {
		return this.num-o.num;
	}
}

public class BaekOJ1766_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M;
	static Exam[] exams;
	static PriorityQueue<Exam> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		exams = new Exam[N+1];
		for(int i = 1; i <= N; i++) exams[i] = new Exam(i);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			exams[out].out.add(in); // 진출 => 진입 edge
			exams[in].in.offer(out); // 진입 <= 진출 edge
		}
		
		queue = new PriorityQueue<Exam>(); // 선수 문제가 없는 문제들 중 가장 쉬운 문제들이 나오기 위한 pq
		for(int i = 1; i <= N; i++) enqueue(i); // 진입 edge가 없는 vertex들 pq에 enqueue
		
		while(!queue.isEmpty()) {
			Exam std = queue.poll(); // 선수 문제가 없는 문제들 중 가장 쉬운문제 
			sb.append(std.num).append(" ");
			
			for(int i : std.out) { // 해당 문제를 선수문제로 하는 문제들
				exams[i].in.poll(); // 선수 문제에서 해당 문제 제거
				enqueue(i); // 더이상 선수 문제가 없다면 pq에 enqueue
			}
		}
		
		System.out.println(sb);
	}
	
	// 진입 edge가 없는지 체크하고 enqueue
	public static void enqueue(int i) {
		if(exams[i].in.isEmpty()) queue.offer(exams[i]);
	}
	
}
