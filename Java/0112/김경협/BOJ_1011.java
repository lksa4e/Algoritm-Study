import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 1011�� Fly me to the Alpha Centauri
 * 
 * ���� ����. count�� �ٲ�� ������ �� �����ϰ� ��Ģ�� ã�Ƴ��� �����ߴ�.
 * 
 * �Ʒ��� �����̵� ��ġ �۵� Ƚ�� N�� �����ϱ� �ٷ� �����ܰ�鸸 ������ ���̴�.
 * 
 * 			�Ÿ�		����		�ڸ��� N
 *			0		 		0
 * 1		1		1		1
 * 11	 	2		1		2
 * 121		4		2		3
 * 1221		6		2		4
 * 12321	9		3		5
 * 123321	12		3		6
 * ...
 * 
 * ������ �����̵� ��ġ �۵� Ƚ�� N�� �����ϱ� ���ؼ�
 * 1,1,2,2,3,3 ... ������ �Ÿ� dis�� �����Ѵ�.
 * �� �����ϴ� ���� increment�� 1,4,9,16 ... �� dis�� �������� �� 1�� �þ��.
 * ���� increment�� dis�� ���������� ���� �� �ִ�.
 * increment = (int) sqrt(dis)
 * 
 * �׸��� �ڸ��� N�� increment * 2�ε�, ������ ���� �ణ�� �޶�����.
 * 
 * ���� ���
 * 4,5,6,7,8�� ��� �������� 2������
 * 4�� 3�ڸ�
 * 5,6�� 4�ڸ�
 * 7,8�� 5�ڸ��̴�.
 * 
 * ���� �̸� ���� ���� increment�� �Ÿ� dis�� ǥ���ϸ�
 * increment^2 == dis�� �� 							N = increment * 2 - 1
 * increment^2 < dis <= increment^2 + increment�� �� 	N = increment * 2
 * increment^2 < dis <= (increment+1)^2�� ��			N = increment * 2 + 1�� �ȴ�.
 * 
 * �̸� �ڵ�� �Űܼ� dis�� �־��� �� N�� ���ϸ� �ȴ�.
 */
public class BOJ_1011 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int dis = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
			dis *= -1;
			
			int increment = (int) Math.sqrt(dis), increment2 = increment * increment, N = 0;
			
			if(increment2 == dis) {
				N = increment * 2 - 1;
			} else if(increment2 < dis && dis <= increment2 + increment) {
				N = increment * 2;
			} else {
				N = increment * 2 + 1;
			}
			
			sb.append(N).append("\n");
		}
		System.out.println(sb);
	}

}
