import java.io.*;
import java.util.*;
/**
 * [D3]SWEA 2005 파스칼의 삼각형 : 
 * 메모리 : 18400kb 시간 : 100ms
 * 
 * 2차원 배열을 선언한 후 파스칼 삼각형 값을 채워넣는다
 * 1. 맨 앞, 맨 뒤 -> 1
 * 2. 중간 -> 위쪽 + 위오른쪽 
 * 
 */
 
public class SWEA_2005_파스칼의삼각형_김경준 {
	static StringBuilder sb = new StringBuilder();
    static int T, N;
    static int arr[][] = new int[10][10];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
         
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append("#").append(tc).append("\n");
            //파스칼 삼각형 그리기
            for(int i = 0; i < N; i++) {
                for(int j = 0; j <= i; j++) {
                    if(j == 0 || j == i) {    // 맨앞, 맨뒤 -> 1
                        arr[i][j] = 1;  
                        sb.append(1).append(" ");
                    }
                    else {  // 중간 -> 위 + 위오른쪽
                        arr[i][j] = arr[i-1][j-1] + arr[i-1][j]; 
                        sb.append(arr[i][j]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
