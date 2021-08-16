import java.util.Scanner;

public class BOJ_8320 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int cnt = 0;
		for (int i = 1; i <= n; i++)
			for(int j = 1; j <= i; j++)
				if(i % j == 0) {
					int k = i / j;
					if(j <= k)
						cnt++;
					else
						break;
				}
		System.out.println(cnt);
		sc.close();
	}
}
