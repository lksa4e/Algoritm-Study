import java.util.*;
import java.io.*;

/*
 * BOJ 11054�� ���� �� ������� �κ� ����
 * 
 * �� ���ں��� �������� ���� �������� ���� ������ ������ ���� �ջ��ߴ�.
 * �׸��� �� �߿��� ���� ū ���� ���� �� ������� �κ� ������ �ȴ�.
 * 
 * 14900KB	156ms
 */

public class BOJ_11054 {

	static int N, nums[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		nums = new int[N];
		
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(solve());
	}

	private static int solve() {
		
		// �������� ���ϱ�
		int ascDp[] = new int[N];	// �� ascDp[i]���� i��° ���ڰ� ������ �������� ���� ����ȴ�.
		for(int i = 0; i < N; i++) {		// i������ ������������ i�� ������Ʈ �� ���
			for(int j = 0; j < i; j++) {	// j�� 0~i-1���� ���鼭 �˸��� �������� ����� ���Ѵ�.
				// ���� ������Ʈ�ϱ� ���ؼ��� j��° ���ڰ� ������Ʈ ����̵Ǵ� i���� �۾ƾ��ϰ�
				// �� �߿��� ���� ū ���������� ���ؾ��ϱ� ������ ascDp[j] + 1���� ������ ��� ������Ʈ �ؼ�
				// 0~i-1���� ���� ���� �� �ִ� ���� ū �������� ���� ���� �� �ִ�.
				if(nums[j] < nums[i] && ascDp[i] < ascDp[j] + 1) {
					ascDp[i] = ascDp[j] + 1;
				}
			}
		}
		
		// �������� ���ϱ�
		int descDp[] = new int[N];	
		for(int i = N - 1; i >= 0; i--) {		
			for(int j = N - 1; j > i; j--) {	
				
				if(nums[j] < nums[i] && descDp[i] < descDp[j] + 1) {
					descDp[i] = descDp[j] + 1;
				}
			}
		}
		
		// ���� �ջ�
		int max = 0;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, ascDp[i] + descDp[i]);
		}
		
		return max + 1;
	}

}
