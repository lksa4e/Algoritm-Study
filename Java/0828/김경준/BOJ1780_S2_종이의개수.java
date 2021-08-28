import java.io.*;
import java.util.*;
/**
 * BOJ 1780 종이의 개수 : 
 * 메모리 : 310388KB 시간 : 1004ms
 * 
 * 수업시간에 풀었던 색종이와 같은 문제
 * 이번엔 4등분이 아닌 9등분으로 해결하자
 * 
 */

public class BOJ1780_S2_종이의개수 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, map[][], answer[] = {0,0,0};  // -1 -> 0 , 0 -> 1, 1 -> 2로 맵핑
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0,0,N);
		System.out.print(answer[0]+ "\n" + answer[1] +"\n" + answer[2]);
	}
	
	// 전부 같은 숫자인지 체크하는 함수
	static boolean check(int x, int y, int len) {
		int flag = map[x][y];
		for(int i = x; i < x + len; i++) {
			for(int j = y; j < y + len; j++)
				if(map[i][j] != flag) return false;
		}
		return true;
	}
	
	// 분할정복하는 재귀 함수, 시작 x좌표, 시작 y좌표, 한 변의 길이를 가지고 있음
	static void solve(int x, int y, int len) {
		if(check(x,y,len)) {  // 전부 같은 숫자면
			answer[map[x][y] + 1]++; //해당 숫자 정답 추가
			return;
		}
		int size = len/3;
		for(int i = 0; i < 3; i++) {    //9등분에 대하여 재귀 수행
			for(int j = 0; j < 3; j++) 
				solve(x + i*size, y + j*size, size);
		}
	}
}
