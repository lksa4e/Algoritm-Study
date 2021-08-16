import java.util.Scanner;

/**
 * [0817] BOJ 10817 세 수
 *
 * sol) 정렬(선택 정렬)
 * tc) O(N^2)
 */

public class BOJ10817 {
	static int[] nums;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 입력 정수 배열에 저장
		nums = new int[3];
		for (int i=0; i<3; i++) nums[i] = sc.nextInt();
		
		// 선택 정렬
		for (int i=2; i>0; i--) {
			for (int j=0; j<i; j++) {
				if (nums[j] > nums[j+1]) swap(j, j+1);   // 인덱스 작은 수가 더 크면 교환
			}
		}
		
		System.out.println(nums[1]);

	}

	// 원소 교환 메서드
	private static void swap(int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
		
	}

}
