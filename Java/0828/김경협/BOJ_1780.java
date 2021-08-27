import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  BOJ 1780 종이의 개수
 *  
 *  전형적인 분할정복 문제
 *  주어진 색종이가 단일한지 보고, 아니면  9등분하고
 *  나눠진 작은 종이에서 같은 일을 반복한다.
 *  
 */

public class BOJ_1780 {
	static int N, map[][], result[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		N = Integer.parseInt(br.readLine());
		result = new int[3];	// index 0: -1저장 ...
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0,0,N);
		
		for(int a : result)
			System.out.println(a);
	}
	
	static void cut(int row, int col, int size) {
		if(isSame(row, col, size))
		{
			if(map[row][col] == -1)
				result[0]++;
			else if(map[row][col] == 0)
				result[1]++;
			else
				result[2]++;
			return;
		}
		
		// 각각의 index는 row + (size / 3) * i 이다.
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				cut(row + size * i / 3, col + size * j / 3, size / 3);
	}
	
	static boolean isSame(int r, int c, int size) {
		int flag = map[r][c];	// map의 첫번째에 있는 값을 flag로 설정
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if(map[r+i][c+j] != flag)	// 하나라도 다르면 false
					return false;
		return true;
	}

}
