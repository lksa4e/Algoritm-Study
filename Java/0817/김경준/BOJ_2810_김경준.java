import java.io.*;
import java.util.*;
/**
 * BOJ 2810 컵홀더 : https://www.acmicpc.net/problem/2810
 * 메모리 : 14168KB 시간 : 140ms
 * 
 * 왼쪽부터 쓸 수 있는경우 먼저 사용해도 항상 최대값을 구할 수 있다.(그리디)
 * 
 * 현재 좌석이 S인 경우 언제나 컵홀더를 사용할 수 있다. (왼쪽 비었으면 왼쪽, 왼쪽 사용중이면 오른쪽)
 * 하지만 현재 좌석이 LL 인 경우 오른쪽 커플은 언제나 컵홀더를 사용할 수 있지만, 왼쪽 커플은 이전 사람이 오른쪽 컵홀더를 사용했는지 여부에 따라 컵홀더 사용 여부가 갈린다.
 * 
 * 따라서 can이라는 flag를 사용하여 현재 좌석에서 왼쪽 컵홀더를 사용할 수 있는지를 체크한다.
 * 초기에는 can flag는 1로 존재한다. 하지만 중간에 한번이라도 커플이 앉은 경우 can flag는 0이 된다.
 * 
 */
public class BOJ_2810_김경준 {
	static int N,M,L;
	static char seat[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		seat = br.readLine().toCharArray();
		int cnt = 0, can = 1; // 내 왼쪽 컵홀더 쓸 수 있는지
		for(int i = 0; i < N; i++) {
			if(i == 0 || i == N - 1) { // 첫번째, 마지막 자리 -> 무조건 컵홀더 쓸 수 있음
				if(seat[i] == 'L') {
					i++;               // 커플인 경우 한번에 2자리 건너뛰기
					can = 0;           // 커플 나오면 can flag off
				}
				cnt++;
			}else {  // 중간자리
				if(seat[i] == 'S') {
					cnt++;
				}else { // 커플
					if(can == 1) {        // 이전까지 S만 나왔다가 처음으로 커플 나옴 -> 왼쪽 오른쪽 컵홀더 둘 다 사용 가능
						cnt += 2;     
						can = 0;
					}else {
						cnt++;
					}
					i++;
				}
			}
		}
		System.out.print(cnt);
	}
}
