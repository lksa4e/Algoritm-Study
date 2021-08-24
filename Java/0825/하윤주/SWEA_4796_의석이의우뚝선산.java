import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * [0825] SWEA 4796 의석이의 우뚝 선 산
 * 구현, 의석아 제발 그만,,, | 25min
 * 
 * sol)
 * 우뚝 선 산의 넓은 구간을 구하고 그 안에서 몇 개의 좁은 구간을 포함하는지 계산
 * 꼭대기 산을 만날때까지 오름차순으로 계속 증가하고 몇개 증가했는지 카운트, 마찬가지로 바닥을 만날때까지 내림차순으로 계속 감소하고 몇개 감소했는지 카운트
 * 꼭대기는 최초로 감소하기 직전이고 바닥은 최초로 증가하기 직전이므로, 
 * 높이가 감소하다 최초로 증가하기 직전까지의 (증가 개수)와 (감소 개수)를 곱하면 하나의 넓은 우뚝 선 산의 구간 동안의 좁은 구간 총 개수를 구할 수 있음
 * 최초로 올라가는지는 플래그 변수로 확인
 * 
 * 시행착오)
 * 경준님이 분명 스캐너 써야한다고 하셨는데 까먹고 또 버퍼드리더로 읽다가 오답파티...
 * 
 * BufferedReader 클래스 API 따라가보면 아래와 같이 대략 8천 사이즈 정도로 초기화가 되어있네요
 * 		private static int defaultCharBufferSize = 8192;
 * 
 * 그리고 또 재밌는거는 아래처럼 직접 사이즈를 설정해서 사용할 수 있게 생성자를 오버로딩 해놓았습니다.
 * 		int customSize = 10000;
 * 		new BufferedReader(new InputStreamReader(System.in), customSize);
 * 
 * 근데 버퍼드리더로 사이즈 바꿔서 제출해도 계속 런타임 에러뜨는거 보면 뭔가 버퍼드리더 사이즈 범위 문제는 아닌 것 같아요
 * API 문서에서도 디폴트 사이즈가 거의 어떤 목적에서든 충분할 것이라고 하네요..!
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_4796_의석이의우뚝선산 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			
			// 입력으로 들어온 산의 높이들을 배열로 관리
			int[] hills = new int[N];
			for (int i=0; i<N; i++) hills[i] = sc.nextInt();
			
			int up = 0;               // 증가하고 있는 산의 개수
			int down = 0;             // 감소하고 있는 산의 개수
			boolean flag = false;     // 우뚝 선 산이 완성되었는지 여부를 판단하는 플래그
			int answer = 0;           // 최종 우뚝 선 산의 개수
			
			// 우뚝 선 산 세기
			for (int i=1; i<N; i++) {
				// 산의 높이가 오름차순일 경우
				if (hills[i-1] < hills[i]) {
					// 높이가 내림차순이 될 때 flag가 true가 됨. 그동안 오름차순이었기때문에 false 였다가 내림차순을 만나 true로 변경된 이후 최초로 다시 오름차순이 되는 순간이 우뚝 선 산이 발견된 것 
					if (flag) {                 
						answer += up*down;     // 그동안의 증가하던 높이 수와 감소하던 높이 수를 곱하면 우뚝 선 산 구간 개수를 구할 수 있음
						up = down = 0;         // 다음 구간을 위해 증가와 감소는 0으로 초기화
						flag = false;          // 플래그도 초기화
					}
					up++;                      // 현재는 높이가 오름차순인 경우이므로 무조건 up 카운트 증가
				// 산의 높이가 내림차순일 경우
				} else {
					flag = true;               // 최초로 내림차순이 되던 순간부터 최초로 오름차순이 되기 전까지 계속 플래그를 true로 유지
					down++;                    // 현재는 높이가 내림차순인 경우이므로 무조건 down 카운트 증가
				}
			}
			
			// 높이 순회 끝나고 나서 미처 포함하지 못한 우뚝 선 산의 경우가 존재할 경우 최종 정답에 더해줌
			if (up > 0 && down > 0) answer += up*down;
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
		}
	}

}
