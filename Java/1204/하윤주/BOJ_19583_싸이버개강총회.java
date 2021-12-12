import java.io.*;
import java.util.*;

/**
 * [1204] BOJ 19583 싸이버개강총회
 * 문자열, 맵
 * 
 * sol)
 * 입력에서 채팅 시간만 파싱하여 개총 시작시간 이전에 채팅했으면 맵에 닉네임을 저장한다.
 * 만약 개총 종료시간, 스밍 종료시간 사이에 채팅했으면 최종 출석 인원을 증가하고, 
 * 중복적으로 채팅하는 인원을 제외하기위해 출첵한 닉네임은 맵에서도 지운다.
 * 
 * 시행착오)
 * 1.
 * EOF(End Of File)을 처음 처리해봄.
 * 	  (line = br.readLine()) != null
 * 위에서 line부분을 StringTokenizer로 했었는데 NullPointer 예외가 떴다;;
 * 왜인진 모르겠다...
 * 
 * 2.
 * 다른 사람들 풀이를 봤는데, 굳이 채팅 시간을 int로 형변환하지 않고 String의 compareTo() 메서드로 비교해도 훨씬 간단하고 빠르게 처리할 수 있었다!
 *
 */

public class BOJ_19583_싸이버개강총회 {
	static final int HOUR = 0;
	static final int MIN = 1;
	
	static int startHour;         // S
	static int startMin;
	static int endHour;           // E
	static int endMin;
	static int quitHour;          // Q
	static int quitMin;
	
	static BufferedReader br;
	static StringTokenizer st;
	static String line = "";      // 채팅 입력 처리를 위한 문자열 변수
	static Map<String, Boolean> nicknames = new HashMap<String, Boolean>();   // 개총 시작에 맞춰 입장한 학회원들
	static int answer;            // 총 출첵 인원

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 개총 시작 시간
		String[] times = st.nextToken().split(":");
		startHour = Integer.parseInt(times[HOUR]);
		startMin = Integer.parseInt(times[MIN]);
		
		// 개총 종료 시간
		times = st.nextToken().split(":");
		endHour = Integer.parseInt(times[HOUR]);
		endMin = Integer.parseInt(times[MIN]);
		
		// 스밍 종료 시간
		times = st.nextToken().split(":");
		quitHour = Integer.parseInt(times[HOUR]);
		quitMin = Integer.parseInt(times[MIN]);
		
		// 학생들 채팅
		while((line = br.readLine()) != null) {
			checkAttendance();
		}
		
		System.out.println(answer);
		
	}

	// 채팅을 남긴 학생들의 출석을 체크
	private static void checkAttendance() throws IOException {
		st = new StringTokenizer(line);
		String[] time = st.nextToken().split(":");
		int chatHour = Integer.parseInt(time[HOUR]);     // 채팅 남긴 시간
		int chatMin = Integer.parseInt(time[MIN]);
		
		if ((chatHour == startHour && chatMin <= startMin) ||         // 개총 시작시간이나 그 전에 채팅 남긴 학생 기록
			(chatHour < startHour)) {
			nicknames.put(st.nextToken(), true);
		} else if ((chatHour == endHour && chatMin >= endMin) ||      // 개총 종료시간이나 그 후에 채팅 남긴 학생
					(chatHour > endHour)) {
			if ((chatHour == quitHour && chatMin <= quitMin) ||       // 그 중에서 스밍 종료시간이나 그 전에 채팅 남긴 학생
				(chatHour < quitHour)) {
				String chatNickname = st.nextToken();
				if (nicknames.containsKey(chatNickname)) {            // 제 시간에 입장했던 학생이면 출첵하고 명단에서 제거
					answer++;
					nicknames.remove(chatNickname);
				}
			}
		}
		
	}

}
