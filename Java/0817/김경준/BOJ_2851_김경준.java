import java.io.*;
import java.util.*;
/**
 * BOJ 2851 슈퍼 마리오 : https://www.acmicpc.net/problem/2851
 * 메모리 : 14212KB 시간 : 144ms
 * 
 * 앞에서부터 입력받으면서 100과의 간격을 측정한다. 이전에 측정한 간격보다 작거나 같으면 정답 갱신
 * 이전에 측정한 값보다 갭이 더 커지면 이전 값을 정답으로 설정
 * 
 * Time Complexity
 * - O(1)
 */
public class BOJ_2851_김경준 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int min_gap = Integer.MAX_VALUE;
		int answer = 0;
		int flag = 0;
		for(int i = 0; i < 10; i++) {
			int num = Integer.parseInt(br.readLine());
			if(flag == 1) continue;
			answer += num;
			if(Math.abs(100 - answer) <= min_gap) { // 100에 더 가까운 합이 있으면
				min_gap = Math.abs(100-answer);
			}else { // 더한게 넘어가 버리면
				answer -= num;
				flag = 1;
			}
		}
		System.out.println(answer);
	}
}
