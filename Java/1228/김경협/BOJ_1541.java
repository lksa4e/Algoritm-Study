import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * BOJ 1541번 잃어버린 괄호
 * 
 * 메모이제이션, 재귀
 * 
 * 식에 +와 -만 있다는 것에 착안하여 -를 기준으로 최댓값, 최솟값을 구해줬다.
 * 예를 들어 A-B가 있으면 이 값을 최소화 하기 위해서는 A를 최소화하고 B를 최대화하면 된다.
 * 
 * 그래서 들어온 수식의 최댓값을 구하는 기능과 최솟값을 구하는 기능을 구현한 뒤,
 * top-down 방식으로 구현했다.
 * 
 * 14472KB	132ms
 */

public class BOJ_1541 {
	static Map<String, Integer> maxMap;	// Memoization, 이미 계산해본 수식이 있으면 재사용
	static Map<String, Integer> minMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		maxMap = new HashMap<String, Integer>();
		minMap = new HashMap<String, Integer>();

		System.out.println(find(str, true));
	}

	private static int find(String str, boolean isFindMin) {
		if(isFindMin && minMap.containsKey(str))
			return minMap.get(str);
		
		if(!isFindMin && maxMap.containsKey(str))
			return maxMap.get(str);

		int target = isFindMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		boolean isLeaf = true;
		
		for (int i = 0, size = str.length(); i < size; i++) {
			if(str.charAt(i) == '-') {		// 최소를 찾을 경우, A-B 를 기준으로 A를 최소화, B를 최대화
				isLeaf = false;				// 최대를 찾을 경우, A+B 를 기준으로 A를 최대화, B를 최소화
				int val = 0;
				if(isFindMin)
					val = find(str.substring(0, i), true) - find(str.substring(i+1, size), false);
				else
					val = find(str.substring(0, i), false) - find(str.substring(i+1, size), true);
				
				if(isFindMin) {
					if(val < target)
						target = val;
				} else {
					if(val > target)
						target = val;
				}
			}
		}
		
		if(isLeaf) {	// -파트는 모두 계산했기 때문에 +파트를 계산해주기
			StringTokenizer st = new StringTokenizer(str, "+");
			int val = 0;
			while(st.hasMoreTokens()) {
				val += Integer.parseInt(st.nextToken());
			}
			target = val;
		}
		
		if(isFindMin)
			minMap.put(str, target);
		else
			maxMap.put(str, target);
		
		return target;
	}
}
