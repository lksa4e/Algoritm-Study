import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 20055번 컨베이어 벨트 위의 로봇
 * 
 * circular로 배열 돌리는 문제
 * 인덱스 관리하는게 꽤 까다로웠다.
 * 
 * circular 인덱스 처리할 때 -1 처리한 다음에 % 쓰기 힘들어서
 * 아예 start와 end 그리고 배열을 역순으로 받은 다음에 인덱스 + 1 % N*2 처리했다.
 */
public class BOJ_20055 {

	static int N, K, belt[][], start, end, firstRobot, robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[N * 2][2];
		st = new StringTokenizer(br.readLine());
		for (int i = N*2 -1; i >= 0; i--) {
			belt[i][0] = Integer.parseInt(st.nextToken());	// 역순으로 받아주기
		}
		
		start = 2* N - 1;	// start가 맨 끝부분
		end = N;			// end가 start - N
		
		System.out.println(beltOn());
			
	}
	
	static int beltOn() {
		/*
		* 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
		* 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
		* 		로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
		* 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
		* 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
		*/
		int step = 1;
		
		while(true) {
			// 1. 배열은 가만히 있고 컨베이어의 start부분과 end부분이 이동
			start = (1+start) % (2*N);
			end = (1+end) % (2*N);
			
			if(belt[end][1] == 1) {	// 이동한 뒤, 끝 부분에 도착한 로봇이 있으면 처리해주기
				belt[end][1] = 0;
			}
			
			// 2. 로봇 이동, end 부터 start까지 이동하면서 로봇을 한 칸씩 이동시키기
			int searchStart = (end + 1) % (2*N);
			int searchEnd = (start + 1) % (2*N);
			for (int i = searchStart; i != searchEnd; i = (i+1) % (2*N)) {
				if(belt[i][1] == 0) continue;	// 그 칸에 로봇이 없을 경우
				int nextIdx = i - 1;
				if(nextIdx < 0) nextIdx = 2 * N - 1;
				if(belt[nextIdx][0] == 0) continue;	// 로봇이 이동할 칸의 내구도가 0인 경우
				if(belt[nextIdx][1] != 0) continue; // 로봇이 이동할 칸에 다른 로봇이 있는 경우
				belt[i][1] = 0;
				belt[nextIdx][1] = 1;
				belt[nextIdx][0]--;
			}
			if(belt[end][1] == 1) {		// 로봇이 이동한 뒤, 끝 단에 도착한 로봇 처리
				belt[end][1] = 0;
			}
			
			// 3. 시작 부분에 로봇 놓기, 근데 내구도가 0이 아닐 경우에만,
			if(belt[start][0] != 0) {
				belt[start][0]--;
				belt[start][1] = 1;
			}
			
			// 4. 벨트에서 내구도 0인 칸 체크
			int countZero = 0;
			for (int i = 0; i < 2*N; i++) {
				if(belt[i][0] == 0) countZero++;
			}
			if(countZero >= K) return step;
			step++;
		}
	}
	
}
