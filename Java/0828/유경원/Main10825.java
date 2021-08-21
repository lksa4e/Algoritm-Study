import java.io.*;
import java.util.*;

public class Main10825 {
	/*
	 * 조건에 맞게 정렬한다
	 */
	static int N;
	static Student[] students;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		students = new Student[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(students);
		
		for(Student s: students) sb.append(s.name).append("\n");
		
		System.out.println(sb);
	}

}

class Student implements Comparable<Student> {
	String name;
	int kor, eng, math;
	
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	@Override
	public int compareTo(Student o) {
		
		if(o.kor != this.kor) return o.kor - this.kor;
		if(o.eng != this.eng) return this.eng - o.eng;
		if(o.math != this.math) return o.math - this.math;
		
		return this.name.compareTo(o.name);
	}
}
