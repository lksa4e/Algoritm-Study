import java.util.*;

/*
 * ���α׷��ӽ� �ܼ�ī�޶�
 * 
 * �����ð����� ��� �������� �������ش�.
 * 
 * ���� ���� ���ϴ� ���� ���� �ð��� cctv ��ġ���� ũ�� cctv �ϳ� �� ��ġ���ְ�
 * ���� �ڵ����� ���� �ð��� cctv�� ��ġ�� �ش�.
 */

class PG_�ܼ�ī�޶� {
    static final int CCTV_INIT = -30001;
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a1,a2) -> Integer.compare(a1[1], a2[1]));
        
        int answer = 0, currCCTV = CCTV_INIT;
        for(int i = 0, len = routes.length; i < len; i++) {
            if(routes[i][0] <= currCCTV) continue;
            
            // CCTV �ϳ� �� ��ġ�ؾ� ��
            answer++;
            currCCTV = routes[i][1];
        }
        
        return answer;
    }
}