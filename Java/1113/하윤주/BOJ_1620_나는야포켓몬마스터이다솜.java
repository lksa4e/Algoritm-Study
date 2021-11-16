import java.io.*;
import java.util.*;

/**
 * [1113] BOJ 1620 나는야 포켓몬 마스터 이다솜
 * 문자열, 맵
 * 
 * sol)
 * 이름이 키, 인덱스가 값인 맵과 반대로 인덱스가 키, 이름이 값인 맵 2개를 구현한다.
 *
 */

public class BOJ_1620_나는야포켓몬마스터이다솜 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 맵에 데이터 저장
		Map<String, String> name = new HashMap<>();       // 키: 인덱스, 값: 이름
		Map<String, String> index = new HashMap<>();      // 키: 이름, 값: 인덱스
		for (int i=1; i<=N; i++) {
			String pocketmon = br.readLine();
			String idx = Integer.toString(i);
			name.put(idx, pocketmon);
			index.put(pocketmon, idx);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			String cmd = br.readLine();
			
			if (name.get(cmd)==null) {                    // 이름 맵에 없으면 인덱스 맵으로 검색
				sb.append(index.get(cmd)).append("\n");
			} else {                                      // 인덱스 맵에 없으면 이름 맵으로 검색
				sb.append(name.get(cmd)).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
