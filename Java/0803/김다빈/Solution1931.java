import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Solution1931 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] list = new int[N][2];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(o1[1] == o2[1]){
                    return Integer.compare(o1[0], o2[0]);	// 시작 시간 순 
                }

                return Integer.compare(o1[1], o2[1]);
			}
		});
		
		int end = -1, count = 0;
		for(int i=0;i<N;i++) {
			if(list[i][0] >= end) {
				end = list[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}

}
