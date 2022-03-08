import java.util.*;

/**
 * 
 * [0309] PGS 탐욕법(Greedy) 조이스틱
 * 그리디, 구현
 * 
 * 조이스틱은 알파벳 변환(A~Z 사이)과 자리 변환(커서 이동)에 영향을 받음
 * 
 * 알파벳 변환의 경우 A로부터 이동하는 것과 Z로부터 이동하는 것 중 더 가까운 것을 기준으로 이동하면 됨.
 * 자리 변환은 4가지 방법이 있음.
 *  1. 0~마지막 인덱스로 오름차순 이동
 *  2. 마지막~0 인덱스로 내림차순 이동
 *  3. 중간지점 미만까지는 오름차순 갔다가 0으로 돌아와 중간지점 이상은 내림차순으로
 *  4. 중간지점 이상까지는 내림차순갔다가 마지막으로 돌아가 중간지점 이하는 오름차순으로
 *
 */

public class PGS_탐욕법_조이스틱 {
    int size, mid, totalControl;
    int minIdx, maxIdx, maxBeforeMid, minAfterMid;   // 자리 변환에 사용될 변수
    
    public int solution(String name) {
        size = name.length();
        mid = size/2;
        
        minIdx = size;
        minAfterMid = size;
        maxIdx = 0;
        maxBeforeMid = 0;
        
        // 0번째 인덱스 알파벳 변환(0번째는 자리 변환을 하지 않으므로 for문 밖에서)
        totalControl += moveAlphabet(name.charAt(0));
        // 1~마지막 인덱스 알파벳 변환
        for (int i=1; i<size; i++) {
            char target = name.charAt(i);
            int curControl = moveAlphabet(target);     // 알파벳 변환
            // 만약 알파벳 변환이 이루어졌으면 자리 변환을 위해 몇번째 인덱스인지 기억
            if (curControl != 0) {
                minIdx = Math.min(minIdx, i);          // 자리 변환 2를 위해 기억
                maxIdx = i;                            // 자리 변환 1을 위해 기억
                if (i <= mid) maxBeforeMid = i;        // 자리변환 3, 4를 위해 기억      
                else minAfterMid = Math.min(minAfterMid, i);
            }
            totalControl += curControl;                // 우선 알파벳 변환을 토탈에 합함
        }
        // 전체 자리 변환을 구해 토탈에 합함
        totalControl += moveDigits();
    
        int answer = totalControl;
        return answer;
    }
    
    // 알파벳 변환
    public int moveAlphabet(char target) {
        int moveUp = Math.abs('A' - target);     // A에서 B로 올라가는 방향으로 조작
        int moveDown = 'Z' - target + 1;         // A에서 Z로 내려가는 방향으로 조작
        return Math.min(moveUp, moveDown);       // 더 조금 조작하는 방식 채택
    }
    
    // 자리 변환
    public int moveDigits() {
        int asc = maxIdx;                   // 0에서 마지막 인덱스로 오른쪽으로 조작
        int desc = size - minIdx;           // 마지막 인덱스에서 0으로 왼쪽으로 조작
        int ascThenDesc = (maxBeforeMid * 2) + (size - minAfterMid);                // 중간 이전까지는 오른쪽으로 조작했다가 다시 0까지 돌아와서 중간 이후는 왼쪽으로 조작
        int descThenAsc = (size - minAfterMid) * 2 + maxBeforeMid;                  // 중간 이후까지는 왼쪽으로 조작했다가 다시 0까지 돌아와서 중간 이전은 오른쪽으로 조작
        return Math.min(Math.min(asc, desc), Math.min(ascThenDesc, descThenAsc));   // 최소 이동 방식 채택
    }
}