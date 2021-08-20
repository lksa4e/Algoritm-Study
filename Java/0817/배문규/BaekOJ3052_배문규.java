package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
 * 나머지가 다른 값의 개수 == set에 입력값의 42 나머지 값을 넣어주고 난 뒤의  set의 사이즈
 * 
 * 메모리		시간
 * 14136	132
 */
public class BaekOJ3052_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < 10; i++) set.add(Integer.parseInt(br.readLine())%42);
		
		System.out.println(set.size());
	}

}
