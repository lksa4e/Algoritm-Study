import java.io.*;
import java.util.*;

/**
 * [1221] BOJ 1946 신입 사원
 * 그리디, 정렬
 *
 * sol)
 * 서류 성적과 면접 성적 중 하나를 기준으로 오름차순 정렬하여 배열 인덱스를 랭크로하여 담는다.
 * 만약 서류 성적을 기준으로 한다면 서류 성적이 배열의 인덱스이고 각 배열에 대치되는 값이 면접 성적이다.
 * 서류 등수가 낮은 사람(인덱스가 낮은 사람)부터 모든 인원을 확인하면서 
 * 서류 등수가 더 높은 사람의 면접 점수가 지금까지의 서류 등수가 낮았던 사람의 면접 최저 등수보다 크면 채용에서 제외한다.
 * 
 * 시행착오)
 * 서류 기준으로 배열을 구성할 경우 면접 최저 등수를 비교 대상으로 했어야 했는데, 서류 등수가 직전인 사람의 면접 등수를 기준으로 하여 제대로 필터링하지 못했다.
 * 채용에서 제외되는 조건은 한 지원자가 다른 어떤 지원자보다도 밀리면 안되는 것이므로 직전 등수가 아닌 최저 등수를 기준으로 비교해야 함.
 * 
 */

public class BOJ_1946_신입사원 {
	static int N;
	static int[] ranks;     // 서류나 면접 중 하나를 기준 삼아 기준 등수를 인덱스로하고 나머지 등수를 값으로 갖는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테케 별
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			ranks = new int[N+1];
			
			// 기준 등수(서류 등수)를 배열 인덱스로하고 나머지 등수(면접 등수)를 값으로 갖는 배열 완성
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int resumeRank = Integer.parseInt(st.nextToken());
				int interviewRank = Integer.parseInt(st.nextToken());
				ranks[resumeRank] = interviewRank;
			}
			// 전체 인원에서 제외되는 인원을 차감하여 최종 채용 인원 출력
			System.out.println(N-filtering());
		}
	}

	// 제외되는 인원 찾기(모든 지원자 중 어떤 지원자(단 한 지원자)보다도 뒤떨어지면 제외
	private static int filtering() {
		int removed = 0;
		int limit = ranks[1];         // 초기 비교 기준은 기준 등수(서류 등수)가 1등인 사람의 나머지 등수(면접 등수)로 설정
		
		// 기준 등수를 하나씩 높여가며 기준 등수가 자신보다 우위에 있는 지원자보다 나머지 등수마저 뒤떨어지면 제외
		for (int i=2; i<=N; i++) {
			int interviewRank = ranks[i];
			// 나머지 등수마저 뒤떨어지면 제외 인원으로 추가
			if (interviewRank>limit) removed++;
			// 나머지 등수는 늘 자신의 기준 등수보다 더 낮은 등수인 사람들 중 나머지 등수가 가장 낮은 경우로 설정
			limit = Math.min(limit, interviewRank);
		}
		return removed;
	}
}

