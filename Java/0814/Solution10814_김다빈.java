package P0810;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * 10814번 나이순 정렬 
 * @author 김다빈 
 *
 */

public class Solution10814_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		// 입력받은 회원 정보 저장
		// 0 - 나이, 1 - 이름 
		String[][] member = new String[N][2];
		
		for(int i=0;i<N;i++) {
			member[i][0] = sc.next();
			member[i][1] = sc.next();
		}
		
		// String 형태로 받은 나이를 기준으로 정렬 
		Arrays.sort(member, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
			}
		});
		
		for(int i=0;i<N;i++) {
			sb.append(member[i][0]+" "+ member[i][1]+"\n");
		}
		
		// 마지막 개행문자 삭제하고 출력 
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}

}
