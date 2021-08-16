import java.util.Scanner;

public class BOJ_2810 {
	static int LEFT = 0, RIGHT = 1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		char[] line = sc.next().toCharArray();
		
		int state = LEFT, count = 0;
		for(int i = 0; i < N; i++) {
			if(line[i] == 'L') {
				if(state == LEFT)
				{
					state++;
					count++;
				}
				i++;
			}
			count++;
		}
		
		System.out.println(count);
		sc.close();
		
	}

}
