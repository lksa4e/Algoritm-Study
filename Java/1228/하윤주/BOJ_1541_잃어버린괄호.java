import java.io.*;
import java.util.*;

/**
 * [1221] BOJ 1541 잃어버린 괄호
 * 문자열, 그리디
 * 
 * sol)
 * 연산 결과가 최소가 되게 하기 위해서는 마이너스 부호 뒤를 최대로 만들어야 한다.
 * 따라서 마이너스 부호를 기준으로 수식을 자르면 숫자와 플러스 부호만 남게되는데 이 플러스 연산들을 모두 수행한다.
 * 이후 처음부터 순서대로 마이너스 연산 수행하면 끝
 *
 */

public class BOJ_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 수식을 마이너스 부호를 기준으로 구분
		StringTokenizer tokensSplitByMinus = new StringTokenizer(br.readLine(), "-");
		int tokenSizeSplitByMinus = tokensSplitByMinus.countTokens();
		
		int result = 0;
		// 마이너스 부호를 기준으로 쪼개진 각 수식(혹은 수 그자체)을 연산(오로지 플러스 연산)함
		for (int i=0; i<tokenSizeSplitByMinus; i++) {
			StringTokenizer tokensSplitByPlus = new StringTokenizer(tokensSplitByMinus.nextToken(), "+");
			// 플러스 연산 수행
			int subResult = 0;
			while(tokensSplitByPlus.hasMoreTokens()) subResult += Integer.parseInt(tokensSplitByPlus.nextToken());
			// 첫번째 수는 마이너스로 시작하지 않으므로 무조건 더해주고 나머지부터는 마이너스로 쪼개진 덩어리들이므로 빼줌
			if (i==0) result += subResult;
			else result -= subResult;
		}
		
		System.out.println(result);
	}

}
