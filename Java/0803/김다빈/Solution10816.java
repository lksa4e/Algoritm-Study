import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution10816 {

	static int[] card;
	static int[] number = new int[20000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		card = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		int num;
		for(int i=0;i<N;i++) {
			num = Integer.parseInt(st.nextToken());
			card[i] = num;
			number[10000000 - num]++;
			
		}
		Arrays.sort(card);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0;i<M;i++) {
			num = Integer.parseInt(st.nextToken());
			if(binarySearch(num)) {
				sb.append(number[10000000 -num]+" ");
			} else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
		
	}

	public static boolean binarySearch(int num) {
		int left = 0;
		int right = card.length - 1;
		
		while(left <= right) {
			int midIndex = (left+right)/2;
			int mid = card[midIndex];
			
			if(num < mid) right = midIndex - 1;
			else if(num > mid) left = midIndex + 1;
			else return true;
		}
		return false;
	}
}
