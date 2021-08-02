import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo_bj_1654 {
	static int n, k;
	static long[] kLength = new long[10000];
	static long maxLength = 0;
	
	
	static boolean isFit(long lineLen) {
		int sum = 0;
		for (int i = 0; i < k; i++)
			sum += kLength[i] / lineLen;
		
		if(sum >= n)
			return true;
		return false;
	}
	
	
	// 조건을 만족하지 않는 upper bound를 찾는다.
	static long bSearch() {
		long low = 1;
		long high = maxLength, maximumLen = 0;
		long mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			if(isFit(mid)) {
				if(maximumLen < mid)
					maximumLen = mid;
				low = mid + 1;
			} else
				high = mid - 1;
		}
		
		return maximumLen;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < k; i++) {
			kLength[i] = Long.parseLong(br.readLine());
			maxLength = Math.max(maxLength, kLength[i]);
		}
		
		
		System.out.println(bSearch());
	}

}
