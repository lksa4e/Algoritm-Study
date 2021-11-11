package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 1620 나는야 포켓몬 마스터 이다솜
 * 
 * 이거 혹시 시뮬문제였었나? 하고 문제를 굉장히 꼼꼼히 읽었었는데 놀랍게도 마지막줄 빼고는 1도 의미가 없었음
 * 그냥 맵 사용해서 간단하게 풀 수있었다
 * 
 * 메모리 	시간
 * 57136	584
 * 
 */
public class BaekOJ1620_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;

	static int N, M;
	static HashMap<String, String> getPocketMon = new HashMap<String, String>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			String pocketMon = br.readLine();
			getPocketMon.put(i+"", pocketMon);
			getPocketMon.put(pocketMon, i+"");
		}
		
		for(int i = 0; i < M; i++) sb.append(getPocketMon.get(br.readLine())).append("\n");
		
		System.out.println(sb);
	}
}
