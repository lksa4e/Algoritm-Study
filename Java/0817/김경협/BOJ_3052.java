import java.util.Scanner;

public class BOJ_3052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] num = new int[10];

		int cnt = 0, j = 0;
		for (int i = 0; i < 10; i++) {
			num[i] = sc.nextInt() % 42;

			for (j = 0; j < i; j++) {
				if (num[j] == num[i])
					break;
			}
			if (j == i)
				cnt++;
		}
		
		System.out.println(cnt);
		sc.close();
	}
}
