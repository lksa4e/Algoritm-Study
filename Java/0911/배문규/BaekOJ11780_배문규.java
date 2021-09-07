package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 * 플로이드 워셜 문제는 맞는데... 
 * 플로이드 워셜 알고리즘보다 경로를 어떻게 추적하느냐의 문제.
 * 
 * 플로이드 워셜 알고리즘에서 i에서 j로 가는 최적의 경로 k를 발견했하면 그 k를 저장함.
 * 
 * 최종적으로  i에서 j로 가는 최적의 경유지 k가 정해졌을 떄,
 * i에서 k로 가는 또 다른 최적의 경유지 k2를 알 수 있고, 또 i에서 k2로 가는 또 다른 최적의 경유지 k3를 알 수 있게 되고....
 * 최종적으로 더 이상 경유지가 없을 때 까지 재귀적으로 추적할 수 있다.
 * 동일한 메커니즘으로 k에서 j로 가는 중간 최적 경유지 또한 추적할 수 있다.
 * 
 * 그래서 i에서 k로 가는 최적 경유지들 + k + k에서 j로 가는 최적 경유지들을 추적하면
 * i에서 j로 가는 최적 경로의 모든 경유지들을 다 찾을 수 있다.
 * 
 * 시행착오 : 경로를 추출할 때, StringBuilder를 사용해서 charAt을 사용했는데 이건 두자릿수 이상은 불가
 * 
 * 메모리 	시간
 * 53216	856
 */

public class BaekOJ11780_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder result = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E, bus[][], via[][], INF = 100001;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		bus = new int[V+1][V+1]; 
		via = new int[V+1][V+1];
		
		// 맵 입력
		setMap();
		
		// 플로이드 워셜 알고리즘
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(bus[i][j] > bus[i][k] + bus[k][j]) {
						bus[i][j] = bus[i][k] + bus[k][j]; // i에서  j로 갈 때, k를 경유해서 가는게 더 빠르면 갱신 
						via[i][j] = k; // i에서 k까지 가는 최적 경로 + k에서 j로 가는 최적경로의, k 저장
					}
				}
			}
		}
		
		// 맵 출력
		printResult();
		
		// i에서 j로 가는 최적 경로 찾기
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				// 경로가 없거나 자기에게 오는 경로면 0 출력
				if(bus[i][j] == INF || bus[i][j] == 0) result.append(0).append("\n");
				else {
					// i에서 j로 가는 경로가 존재할 때,
					// 중간 경유지가 저장된 sb를 스트링으로 바꾸고 st로 저장.(st는 공백 무시하므로 공백이 아닌 데이터만 골라서 경유지 단위로 분리됨)
					st = new StringTokenizer(find_Via(i, j).toString());
					int viaCnt = st.countTokens(); // 경유지 수
					
					result.append(viaCnt+2).append(" "); // 출발지 + 경유지 + 도착지 수
					result.append(i).append(" "); // 출발지
					for(int t = 0; t < viaCnt; t++) result.append(st.nextToken()).append(" "); // 경유지
					result.append(j).append("\n"); // 도착지
				}
			}
		}
		
		System.out.println(result);
	}
	
	public static void setMap() throws IOException {
		// 자신으로 오는 경로를 제외한 모든 경로를 INF로 초기화 함
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(i == j) continue;
				bus[i][j] = INF;
			}
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(bus[from][to] > weight) bus[from][to] = weight;
		}
	}

	public static void printResult() {
		// 경로가 없으면(INF) 0으로 초기화하고 출력
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(bus[i][j] == INF) bus[i][j] = 0;
				System.out.print(bus[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// i에서 j로 갈 때, 경유했던 곳들
	public static StringBuilder find_Via(int i, int j) {
		// 경유한 곳이 없으면 빈 sb 리턴
		if(via[i][j] == 0) return new StringBuilder();
		
		// 최적 경유지
		int k = via[i][j];
		// i에서 k로 갔던 최적경로 + k + k에서 j로 갔던 최적경로를 재귀로 추적 
		return find_Via(i, k).append(" ").append(k).append(" ").append(find_Via(k, j));
	}
}
