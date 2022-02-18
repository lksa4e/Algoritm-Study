import java.io.*;
import java.util.*;

/**
 * [0212] PGS 완전탐색 카펫
 * 구현
 * 
 * sol)
 * 카펫의 실제 가로와 세로 길이를 각각 w, h라고 설정하면
 * 갈색의 가로 길이를 (x)로 설정할 경우 세로 길이는 가로 길이에 의해 가려진 타일을 제외하여 (y-2)가 됨
 * 노란색의 가로 길이는 갈색의 가로보다 작은 (x-2)가 되며 세로 길이는 갈색과 동일한 (y-2)가 됨
 * 
 * 이를 바탕으로 2차 방정식을 세운 뒤 1부터 미지수에 적용 가능한 수를 대입해보며 구한다
 * 
 */

public class PGS_완전탐색_카펫 {
    public int[] solution(int brown, int yellow) {
        int h = findOptimalHeight(brown, yellow);
        int w = (-1 * h) + brown/2 + 2;             // y값을 바탕으로 x값 구함
        
        int[] answer = new int[2];
        answer[0] = w;
        answer[1] = h;
        return answer;
    }
    
    // 가능한 h 값 구하기
    public int findOptimalHeight(int brown, int yellow) {
    	// 갈색 최대 크기는 5000 이므로 양쪽 면을 고려하여 절반인 2500만큼만 확인
        for (int y=0; y<2500; y++) {
        	// 2차 방정식에 대입하여 가능한 y 찾기
            if (((-1 * y) + brown/2) * (y-2) == yellow) return y;
        }
        return 0;
    }
}