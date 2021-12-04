import java.io.*;
import java.util.*;

/**
 * 백준 20055번 컨베이어 벨트 위의 로봇
 * 
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
 * 		로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 * 
 * 17580KB	308ms
 */
public class Main20055_컨베이어벨트위의로봇 {
	
	static int N, K;
	static int zeroCnt;
	static int[] durability;
	static ArrayList<Integer> belt = new ArrayList<Integer>();
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[2*N];
		durability = new int[2*N];
		
		// 내구도 입력 받기 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			belt.add(i);
			durability[i] = Integer.parseInt(st.nextToken());
		}
		
		int turn = 1;
		while(true) {
			moveRobot();
			
			if(zeroCnt >= K) {	// 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료
				System.out.println(turn);
				break;
			}
			
			turn++;
		}
	}

	private static void moveRobot() {
		// 벨트 한칸 회전 
		int num = belt.remove(2*N-1);
		belt.add(0, num);
		
		// 올리는 위치, 내리는 위치 계산 
		int up = belt.get(0);
		int down = belt.get(N-1);
		
		// 내리는 위치에 로봇이 있으면 즉시 내리기 
		if(visited[down]) visited[down] = false;
		
		// 가장 먼저 벨트에 올라간 로봇부터 이동 
		for (int i = N-2; i >= 0; i--) {
			int cur = belt.get(i);
			int next = belt.get(i+1);
			
			// 현재 로봇이 위치해있고 다음 이동할 곳에 로봇이 없고 내구성도 1 이상이면 이동
			if(visited[cur] && !visited[next] && durability[next] >= 1) { 
				visited[cur] = false;
				visited[next] = true;
				durability[next]--;
				
				if(durability[next] == 0) zeroCnt++;	// 내구성이 0이면 zeroCnt++
			}
		}
		
		// 내리는 위치에 로봇이 있으면 즉시 내리기 
		if(visited[down]) visited[down] = false;
		
		// 올리는 위치에 로봇 올리기 
		if(!visited[up] && durability[up] >= 1) {
			visited[up] = true;
			durability[up]--;
			
			if(durability[up] == 0) zeroCnt++;	// 내구성이 0이면 zeroCnt++
		}
	}
	
}
