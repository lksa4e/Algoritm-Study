package BaekOJ.study.date1228;

import java.io.*;

/*
 * 백준 1541 잃어버린 괄호
 * 
 * 마이너스가 한번이라도 나오면 마이너스를 기준으로 바로 뒤에 괄호를 치고 앞에 값을 모두 뺄 수 있기 때문에 마이너스가 나오면 계속 값을 빼준다
 * 
 * 메모리 	시간
 * 14204	124
 */

public class BaekOJ1541_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static StringBuilder sbNum = new StringBuilder();
	static StringBuilder sbOp = new StringBuilder();
	static int sum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		String input = br.readLine();
		for(int i = 0; i < input.length(); i++) {		// 숫자와 연산자 분리
			char ch = input.charAt(i);
			if(Character.isDigit(ch)) sbNum.append(ch);
			else {
				sbNum.append(" ");
				sbOp.append(ch);
			}
		}
		String[] num = sbNum.toString().split(" ");
		String op = sbOp.toString();
		boolean isMinus = false;
		
		sum = Integer.parseInt(num[0]);
		for(int i = 0; i < num.length-1; i++) {
			if(op.charAt(i) == '-') isMinus = true;		// 마이너스가 나오는 순간부터 다 빼줌
			
			if(isMinus) sum -= Integer.parseInt(num[i+1]);
			else sum += Integer.parseInt(num[i+1]);
		}
		
		System.out.println(sum);
	}
	
}