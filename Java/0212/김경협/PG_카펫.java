/*
 * 프로그래머스 카펫
 * 
 * 갈색이 전체 테두리 갯수이기 때문에 이를 이용해
 * 가로 + 세로길이 = 2 + 갈색 / 2
 * 이걸로 가로 + 세로길이를 구한다.
 * 그리고 세로 길이를 작은 것 부터 1씩 늘려가면서
 * 노란색과 일치하는 가로세로 길이를 찾는다.
 */

class PG_카펫 {
    public int[] solution(int brown, int yellow) {
        int total = brown / 2 + 2;  // 가로 길이 + 세로 길이 = 2 + 갈색 / 2
        int width = 0, height = 0;
        for(height = 3; height <= total / 2; height++) {    // 세로 길이가 작으니 최소길이 3부터 가+세 / 2 까지 반복
            width = total - height;
            if((width - 2) * (height - 2) == yellow)    // 가로 - 2 * 세로 - 2가 노란색과 같으면 break;
                break;
        }
        int[] answer = {width, height};
        return answer;
    }
}