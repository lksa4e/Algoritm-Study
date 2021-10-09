import java.io.*;
import java.util.*;

/**
 * 백준 14890번 경사로
 * 
 * 풀이 : 구현
 * 
 * "행 우선 탐색"으로, 0열부터 N-2열까지 오른쪽 열과 높이를 비교하면서 경사로 설치 
 * 1. c열과 c+1열의 길이가 같으면 다음 열로 이동
 * 2. c열과 c+1열의 길이가 다르고 &&
 * 		1) c열 = c+1열 - 1이면, 높이차가 1인 "오르막"
 * 			c열부터 경사로 길이만큼 왼쪽으로 이동하면서 설치 가능 여부 확인 (<-)
 * 		2) c열 = c+1열 + 1이면, 높이차가 1인 "내리막" 
 * 			c+1열부터 경사로 길이만큼 오른쪽으로 이동하면서 설치 가능 여부 확인 (->)
 * 		3) 높이차가 2 이상이면 경사로 설치 가능 X
 * 
 * 같은 매커니즘으로 "열 우선 탐색"을 하기 위해 맵 회전 후, 똑같은 함수 실행 
 * 
 * 15164kb	160ms
 */
public class Main14890_경사로 {
 
    static int N, length, result;
    static int[][] map;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        length = Integer.parseInt(st.nextToken());
        
        // 맵 정보 입력받기 
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 행 우선 탐색으로 경사로 설치 
        for (int i = 0; i < N; i++) buildSlope(i);    
        
        reverseMap();	// 맵 회전 
        
        // 열 우선 탐색으로 경사로 설치 
        for (int i = 0; i < N; i++) buildSlope(i);
        
        // 경사로 개수 출력 
        System.out.println(result);
    }
 
    private static void reverseMap() {
    	int[][] temp = new int[N][N];	// 임시 배열에 행과 열을 반대로 저장 
    	for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				temp[r][c] = map[c][r];
			}
		}
    	// 임시 배열에 저장된 회전된 맵 정보 저장 
    	for (int i = 0; i < N; i++) map[i] = temp[i].clone();
	}

	private static void buildSlope(int index) {
        boolean isPossible = true;	// index 행에 경사로 설치가 가능한지 여부
        boolean[] visited = new boolean[N];	// 경사로가 이미 설치되있는지 여부 
        
        // 0열부터 오른쪽 경사를 확인하면서 경사로 설치할 수 있는지 반복문을 돌면서 확인 
        for (int c = 0; c < N-1; c++) {
        	// 현재 c열과 오른쪽 열의 높이가 다른 경우 
            if(map[index][c] != map[index][c+1]) {
            	// 1. 오르막인 경우 -> c열부터 왼쪽으로 경사로 설치 
                if(map[index][c] == map[index][c+1] - 1) {
                    int cur = c;
                    int height = map[index][cur];
                    
                    // c열부터 왼쪽으로 탐색하면서 경사로의 길이만큼 높이를 유지하는지 확인 
                    for (int i = cur; i > cur - length; i--) {
                    	// 경계를 벗어나거나, 이미 경사로가 설치되어있거나, 높이가 다르면 경사로 설치 불가 
                        if(i < 0 || visited[i] || map[index][i] != height) {
                            isPossible = false;
                            break;
                        }
                    }
                } 
                // 2. 내리막인 경우 -> c+1열부터 오른쪽으로 경사로 설치 
                else if(map[index][c] == map[index][c+1] + 1) {
                    int cur = c + 1;
                    int height = map[index][cur];
                     
                    // c+1열부터 오른쪽으로 탐색하면서 경사로의 길이만큼 높이를 유지하는지 확인 
                    for (int i = cur; i < cur + length; i++) {
                    	// 경계를 벗어나거나, 이미 경사로가 설치되어있거나, 높이가 다르면 경사로 설치 불가 
                        if(i >= N || visited[i] || map[index][i] != height) {
                            isPossible = false;
                            break;
                        }
                        visited[i] = true;	// 경사로 설치했다고 표시 
                    }
                    
                    c += length - 1;	// 경사로를 설치한 곳은 탐색할 필요가 없으므로 경사로의 가장 끝으로 이동 
                }
                // 3. 높이가 2 이상 차이나는 경우 -> 경사로 설치 불가 
                else {
                    isPossible = false;
                    break;
                }
            }
            
            if(!isPossible) break;
        }
        
        // 경사로를 설치할 수 있으면 경사로의 개수 증가 
        if(isPossible) result++;
    }
 
}