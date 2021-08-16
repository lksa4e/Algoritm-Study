package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 백준 3985번 롤 케이크  
 * 풀이 : 방청객의 순서대로 P~K번째 롤케이크 선점 
 * 선점할 때마다 기댓값과 결과값 갱신 
 * 
 * 16028KB	192ms
 */
public class Solution3985_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int estimate = Integer.MIN_VALUE, e_num = 1;	// 기댓값, 기댓값의 index 
		int max = Integer.MIN_VALUE, m_num = 1;	// 결과값, 결과값의 index
		
		int[] cake = new int[L];
		
		int P,K;
		for(int i=1;i<=N;i++) {	// 인덱스 계산을 위해 1~N 
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			if(K-P > estimate) {	// 기댓값보다 크면 갱신 
				estimate = K-P;
				e_num = i;
			}
			
			int num = 0;
			for(int j=P-1;j<K;j++) {
				if(cake[j] == 0) {	// 선점하지 않았다면 번호 입력 
					cake[j] = i;
					num++;
				}
			}
			if(num > max) {	// 최대 결과값보다 크면 갱신 
				max = num;
				m_num = i;
			}
		}
		System.out.println(e_num+"\n"+m_num);
	}

}
