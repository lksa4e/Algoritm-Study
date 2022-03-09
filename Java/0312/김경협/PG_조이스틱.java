/*
 * 프로그래머스 조이스틱
 * 
 * 제일 쉬운줄 알았는데 제일 어려웠던 문제
 * 
 * 알파벳 변환하는건 A에서 시작, Z에서 시작 계산해서 가까운걸 선택하면 된다.
 * 
 * 커서를 옮겨서 최소 거리를 구하는게 어려웠는데,
 * 먼저 현재 위치에서 A 묶음을 만나면, 되돌아가는게 빠른지 비교한다.
 * 되돌아 가는게 빠르다면 현재까지 온 i값과 되돌아가는 i값을 더하고, 되돌아 가는 값을 더해준다.
 * 그리고 만약 처음부터 되돌아갔다 오는게 빨랐다면 처음부터 되돌아갔다온 값 + 현재 index를 더해준다.
 * 그리고 이 셋 중에서 최솟값이 현재 위치에서 다음 최솟값이 된다.
 * 
 */
class PG_조이스틱 {
    public int solution(String name) {
        int answer = 0;
        
        int len = name.length(), minLen = len - 1;
        
        for(int i = 0; i < len; i++) {
            answer += convert(name.charAt(i));
            
            int nextIdx = i + 1;
            while(nextIdx < len && name.charAt(nextIdx) == 'A')
                nextIdx++;
            
            // 중간 위치에서 되돌아 오는게 빠를 경우,
            minLen = Math.min(minLen, i * 2 + len - nextIdx);
            // 처음부터 뒤로 갔다가 다시 돌아오는게 빠른 경우.
            minLen = Math.min(minLen, (len - nextIdx) * 2 + i);
        }
        
        answer += minLen;
        
        return answer;
    }
    
    int convert(char target) {
        return Math.min(target - 'A', 'Z' - target + 1);
    }

}