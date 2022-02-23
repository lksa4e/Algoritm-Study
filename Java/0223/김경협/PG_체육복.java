import java.util.*;

/*
 * ���α׷��ӽ� ü����
 * 
 * ������ �׸��� ����
 * ���ʺ��� ü������ ������ üũ�ϰ�, ���ٸ� ��,�� ����� ������ �ִ��� Ȯ���ϸ� �ȴ�.
 */

class PG_ü���� {
    static int N;
    public int solution(int n, int[] lost, int[] reserve) {
        N = n;
        int[] cloth = new int[n];
        Arrays.sort(lost);	// �˰��� ���� �� ����,
        Arrays.sort(reserve);
        
        for(int r : reserve) {	// �� ���� �ִ� ��� üũ
            cloth[r - 1]++;
        }
        
        for(int l : lost) {	// �� ���� ���� ��� üũ
            cloth[l - 1]--;
        }
        
        int cnt = 0;
        
        for(int l : lost) {
            if(cloth[l - 1] == -1) {	// ���� �������� ���� �� �ִ��� üũ
                if(!borrow(l - 1, cloth)) {
                    cnt++;
                }
            }
        }
        
        int answer = n - cnt;
        return answer;
    }
    
    static boolean borrow(int lost, int[] cloth) {	// ��, �� ��� ���� �ִ��� üũ�ϱ�
        if(lost - 1 >= 0) {
            if(cloth[lost - 1] == 1) {
                cloth[lost - 1]--;
                return true;
            }
        }
        
        if(lost + 1 < N) {
            if(cloth[lost + 1] == 1) {
                cloth[lost + 1]--;
                return true;
            }
        }
        return false;
    }
}