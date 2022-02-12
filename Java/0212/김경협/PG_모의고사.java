/*
 * ���α׷��ӽ� ���ǰ��
 * 
 * ���� �迭�� ������ �����ϰ�, �ѻ���� ������ ������ �����ߴ�.
 */
import java.util.*;

class PG_���ǰ�� {
	// �� ����� ��� ������ ���
    public static int[][] method = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
    
    public int[] solution(int[] answers) {
        
        int[] score = new int[3];	// �� ������� ���䰳���� ����
        
        for(int i = 0; i < 3; i++) {	// �� ������� ������ ������ ����
            int idx = 0;	// ��� ������ index, ���� �����ϸ� �ٽ� ó�� index�� ����Ų��.
            for(int j = 0; j < answers.length; j++) {
                if(idx >= method[i].length) idx = 0;
                if(method[i][idx] == answers[j]) score[i]++;
                idx++;
            }
        }
        
        List<Integer> list = new ArrayList<>();	// ������ ������ list
        int max = 0;
        for(int i = 0; i < 3; i++) {
            if(score[i] > max) {	// ���� ���� max���� ���� ���,
                max = score[i];
                list.clear();
                list.add(i + 1);
            } else if(score[i] == max) {	// ���� ���� max�� ���� ��� list�� ���� ����
                list.add(i + 1);
            }
        }
        int[] answer = new int[list.size()];	// list to array
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}