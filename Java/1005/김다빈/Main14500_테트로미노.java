import java.io.*;
import java.util.*;

/**
 * 백준 14500번 테트로미노 
 * 
 * 풀이 : 구현
 * 
 * 모든 좌표에 대해 수행할 수 있는 19개의 테트로미노를 놓고 점수 계산 -> 최댓값 갱신 
 * 
 * 최대 500 * 500 좌표에 대해 19개의 테트로미노를 계산하니까 
 * 500 * 500 * 19 * 4 = 19,000,000 (1900만)이라 가능!
 * 
 * 31708KB	556ms
 */
public class Main14500_테트로미노 {

	static int R, C;
	static int[][] valueMap;
	static int[][][] tetromino = {
			// 정사각형 모양 
			{{0,0}, {0,1}, {1,0}, {1,1}},
			// 일자 모양 
			{{0,0}, {0,1}, {0,2}, {0,3}},
			{{0,0}, {1,0}, {2,0}, {3,0}},
			// 니은 모양 
			{{0,0}, {1,0}, {2,0}, {2,1}},
			{{0,1}, {1,1}, {2,1}, {2,0}},
			{{0,0}, {1,0}, {2,0}, {0,1}},
			{{0,0}, {0,1}, {1,1}, {2,1}},
			{{0,0}, {0,1}, {0,2}, {1,0}},
			{{0,0}, {0,1}, {0,2}, {1,2}},
			{{0,0}, {1,0}, {1,1}, {1,2}},
			{{1,0}, {1,1}, {1,2}, {0,2}},
			// 번개 모양 
			{{0,0}, {1,0}, {1,1}, {2,1}},
			{{0,1}, {1,1}, {1,0}, {2,0}},
			{{1,0}, {1,1}, {0,1}, {0,2}},
			{{0,0}, {0,1}, {1,1}, {1,2}},
			// ㅜ 모양
			{{0,0}, {0,1}, {0,2}, {1,1}},
			{{0,0}, {1,0}, {2,0}, {1,1}},
			{{0,1}, {1,0}, {1,1}, {1,2}},
			{{0,1}, {1,0}, {1,1}, {2,1}}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		valueMap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				valueMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxValue = 0;
		// 모든 좌표에 대해 수행할 수 있는 테트로미노의 경우의 수를 계산해서 최댓값 갱신 
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int tetri = 0; tetri < tetromino.length; tetri++) {
					maxValue = Math.max(maxValue, calcValue(i, j, tetri));					
				}
			}
		}
		
		System.out.println(maxValue);
	}

	// (r,c) 기준으로 테트로미노 tetri번째를 수행해서 얻을 수 있는 점수 계산 
	private static int calcValue(int r, int c, int tetri) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + tetromino[tetri][i][0];
			int nc = c + tetromino[tetri][i][1];
			
			// 경계를 벗어나면 제외하기 위해 -1 return 
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) return -1;
			
			value += valueMap[nr][nc];
		}
		return value;
	}

}
