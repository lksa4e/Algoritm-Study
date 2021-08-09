import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0810] 백준 10814 나이순 정렬
 * 서로 다른 타입의 데이터를 쌍으로 묶어 정렬하기
 * 
 * sol)
 * 클래스를 정의하여 데이터를 관리
 * 비교 방법
 * - Comparable 인터페이스의 compareTo 메서드 오버라이딩
 * - Comparator 인터페이스의 compare 메서드 오버라이딩
 * 
 * time_complex)
 * 내장 정렬함수(퀵소트) - O(nlogn) ~ O(n^2)
 */

public class BOJ10814 {
	static int N;
	static Member[] members;
	
	// 회원의 나이와 이름을 관리하는 클래스 정의
	static class Member implements Comparable<Member> {
		int age;
		String name;
		
		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}
		
		// Comparable 인터페이스의 compareTo 메서드 오버라이드
		@Override
		public int compareTo(Member m) {
			return age - m.age;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		members = new Member[N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			members[i] = new Member(age, name);   // 입력을 Member 객체로 생성하여 배열로 묶음
		}
		
		// compareTo 메서드 구현으로 Arrays.sort() 가능
		Arrays.sort(members);
		
		// Comparator 인터페이스의 compare 메서드 오버라이드(람다식 표현)
		// Arrays.sort(members, (x, y) -> {return x.age - y.age;});
		
		StringBuilder sb = new StringBuilder();
		for (Member m : members) {
			sb.append(m.age).append(" ").append(m.name).append("\n");
		}
		
		System.out.println(sb);
	}

}
