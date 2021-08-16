package P0816;

import java.util.Scanner;

/**
 * 백준 1592번 영식이와 친구들 
 * 풀이 : 공을 받은 횟수를 저장하는 배열 ball 
 * 받은 횟수가 홀수인지 짝수인지 판단하고
 * 범위를 벗어나지 않게 공 던지기! 
 * 
 * 17648KB	240ms 
 */
public class Solution1592_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int[] ball = new int[N];
		
		int curNum = 0;
		int result = 0;
		
		while(++(ball[curNum]) != M) {
			if(ball[curNum] % 2 == 1) {	// 시계방향 L번째 
				if(curNum+L >= N) curNum = (curNum+L)-N;
				else curNum += L;
			} else {	// 반시계방향 L번째 
				if(curNum-L < 0) curNum = N+(curNum-L);
				else curNum -= L;
			}
			result++;
		}
		
		System.out.println(result);
	}

}
