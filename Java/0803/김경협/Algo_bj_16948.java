import java.util.Scanner;

public class Main {
	static int r1, c1;
	static int r2, c2;

	static int move() {
		int count = 0;
		
		// 목표가 있는 행이 도달 할 수 있는 행인지 판단
		if (r1 % 2 != r2 % 2)
			return -1;

		// 목표가 있는 행까지 이동
		while (r1 != r2) {
			if (r1 < r2) {
				if (c1 < c2) {
					r1 += 2; c1 += 1;
				} else {
					r1 += 2; c1 -= 1;
				}
			} else if (r1 > r2) {
				if (c1 < c2) {
					r1 -= 2; c1 += 1;
				} else {
					r1 -= 2; c1 -= 1;
				}
			}
			count++;
		}

		// 목표가 있는 열까지 이동
		while (Math.abs(c1 - c2) > 1) {
			if (c1 < c2)
				c1 += 2;
			else
				c1 -= 2;
			count++;
		}
		
		if(Math.abs(c1 - c2) == 1)
			return -1;
		else
			return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		r1 = sc.nextInt();
		c1 = sc.nextInt();
		r2 = sc.nextInt();
		c2 = sc.nextInt();

		System.out.println(move());

		sc.close();
	}

}
