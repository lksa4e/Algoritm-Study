package study.date0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Permutation {
    private int n;
    private int[] now; 
    private List<List<int[]>> result; 
    private int _max = Integer.MIN_VALUE;
    
    public int getResult() {
        return _max;
    }

    public Permutation(int n, List<List<int[]>> result) {
        this.n = n;
        this.now = new int[n];
        this.result = result;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void permutation(int[] arr, int depth) {
        if (depth == n) {
            int sum = 0;
            int idx = 0;
            for(List<int[]> j : result) {
            	for(int[] k : j) {
            		sum += now[idx] * k[1] * Math.pow(10, k[0]);
            	}
            	idx++;
            }
            _max = Math.max(_max, sum);

            return;
        }
        
        for (int i = depth; i < n; i++) {
            swap(arr, i, depth);
            now[depth] = arr[depth];
            permutation(arr, depth + 1);
            swap(arr, i, depth);
        }
    }
}

public class BeakOJ1339 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<String> sArr = new ArrayList<String>();
		for(int i = 0; i < N; i++) sArr.add(br.readLine());
		
		int[][] alpha = new int[26][8];
		for(String str : sArr) {
			for(int i = 0; i < str.length(); i++) {
				alpha[(int)str.charAt(i)-65][str.length()-i-1]++;
			}
		}
		
		List<List<int[]>> result = new ArrayList<List<int[]>>();
		for(int i = 0; i < alpha.length; i++) {
			List<int[]> res = new ArrayList<int[]>();
			for(int j = 0; j < alpha[0].length; j++) {
				if(alpha[i][j] == 0) continue;
				res.add(new int[] {j, alpha[i][j]});
			}
			if(res.size() != 0) result.add(res);
		}

        Permutation perm = new Permutation(result.size(), result);
        perm.permutation(Arrays.copyOfRange(arr, 0, result.size()), 0);
        System.out.println(perm.getResult());
    }
}

/*
 
반례 || A:8, B:9 (1790) > A:9, B:8(1780) 

10
ABB
BB
BB
BB
BB
BB
BB
BB
BB
BB
 
 public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		List<String> sArr = new ArrayList<String>();
		for(int i = 0; i < N; i++) sArr.add(br.readLine());
		
		int[][] alpha = new int[26][9];
		for(String str : sArr) {
			for(int i = 0; i < str.length(); i++) {
				alpha[(int)str.charAt(i)-65][9 - (str.length()-i)]++;
			}
		}
		
		Integer[] result = new Integer[26];
		for(int i = 0; i < alpha.length; i++) {
			sb.setLength(0);
			for(int j = 0; j < alpha[0].length; j++) {
				sb.append(alpha[i][j]);
			}
			if(sb.toString().equals("0")) continue;
			result[i] = Integer.parseInt(sb.toString());
		}
		
		Arrays.sort(result, Collections.reverseOrder());
		
		int sum = 0;
		for(int i = 0; i < result.length; i++) {
			sum += result[i]*(9-i);
		}
		
		System.out.println(sum);
	}
}
 
*/
