import java.io.*;
import java.util.*;
/**
 * [D4]SWEA 5356 의석이의 세로로 말해요 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT
 * 메모리 : 19236kb, 시간 : 104ms
 * 
 * br.readLine().toCharArray(); 를 통해 통째로 입력받으면서 최대 열길이를 저장한다.
 * 최대 열 길이만큼 탐색하면서 현재 비교중인 행의 열 길이가 허용하는 범위까지만 출력을 한다.
 *                                      ㄴ> 현재 비교중인 열 index가 특정 행의 length보다 짧을 때
 *  
 */
 
public class SWEA_5356_의석이의세로로말해요_김경준 {
    static StringBuilder sb = new StringBuilder();
    static int T;
    static char[][] arr = new char[5][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int max_len = 0;
            for(int i = 0; i < 5; i++) {
                arr[i] = br.readLine().toCharArray();
                max_len = Math.max(max_len, arr[i].length);
            }
             
            sb.append("#").append(tc).append(" ");
            for(int i = 0; i < max_len; i++) {  // 최대 열 길이만큼 탐색
                for(int j = 0; j < 5; j++) {
                	// 만약 현재 행의 열길이가 비교중인 열 인덱스 i 보다 크면 (인덱스 접근 가능할 때) 
                    if(i < arr[j].length) sb.append(arr[j][i]);    
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }   
}
