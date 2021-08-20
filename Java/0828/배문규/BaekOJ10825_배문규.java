package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 새벽에 몽롱한 상태로 문제를 풀어서 그런가 뻔히 4번 조건 "모든 점수가 같으면 이름이 사전 순으로 증가하는 순서" 가 있는데도 못보고 지나쳤다가 한번 틀리고 정신차림
 * Comparable 인터페이스를 상속받고 compareTo를 요구사항에 맞게 오버라이딩하면 되는 간단한 문제이기 때문에 요구사항만 제대로 읽으면 됨.
 * 
 * 메모리 	시간
 * 60164	760
 */
class Student implements Comparable<Student>{
	String name;
	int kor;
	int eng;
	int mth;
	Student(String name, int kor, int eng, int mth){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mth = mth;
	}
	
	@Override
	public int compareTo(Student o) {
		if(this.kor == o.kor) {
			if(this.eng == o.eng && this.mth == o.mth) return this.name.compareTo(o.name); // 모든 점수가 같다면 이름 비교
			else if(this.eng == o.eng) return o.mth - this.mth; // 국,영 점수가 같으면 수학점수 내림차순
			else return this.eng - o.eng; // 국어 점수만 같으만 영어점수 오름차순
		}
		else return o.kor - this.kor; // 국어 점수 내림차순
	}
}

public class BaekOJ10825_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(br.readLine());
		Student[] students = new Student[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(students);
		
		for(Student student : students) sb.append(student.name).append("\n");
		System.out.println(sb);
	}
}
