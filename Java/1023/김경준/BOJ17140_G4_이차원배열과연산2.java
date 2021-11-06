import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * G4 BOJ 17140 이차원 배열과 연산
 * 메모리 : 19124kb 시간 : 268ms
 * 
 * R,C 입력값이 만들어진 중간 행렬 범위를 넘어가는 입력이 존재했음,,, 개똥문제
 * 
 * R,C 연산
 * 1. 모든 행 or 열 탐색
 * 	  1. 배열에 들어가 있는 숫자들을 map을 이용해서 빈도수 카운트
 * 	  2. Map을 value 기준 내림차순으로 정렬
 *    3. Map에서 하나씩 꺼내서 arraylist에 담음
 * 2. 모든 행 or 열 중 arraylist size가 가장 큰 기준으로 map 재생성
 * 3. 새로 만든 map에 값 채워주기
 */

public class BOJ17140_G4_이차원배열과연산2 {
	static int R,C,K;
	static int map[][] = new int[3][3];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		while(true) {
			int row_size = map.length;
			int col_size = map[0].length;
			
			//  R,C가 배열 범위를 넘어갈 수 있음... 조건처리해줘야함
			if(R < row_size && C < col_size)
				if(map[R][C] == K) break;
			
			if(row_size >= col_size) R_operation();
			else C_operation();
			
			cnt++;
			
			// 만약 횟수가 100을 넘으면 -1 출력
			if(cnt > 100) {
				cnt = -1;
				break;
			}
		}
		System.out.println(cnt);
	}
	// R 연산
	static void R_operation() {
		int max_col_size = 0;
		int row_size = map.length;
		int col_size = map[0].length;
		
		// 각 행을 재배치하기 위한 list 배열 선언
		List<Integer> list[] = new ArrayList[row_size];
		for(int i = 0; i < row_size; i++) list[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < row_size; i++) {
			// 들어가는 숫자 세기
			Map<Integer, Integer> m = new HashMap<Integer, Integer>();
			for(int j = 0; j < col_size; j++) {
				if(map[i][j] == 0) continue;
				
				if(m.get(map[i][j]) == null) m.put(map[i][j], 1);
				else m.put(map[i][j], m.get(map[i][j]) + 1);
			}
			
			// 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬
			// map을 entry List에 담고 정렬하는 방법
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(m.entrySet());
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
					if(obj1.getValue() == obj2.getValue()) return obj1.getKey().compareTo(obj2.getKey());
					return obj1.getValue().compareTo(obj2.getValue());
				}
			});
			
			// Entry list에 옮겨담기
			for(int j = 0; j < list_entries.size(); j++) {
				list[i].add(list_entries.get(j).getKey());
				list[i].add(list_entries.get(j).getValue());
			}
			// 가장 큰 행 찾기
			max_col_size = Math.max(max_col_size, list[i].size());
		}
		
		if(max_col_size > 100) max_col_size = 100;
		
		// map resize 하고 값 채워넣기
		map = new int[row_size][max_col_size];
		for(int i = 0; i < row_size; i++) {
			for(int j = 0; j < max_col_size; j++) {
				if(j >= list[i].size()) map[i][j] = 0;
				else map[i][j] = list[i].get(j);
			}
		}
	}
	static void C_operation() {
		int max_row_size = 0;
		int row_size = map.length;
		int col_size = map[0].length;
		
		List<Integer> list[] = new ArrayList[col_size];
		for(int i = 0; i < col_size; i++) list[i] = new ArrayList<Integer>();
		
		for(int j = 0; j < col_size; j++) {
			// 들어가는 숫자 세기
			Map<Integer, Integer> m = new HashMap<Integer, Integer>();
			for(int i = 0; i < row_size; i++) {
				if(map[i][j] == 0) continue;
				
				if(m.get(map[i][j]) == null) m.put(map[i][j], 1);
				else m.put(map[i][j], m.get(map[i][j]) + 1);
			}
			
			// 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(m.entrySet());
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
					// 오름 차순 정렬
					if(obj1.getValue() == obj2.getValue()) return obj1.getKey().compareTo(obj2.getKey());
					return obj1.getValue().compareTo(obj2.getValue());
				}
			});
			
			// list에 옮겨담기
			for(int i = 0; i < list_entries.size(); i++) {
				list[j].add(list_entries.get(i).getKey());
				list[j].add(list_entries.get(i).getValue());
			}
			max_row_size = Math.max(max_row_size, list[j].size());
		}
		
		if(max_row_size > 100) max_row_size = 100;
		
		map = new int[max_row_size][col_size];
		for(int j = 0; j < col_size; j++) {
			for(int i = 0; i < max_row_size; i++) {
				if(i >= list[j].size()) map[i][j] = 0;
				else map[i][j] = list[j].get(i);
			}
		}
	}
}
