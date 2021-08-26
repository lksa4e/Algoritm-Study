import java.io.*;
import java.util.*;
/**
 * [D2]SWEA 1948 날짜 계산기 : 
 * 메모리 : 18628kb 시간 : 101ms
 * 
 * 1. 같은 월일때 -> 날짜 차이만
 * 2. 다른 월일때 -> 첫달에서 남은 일수 + 해당 월까지의 일수들의 합 + 마지막달 일수
 */
 
public class SWEA_1948_날짜계산기_김경준 {
    static StringBuilder sb = new StringBuilder();
    static int T, N, start_m, start_d, end_m, end_d;
    static int month[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	start_m = Integer.parseInt(st.nextToken());
        	start_d = Integer.parseInt(st.nextToken());
        	end_m = Integer.parseInt(st.nextToken());
        	end_d = Integer.parseInt(st.nextToken());
        	
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.print(sb);
    }
    static int solve() {
    	int answer = 0;
    	if(start_m == end_m) return end_d - start_d + 1;
    	else {
    		answer += month[start_m] - start_d + 1;  // 첫달
    		for(int i = start_m + 1; i < end_m; i++)  // 중간달 
    			answer += month[i];
    		answer += end_d;    //마지막 달
    	}
    	return answer;
    }
}
