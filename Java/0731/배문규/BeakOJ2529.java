package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BeakOJ2529 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> result = new ArrayList<Integer>();
    static boolean[] check = new boolean[10];
    static long _max = Long.MIN_VALUE;
    static long _min = Long.MAX_VALUE;
    static String _maxS;
    static String _minS;
    
	public static void main(String[] args) throws NumberFormatException, IOException {
        int N = Integer.parseInt(br.readLine());
        String[] sign = br.readLine().split(" ");
        backtracking(0, N, sign);
        System.out.println(_maxS+"\n"+_minS);
	}
	
	public static void backtracking(int idx, int n, String[] sn) {
		if(idx == n) {
			StringBuilder sb = new StringBuilder();
			for(int i : result) sb.append(i);
			long num = Long.parseLong(sb.toString());
			if(_max < num) {
				_max = num;
				_maxS = sb.toString();
			}
			if(_min > num) {
				_min = num;
				_minS = sb.toString();
			}
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			if(check[i]) continue;
			
			if(result.size() == 0) {
				result.add(i);
				check[i] = true;
				backtracking(idx, n, sn);
				result.remove(idx);
				check[i] = false;
			}
			else if(sn[idx].equals("<")) {
				if(result.get(idx) < i) {
					result.add(i);
					check[i] = true;
					backtracking(idx+1, n, sn);
					result.remove(idx+1);
					check[i] = false;
				}
			}
			else if(sn[idx].equals(">")){
				if(result.get(idx) > i) {
					result.add(i);
					check[i] = true;
					backtracking(idx+1, n, sn);
					result.remove(idx+1);
					check[i] = false;
				}
			}
		}
	}
	
}