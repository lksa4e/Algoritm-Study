import java.io.*;
import java.util.*;

/**
 * [0108] BOJ 1011 Fly me to the Alpha Centauri
 * 수학, 파라메트릭 서치
 * 
 * sol)
 * x에서 y로 이동하기까지 최소한의 작동 횟수로 이동하기 위해서는 매번 이동할 수 있는한 최대한의 거리(k-1, k, k+1 중 k+1)로 이동해야한다.
 * 하지만 x에서 출발할 때 최대 1만큼 이동할 수 있고 y로 도착하기 직전에는 최대 1만큼 이동해서 도착할 수 있으므로
 * 결국 이동 거리는 1부터 1씩 증가하여 중간까지 이동했을 때 최대로 증가한 다음 중간 이후에서는 1씩 감소하여 다시 1로 돌아오는 형태가 된다.
 * 이때 이동 거리를 최대로 하기위해 중간에서 최대로 증가한 이동거리를 한번 더 반복할 수 있다.(최대 증가한 거리를 2번까지 반복)
 * 최대로 증가한 이동거리를 3번 이상으로 반복하게되면 오히려 이동거리는 최대가 아니게 되므로 2번까지만 반복하고 그 다음 수열에서는 최대 이동 거리 자체를 증가시켜야한다.
 *      1, 1+1, 1+2+1, 1+2+2+1, 1+2+3+2+1, 1+2+3+3+2+1 ...
 *      
 * 가능한 이동 장치 작동 횟수를 최대 거리인 2^31까지 구해놓고 리스트에 담아둔 뒤 
 * 테케 별 거리가 입력되면 범위에 걸치는 작동횟수를 파라메트릭 서치를 이용해 찾아 출력한다.
 *
 */

public class BOJ_1011_FlyMeToTheAlphaCentauri {
	static final long LIMIT = (long) Math.pow(2, 31);     // 최대 거리
	static int sequenceLen;
	static long x, y, gap;
	static ArrayList<Long> sequence = new ArrayList<Long>();    // 이동 거리 0부터 2^31까지 이동하는데 필요한 작동 횟수를 기록한 리스트(인덱스가 작동횟수이고 거리가 값)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// while문 이전에 이동 거리 별 장치 작동 횟수 구하여 리스트에 저장
		makeSequence();
		
		while(T-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Long.parseLong(st.nextToken());
			y = Long.parseLong(st.nextToken());
			// 테케 별 두 지점의 간격을 구한 뒤 리스트에서 찾아 작동 횟수 출력
			gap = y-x;
			System.out.println(findMinMovement(0, sequenceLen));
		}
	}

	// 이동 거리 별 장치 작동 횟수 리스트에 저장
	private static void makeSequence() {
		sequence.add((long) 0);     // 장치 이동 횟수 0 : 거리 0
		sequence.add((long) 1);     // 장치 이동 횟수 1 : 거리 1
		
		long n = 1;
		long i = 2;
		while(n<LIMIT) {            // 거리가 2^31보다 작을때까지만
			long curAdd = i/2;
			
			if (i++%2==0) n += curAdd;   // 짝수번째일때는 중간 지점에서 최대 이동 거리를 2번 반복해서 이동할 수 있고 (1+2+2+1)
			else n += ++curAdd;          // 홀수번째일때는 중간 지점에서 최대 이동 거리를 1 증가시킨다 (1+2+3+2+1)
			
			sequence.add(n);        // 장치 이동 횟수 i : 거리 n
		}
		
		sequenceLen = sequence.size();
	}

	// 파라메트릭 서치를 이용해 이동 거리 별 장치 작동 횟수 찾기
	private static int findMinMovement(int s, int e) {
		int mid = 0;
		
		// s : 최소 작동 횟수, e : 최대 작동 횟수
		while(s<e) {
			mid = (s+e)/2;
			// 두 지점 간격을 작동 횟수에따른 이동 거리와 비교하여 적정 작동 횟수 찾기
			if (gap>sequence.get(mid)) s = mid+1;
			else e = mid;
		}
		return e;
	}
}
