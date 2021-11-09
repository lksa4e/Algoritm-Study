import java.io.*;
import java.util.*;

/**
 * [1109] BOJ 1764 듣보잡
 * 	문자열, 정렬
 * 
 * sol)
 * 모든 듣도 못한 사람, 보도 못한 사람 하나의 리스트에 저장한 뒤 정렬하고,
 * 리스트의 처음부터 이름 하나씩 보면서 중복된 것만 찾기
 *
 */

public class BOJ_1764_듣보잡 {

	public static void main(String[] args) throws IOException {
		int cnt = 0;           // 총 듣보잡 수
		ArrayList<String> unknown = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int size = N+M;
		
		// 듣도 못한 사람들, 보도 못한 사람들 모두 하나의 리스트에 저장한 뒤 정렬
		for (int i=0; i<size; i++) {
			unknown.add(br.readLine());
		}
		Collections.sort(unknown, (p1, p2) -> p1.compareTo(p2));
		
		// 중복 찾기 및 출력
		StringBuilder sb = new StringBuilder();
		String prevName = unknown.get(0);
		
		for (int i=1; i<size; i++) {                 // 리스트의 이름 하나씩 보면서
			String curName = unknown.get(i);
			if (prevName.equals(curName)) {          // 중복 있을 경우 중복 횟수 증가하고 해당 이름은 최종 정답에 추가
				cnt++;
				sb.append(curName).append("\n");
			}
			prevName = curName;
		}
		
		sb.insert(0, cnt + "\n");                    // 최종 정답 맨 앞에 중복 횟수 삽입하여 출력
		System.out.println(sb);
	}

}
