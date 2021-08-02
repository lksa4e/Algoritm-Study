import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon1931 {
	static int N;
	static int[][] meetings;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		meetings = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int[] subArr = new int[2];
			for (int j=0; j<2; j++) {
				subArr[j] = Integer.parseInt(st.nextToken());
			}
			meetings[i] = subArr;
		}
		
		Arrays.sort(meetings, Comparator.comparingInt(x -> x[0]));
		Arrays.sort(meetings, Comparator.comparingInt(x -> x[1]));
		
		/*
		Arrays.sort(meetings, (x, y) -> {
			if (x[1] == y[1]) {
				return Integer.compare(x[0], y[0]);
			} else {
				return Integer.compare(x[1], y[1]);
			}
		});
		*/
		System.out.println(countMeetings());
		
	}
	
	static int countMeetings() {
		int cnt = 1;
		int endTime = meetings[0][1];
		for (int i=1; i<N; i++) {
			if (meetings[i][0] >= endTime) {
				endTime = meetings[i][1];
				cnt++;
			}
		}
		return cnt;
	}
}
