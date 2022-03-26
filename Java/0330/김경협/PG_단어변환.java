import java.util.*;

/*
 * ���α׷��ӽ� �ܾ� ��ȯ
 * 
 * BFS�� �ִܰŸ� ���ϱ�
 * �Ϲ����� BFS �����ε� �ܾ�� �ܾ�� �̵��� ��
 * ���ĺ� �ϳ� ���ϴ� �͸� üũ�ϱ�
 * 
 * �ִ� �Ÿ��� BFS ���� �� Queue �����ŭ for���� ������
 * �� ��° �̵������� üũ�ϱ�.
 * 
 */

class PG_�ܾȯ {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    int bfs(String begin, String target, String[] words) {
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        q.offer(begin);
        
        while(!q.isEmpty()) {
            answer++;	// �� ��° �̵����� üũ
            // N��° �̵��� �ܾ�鸸 cycle ������
            for(int cycle = 0, size = q.size(); cycle < size; cycle++) {
                String curr = q.poll();

                for(int i = 0; i < words.length; i++) {
                    if(visited[i])
                        continue;
                    
                    // �ѱ��� ����� �ܾ �̵��� �� �ֵ���
                    if(isConvertible(curr, words[i])) {
                        if(words[i].equals(target)) {	// ��ȯ�ߴµ� ��ȯ�� �ܾ target�� ��, ���� �̵��Ÿ��� return
                            return answer;
                        }
                        q.offer(words[i]);
                        visited[i] = true;
                    }  
                }
            }
            
        }
        
        // while �ٵ��� ���� : target�� �������� ����
        return 0;
    }
    
    // �� �ܾ�� ��ġ���� �ʴ� ���� ã�Ƴ���.
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