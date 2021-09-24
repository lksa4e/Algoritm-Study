import java.io.*;
import java.util.*;

public class Main17281 {
	/* 
	 * 루 계산 방식에 따라 큰 차이가 있었음
	 * int[] lu = new int[4]; 이런식으로 배열 만들어서 했을때
	 * 85540 / 692
	 * int lu1 = 0, lu2 = 0, lu3 = 0 변수로 했을때
	 * 18800 / 448
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int[] perm = new int[] {1,2,3,4,5,6,7,8};
	static int[][] actions;
	static int max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		actions = new int[N][9];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				actions[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		do {
			// 타순 만들기
			int[] order = new int[9];
			for(int i=0; i<9; i++) {
				if(i>3) {
					order[i] = perm[i-1];
				}else if(i<3){
					order[i] = perm[i];
				}else {
					order[i] = 0;
				}
			}
			
			// 경기 진행
			int score = play(order);
			
			// 점수 최댓값 갱신
			if(max<score) max = score;
			
		}while(np(perm));
		System.out.println(max);
	}
	
	private static int play(int[] order) {
		int sum = 0, i = 0;
		for(int[] action:actions) {
            int out = 0, lu1 = 0, lu2 = 0, lu3 = 0, act = 0;
            while(out < 3) {
                act = action[order[i]];
                i = (i+1)%9;
                
                if(act == 0) {
                    out += 1;
                }else if(act == 1) {
                    sum += lu3;
                    lu3 = lu2;
                    lu2 = lu1;
                    lu1 = 1;
                }else if(act == 2) {
                    sum += (lu2+lu3);
                    lu3 = lu1;
                    lu2 = 1;
                    lu1 = 0;
                }else if(act == 3) {
                    sum += (lu1+lu2+lu3);
                    lu1 = lu2 = 0;
                    lu3 = 1;
                }else {
                    sum += (lu1+lu2+lu3+1);
                    lu1 = lu2 = lu3= 0;
                }
            }
        }
		return sum;
	}

	// 다음 큰 순열이 있으면 true, 없으면 false
	private static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		// step1. 꼭대기(i)를 찾는다. 꼭대기를 통해 교환위치(i-1) 찾기
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		
		if(i==0) return false;
		
		// step2. i-1 위치값과 교환할 큰 값 찾기
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) --j;
		
		// step3. i-1 위치값과 j 위치값 교환
		swap(numbers, i-1, j);
		
		// step4. 꼭대기부터 맨뒤까지 내림차순형태의 순열을 오름차순으로 처리
		int k = N-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}