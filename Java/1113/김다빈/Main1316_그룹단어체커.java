import java.io.*;
import java.util.*;

/**
 * 백준 1316번 그룹 단어 체커
 * 
 * 각 문장마다 바로 직전 단어와 비교해서
 * 	만약 다른 단어이고 이미 나온 적이 있는 단어라면 그룹 단어 X
 * 
 * 14280KB	132ms
 */
public class Main1316_그룹단어체커 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			boolean[] flag = new boolean[26];
			boolean isGroupWord = true;	// 그룹단어인지 여부
			
			int prev = input[0]-'a', next;
			flag[prev] = true;
			
			// 바로 직전 단어를 기준으로 판단 
			for (int j = 1; j < input.length; j++) {
				next = input[j]-'a';
				
				if(prev != next) {	// 다른 단어이고 이미 나왔던 적이 있다면, 그룹단어 X
					if(flag[next]) {
						isGroupWord = false;
						break;
					}
					flag[next] = true;
					prev = next;
				}
			}
			
			if(isGroupWord) result++;	// 그룹단어 개수 ++ 
		}
		
		System.out.println(result);
	}
	
}
