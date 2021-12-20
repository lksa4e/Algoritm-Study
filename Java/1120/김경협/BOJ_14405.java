import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 14405번 피카츄
 * 
 * pi와 ka와 chu로만 이루어진 string인지 판단하는 문제
 * 
 * p,k,c의 case로 나눠서 판단하고 loop를 돌렸다.
 * 
 * 	14188	128
 */

public class BOJ_14405 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		if(isPikachu(br.readLine()))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	static boolean isPikachu(String str) {
		while(str.length() > 0) {	// loop till str exist
			char c = str.charAt(0);
			if(c == 'p') {	// case of pi
				if(str.length() < 2) return false;
				String tmp = str.substring(0, 2);
				if(tmp.equals("pi"))
					str = str.substring(2);
				else
					return false;
			} else if(c == 'k') {	// case of ka
				if(str.length() < 2) return false;
				String tmp = str.substring(0, 2);
				if(tmp.equals("ka"))
					str = str.substring(2);
				else
					return false;
			} else if(c == 'c') {	// case of chu
				if(str.length() < 3) return false;
				String tmp = str.substring(0, 3);
				if(tmp.equals("chu"))
					str = str.substring(3);
				else
					return false;
			} else {
				return false;
			}
		}
		return true;
	}

}
