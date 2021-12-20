import java.io.*;
import java.util.*;

/**
 * [1214] BOJ 13013 접미사 배열
 * 문자열, 구현
 * 
 * sol)
 * - 접미사 배열에따라 인덱스 위치에 문자 하나씩 오름차순으로 삽입한다(A,B,C...)
 * ex)
 * 3012 -> bcda
 * - 우선순위가 1인 문자부터 우선순위 0번 문자로 대치한 다음 접미사 배열을 구하여 순서가 바뀌지 않는다면 문자 대치
 * ex)
 * bcda -> acda
 * - 마지막 우선순위 문자까지 진행한 뒤 몇개의 문자가 바뀌었는지 카운트
 * 
 * 시행착오)
 * 문자를 하나씩 대치할때마다 다시 접미사 배열을 구해 우선순위를 구하므로 효율이 매우 좋지 않아보임..
 * 더 좋은 방법이 있을까요? 코드리뷰가 기대되는 문제입니다!
 *
 */

public class BOJ_13013_접미사배열 {
	static int N, primaryCharCnt;    // 필수로 필요한 문자 개수(최종 정답)
	static char[] chars;             // 접미사 배열에따라 인덱스를 문자로 대치할때 문자가 저장되는 배열
	static int[] inputIndex;         // 입력으로 주어진 접미사 배열
	static String stringifiedIdx;    // 입력으로 주어진 접미사 배열을 문자열화
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		primaryCharCnt = N;
		chars = new char[N];
		inputIndex = new int[N];
		stringifiedIdx = br.readLine();
		StringTokenizer st = new StringTokenizer(stringifiedIdx);
		
		// 접미사 배열 입력
		for (int i=0; i<N; i++) {
			int idx = Integer.parseInt(st.nextToken());
			inputIndex[i] = idx;         // 접미사 배열 저장
			chars[idx] = (char)(i+65);   // 접미사 배열에 따라 문자로 대치하여 문자 저장
		}
		String str = new String(chars);  // 대치된 문자를 붙여 문자열로 만듦
		
		// 1번 인덱스부터 문자를 대치해봄
		for (int i=1; i<N; i++) {
			char frontChar = str.charAt(inputIndex[i-1]);    // 사전순으로 앞에오는 문자. 대치되면 될 타겟 문자
			char rearChar = str.charAt(inputIndex[i]);       // 사전순으로 뒤에오는 문자. 대치되어야할 문자
			
			// 사전순으로 뒤에있는 문자를 앞에있는 문자로 대치한 뒤 해당 문자열의 접미사들 우선순위 구함
			String sortedIdx = sortSubString(str.replace(rearChar, frontChar));
			
			// 대치 결과 접미사 배열이 바뀌는지 확인
			if (stringifiedIdx.equals(sortedIdx)) {
				str = str.replace(rearChar, frontChar);    // 바뀌지 않는다면 대치한 것으로 저장
				primaryCharCnt--;                          // 필수 문자 개수는 1개씩 감소
			}
		}
		
		// 필수 문자 개수 출력
		System.out.println(primaryCharCnt);
	}
	
	// 접미사 배열 계산
	private static String sortSubString(String s) {
		ArrayList<String> originPrefixs = new ArrayList<String>();    // 문자열의 각 인덱스별 접미사를 저장할 리스트
		ArrayList<String> sortedPrefixs = new ArrayList<String>();    // 문자열의 접미사들을 정렬하여 저장할 리스트
		
		for (int size=s.length(), i=0; i<size; i++) {
			originPrefixs.add(s.substring(i, size));
			sortedPrefixs.add(s.substring(i, size));
		}
		Collections.sort(sortedPrefixs);          // 접미사 정렬
		
		StringBuilder sb = new StringBuilder();
		// 접미사를 정렬한 결과를 바탕으로 각 접미사의 우선순위를 구하여 문자열 형태로 저장
		for (String str : sortedPrefixs) sb.append(originPrefixs.indexOf(str)).append(" ");
		sb.setLength(sb.length()-1);
		
		return sb.toString();      // 문자열 형태의 접미사 배열 우선순위를 반환
	}
}
