package P0816;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** 
 * 백준 3052번 나머지   
 * 풀이 : Set을 이용하여 42로 나눈 나머지를 저장 (중복 제거)
 * 결과로 Set의 사이즈 출력
 * 
 * 17688KB	228ms
 */
public class Solution3052_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Integer> remain = new HashSet<Integer>();
		
		for(int i=0;i<10;i++) remain.add(sc.nextInt() % 42);
		
		System.out.println(remain.size());
	}

}
