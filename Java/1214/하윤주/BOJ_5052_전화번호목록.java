import java.io.*;
import java.util.*;

/**
 * [1214] BOJ 5052 전화번호 목록
 * 문자열, 트라이, 접두사
 * 
 * sol)
 * 각 전화번호의 숫자를 노드로하는 트라이 자료구조를 활용한다.
 * 0~9의 숫자로 시작하는 전화번호를 각각 0~9의 노드에 링크로 연결하여 전화번호 길이만큼의 노드가 연결된 형태로 만든다.
 * 이때 각 하나의 전화번호 저장이 끝나면 isLeaf라는 boolean형 변수를 통해 각 전화번호 단위(접두사)가 완성되었음을 기록해야한다.
 * 
 * 새로운 전화번호도 위와 같은 방법으로 삽입하는데 각 숫자에 해당하는 노드가 이미 존재하면 다른 전화번호와 중복되는 순서의 전화번호라는 의미이므로,
 * 중복되는 길이까지가 다른 전화번호 단위(접두사)와 겹치는지 isLeaf 변수로 확인한다.
 * 만약 다른 전화번호와 통째로 일치하는 부분이 존재한다면 즉, 접두사가 일치한다면 일관성이 없는 목록으로 판별한다.
 * 
 * 시행착오)
 * - 우선 트라이 자료구조 구현을 기억할리..가 없어서 과거에 정리한 것 보고 풂
 * - 또한 일치하는 접두사인지 확인하기 위해 전화번호의 처음부터 특정 길이까지가 다른 전화번호와 일치하는지만 확인했는데,
 * 접두사가 일치하는 것이 아니라 전화번호의 일부만 일치하는 경우를 잡아내지 못해 틀렸었음..
 * ex)
 * 123
 * 39940
 * 39950
 * - 길이가 더 긴 번호먼저 저장한다면 isLeaf 체크를 해도 미처 접미사로 등록되지 않은 경우는 일치한다고 보지 못하기때문에
 * 전화번호 길이를 오름차순으로 저장한 뒤 트라이로 만들었다.
 * ex)
 * 999999
 * 9999
 *
 */

public class BOJ_5052_전화번호목록 {
	static Node root;            // 트라이 자료구조 루트 노드
	static String[] numbers;     // 전화번호를 길이 오름차순 정렬하기 위한 배열
	
	// 트라이의 각 숫자 노드
	public static class Node {
		Node[] next;         // 현재 숫자 바로 다음 숫자 종류를 저장할 노드 배열(0~9)
		boolean isLeaf;      // 접두사가 완성되었는지 여부
		
		public Node() {
			this.next = new Node[10];     // 0~9로 초기화
			this.isLeaf = false;
		}
	}
	
	// 트라이 구조에 각 노드 삽입
	public static boolean insert(char[] inputNums) {
		Node curNum = root;
		int size = inputNums.length;
		boolean isPrefixSame = false;
		
		// 하나의 전화번호의 각 숫자를 노드로 연결하여 저장
		for (int j=0; j<size; j++) {
			int digit = inputNums[j] - '0';
			
			if (curNum.next[digit] == null) {      // 현재 숫자에 연결된 다음 숫자의 흐름(숫자 순서)이 처음이면
				curNum.next[digit] = new Node();   // 현재 숫자에 해당하는 다음 숫자 노드를 생성하여 연결
			} else {                               // 현재 숫자에 연결된 다음 숫자의 흐름(숫자 순서)이 다른 전화번호에 의해 저장된 적이 있으면
				if (curNum.next[digit].isLeaf) isPrefixSame = true;     // true 반환
			}
			
			curNum = curNum.next[digit];           // 기준이 현재 숫자에서 다음 숫자로 넘어감
		}
		curNum.isLeaf = true;                      // 하나의 전화번호 단위를 다 저장했음을 기록(하나의 접두사 완성) 
		
		return isPrefixSame;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		while(t-->0) {
			// 트라이 루트 노드 생성
			root = new Node();
			int n = Integer.parseInt(br.readLine());
			numbers = new String[n];
			
			// 전화번호를 모두 입력받아 배열에 저장한 뒤, 길이 오름차순으로 정렬
			for (int i=0; i<n; i++) numbers[i] = br.readLine();
			Arrays.sort(numbers, (s1, s2) -> s1.length()-s2.length());
			
			// 모든 전화번호에 대해 트라이에 저장하며 이전에 저장한 전화번호 단위와 일치하는 접두사가 있는지 확인
			boolean consistent = false;
			for (int i=0; i<n; i++) {
				if (insert(numbers[i].toCharArray())) consistent = true;
			}
			
			// 출력
			if (consistent) System.out.println("NO");
			else System.out.println("YES");
		}
	}

}
