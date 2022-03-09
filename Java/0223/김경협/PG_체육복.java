import java.util.*;

/*
 * 프로그래머스 체육복
 * 
 * 간단한 그리디 문제
 * 앞쪽부터 체육복이 없음을 체크하고, 없다면 전,뒤 사람이 여벌이 있는지 확인하면 된다.
 */

class PG_체육복 {
    static int N;
    public int solution(int n, int[] lost, int[] reserve) {
        N = n;
        int[] cloth = new int[n];
        Arrays.sort(lost);	// 알고리즘 수행 전 정렬,
        Arrays.sort(reserve);
        
        for(int r : reserve) {	// 옷 여벌 있는 사람 체크
            cloth[r - 1]++;
        }
        
        for(int l : lost) {	// 옷 도난 당한 사람 체크
            cloth[l - 1]--;
        }
        
        int cnt = 0;
        
        for(int l : lost) {
            if(cloth[l - 1] == -1) {	// 도난 당했으면 빌릴 수 있는지 체크
                if(!borrow(l - 1, cloth)) {
                    cnt++;
                }
            }
        }
        
        int answer = n - cnt;
        return answer;
    }
    
    static boolean borrow(int lost, int[] cloth) {	// 전, 뒷 사람 여벌 있는지 체크하기
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