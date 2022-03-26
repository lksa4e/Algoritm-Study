import java.util.*;

/*
 * 프로그래머스 단어 변환
 * 
 * BFS로 최단거리 구하기
 * 일반적인 BFS 문제인데 단어에서 단어로 이동할 때
 * 알파벳 하나 변하는 것만 체크하기
 * 
 * 최단 거리는 BFS 돌릴 때 Queue 사이즈만큼 for문을 돌려서
 * 몇 번째 이동인지를 체크하기.
 * 
 */

class PG_단어변환 {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    int bfs(String begin, String target, String[] words) {
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        q.offer(begin);
        
        while(!q.isEmpty()) {
            answer++;	// 몇 번째 이동인지 체크
            // N번째 이동인 단어들만 cycle 돌리기
            for(int cycle = 0, size = q.size(); cycle < size; cycle++) {
                String curr = q.poll();

                for(int i = 0; i < words.length; i++) {
                    if(visited[i])
                        continue;
                    
                    // 한글자 변경된 단어만 이동할 수 있도록
                    if(isConvertible(curr, words[i])) {
                        if(words[i].equals(target)) {	// 변환했는데 변환된 단어가 target일 때, 현재 이동거리를 return
                            return answer;
                        }
                        q.offer(words[i]);
                        visited[i] = true;
                    }  
                }
            }
            
        }
        
        // while 다돌고도 없음 : target이 존재하지 않음
        return 0;
    }
    
    // 두 단어에서 일치하지 않는 문자 찾아내기.
    boolean isConvertible(String curr, String target) {
        int diffNum = 0;
        for(int i = 0; i < curr.length(); i++) {
            if(curr.charAt(i) != target.charAt(i)) {
                diffNum++;
            }
            
            if(diffNum > 1)
                return false;
        }
        return true;
    }
}