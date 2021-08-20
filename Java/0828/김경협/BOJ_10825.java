import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 10825번 국영수
 * 
 * 주어진 이름과, 국영수 점수에 따라서 정렬하는 문제
 * 1. 국어 점수 내림차순
 * 2. 영어 점수 오름
 * 3. 수학 점수 내림
 * 4. 이름 사전순 오름
 * 과 같은 순서로 정렬한다.
 * 
 * 정보를 담는 class를 구현, comparable을 구현하여 Arrays.sort를 사용했다.
 * compareTo에서 뺄셈으로 return값을 줬는데 값의 범위가 양수이므로 언더나 오버플로 X
 */

class ClassScore implements Comparable<ClassScore> {
	int korean;
	int english;
	int math;
	String name;

	@Override
	public int compareTo(ClassScore o) {
		if (o.korean - this.korean == 0) {
			if (this.english - o.english == 0) {
				if (o.math - this.math == 0) {
					return this.name.compareTo(o.name); // 이름 오름차순
				} else // 수학 점수 내림차순
					return o.math - this.math;
			} else // 영어 점수 오름차순
				return this.english - o.english;
		} else // 국어 점수 내림차순
			return o.korean - this.korean;
	}
}

public class BOJ_10825 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		ClassScore[] cs = new ClassScore[N];
		
		// 학생들 정보를 배열에 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			ClassScore tmp = new ClassScore();
			tmp.name = st.nextToken();
			tmp.korean = Integer.parseInt(st.nextToken());
			tmp.english = Integer.parseInt(st.nextToken());
			tmp.math = Integer.parseInt(st.nextToken());
			cs[i] = tmp;
		}
		
		Arrays.sort(cs);
		
		for(ClassScore student : cs)
			sb.append(student.name).append("\n");
		
		System.out.println(sb);
	}

}
