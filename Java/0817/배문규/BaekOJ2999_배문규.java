package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
브론즈라고 그냥 쉽게 생각하고 R을 찾는 조건 꼼꼼이 체크안하다가
엄청 틀렸음..
2차원으로 만들어서 직접 인덱스 뒤집으면 편하긴 한데
1차원에서 i,j값을 컨트롤해서 바로 스트링빌더에 넣으려니 좀 헷갈렸다

메모리	시간
14104	128
*/

public class BaekOJ2999_배문규{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {

        // 문자열을 캐릭터 배열로 입력받음
        char[] input = br.readLine().toCharArray();
        // 배열 길이
        int N = input.length;
        // R과 C찾기
        int R = 1;

        // N = 1인 경우 는 for문에 진입을 하지 못하니 R = 1로 초기화 해준것임
        for(int i = 1; i < N; i++){
            // R과 C 중에 R<=C 인 조건
            if(N%i == 0 && i <= N/i) R = i;
        }

        // 1차원 배열을 2차원 배열로 생각하고 행렬을 뒤집어서 해독
        for(int i = 0; i < R; i++){
            for(int j = 0; j < N/R; j++){
                sb.append(input[i + j*R]);
            }
        }

        System.out.println(sb);

    }
}