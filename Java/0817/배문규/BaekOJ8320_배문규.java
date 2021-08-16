package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  혹시 몰라서 처음부터 set에다가 직사각형을 담았다.
 *  i는 1부터 N까지 그리고
 *  j는 j부터 i*j <= N까지 조건으로
 *  i와 j를 담았다.
 *  
 *  근데 j가 i부터 시작하니까 i, j와 j, i가 중복으로 등장할 수는 없으니 굳이 set이 아니라 list도 무방하다.
 *  
 *  메모리	시간
 *  Set
 *  17452	180
 *  
 *  List
 *  15848	144
 *  
 *  여기서 성능은 List가 더 좋다.
 *  
 */

public class BaekOJ8320_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int N = Integer.parseInt(br.readLine());
		List<int[]> rect = new ArrayList<int[]>();
		for (int i = 1; i <= N; i++) {
			for (int j = i; i*j <= N; j++) {
				rect.add(new int[] {i, j});
			}
		}
		
		System.out.println(rect.size());
	}

}
