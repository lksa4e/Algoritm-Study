import java.io.*;
import java.util.StringTokenizer;

public class Main12100 {
	/*
	 * 상하좌우^5 = 1024 순열 돌면서 최댓값 찾기
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st =  null;
	
    static int N, map[][], cmap[][], order[], ans;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cmap = new int[N][N];
        order = new int[5];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);
        System.out.println(ans);
    }
    
    private static void perm(int k) {
        if (k == 5) {
            copyMap(); // 맵 복사
            
            // 게임 진행
            for (int i = 0; i < order.length; i++) {
                move(order[i]);
            }
            
            // 최댓값  찾기
            int block = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	if(block < cmap[i][j]) block = cmap[i][j];
                }
            }
            // 최댓값 갱신
            if(ans < block) ans = block;
            return;
        }

        for (int i = 0; i < 4; i++) {
            order[k] = i;
            perm(k + 1);
        }
    }

    static void move(int num) {
        if (num == 0) { //좌
        	for (int row = 0; row < N; row++) {
                int idx = 0;    // 블록을 저장할 인덱스
                int blockSize = 0;  // 합칠 수 있는 블록의 크기(0이면 합칠 수 없는 상태)

                for (int col = 0; col < N; col++) {
                    if (cmap[row][col] != 0) {  // 현재 칸이 0이 아닐 때
                        if (blockSize == cmap[row][col]) {  // 현재 칸과 합칠 블록이 같으면
                            // 합친 후, 블록 상태 조정 및 해당 칸 0으로 채움
                            cmap[row][idx - 1] = blockSize * 2; 
                            blockSize = 0;
                            cmap[row][col] = 0;
                        } else {    // 현재 칸과 합칠 블럭이 다르면
                            // 왼쪽으로만 붙이고, 합칠 수 있는 블럭의 크기를 업데이트
                            blockSize = cmap[row][col];
                            cmap[row][col] = 0;
                            cmap[row][idx++] = blockSize;
                        } 
                    }
                }
            }
        } else if (num == 1) { //우
        	for (int row = 0; row < N; row++) {
                int idx = N - 1;
                int blockSize = 0;

                for (int col = N - 1; col >= 0; col--) {
                    if (cmap[row][col] != 0) {
                        if (blockSize == cmap[row][col]) {
                            cmap[row][idx + 1] = blockSize * 2;
                            blockSize = 0;
                            cmap[row][col] = 0;
                        } else {
                            blockSize = cmap[row][col];
                            cmap[row][col] = 0;
                            cmap[row][idx--] = blockSize;
                        }
                    }
                }
            }
        } else if (num == 2) { //상
        	for (int col = 0; col < N; col++) {
                int idx = 0;
                int blockSize = 0;

                for (int row = 0; row < N; row++) {
                    if (cmap[row][col] != 0) {
                        if (blockSize == cmap[row][col]) {
                            cmap[idx - 1][col] = blockSize * 2;
                            blockSize = 0;
                            cmap[row][col] = 0;
                        } else {
                            blockSize = cmap[row][col];
                            cmap[row][col] = 0;
                            cmap[idx++][col] = blockSize;
                        }
                    }
                }
            }
        } else { //하
        	for (int col = 0; col < N; col++) {
                int idx = N - 1;
                int blockSize = 0;

                for (int row = N - 1; row >= 0; row--) {
                    if (cmap[row][col] != 0) {
                        if (blockSize == cmap[row][col]) {
                            cmap[idx + 1][col] = blockSize * 2;
                            blockSize = 0;
                            cmap[row][col] = 0;
                        } else {
                            blockSize = cmap[row][col];
                            cmap[row][col] = 0;
                            cmap[idx--][col] = blockSize;
                        }
                    }
                }
            }
        }
    }

    private static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cmap[i][j] = map[i][j];
            }
        }
    }
}
