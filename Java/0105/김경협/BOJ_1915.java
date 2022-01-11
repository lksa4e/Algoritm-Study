import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 1915�� ���� ū ���簢��
 * 
 * DP�� �̿��� ���簢������ üũ�ϸ鼭(1�� ä�����ִ���) �̵�
 * 1. ���簢�� üũ �޼ҵ�
 * 2. ���� ��, ����, ���� dp���� ���� ���� �� + 1�� �ڽ��� dp ä���
 * �� ���ؼ� ���� ū ���簢���� ���ߴ�.
 * 
 * ���簢���� ��� max�� 0�̾��ٸ�, �ƿ� 1�� ĭ�� ��������, ��� �ϳ��� 1�� ĭ�� �־������� üũ�ߴ�.
 * 
 * 28908	356
 */
public class BOJ_1915 {
	static int N, M, map[][], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j] = arr[j] - '0';
			}
		}
		
		// 1,1���� ������ üũ
		int max = 0;
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				if(map[i][j] == 1) {	// 1�� ĭ�� ����
					if(isSquare(i,j)) {	// �ڽ� ���� ���� ������ 1�� ä�����ִ��� üũ
						dp[i][j] = getLeastDP(i,j);	// ������ �簢��dp�� ���� �ּڰ� + 1 �� ������
						max = Math.max(dp[i][j], max);
					}
				}
			}
		}
		
		if(max == 0) {	// ���� �Ⱦ��� �� 2x2 ũ���� ���簢���� ���� ���,
			// �ִ� ũ�Ⱑ 1�� ���簢���� �ִ��� ������ üũ
			System.out.println(isZeroMap() ? 0 : 1);
		}else	// 2x2 �̻��� ���簢�� ����
			System.out.println((max+1)*(max+1));

	}
	
	static boolean isSquare(int row, int col) {	// �簢�� üũ
		if(map[row-1][col-1] == 0) return false;
		if(map[row][col-1] == 0) return false;
		if(map[row-1][col] == 0) return false;
		return true;
	}
	
	static int getLeastDP(int row, int col) {	// ������ �簢���� dp���� ���� �ּ��� �� +1 ��ȯ
		int min = Integer.MAX_VALUE;
		min = Math.min(dp[row-1][col-1], dp[row-1][col]);
		min = Math.min(min, dp[row][col-1]);
		
		return min + 1;
	}
	
	static boolean isZeroMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) return false;
			}
		}
		return true;
	}

}
