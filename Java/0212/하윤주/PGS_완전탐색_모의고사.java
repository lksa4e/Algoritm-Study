import java.io.*;
import java.util.*;

/**
 * [0212] PGS 완전탐색 모의고사
 * 구현
 * 
 * sol)
 * 각 수포자별 찍는 방식을 패턴화하여 상수 배열로 만든다.
 * 이후 맞춘 문제를 계산한 뒤 이를 주어진 규칙에 맞게 정렬하여 수포자 순위를 구한다.
 *
 */

public class PGS_완전탐색_모의고사 {
	// 모든 수포자 찍는 방식을 패턴화
    final int[] ONE = {1, 2, 3, 4, 5};
    final int[] TWO = {2, 1, 2, 3, 2, 4, 2, 5};
    final int[] THREE = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    int[][] score = new int[3][2];        // 각 수포자 번호와 맞은 개수를 저장한 2차원 배열
    
    public int[] solution(int[] answers) {
    	// 수포자별 번호와 점수를 저장
        score[0][0] = 1;
        score[0][1] = grade(answers, ONE);
        score[1][0] = 2;
        score[1][1] = grade(answers, TWO);
        score[2][0] = 3;
        score[2][1] = grade(answers, THREE);
        
        // 점수를 기준으로 정렬한 뒤 동차는 번호를 오름차순으로 정렬
        Arrays.sort(score, (s1, s2) -> s1[1] == s2[1] ? s1[0] - s2[0] : s2[1] - s1[1]);
        
        if (score[0][1] == score[1][1]) {                  // 1등과 2등 맞은 개수가 같고
            if (score[0][1] == score[2][1]) return new int[] {1, 2, 3};    // 2등과 3등의 맞은 개수도 같으면 전원 동차
            else return new int[] {score[0][0], score[1][0]};              // 2등과 3등의 맞은 개수가 다르면 1, 2등 출력
        } else return new int[] {score[0][0]};             // 1등과 2등의 맞은 개수가 다르면 1등 출력
        
    }
    
    // 맞은 개수 구하기
    public int grade(int[] answers, int[] marking) {
        int nowScore = 0;
        
        for (int size=answers.length, i=0; i<size; i++) {
            int idx = i % marking.length;
            if (answers[i] == marking[idx]) nowScore++;
        }
        return nowScore;
    }
}