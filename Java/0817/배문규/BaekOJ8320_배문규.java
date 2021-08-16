package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  i는 1부터 N까지 그리고
 *  j는 i부터 i*j <= N까지 조건이면 N개로 직사각형의 모든 조합을 찾을 수 있음
 *  cnt++
 *  
 *  메모리		시간
 *  14072	  128
 */

public class BaekOJ8320_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i; i*j <= N; j++) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
