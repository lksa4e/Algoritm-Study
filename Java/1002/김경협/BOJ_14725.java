import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * BOJ 14725번 개미굴
 * 
 * 약간 그래프 탐색? 같은 문제
 * 현재 굴의 이름과, 아래로 가는 링크를 지닌 클래스를 만들었다.
 * root가 개미굴 입구가 되고 Input을 받으면서 개미굴을 내려가며 데이터를 채웠다.
 * 
 * 	15,532KB	172ms
 */

public class BOJ_14725 {
	static class Floor implements Comparable<Floor> {
		String name;
		ArrayList<Floor> under;
		public Floor(String name) {
			this.name = name;
			this.under = new ArrayList<>();
		}
		@Override
		public int compareTo(Floor o) {
			return this.name.compareTo(o.name);
		}
	}

	static int N;
	static ArrayList<Floor> root;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		root = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int depth = Integer.parseInt(st.nextToken());
			ArrayList<Floor> curr = root;	// 시작점은 루트부터
			for (int j = 0; j < depth; j++) {
				String name = st.nextToken();	// 이름 받고
				
				Floor next = null;
				// name이 curr에 이미 있는지 확인
				boolean isExist = false;
				int indexOfNextFloor = 0;
				for (indexOfNextFloor = 0; indexOfNextFloor < curr.size(); indexOfNextFloor++) {
					if(curr.get(indexOfNextFloor).name.equals(name)) {
						isExist = true;
						break;
					}
				}
				if(!isExist) {	// 없으면 추가하기
					next = new Floor(name);
					curr.add(next);
				} else {	// 있으면 추가 안하고 원래 있떤 것의 link만 가져다 주기
					next = curr.get(indexOfNextFloor);
				}
				curr = next.under;
			}
		}
		
		printCave(root, 0);
		System.out.println(sb);
	}
	
	static void printCave(ArrayList<Floor> parent, int cnt) {
		Collections.sort(parent);
		
		for(Floor curr : parent) {
			for (int i = 0; i < cnt; i++)
				sb.append("--");
			sb.append(curr.name).append("\n");
			printCave(curr.under, cnt+1);
		}
	}

}
