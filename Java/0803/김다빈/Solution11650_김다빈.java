package Problem0807;

import java.util.Arrays;
import java.util.Scanner;

public class Solution11650_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] list = new int[N][2];
		
		for(int i=0;i<N;i++) {
			list[i][0] = sc.nextInt();
			list[i][1] = sc.nextInt();
		}
		
		Arrays.sort(list, (o1,o2)->{
			if(o1[0]==o2[0]) {
				return Integer.compare(o1[1], o2[1]);
			} else {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		for(int i=0;i<N;i++) {
			System.out.println(list[i][0]+" "+list[i][1]);
		}
	}

}
