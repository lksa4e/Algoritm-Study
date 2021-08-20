package P0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 9019번 DSLR
 * 풀이 : 맨 처음 순열로 명령어의 개수를 1부터 늘리면서
 * 가능한 경우의 조합을 모두 계산해주었다..
 * 
 * 최소한의 명령어의 개수가 제한이 없어
 * 열심히 풀었는데 시간 초과ㅠㅠ
 *
 */

public class Solution9019_김다빈_순열 {
	
	static int A;
	static int B;
	static ArrayList<String> command = new ArrayList<String>();
	static int commandNum;
	static boolean isSuccess;
	static String[] result;
	static String[] order;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=0;test_case<T;test_case++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			commandNum = 0;	// 명령어의 개수 
			isSuccess = false;
			
			while(true) {
				order = new String[++commandNum];
				result = new String[commandNum];
				
				// 명령어의 개수가 늘어날 때마다 추가 
				command.add("D");
				command.add("S");
				command.add("L");
				command.add("R");
				
				// 가능한 모든 조합을 계산 
				combination(0,0);
				
				if(isSuccess) {
					for(int i=0;i<commandNum;i++) {
						sb.append(result[i]);
					}
					sb.append("\n");
					break;
				}
			}
		}
		
		System.out.println(sb);
	}

	private static void combination(int start, int r) {
		if(r == commandNum) {
			// 계산했을 때 결과가 동일한지 확인 
			// 이미 결과가 나왔으면 계산 안함 
			if(!isSuccess) calculate();
			return;
		}
		for(int i=start;i<command.size();i++) {
			order[r] = command.get(i);
			combination(i+1, r+1);
		}
	}

	private static void calculate() {
		int a = A;
		
		for(int i=0;i<commandNum;i++) {
			switch (order[i]) {
			case "D":
				a = (2*a) % 10000;
				break;
			case "S":
				a -= 1;
				if(a<0) a = 9999;
				break;
			case "L":
				a *= 10;	// d1 d2 d3 d4 0
				a = (a+a/10000) % 10000;	// (d1) d2 d3 d4 d1
				break;
			case "R":
				int d4 = a - (a/10)*10; // d1 d2 d3 d4 - d1 d2 d3 0 = d4
				a = d4*1000 + a/10; // d4 0 0 0 + d1 d2 d3 = d4 d1 d2 d3
			}
		}
		
		// 최종 값과 같다면 업데이트 
		if(a == B) {
			for(int i=0;i<commandNum;i++) {
				result[i] = order[i];
			}
			isSuccess = true;
		}
	}
	
}
