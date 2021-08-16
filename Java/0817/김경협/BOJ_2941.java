import java.util.Scanner;

public class BOJ_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();

		int len = arr.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (arr[i] == 'c' && i < len - 1) {
				if (arr[i + 1] == '=' || arr[i + 1] == '-')
					i++;
			} else if (arr[i] == 'd' && i < len - 1) {
				if (arr[i + 1] == '-')
					i++;
				else if (arr[i + 1] == 'z' && i < len - 2)
					if (arr[i + 2] == '=')
						i += 2;
			} else if ((arr[i] == 'l' || arr[i] == 'n') && i < len - 1) {
				if (arr[i + 1] == 'j')
					i++;
			} else if ((arr[i] == 's' || arr[i] == 'z') && i < len - 1) {
				if (arr[i + 1] == '=')
					i++;
			}
			count++;
		}

		System.out.println(count);
		sc.close();
	}
}
