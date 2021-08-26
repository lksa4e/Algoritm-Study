import java.io.*;
import java.util.*;
/**
 * [D3]SWEA 4789 성공적인 공연 기획 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2dSgKA8MDFAVT
 * 메모리 : 21,452kb, 시간 : 120ms
 * 
 * i번째 위치일 때 박수치는 사람들이 최소 i여야만, 다음 i+1에 있는 사람들이 박수를 칠 수 있다.
 * 
 * 현재 박수치는 사람 수 < i 인 경우 박수치는 사람을 i까지 증가시켜준다.
 *  -> answer 추가, 박수치는 사람 i로 맞추기
 * 
 * i번째 위치의 사람 수만큼 박수치는 사람 증가
 */
 
public class SWEA_4789_성공적인공연기획_김경준 {
    static StringBuilder sb = new StringBuilder();
    static int T;
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int clap = str.charAt(0)- '0';  // 초기에 박수치는 사람 수
            int answer = 0;  // 추가해야하는 박수맨 수
            for(int i = 1; i < str.length(); i++) {
            	if(clap < i) { // 만약 i번째 사람들일 때 박수치는 사람이 i보다 작으면 
            		answer += i - clap;  // 차이만큼 박수맨 추가
            		clap = i;            // 박수맨 충원되었으니 박수치는 사람 i가 됨
            	}
            	clap += str.charAt(i) -'0';  // i번째 사람 수만큼 박수치는 사람 추가 
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }   
}
