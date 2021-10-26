import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

/**
 * [1026] BOJ 19239 스타트택시
 * 시뮬레이션, bfs
 * 
 * sol)
 * // 로직 1. 큐에 초기 택시 위치 삽입한 뒤 bfs로 4방 탐색하면서 최단 이용객 찾는다
		// - 너비(depth)마다 확인하며 이용객을 찾는다.(1보다 큼)
		// - 이용객 못찾았는데 연료 바닥났으면 -1 반환 종료
		// - 만약 이용객 찾았으면 바로 방문하지 않고 같은 너비에 대한 탐색 끝날때까지 다른 이용객이 있는지도 확인하기 위해 리스트에 넣는다.
		// - 같은 너비에 대한 탐색 끝났고 찾은 이용객이 1명 이상이라면 좌표 인덱스가 작은 이용객을 선택 -> PQ
		// - 이용객을 찾기까지의 소요된 시간은 탐색한 너비 크기. 이 너비 크기가 남은 연료보다 크면 즉시 종료하고 -1 반환
		
		// 로직 2. 지금부터는 현재 이용객에 대응되는 도착 좌표 찾는다. 역시나 너비 저장하여 이동거리 계산
		// - 도착지 못찾았는데 연료 바닥났으면 -1 반환 종료
		// - 도착지 찾았으면 지금까지 쓴 연료(너비)*2해서 남은 연료에 더함
		// - 승객수 -1 하고 남은 승객수 확인하여 0이면 현재 연료 출력
		// - 승객수 0 초과이면 다시 반복
 *
 */

public class BOJ_19239_스타트택시 {
	static int N, M, fuel;
	static int[][] map;
	static int[] destinations;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int taxiX, taxiY;
	static Queue<Integer> customerQ = new ArrayDeque<>();
	static Queue<Integer> destQ = new ArrayDeque<>();
	static boolean[][] visited;
	static PriorityQueue<Integer> customerList = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		// 벽 정보 저장
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 택시 초기 위치
		st = new StringTokenizer(br.readLine());
		taxiX = Integer.parseInt(st.nextToken())-1;
		taxiY = Integer.parseInt(st.nextToken())-1;
		
		// 지도에 승객 위치 저장, 도착지 정보는 각 승객을 인덱스로 하는 배열에 좌표값으로 저장
		destinations = new int[2+M];
		for (int i=2; i<2+M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
			destinations[i] = (Integer.parseInt(st.nextToken())-1) * N + (Integer.parseInt(st.nextToken())-1);
		}
		
		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		// 도착 전에 연료 바닥나는지 확인하여 예외처리
		if (taxiTour()) System.out.println(fuel);
		else System.out.println(-1);
	}
	
	// 택시 출발
	public static boolean taxiTour() {
		while(M>0) {
			if (!findCustomer()) return false;		// 승객 태우고
			if (!findDestination()) return false;	// 도착지로 가기
			if(--M<=0) return true;					// 이 과정에서 연료 남은 채로 승객 다 태웠으면 성공
		}
		return true;
	}
	
	// 승객 찾는 메서드(bfs)
	public static boolean findCustomer() {
		if (map[taxiX][taxiY]>1) return true;		// 초기 택시의 위치와 승객의 위치가 같은 경우
		
		customerList.clear();
		customerQ.clear();
		customerQ.offer(taxiX);
		customerQ.offer(taxiY);
		visited = new boolean[N][N];
		visited[taxiX][taxiY] = true;
		
		while(!customerQ.isEmpty()) {
			int size = customerQ.size()/2;
			
			while(size-->0) {					// 같은 너비에 대해 bfs
				int x = customerQ.poll();		// 택시 위치
				int y = customerQ.poll();
				
				for (int i=0; i<4; i++) {		// 4방 탐색하면서
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					// 경계 벗어나거나 이미 방문했거나 벽이면 pass
					if (!isInside(nx, ny) || visited[nx][ny] || map[nx][ny]==1) continue;
					
					// 승객을 만났다면 pq에 승객 위치 삽입하여 이번 너비 탐색 끝날때까지 대기
					if (map[nx][ny]>1) customerList.offer(nx*N+ny);
					customerQ.offer(nx);
					customerQ.offer(ny);
					visited[nx][ny] = true;
				}
			}
			--fuel;								// 1칸 이동으로 연료 1 소진
			
			if (customerList.size()==0) {		// 만약 이번 너비 탐색에서 승객을 1명이라도 만났으면
				if (fuel<=0) return false;		// 연료 없으면 실패
			} else {
				int p = customerList.poll();
				taxiX = p/N;					// 연료 남아있었으면 택시를 승객 위치로 이동
				taxiY = p%N;
				return true;
			}
		}
		return false;
	}
	
	// 도착지 찾는 메서드(bfs)
	public static boolean findDestination() {
		int targetDest = destinations[map[taxiX][taxiY]];		// 승객의 위치를 기준으로 대응되는 도착지 좌표 뽑기
		int destX = targetDest/N;
		int destY = targetDest%N;
		map[taxiX][taxiY] = 0;					// 승객이 있던 곳은 빈칸으로 만들어주고
		
		destQ.clear();
		destQ.offer(taxiX);
		destQ.offer(taxiY);
		visited = new boolean[N][N];
		visited[taxiX][taxiY] = true;
		
		int distance = 0;
		while(!destQ.isEmpty()) {
			int size = destQ.size()/2;
			
			while(size-->0) {					// 같은 너비에 대해 bfs
				int x = destQ.poll();			// 승객이 있던 곳부터 택시 출발
				int y = destQ.poll();
				
				for (int i=0; i<4; i++) {		// 4방 탐색하며
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					// 경계 벗어나거나 이미 방문했거나 벽이면 pass
					if (!isInside(nx, ny) || visited[nx][ny] || map[nx][ny]==1) continue;
					
					// 대응되는 도착지 찾았으면
					if (nx==destX && ny==destY) {
						fuel += ((distance+1) * 2) - 1;		// 연료 충전하고 탐색 성공
						taxiX = nx;
						taxiY = ny;
						return true;
					}
					destQ.offer(nx);
					destQ.offer(ny);
					visited[nx][ny] = true;
				}
			}
			++distance;
			if(--fuel<=0) return false;		// 만약 도착지 찾기 전에 연료 다썼으면 실패
		}
		return false;
	}
	
	// 경계 체크
	public static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
