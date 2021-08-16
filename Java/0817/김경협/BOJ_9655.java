import java.util.Scanner;

public class BOJ_9655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		if(sc.nextInt() % 2 == 0)
			System.out.println("CY");
		else
			System.out.println("SK");
		
		sc.close();
	}

}
