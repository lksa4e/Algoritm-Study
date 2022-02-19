/*
 * 프로그래머스 모의고사
 * 
 * 이중 배열로 순서를 저장하고, 한사람씩 정답의 개수를 저장했다.
 */
import java.util.*;

class PG_모의고사 {
	// 각 사람의 찍는 순서를 기억
    public static int[][] method = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
    
    public int[] solution(int[] answers) {
        
        int[] score = new int[3];	// 각 사람마다 정답개수를 저장
        
        for(int i = 0; i < 3; i++) {	// 각 사람마다 정답인 갯수를 측정
            int idx = 0;	// 찍기 순서의 index, 끝에 도달하면 다시 처음 index를 가르킨다.
            for(int j = 0; j < answers.length; j++) {
                if(idx >= method[i].length) idx = 0;
                if(method[i][idx] == answers[j]) score[i]++;
                idx++;
            }
        }
        
        List<Integer> list = new ArrayList<>();	// 정답을 저장할 list
        int max = 0;
        for(int i = 0; i < 3; i++) {
            if(score[i] > max) {	// 정답 수가 max보다 많을 경우,
                max = score[i];
                list.clear();
                list.add(i + 1);
            } else if(score[i] == max) {	// 정답 수가 max와 같을 경우 list에 같이 저장
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