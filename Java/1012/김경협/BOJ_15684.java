import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 15684번 사다리 조작
 * 
 * 조합으로 풀 시 270 C 3 = 450만 정도
 * 
 * 세로선 사이들과 가로선을 2차원처럼 저장하는 1차원 배열을 만들었다.
 * 1차원 배열로 놓고 0 ~ 세로선 사이의 개수 * 가로선 개수 해서 조합으로 0,1,2,3개를 뽑아서 사다리를 놓았다.
 * 사다리를 놓을 때, 중첩되지 않고 양옆으로 겹치지 않도록 놓는 부분이 까다로웠다.
 * 사다리를 놓고 시뮬레이션 돌리는 부분은 swap을 사용했다.
 * 
 * 예를들어 ,
 * 1 2 3 4
 * |*| |*|
 * | |*| |
 * 별표가 있는 곳에 가로선이 있다고 할 때
 * 위에서부터 탐색하면서, 첫 번째 가로줄에 있는 수들을 스왑하면 2 1 4 3이 된다.
 * 그리고 두 번째 가로줄에 있는 수들을 스왑하면 2 4 1 3 으로 사다리를 타고 내려오게 된다.
 * 
 * 조합으로 사다리를 놓을 때, 이미 주어진 사다리들에 대해서는 연속되지 않도록 놓는 걸 처리했는데, 새로 놓는 사다리들은 연속으로 놓는 걸
 * 체크 안해줘서 디버깅하는데 시간 엄청 잡아먹었다.
 * 
 * 147,964KB	792ms
 */
public class BOJ_15684 {
	static final int NOT_SELECTED = 100;
	static int N, H, total, ladder[], selectCnt, minLadder = NOT_SELECTED;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()) - 1; // 세로선의 개수
		int M = Integer.parseInt(st.nextToken()); // 가로선의 개수
		H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수

		total = N * H;
		ladder = new int[total];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int horizon = Integer.parseInt(st.nextToken()) - 1;
			int vertical = Integer.parseInt(st.nextToken()) - 1;
			ladder[horizon * N + vertical] = 1;
		}

		for (int i = 0; i <= 3 && minLadder == NOT_SELECTED; i++) {
			selectCnt = i;
			combination(0, 0);
		}

		// 불가능한 경우: -1 출력
		System.out.println(minLadder == NOT_SELECTED ? -1 : minLadder);
	}

	static void combination(int start, int cnt) {
		if (minLadder != NOT_SELECTED)
			return; // 이미 minLadder가 선택됐으면 return

		if (cnt == selectCnt) { // 기저 조건
			// 만들어진 사다리로 시뮬레이션
			if (simulation())
				minLadder = cnt; // 만들어졌으면, 그게 최솟값이 됨.
			return;
		}

		// 조합
		for (int i = start; i < total && minLadder == NOT_SELECTED; i++) {
			if (ladder[i] == 1)
				continue; // 겹치는지 확인

			// 행에서 왼쪽 끝은 왼쪽을, 오른쪽 끝은 오른쪽을 체크하지 않아도 됨
			if (i % N != N - 1) // 오른쪽 확인
				if (ladder[i + 1] == 1)
					continue;

			if (i % N != 0) // 왼쪽 확인
				if (ladder[i - 1] == 1)
					continue;

			ladder[i] = 1;
			combination(i + 1, cnt + 1);
			ladder[i] = 0;
		}
	}

	static boolean simulation() {
		int[] ladderOrder = new int[N + 1];
		for (int i = 0; i < N + 1; i++)
			ladderOrder[i] = i + 1; // 순서대로 사다리 번호를 넣음

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				if (ladder[i * N + j] != 0) { // 위에서부터 내려오면서 j번째 가로줄을 만나면
					swap(ladderOrder, j, j + 1); // j와 j+1을 바꿔주면서 계속 내려옴
				}
			}
		}

		// i번째에 i가 이어졌는지 확인
		for (int i = 0; i < N + 1; i++)
			if (ladderOrder[i] != i + 1)
				return false;
		return true;
	}

	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
