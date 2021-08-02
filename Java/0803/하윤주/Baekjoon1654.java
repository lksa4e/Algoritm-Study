import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1654 {
	static int K, N;
	static int[] lan;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lan = new int[K];
		for (int i=0; i<K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lan);
		System.out.println(binarySearch(1, lan[lan.length-1]));
	}

	private static long binarySearch(long start, long end) {
		while(start <= end) {
			long mid = (long)(start + end) / 2;
			
			if (cutLan(mid) >= N) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return end;
	}
	
	private static int cutLan(long size) {
		int cnt = 0;
		for (int l:lan) {
			cnt += (l / size);
		}
		return cnt;
	}
}
