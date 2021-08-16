package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
태그인 상태와 태그가 아닌 상태를 어떻게 잘 구분할 수 있을까 고민했는데,
문자열 최대길이 만큼 boolean 배열을 선언하여
태그인 상태와 태그가 아닌상태를 각 인덱스마다 관리해야겠다 생각함
토글변수를 통해 < 문자를 만나면 태그상태에 진입했다를 표시하고
> 문자를 만나면 태그상태가 끝냈다고 표시함

메모리나 시간적인 측면에서 최선은 아니겠지만 일단 통과를 목표로 함
메모리    시간
19840   216
*/

public class BaekOJ17413_배문규{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();
    static StringBuffer sb4reverse = new StringBuffer();
    // 문자열에서 태그인 상태와 아닌 상태를 관리
    static boolean[] isTag = new boolean[100001];
    // '>', '<' 를 만나면 토글 on, off
    static boolean toggle;

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++){
            // 현재 문자가 '>'면 토글 on
            if(str.charAt(i) == '<') toggle = true;
            // 토글이 on이고, 직전 문자가 '>'면 토글 off
            else if(toggle && str.charAt(i-1) == '>') toggle = false;

            // 토글 on이면 태그인 상태
            if(toggle) isTag[i] = true;
        }

        for(int i = 0; i < str.length(); i++){      
            // 태그면      
            if(isTag[i]){
                // 역순 스트링빌더를 문자열에 더하고 역순 스트링빌더 초기화
                // 초기화 된 상태면 건더 뜀
                if(sb4reverse.length() != 0){
                    sb.append(sb4reverse.reverse());
                    sb4reverse.setLength(0);
                }

                // 문자 그대로 추가
                sb.append(str.charAt(i));
            }

            // 태그가 아니고 공백이면
            else if(str.charAt(i) == ' ') {
                // 그동안 역순 스트링빌더에 쌓인 문자들을 역순으로 문자열에 추가하고 공백도 추가
                sb.append(sb4reverse.reverse()).append(str.charAt(i));
                // 역순 스트링빌더 초기화
                sb4reverse.setLength(0);
            }
            else{
                // 역순 스트링빌더에 문자 추가
                sb4reverse.append(str.charAt(i));
            }
        }

        // 역순 스트링빌더에 문자열이 남아있다면 추가하고 추가
        System.out.println(sb.append(sb4reverse.reverse()));
    }
}