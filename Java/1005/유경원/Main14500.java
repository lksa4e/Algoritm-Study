import java.io.*;
import java.util.StringTokenizer;

public class Main14500 {
	/*
	 * 도형 모양 노가다..
	 * 모든 점마다 가능한 모양 합 구해서 최댓값 구하기
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int N, M, map[][], ans = 0;
	static int[][][] shape = new int[][][] {
		{{0,0},{0,1},{0,2},{0,3}}, // ㅡ
		{{0,0},{1,0},{2,0},{3,0}}, // ㅣ
		{{0,0},{0,1},{1,0},{1,1}}, // ㅁ
		{{0,0},{0,1},{0,2},{1,2}}, // ㄱ
		{{0,2},{1,0},{1,1},{1,2}},
		{{0,0},{1,0},{1,1},{1,2}},
		{{0,0},{0,1},{0,2},{1,0}},
		{{0,0},{0,1},{1,0},{2,0}},
		{{0,0},{0,1},{1,1},{2,1}},
		{{2,0},{0,1},{1,1},{2,1}},
		{{0,0},{1,0},{2,0},{2,1}},
		{{1,0},{1,1},{0,1},{0,2}}, // ㄱㄴ
		{{0,0},{0,1},{1,1},{1,2}},
		{{0,0},{1,0},{1,1},{2,1}},
		{{0,1},{1,0},{1,1},{2,0}},
		{{0,0},{0,1},{0,2},{1,1}}, // ㅗ
		{{0,0},{1,0},{2,0},{1,1}},
		{{0,1},{1,0},{1,1},{1,2}},
		{{1,0},{0,1},{1,1},{2,1}},
		};
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			getSum(i,j);
    		}
    	}
    	System.out.println(ans);
    }
    
    private static void getSum(int x, int y) {
    	int nx = 0, ny = 0, sum = 0;
    	
    	for(int[][] shap : shape) {
    		sum = 0;
    		for(int[] s : shap) {
    			nx = x + s[0];
    			ny = y + s[1];
    			if(nx>=N || ny>=M) break;
    			
    			sum += map[nx][ny];
    		}
    		if(ans < sum) ans = sum;
    	}
    }
    
}
