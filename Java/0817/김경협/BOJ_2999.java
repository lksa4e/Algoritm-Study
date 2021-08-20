import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2999 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] message = br.readLine().toCharArray();

		int row = 0;

		for (int i = 1; i <= message.length; i++)
			for (int j = 1; j <= message.length; j++)
				if (i * j == message.length && i <= j)
					if (row < i)
						row = i;
		
		for (int i = 0; i < row; i++)
			for (int j = 0; j < message.length; j+=row)
				sb.append(message[j + i]);

		System.out.println(sb);
	}

}
