import java.io.*;
import java.util.*;
/**
 * [D3]SWEA 4698 테네스의 특별한 소수 : 
 * 메모리 : 92596kb 시간 : 457ms
 * 
 * 에라스토테네스의 체를 사용하여 소수를 빠르게 분류한다.
 * String의 Contains를 활용하여 D가 포함되는지 검사한다.
 * 
 */
 
public class SWEA_4698_테네스의특별한소수_김경준 {
	static StringBuilder sb = new StringBuilder();
    static int T, D, A, B;
    static String D_str;
    static int arr[] = new int[1000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        setting();
         
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());  // D를 포함
            A = Integer.parseInt(st.nextToken());  // A 이상, B 이하의 수 
            B = Integer.parseInt(st.nextToken());
            D_str = Integer.toString(D);
            int answer = 0;
            for(int i = A; i <= B; i++) {
            	// arr[i] == 1 -> 자기 자신으로밖에 안나눠짐 -> 소수
                if(arr[i] == 1 && isSpecial(i)) answer++;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
    // String.contains를 활용하여 D 포함되는지 찾기
    static boolean isSpecial(int num) {
        String str = Integer.toString(num);
         
        if(str.contains(D_str)) return true;
        else return false;
    }
    
    //에라토스테네스의 체를 이용한 소수 구하기
    static void setting() {
        for(int i = 2; i <= 1000000; i++) {
            for(int j = i; j <= 1000000; j += i) {
                arr[j]++;
            }
        }
         
    }
}
