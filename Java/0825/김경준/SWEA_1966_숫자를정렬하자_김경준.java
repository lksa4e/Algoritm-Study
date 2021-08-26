import java.io.*;
import java.util.*;
/**
 * [D3]SWEA 1966 숫자를 정렬하자 : 
 * 메모리 : 18396kb 시간 : 100ms
 * 
 * 감사합니다
 */
 
public class SWEA_1966_숫자를정렬하자_김경준 {
    static StringBuilder sb = new StringBuilder();
    static int T, N;
    static int arr[];
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	arr = new int[N];
        	st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) 
            	arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            sb.append("#").append(tc).append(" ");
            for(int i = 0; i < N; i++) 
            	sb.append(arr[i] + " ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
