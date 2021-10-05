import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 14499번 주사위 굴리기
 * 
 * 육면체 주사위를 굴리면서 인덱스를 잘 조절해줘야하는 문제
 * 
 * 내가 생각한 주사위의 인덱스 조절하는 방법은 다음과 같다.
 *	굴리는 방향에 있어서 메인 축이 되는 배열 하나와, 서브 축 배열 하나를 준비한다.
 *	그리고 각각의 배열에서 어디가 바닥인지를 가르키는 idx 2개를 준비한다.
 *
 *	  2
 *	4 1 3	현재 바닥은 1 이다.
 *	  5
 *	  6
 *
 *	예를 들어 북쪽으로 굴린다면 2, 1, 5, 6이 메인 배열이 되고
 *	4,1,3이 서브 축이 된다. 이 때, 서브 축도 다음 번 굴릴 때 메인 축이 될 수 있기 때문에 위쪽을 바라보고 있는 6도 추가해서
 *	4,1,3,6이 서브 배열이 된다.
 *
 *	북쪽으로 굴리면 메인 축에서 바닥을 가르키는 인덱스가 -1 되어 바닥면은 2가 된다.
 *	그리고 서브 축 idx는 바뀌지 않는다. 대신 바닥이 1에서 2로 바뀌었기 때문에
 *	배열은 4,2,3,6으로 바뀌고, 현재 메인 축에서 맨 위에 있는 면이 6에서 5로 바뀌었기 때문에
 *	이것도 반영해서 4,2,3,5로 바뀐다.
 *
 *	14,444KB	152ms
 */

public class BOJ_14499 {
	
	static int mapCol, mapRow, diceCol, diceRow, map[][], dice[][], diceIdx[];
	static final int dr[] = {0,0,-1,1}; //동서 북남
	static final int dc[] = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		mapRow = Integer.parseInt(st.nextToken());	
		mapCol = Integer.parseInt(st.nextToken());	
		
		// 주사위의 현재 위치 저장
		diceRow = Integer.parseInt(st.nextToken());	// row
		diceCol = Integer.parseInt(st.nextToken()); // col
		
		int commandCnt = Integer.parseInt(st.nextToken());	// 명령 수
		
		// 처음에 주사위는 모든 면에 0이 적혀져 있음
		map = new int[mapRow][mapCol];
		dice = new int[2][4];	// 0번째 축은 동서 담당, 1번째 축은 북남 담당
		diceIdx = new int[2];	// 두개의 축 배열에서 바닥면을 가르키는 idx
		
		for (int i = 0; i < mapRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapCol; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < commandCnt; i++) {
			int command = Integer.parseInt(st.nextToken());
			int top = moveDice(command - 1); // 동 0 서 1 북 2 남 3
			if(top != -1)
				sb.append(top).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 현재 위치에서 주어진 방향으로 이동시킴 0,1 동서 2,3 북남
	static int moveDice(int dir) {
		// 맵에서 주사위를 이동시킴 + 맵 갱신
		diceRow += dr[dir];
		diceCol += dc[dir];
		if(isOutOfMap(diceRow, diceCol)) {
			diceRow -= dr[dir];
			diceCol -= dc[dir];
			return -1;
		}
		return diceUpdate(dir, diceRow, diceCol);
	}
	
	// 방향에 따라 주사위가 돌아가는 메인 축의 인덱스를 바꿔주고, 서브 축을 업데이트 해주기 그리고 바닥면 혹은 주사위면 복사
	// 1. 메인 축의 idx 이동시키기
	// 2. 바닥에 복사 혹은 바닥을 복사
	// 3. 서브 축에 바닥 갱신
	// 4. 서브 축 맨 윗면 갱신
	static int diceUpdate(int dir, int row, int col) {
		int main = 0, sub = 0;
		if(dir < 2) { 	// 동 서
			sub = 1;	// 동서로 움직일 시 메인 축은 0번째 배열
			dir = dc[dir];
		}else {			// 북 남
			main = 1;	// 북남으로 움직일 시 메인 축은 1번째 배열
			dir = dr[dir];
		}	// dir은 이동 방향에 따라 -1, 1 중에 하나를 갖게 된다.
		
		dir = dir < 0 ? 3 : dir;	// dir이 -1 일떄는 +3이 되도록
		int mainIdx = diceIdx[main] = (diceIdx[main] + dir) % 4;	// 바닥에 맞닿는 면이 어딘지 메인 축의 바닥 idx 갱신
		int subIdx = diceIdx[sub];
		
		// 맵이 0 --> 맵에 주사위 바닥면 복사; 맵이 0이 아님 --> 주사위 바닥면에 맵 복사, 맵은 0으로
		if(map[row][col] == 0)
			map[row][col] = dice[main][mainIdx];
		else {
			dice[main][mainIdx] = map[row][col];
			map[row][col] = 0;
		}
		
		dice[sub][subIdx] = dice[main][mainIdx];	// 서브축의 바닥 면 갱신
		
		int mainTopIdx = (mainIdx + 2) % 4;	// 맨 위에 있는 면의 idx 계산
		int subTopIdx = (subIdx + 2) % 4;	// 서브축에서 맨 위쪽 면 idx 계산
		return dice[sub][subTopIdx] = dice[main][mainTopIdx];		// 서브축 맨 위에 있는 면 숫자 갱신
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= mapRow || c >= mapCol;
	}

}
