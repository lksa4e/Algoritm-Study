package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2851번 슈퍼 마리오
 * 풀이 : 순차적으로 버섯을 먹으면서 점수 카운팅
 * 점수가 100을 넘기는 순간, 먹기 전과 후의 점수 중 100과 더 가까운 수를 출력 
 * 만약 같다면 먹은 후의 점수 출력 
 * 
 * 14184KB	136ms
 */
public class Solution2851_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] score = new int[10];
		
		for(int i=0;i<10;i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		int result = 0;
		for(int i=0;i<10;i++) {
			if(result + score[i] >= 100) {	// 먹은 후 점수가 100 이상이면 
				if((100-result) >= (result+score[i]-100)) {	// 먹기 전과 후 중 100과 더 가까운 수 출력 
					result += score[i];
				}
				break;
			}
			result += score[i];
		}
		System.out.println(result);
	}

}
