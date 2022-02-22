import java.util.*;

/**
 * [0223] PGS 탐욕법(Greedy) 체육복
 * 그리디
 * 
 * sol)
 * boolean형 배열을 만들어 reserve 배열에 저장된 번호를 인덱스로 보고 boolean 배열에 빌려줄 수 있는지 여부를 저장한다.
 * 이때 자신의 앞과 뒤 번호를 비교하기 위해 배열은 0~n+1 번 인덱스를 가져야한다.
 * 이제 lost 배열을 돌면서 자기 자신이 빌려줄 수 있는 사람인지 확인하여 그렇다면 boolean 배열 값을 false로 바꿔주고,
 * 다시 남은 사람들에 대해 반복문 돌면서 자신의 앞과 뒤 사람이 빌려줄 수 있는지 확인하여 최종 수업을 듣는 사람의 수를 센다
 *
 * 남은 사람들 인덱스는 pq에 저장하여 오름차순으로 접근하도록 함
 */

public class PGS_탐욕법_체육복 {
    public int lostSize, result;
    public boolean[] reserveIdx;     // 각 인덱스의 사람이 빌려줄 수 있는지 여부
    public PriorityQueue<Integer> lostIdx = new PriorityQueue<>();    // 자기 자신으로부터 빌리고 남은 나머지 빌려야하는 사람들

    public int solution(int n, int[] lost, int[] reserve) {
        lostSize = lost.length;

        makeReserveIdxArr(n, reserve);       // boolean형 배열 생성
        borrowMyself(lost);                  // 스스로에게 먼저 빌리고
        borrowOther(n);                      // 남은 사람들은 앞, 뒤로부터 빌림

        int answer = result;
        return answer;
    }

    // 빌려줄 수 있는 사람의 인덱스를 기준으로 빌려줄 수 있는지 여부 저장
    public void makeReserveIdxArr(int n, int[] reserve) {
        reserveIdx = new boolean[n+2];
        for (int idx : reserve) reserveIdx[idx] = true;
    }

    // 스스로에게 빌림
    public void borrowMyself(int[] lost) {
        for (int i=0; i<lostSize; i++) {
            int me = lost[i];

            if (reserveIdx[me]) reserveIdx[me] = false;     // 자신에게 빌렸으면 빌려줄 수 있음 여부에서 제외
            else lostIdx.offer(me);                         // 자신에게 빌릴 수 없으면 남은 사람 큐에 삽입
        }
    }

    public void borrowOther(int n) {
        int lostIdxSize = lostIdx.size();
        result = n - lostIdxSize;

        for (int i=0; i<lostIdxSize; i++) {
            int front = lostIdx.poll()-1;
            int back = front+2;

            if (reserveIdx[front]) {                        // 앞 사람에게 빌리기 시도
                result++;
                reserveIdx[front] = false;
            }
            else if (reserveIdx[back]) {
                result++;                                   // 뒷 사람에게 빌리기 시도
                reserveIdx[back] = false;
            }
        }
    }
}