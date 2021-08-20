import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0821] 백준 10825 국영수
 * 정렬(Comparable, Comparator)
 * 
 * sol)
 * 1순위 - 국어점수 내림차순
 * 2순위 - 영어점수 오름차순
 * 3순위 - 수학점수 내림차순
 * 4순위 - 이름 오름차순
 * 
 * 뭔가 클래스 안만들면 안될 것 같은 아우라를 풍기길래 울며겨자먹기로 만들어줬습니다.
 * 대놓고 comparable 연습문제같았던..
 * 
 * Comparator 람다식으론 못하겠지 했는데 삼항 연산자 안에 삼항 연산자가 올 수 있는지가 너무 궁금해서 도전해봤습니다. ㅎㅎ
 * 아주 근소하지만 람다식이 성능이 더 좋았는데 너무 근소해서 성능차이로 볼 수 없을 정도입니다.
 * 
 * time_complex)
 * 내장 머지 소트에 의하면 정렬이 거의 안되어있는 배열은 N번정도의 연산이 필요하다고 했는데 비교 조건이 많아서 그만큼 연산도 늘어날 것 같습니다.
 *	
 */

public class BOJ_10825_국영수 {
	static int N;
	static StudentGrade[] students;    // 학생 정보와 과목별 성적을 저장한 타입의 배열
	
	// 학생별 이름과 과목별 점수 데이터를 모은 타입 생성
	static class StudentGrade implements Comparable<StudentGrade> {
		String name;     // 이름
		int kor;         // 국어 점수
		int eng;         // 영어 점수
		int math;        // 수학 점수
		
		public StudentGrade(String name, int kor, int eng, int math) {
			super();
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
		
		// Comparable 인터페이스의 compareTo 메서드 재정의
		@Override
		public int compareTo(StudentGrade s) {
			int korStandard = s.kor - this.kor;                // 국어 점수 내림차순
			int engStandard = this.eng - s.eng;                // 영어 점수 오름차순
			int mathStandard = s.math - this.math;             // 수학 점수 내림차순
			int nameStandard = this.name.compareTo(s.name);    // 이름 오름차순
			
			if (korStandard != 0) return korStandard;          // 국어 점수 같지 않으면
			else if (engStandard != 0) return engStandard;     // 국어 점수 같고 영어 점수 같지 않으면
			else if (mathStandard != 0) return mathStandard;   // 국어 점수 같고 영어 점수 같고 수학 점수 같지 않으면
			else return nameStandard;                          // 국어 점수 같고 영어 점수 같고 수학 점수 같으면
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		students = new StudentGrade[N];         // 입력받은 학생들의 이름과 점수들을 저장한 배열
		
		// 이름 및 점수를 저장한 객체 생성 후 배열에 저장
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			students[i] = new StudentGrade(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// Comparable 인터페이스에게서 상속받은 compareTo 메서드를 재정의하고 이를 호출하여 정렬(객체 자기 자신과 매개변수로 오는 객체를 비교)
		Arrays.sort(students);
		
		// Comparator 인터페이스에게서 상속받은 compare 메서드를 재정의하고 이를 호출하여 정렬하되, 람다식 형태로 구현(매개변수로 오는 객체 두개를 Comparator가 비교해줌)
		Arrays.sort(students, (s1, s2) -> s1.kor != s2.kor ? s2.kor - s1.kor : (s1.eng != s2.eng ? s1.eng - s2.eng : (s1.math != s2.math ? s2.math - s1.math : s1.name.compareTo(s2.name))));
		
		// 출력
		for (StudentGrade student : students) System.out.println(student.name);
	}

}
