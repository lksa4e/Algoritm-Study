import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20055_S1_컨베이어벨트위의로봇 {
	static int N,K, start_point, end_point, die = 0, belt_health[];
	static boolean belt_robot[];
	static Queue<Integer> robots = new ArrayDeque<Integer>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt_health = new int[2*N];
		belt_robot = new boolean[2*N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 * N; i++) {
			belt_health[i] = Integer.parseInt(st.nextToken());
		}
		
		start_point = 0;
		end_point = N-1;
		int level = 1;
		while(true) {
			// 벨트 회전
			rotateBelt();
			
			// 가장 먼저 올라간 로봇부터 이동
			robotMove();
			
			// 올리는 위치에 내구도가 0이 아니면 올리는 위치에 로봇을 올림
			robotAdd();
			
			// 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
			if(die >= K) break;
			
			level++;
		}
		System.out.println(level);
	}
	static void rotateBelt() {
		// 올리는 지점, 내리는 지점을 바꾸는것으로 회전된것처럼 사용
		start_point = start_point == 0 ? 2*N-1 : start_point - 1;
		end_point = end_point == 0 ? 2*N-1 : end_point - 1;
	}
	static void robotMove() {
		int size = robots.size();
		for(int i = 0; i < size; i++) {
			// 일단 뽑고 내리는 지점이면 continue, 아니면 다시 offer
			int cur_robot = robots.poll();
			belt_robot[cur_robot] = false;
			
			// 회전하는것을 단순하게 올리는지점, 내리는지점만 바꾸었으니 회전한 이후에 내리는 지점이 되었는지를 여기서 체크해줘야함
			// 이부분을 생각 못해서 계속 틀림....
			if(cur_robot == end_point) continue;
			
			int next_pos = (cur_robot == 2*N-1) ? 0 : cur_robot+ 1;
			
			// 다음 자리로 이동 가능한 경우
			if(belt_health[next_pos] > 0 && belt_robot[next_pos] == false) {
				belt_health[next_pos]--;
				if(belt_health[next_pos] == 0) die++;
				
				// 내리는 지점으로 도착하면 queue에 다시 안넣어주고 continue
				if(next_pos == end_point) continue;
				
				belt_robot[next_pos] = true;
				robots.offer(next_pos);
			}
			// 다음 자리로 이동 불가능한 경우
			else {
				robots.offer(cur_robot);
				belt_robot[cur_robot] = true;
			}
		}
	}
	static void robotAdd() {
		// start_point 조건을 체크하고 로봇 추가
		if(belt_health[start_point] > 0 && belt_robot[start_point] == false) {
			robots.offer(start_point);
			belt_health[start_point]--;
			belt_robot[start_point] = true;
			
			if(belt_health[start_point] == 0) die++;
		}
	}
	
}
