import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * BOJ 19583번 싸이버개강총회
 * 
 * 중복체크 문제,
 * id 체크 + 중복 체크도 해야해서 Map 사용
 */

public class BOJ_19583 {

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		Map<String, Boolean> map = new HashMap<>();	// 개강 총회 끝난 후 아이디 중복 카운팅을 막기 위해 map 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int startTime = timeToInt(st.nextToken());
		int endTime = timeToInt(st.nextToken());
		int streamEndTime = timeToInt(st.nextToken());

		String str;
		while((str = br.readLine()) != null) {	// EOF까지 반복
			st = new StringTokenizer(str);	
			int time = timeToInt(st.nextToken());
			String name = st.nextToken();
			if (time <= startTime) { // 개강총회 시작 전(시작하자마자도 포함)
				map.put(name, true);
			} else if (time >= endTime && time <= streamEndTime) { // 개강 총회 끝나자마자 + 스트리밍 끝나자마자
				if (map.containsKey(name)) {
					if (map.get(name)) {	// 중복 체크
						cnt++;
						map.put(name, false);	// 총회 끝난 후 댓글 2번 있을 경우 중복 카운팅을 방지하기 위해 value를 false로 변경
					}
				}
			}
		}
		System.out.println(cnt);
	}

	static int timeToInt(String time) {		// HH:MM 을 4자리 정수로 바꾸는 메소드
		StringTokenizer st = new StringTokenizer(time, ":");
		int i = Integer.parseInt(st.nextToken()) * 100;
		i += Integer.parseInt(st.nextToken());
		return i;
	}

}
