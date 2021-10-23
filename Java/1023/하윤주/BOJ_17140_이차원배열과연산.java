import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * [1023] BOJ 17140 이차원 배열과 연산
 * 구현, 맵, 정렬
 * 
 * sol)
 * 1. 각 행이나 열을 기준으로 저장된 수와 등장 횟수를 쌍으로 묶어 map에 저장. 즉, 한 행이나 열 당 (1차원 배열 당) 1개의 맵을 사용
 * 2. 맵을 value 기준으로 오름차순 정렬한 다음 key를 기준으로 오름차순 정렬(등장횟수가 커지는 순 -> 등장횟수 같으면 수가 커지는 순)
 * 3. 맵을 1차원 배열로 생각했으니 정렬된 이후 다시 원본 배열 각 행이나 열에 업데이트 하기
 * 
 * 맵을 정렬하는 방법을 새롭게 배웠습니다... 정말 절실하게 파이썬이 그리워지던 문제였습니다ㅜㅜ 파이썬 딕셔너리 정렬이 정말 편했는데 다 까먹은 것 같기도 하네요 ㅎㅎ
 * 
 */

public class BOJ_17140_이차원배열과연산 {
	static int r, c, k, rLen, cLen;
	static int[][] array = new int[100][100];
	static Map<Integer, Integer> num = new HashMap<Integer, Integer>();
	static List<Map.Entry<Integer, Integer>> entries;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) array[i][j] = Integer.parseInt(st.nextToken());
		}
		
		rLen = cLen = 3;     // 최초 배열 행, 열 길이
		calcArray();         // 배열 연산 시작
	}

	// 배열 연산
	private static void calcArray() {
		for (int t=0; t<=100; t++) {         // 최대 100초 동안 지속
			if (array[r][c]==k) {            // 원하는 좌표 원하는 값 채웠으면 종료
				System.out.println(t);
				return;
			}
			
			if (rLen>=cLen) cLen = rearrange(rLen, cLen);     // R 연산
			else {                                            // C 연산
				turnArray();                     
				rLen = rearrange(cLen, rLen);                 // 연산 전후로 배열 뒤집기
				turnArray();
			}
		}
		
		System.out.println(-1);              // 100초 동안에도 원하는 값 못 채웠으면 예외처리
	}

	// 배열의 R와 C연산
	private static int rearrange(int outer, int inner) {
		int max = 0;
		for (int i=0; i<outer; i++) {
			num.clear();
			
			for (int j=0; j<inner; j++) {
				int key = array[i][j];       // 각 행이나 열의 수
				if (key==0) continue;        // 0인 경우는 정렬 대상 아님
				
				if (num.containsKey(key)) num.put(key, num.get(key)+1);     // 맵에 저장된 수이면 등장 횟수 +1 해주고
				else num.put(key, 1);                                       // 맵에 저장된 적 없는 수이면 등장을 1로 기록
			}
			max = Math.max(max, num.size()*2);       // 정렬 이후 행이나 열의 길이는 (맵의 원소 개수*2)(수, 등장횟수 쌍으로 늘 2개씩 증가)한 값으로 업데이트 
			sortMap();                               // 각 행이나 열을 정렬
			updateArray(i);                          // 정렬된 값으로 배열 업데이트
		}
		return max;
	}

	// 새롭게 정렬한 1차원 배열(맵)로 각 행이나 열을 업데이트
	private static void updateArray(int x) {
		Arrays.fill(array[x], 0);
		
		int y = 0;
		for (Entry<Integer, Integer> e : entries) {     // 정렬이 완료된 1차원 배열(맵)을 기준으로 각 행이나 열 업데이트
			if (y>=100) break;
			array[x][y++] = e.getKey();
			array[x][y++] = e.getValue();
		}
	}
	
	// 각 행이나 열 정렬(맵 정렬)
	private static void sortMap() {
		entries = new ArrayList<Map.Entry<Integer,Integer>>(num.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {      // Comparator 인터페이스의 compare() 메서드 오버라이딩

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				if (o1.getValue()==o2.getValue()) return o1.getKey()-o2.getKey();       // 1차적으로 등장횟수 오름차순으로 정렬하되, 등장횟수가 같으면 해당 수를 오름차순 정렬
				return o1.getValue()-o2.getValue();
			}
		});
	}
	
	// C 연산을 위해 배열 뒤집는 메서드
	private static void turnArray() {
		int[][] newArray = new int[100][100];
		int size = Math.max(rLen, cLen);
		// 대칭으로 뒤집기
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) newArray[i][j] = array[j][i];
		}
		// 복사하기
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) array[i][j] = newArray[i][j];
		}
	}

}
