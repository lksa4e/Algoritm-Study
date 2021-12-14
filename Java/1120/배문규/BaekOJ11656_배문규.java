package BaekOJ.study.date1120;

/*
 * 백준  11656 접미사 배열
 * 
 * pq에 문자열을 앞에서 하나씩 기준을 뒤로 넘기며 슬라이싱해서 넣어주고 poll 해서 프린트함
 * 
 * 메모리 	시간
 * 	17708	172
 */

import java.io.*;
import java.util.*;

public class BaekOJ11656_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static PriorityQueue<String> pq = new PriorityQueue<String>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String input = br.readLine();
		
		for(int i = 0; i < input.length(); i++) 
			pq.offer(input.substring(i));
		
		while(!pq.isEmpty()) 
			sb.append(pq.poll()).append("\n");
		
		System.out.println(sb);
	}
}
