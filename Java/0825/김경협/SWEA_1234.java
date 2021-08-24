import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  SWEA 1234번 비밀번호
 *  
 *  연속되고 같은 번호 2개가 없도록 계속 지우는 문제,
 *  List를 사용해서 문제를 풀었다.
 *  index를 뒤에서부터 읽으며 삭제했다.
 *  삭제한 후 다음 index로 또 삭제할 수 있는 장점이 있다.
 *  대신 약간의 예외 상황 생각해 줘야함.(맨 뒤에 2자리를 지운 경우)
 *  
 */

public class SWEA_1234 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			char[] arr = st.nextToken().toCharArray();
			List<Character> psword = new ArrayList<>();
			for (int i = 0; i < n; i++)
				psword.add(arr[i]);
			
			for(int i = n - 1; i > 0; i--) {
				if(i >= psword.size())
					continue;
				if(psword.get(i) == psword.get(i-1)) {
					psword.remove(i);
					psword.remove(i - 1);
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(char a : psword)
				sb.append(a);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
