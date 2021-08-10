package study.date0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 어쩔 수 없이 입력값들 사이에 존재하는 연산자들의 조합을 모두 찾아야겠다 생각.
 * 선택한 연산자들의 개수를 줄여나가며 탐색하는 dfs가 적절하다 판단함
 * 처음엔 operator를 통째로 dfs메소드에  넘겨주니 재귀를 빠져나올 때 다시 배열의 값을 더해주고 하는게
 * 번거롭다고 생각하여 메인에서 +, -, *, /을 그냥 int형 값으로 분리해서 넘겨주니 직관적이고 간단해짐
 * 
 * 메모리		시간
 * 18792	168
 */

public class BaekOJ14888 {
	
	// 변경없이 그냥 사용만 하는 경우에는  static이 참 편하다.
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] num;
	
	static int _max = Integer.MIN_VALUE;
	static int _min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
	    N = Integer.parseInt(br.readLine());
	    
	    // 숫자들 입력받음
	    num = new int[N];
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
	    
	    // 연산자들 입력받음
	    int[] operator = new int[4];
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < 4; i++) operator[i] = Integer.parseInt(st.nextToken());
	    
	    //[ 0 : + ][ 1 : - ][ 2 : * ][ 3 : / ]
	    solve(1, num[0], operator[0], operator[1], operator[2], operator[3]);
	    System.out.println(_max+"\n"+_min);
	}
	
	public static void solve(int i, int result, int add, int sub, int mul, int div) {
		if (i == N) {
			_max = Math.max(_max, result);
			_min = Math.min(_min, result);
		}
		
		// add -> sub -> mul -> div 순으로 파고들어서 DFS 
		if (add > 0) solve(i+1, result+num[i], add-1, sub, mul, div);
		if (sub > 0) solve(i+1, result-num[i], add, sub-1, mul, div);
		if (mul > 0) solve(i+1, result*num[i], add, sub, mul-1, div);
		if (div > 0) solve(i+1, result/num[i], add, sub, mul, div-1);
	}
	
}