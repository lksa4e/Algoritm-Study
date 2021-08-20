import java.util.Scanner;

public class BOJ_1592 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		int dir = 1;
		int[] count = new int[N];
		int totalCount = 0;
		int index = 0;
		count[0]++;
		
		while(true) {
			if(count[index] % 2 == 0)
				dir = 1;
			else
				dir = -1;
			
			totalCount++;
			
			index = (index + dir * L) % N;
			if(index < 0)
				index = N + index;
			if(++count[index] >= M)
				break;
		}
		
		System.out.println(totalCount);
		
		sc.close();
	}

}
