package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
크로아티아 알파벳은 총 8개이고, 입력 문자열길이가 총 100자이기 때문에,
시간초과나 메모리초과가 일어나진 않을거라 생각함.
그래서 카운트하는 방법과 조건만 제대로 찾는게 핵심이라 생각해서
foreach로 8개 알파벳을 각 문장에서 찾아서 전체 문장길이에서 찾은 알파벳 개수만큼 빼주면 되겠다고 생각함
dz=는 운이좋게 z=가 있기 때문에 2개가 빠져서 전체길이에서 찾은카운트를 빼줘도 정답이 맞았음

메모리	시간
14192	128
*/

public class BaekOJ2941_배문규{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {

        // 미리 크로아티아 알파벳을 저장
        String[] cWord = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        String input = br.readLine();
        // 
        int cnt = input.length();

        // 크로아티아 알파벳 하나씩 체크
        for(String cword: cWord){
            // 단어 위치 저장 인덱스
            int idx = 0;
            while(idx < input.length()){
                // 찾는 단어의 인덱스를 찾음
                // return -1 : 없음
                // return (n > -1) : 해당 단어의 시작 인덱스 
                int findword = input.indexOf(cword, idx);
                if(findword != -1){
                    // 찾으면 카운트 -1
                    // 사실 단어 길이를 재야되는데
                    // dz= 만 3단어고 나머지는 다 2단어임
                    // 그리고 dz=도 있고 z=도 있어서
                    // dz= 같은 경우는 dz=에서 -1, z=에서 -1 총 -2가 되기 때문에
                    // 결과는 잘나옴
                    cnt -= 1;
                    // 인덱스 밀기
                    idx = findword+cword.length()-1;
                }
                else{
                    break;
                }
            }
        }

        System.out.println(cnt);

    }
}