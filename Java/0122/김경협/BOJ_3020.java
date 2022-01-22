import java.io.*;
import java.util.*;

/*
 * BOJ 3020�� ���˹���
 * 
 * ���� ���� ���ϴ� ���.
 * 
 * ù ��° ���̵��. H¥�� �迭�� �غ��� ���� ���̰� 3�� ������ ������
 * �ؿ��� 3ĭ���� +1�� ���ְ� 5�� �������� ������ ������ 5ĭ�� ��� +1 ���ش�.
 * --> N(500000 * 200000)
 * 
 * �ι�° ���̵��,
 * ���� �迭�� ������ �迭�� �غ�, 3¥�� ������ ������ ���� �迭�� 3��° ��ġ�� +1�Ѵ�.
 * 5¥�� �������� ������ 5��° ��ġ�� +1�Ѵ�.
 * �� �������� ���� ���������� �������鼭 �Ʒ����� �ڱ� ���� �����ش�.
 * ���� �迭�� ������ �迭�� ��ġ�� �ּڰ��� ã�´�.
 * --> N(500000 * 4)
 * 
 * 30688KB	360ms
 */

public class BOJ_3020 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] toTop = new int[H], toBottom = new int[H];	// ����, ������ �迭
		
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {	// �����ư��鼭 �� ���̿� ���� ����
				toTop[Integer.parseInt(br.readLine()) - 1]++;
			} else
				toBottom[Integer.parseInt(br.readLine()) - 1]++;
		}
		
		getSum(toTop);
		getSum(toBottom);	// ������ ������ ���
		
		getSum(toTop, toBottom);	// ���� �迭, ������ �迭 ��ġ��
		
		int min = Integer.MAX_VALUE, cnt = 0;
		
		for(int i = 0; i < H; i++) {	// �ּڰ� ã��
			if(toTop[i] < min) {
				cnt = 1;
				min = toTop[i];
			} else if(toTop[i] == min) {
				cnt++;
			}
		}
		
		sb.append(min).append(" ").append(cnt);
		System.out.println(sb);
		
	}
	
	static void getSum(int[] arr) {
		for(int i = arr.length - 1; i > 0; i--) {	// ����������, �Ʒ������� �ڱⰪ�� �����ؼ� �Ѱ���
			arr[i - 1] += arr[i];
		}
	}
	
	// ���� ������ ��ġ��
	static void getSum(int[] toTop, int[] toBottom) {
		for(int i = 0, size = toTop.length; i < size; i++) {
			toTop[i] += toBottom[size - i - 1];
		}
	}

}
