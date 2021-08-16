package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 
 * 백준 8320번 직사각형을 만드는 방법   
 * 풀이 : 높이가 주어졌을 때 만들 수 있는 최소 직사각형의 넓이를 계산
 * N보다 크면 반복 중단하고 개수 출력  
 * 
 * 14084KB	136ms
 */
public class Solution8320_김다빈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int result = 0;
		int height = 1;
		
		while(N >= (height*height)) {	// 최소 직사각형의 넓이 확인 
			int temp = height;
			while((temp++)*height <= N) {	// 높이를 올리면서 가능한지 확인 
				result++;
			}
			height++;
		}
        System.out.println(result);
	}

}
