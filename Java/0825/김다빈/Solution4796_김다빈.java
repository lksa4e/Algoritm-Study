import java.util.Scanner;

/**
 * SW Expert 4796번 의석이의 우뚝 선 산
 *
 * 풀이 : 구현?
 * 1. 구간의 길이가 최소 3이므로 0~N-3까지 탐색 시작
 * 2. 처음 높이가 바로 다음 높이보다 작다면, 구간 탐색 시작
 * 3. 먼저 가장 우뚝선 산의 인덱스(^)를 찾고 그 다음으로 이어지는 첨점(꺾이는 점 v)의 인덱스를 찾는다.
 * 4. 찾은 인덱스들을 기반으로 (i~^) * (^~v) 모든 구간의 개수 계산
 * 5. 첨점 인덱스부터 다시 탐색 시작 
 * 
 * BufferedReader로 입력을 받으니 런타임 에러가 발생!
 * BufferedReader의 버퍼 사이즈가 8192(2^13)라서
 * readLine()으로 읽을 때 버퍼 사이즈보다 큰 입력이 들어오면 에러가 발생하는 듯하다.
 * 
 * 아래 댓글을 보고 Scanner의 nextInt로 끊어서 입력을 받아주니 해결됨.
 * 처음 겪은 에러라 신기했습니다.. 허허 
 * 
 * 107,580 kb	723 ms
 */
public class Solution4796_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_4796.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[] height = new int[N];
			int result = 0;
			
			for(int i=0;i<N;i++) {
				height[i] = sc.nextInt();
			}
			
			for(int i=0;i<N-2;i++) {	// 구간의 길이가 최소 3이므로 (0~N-3) 범위만 체크 
				if(height[i] < height[i+1]) {	// 처음 값이 다음 값보다 작을 때만 구간 구함 
					int maxIndex = i+1;
					
					for(int j=i+2;j<N;j++) {	// 우뚝선 산 인덱스 구하기 
						if(height[maxIndex] < height[j]) maxIndex = j;
						else break;
					}
					
					int minIndex = maxIndex;
					for(int j=minIndex+1;j<N;j++) {	// 첨점 인덱스 구하기
						if(height[minIndex] > height[j]) minIndex = j;
						else break;
					}
					
					// i ~ minIndex까지 구할 수 있는 모든 구간의 개수 구하기 
					result += (maxIndex-i) * (minIndex-maxIndex);
					i = minIndex-1;	// minIndex부터 다시 탐색 시작 
				}
			}
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}

}
