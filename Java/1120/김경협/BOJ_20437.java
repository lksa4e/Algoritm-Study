import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * BOJ 20437번 문자열 게임2
 * 
 * 어떤 문자를 K개 포함하는 가장 짧은 문자열의 길이와,
 * 어떤 문자를 K개 포함하는 가장 긴 문자열의 길이를 구하는 문제
 * 
 * 알파벳마다 start index와 end index, 현재 start와 end 사이에 있는 문자의 개수를 저장
 * idx를 하나씩 증가시키며 어떤 문자가 K개가 되었을 때 갱신하는 방식으로 풀었다.
 * 
 */

public class BOJ_20437 {

	public static void main(String[] args) throws Exception {
		// 문자 별로 start와 end, 현재 start와 end 사이에 있는 문자열 개수를 저장
		// update 될 때마다 longest, shortest 길이 비교해서 갱신

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			char[] line = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			int longest = Integer.MIN_VALUE, shortest = Integer.MAX_VALUE;

			int size = line.length;

			int charInfo[][] = new int[26][3]; // 0: start, 1: end, 2: numOfChar
			for (int idx = 0; idx < size; idx++) {
				int curr = (int) (line[idx] - 'a');	// 현재 idx가 위치한 문자
				
				if(charInfo[curr][0] == 0 && charInfo[curr][2] == 0) {	// 맨 처음 start index가 모두 0이기 때문에 init을 위한 조건 체크
					charInfo[curr][0] = idx;	// 처음 나온 문자라면 start에 index 저장
				}
				charInfo[curr][1] = idx;		// 맨 끝 부분 index로 저장
				charInfo[curr][2]++;			// start와 end 사이에 있는 문자열의 개수 1 증가

				if (charInfo[curr][2] == K) {	// 문자열에서 동일한 문자가 K개 있을 때,
					int charLength = charInfo[curr][1] - charInfo[curr][0] + 1; // end - start + 1: 문자를 K개 포함한 문자열의 길이

					shortest = Math.min(shortest, charLength);
					longest = Math.max(longest, charLength);

					int idx2 = charInfo[curr][0] + 1;		// start index를 찾아서 갱신
					while (true) {
						if (idx2 >= size) {			// size보다 커지지는 않도록,
							idx2 = -1;
							break;
						}
						if ((int)(line[idx2] - 'a') == curr)
							break;
						idx2++;
					}

					if (idx2 != -1) {		// size보다 크지않고, 새로운 start index 갱신
						charInfo[curr][0] = idx2;
						charInfo[curr][2]--;
					}
				}
			}

			if (shortest == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(shortest).append(" ").append(longest).append("\n");
			}
		}
		System.out.print(sb);
	}

}
