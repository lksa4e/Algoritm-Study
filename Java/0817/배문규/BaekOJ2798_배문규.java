package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
무조건 카드 3개를 뽑는다고 하여서 따로 조합코드를 짜는 것 보다
그냥 3중 for문을 돌리는 것이 가장 깔끔하다고 생각함.
i,j,k 번째의 카드의 합을 구해서 M의 넘지 않는 최대값을  찾아냄

메모리      시간
14156	128
*/

public class BaekOJ2798_배문규 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();
    static StringTokenizer st = null;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] card = new int[N];
        for(int i = 0; i < N; i++) card[i] = Integer.parseInt(st.nextToken());

        int result = 0;
        // 3가지 카드 조합을 3중 for문으로 찾기 
        for(int i = 0; i < N-2; i++){
            for(int j = i+1; j < N-1; j++){
                for(int k = j+1; k < N; k++){
                    int _sum = card[i] + card[j] + card[k];
                    if(_sum <= M && _sum >= result) result = _sum;
                }
            }
        }

        System.out.println(result);
    }
}