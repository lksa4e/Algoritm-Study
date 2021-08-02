import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1654 {

	static int[] line;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		line = new int[K];
		for(int i=0;i<K;i++) {
			line[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(line);
		long max = line[K-1];
		long min = 1;
		
		System.out.println(binarySearch(max, min));
	}
	
	public static long binarySearch(long max, long min) {
		while(max >= min) {
			long mid = (max+min)/2;
			
			if(checkLan(mid)) {
				min = mid+1;
			} else {
				max = mid-1;
			}
		}
		return max;
	}
	
	public static boolean checkLan(long num) {
		long sum = 0;
		for(int i : line) {
			sum += i/num;
		}
		if(sum >= N) {
			return true;
		}
		return false;
	}
}
