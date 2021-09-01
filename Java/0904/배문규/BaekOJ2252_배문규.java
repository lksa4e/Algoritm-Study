package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 여러 방식을 트라이해보다가 테케만 다맞는 왜맞틀을 반복함
 * 
 * 그래서 알고리즘 분류를 보니 위상 정렬이라길래 이 위상정렬에 대한 공부가 필요했음.
 * 위상 정렬의 개념만 살펴봤는데 나는 아래와 같은 개념으로 이해함
 * 
 * 우선 순위 관계가 있는 작업들이 존재 한다고 할 때, 만약 작업 A는 2가지 데이터 필드가 필요하다.
 * 1. 작업 A 보다 우선적으로 수행해야할 다른 작업 리스트, 그리고 2. 작업 A가 끝나야 수행할 수 있는 다른 작업 리스트
 * 여러 작업들 중 선행될 작업들이 없는 작업들은 바로 수행해도 된다.
 * 그대신 해당 작업이 끝나면 그 작업이 끝나야 수행할 수 있는 작업들에게 그 작업이 끝났다는 알림을 줘야한다.
 * 그 대신 사이클이 없어야 한다. 사이클이 있어 서로 작업의 우선수위가 꼬리를 문다면 운영체제에서 말하는 데드락에 상태가 된다.
 * 
 * 학생 A, B 가 주어질 때 A는 작업으로 치면 우선순위 작업이고, B는 A에 종속된 후순위 작업으로 이해하고 문제를 풀었음
 * 큐를 이용해서 우선순위가 없으면 sb에 데이터를 추가하고 큐에 인큐하였다.
 * 작업 A가 끝나면 A에 종속된 작업B의 종속성을 해결해주었고, 아무런 종속성이 없는 작업은 마찬가지로 sb에 데이터를 추가하고 인큐하는 것을 반복하여 해결하였다.  
 * 
 * 메모리		시간
 * 50708	568
 */

class Student{
	int num;
	List<Integer> out = new ArrayList<Integer>(); // 해당 vertex가 진출하는 vertex
	Queue<Integer> in = new ArrayDeque<Integer>(); // 해당 vertex로 진입하는 vertex
	
	Student(int num){
		this.num = num;
	}
}

public class BaekOJ2252_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M;
	static Student[] students;
	static Queue<Student> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		students = new Student[N+1];
		for(int i = 1; i <= N; i++) students[i] = new Student(i);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			students[out].out.add(in); // 진출 => 진입 edge
			students[in].in.add(out); // 진입 <= 진출 edge
		}
		

		queue = new ArrayDeque<Student>();
		for(int i = 1; i <= N; i++) enqueue(i);
		
		// 더이상 진입하는 vertex가 없는 vertex들을 dequeue
		while(!queue.isEmpty()) {
			Student std = queue.poll();
			sb.append(std.num).append(" ");

			// 해당 vertex가 진출하는 vertex
			for(int i : std.out) {
				students[i].in.poll(); // 진입되는 vertex에서 연결 제거
				enqueue(i);
			}
		}
		
		System.out.println(sb);
	}
	
	// 들어오는 vertex가 없으면 sb에 vertex를 추가하고 큐에 enqueue하는 메소드
	public static void enqueue(int i) {
		if(students[i].in.isEmpty()) queue.offer(students[i]);
	}
}
