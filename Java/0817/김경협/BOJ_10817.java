import java.util.Scanner;

public class BOJ_10817 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int first = 0, second = 0;

		for (int i = 0; i < 3; i++) {
			int curr = sc.nextInt();
			if (curr >= first) {
				second = first;
				first = curr;
			} else if (curr >= second) {
				second = curr;
			}
		}
		
		System.out.println(second);
		sc.close();
	}

}
