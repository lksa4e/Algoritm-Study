import java.io.*;

/*
 * BOJ 15927�� ȸ���� ȸ���ƴϾ�!!
 * 
 * �Ӹ������ �ƴ� ���� �� �κ� ���ڿ��� ã�ƾ� �ϴµ� �� 3���� ��찡 �־���.
 * 1. ���� ���ڷ� �̷���� �Ӹ������ ��� --> �� ��� ��� �κй��ڿ��� �Ӹ�����̱� ������ -1
 * 2. ���ڿ� ��ü�� �Ӹ������ ���(1������) --> ��ü���� �ϳ� ���� �Ӹ������ �ƴϱ� ������ len-1
 * 3. �׳� �Ӹ���� �ƴ� ��� -> ���ڿ� ���� ���
 * 
 * �Ӹ������ üũ�ϸ鼭 �� 3���� ������ üũ�ߴ�.
 * 
 * 19484KB	204ms
 */

public class BOJ_15927 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray();
		
		System.out.println(getLongestSubStr(str));
	}

	private static int getLongestSubStr(char[] str) {
		boolean isSingleCharStr = true;
		
		for(int i = 0, size = str.length; i < size / 2; i++) {
			if(str[i] != str[size - 1 - i]) return size;
			if(str[i] != str[i + 1]) isSingleCharStr = false;
		}
		
		return isSingleCharStr ? -1 : str.length - 1;
	}

}
