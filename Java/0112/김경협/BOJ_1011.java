import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 1011번 Fly me to the Alpha Centauri
 * 
 * 수학 문제. count가 바뀌는 순간을 쭉 나열하고 규칙을 찾아내어 정리했다.
 * 
 * 아래는 공간이동 장치 작동 횟수 N이 증가하기 바로 직전단계들만 나열한 것이다.
 * 
 * 			거리		차이		자릿수 N
 *			0		 		0
 * 1		1		1		1
 * 11	 	2		1		2
 * 121		4		2		3
 * 1221		6		2		4
 * 12321	9		3		5
 * 123321	12		3		6
 * ...
 * 
 * 위에서 공간이동 장치 작동 횟수 N이 증가하기 위해서
 * 1,1,2,2,3,3 ... 순으로 거리 dis가 증가한다.
 * 이 증가하는 수들 increment는 1,4,9,16 ... 등 dis가 제곱수일 때 1씩 늘어난다.
 * 따라서 increment는 dis의 제곱근으로 구할 수 있다.
 * increment = (int) sqrt(dis)
 * 
 * 그리고 자릿수 N은 increment * 2인데, 범위에 따라서 약간씩 달라진다.
 * 
 * 예를 들어
 * 4,5,6,7,8은 모두 제곱근이 2이지만
 * 4는 3자리
 * 5,6은 4자리
 * 7,8은 5자리이다.
 * 
 * 따라서 이를 위의 변수 increment와 거리 dis로 표현하면
 * increment^2 == dis일 때 							N = increment * 2 - 1
 * increment^2 < dis <= increment^2 + increment일 때 	N = increment * 2
 * increment^2 < dis <= (increment+1)^2일 때			N = increment * 2 + 1이 된다.
 * 
 * 이를 코드로 옮겨서 dis가 주어질 때 N을 구하면 된다.
 */
public class BOJ_1011 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int dis = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
			dis *= -1;
			
			int increment = (int) Math.sqrt(dis), increment2 = increment * increment, N = 0;
			
			if(increment2 == dis) {
				N = increment * 2 - 1;
			} else if(increment2 < dis && dis <= increment2 + increment) {
				N = increment * 2;
			} else {
				N = increment * 2 + 1;
			}
			
			sb.append(N).append("\n");
		}
		System.out.println(sb);
	}

}
