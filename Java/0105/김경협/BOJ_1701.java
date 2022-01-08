package BOJ_passed;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * BOJ 1701�� Cubeditor
 * 
 * KMP
 * 
 * KMP�� �����Լ��� �����ߴ�. BOJ ���ڿ� �������� ����ߴ� ��Ĵ��
 * �����Լ� ���ϴ� �� ���ؼ� 2�� �̻� �ݺ��Ǵ� ���� �� ���� ���̻縦 ���ߴ�.
 * �׸��� ���� ���ڿ�, ���� ���ڿ����� ���ڸ��� �� ���ڿ�, ���� ���ڿ����� ���� ���ڸ���
 * �� ���ڿ� ... �̷������� �ؼ� ��ü�� ���� ���� �� ���� ���̻縦 ���ߴ�.
 * 
 * 113072KB	328ms
 */

public class BOJ_1701 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = br.readLine().toCharArray();
		int max = 0;
		
		for (int startIdx = 0, len = text.length; startIdx < len; startIdx++) {
			
			int[] pi = new int[len];
			
			for(int i = 1, j = 0; i < len - startIdx; i++) {
				while(j > 0 && text[i + startIdx] != text[j + startIdx]) j = pi[j - 1];
				
				if(text[i + startIdx] == text[j + startIdx]) 
				{
					pi[i] = ++j;
					max = Math.max(pi[i], max);
				}
			}
			
			
		}
		
		System.out.println(max);
		
	}

}
