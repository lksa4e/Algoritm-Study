import java.io.*;
import java.util.*;
/**
 * [D4]SWEA 4796 의석이의 우뚝 선 산 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT
 * 메모리 : 102,232kb, 시간 : 690ms
 * 
 * 하나의  △ 구역에서 우뚝 선 산의 구간 수 -> 올라가는 산의 개수 * 내려가는 산의 개수
 * 
 * 오름차 구간에서는 오름차 개수를 계속해서 증가시킨다. (upcount++)
 * 
 * 내림차 구간에서는 매번 upcount 저장값을 더한다. (올라가는 산의 개수 * 내려가는 산의 개수) 공식 계산을 위함
 * 
 * 다시 오름차가 되면 upcount를 초기화한다.
 * 
 */

public class SWEA_4796_의석이의우뚝선산_김경준 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T,N,a;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			int flag = 0, before_num = 0, upcount = 0, answer = 0;
			for(int i = 0; i < N; i++) {
				a = sc.nextInt();
				if(i == 0) {          // 첫번째 원소에 대한 세팅 
					before_num = a;
					continue;
				}

				if(i == 1) {            // 두번째 원소에 대한 세팅 - 오름차인지, 처음부터 내림차인지 구별하기 위함
					if(before_num < a) {
						flag = 1;       // 오름차
						upcount = 1;
					}
					else flag = 2;  // 시작부터 내림차면 answer 카운트하지 않음
					
					before_num = a;
					continue;
				}
				
				if(flag == 1) {  // 오름차인 경우
					if(before_num > a) flag = 2;  // 내림차 시작되면 flag 변경
					else upcount++;  // 오름차 개수 증가
				}
				
				if(flag == 2){  // 내림차인 경우
					if(before_num > a) answer += upcount;  // 내림차 계속되는동안에 오름차 개수만큼 더함
					else {
						flag = 1;       // 다시 오름차로 변경되면 flag, upcount 초기화
						upcount = 1;
					}
				}
				before_num = a;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}	
}
