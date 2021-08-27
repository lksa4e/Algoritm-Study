import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 *  SWEA 4789번 성공적인 공연 기획
 *  
 *  i번째 인덱스를 넘어가기 위해서는 i명의 사람이 필요하다.
 *  만약 clap[i]가 0이라 사람이 없다면 1명 고용하여 i+1번째 인덱스로 넘어가고 이 과정을 반복한다.
 * 
 */

public class SWEA_4789 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			String input = br.readLine();
			int[] clap = new int[input.length()];
			for (int i = 0; i < input.length(); i++) 
				clap[i] = input.charAt(i) - '0';
			
			
			int people = 0, cnt = 0, N = clap.length;
			for (int i = 0; i < N; i++) {
				people += clap[i];	// i번째에 박수치는 사람이 있으면 전체 박수치는 사람인 people에 더해줌
				if(i >= people) {	// i번째에 있는 사람이 박수치기 위해서는 people이 i명이 필요하다. 이보다 작거나 같을 경우, 사람 1명 추가해줘야 함
					cnt++;
					people++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}

}
