import java.io.*;
import java.util.*;

/**
 * [0212] PGS 완전탐색 소수 찾기
 * 완전탐색, 순열, 소수 구하기, 에라토스테네스의 체
 * 
 * sol)
 * 에라토스테네스의 체 알고리즘을 이용해 0부터 7자리 수들의 소수 여부를 구한다.
 * 이후 순열을 이용해 문자열로 만들 수 있는 수를 모두 구한 뒤
 * 각각의 수가 소수라면 해시맵에 추가한다.
 * 최종 소수의 개수는 해시맵의 길이가 됨!
 *
 */

public class PGS_완전탐색_소수찾기 {
    final int INF = 10000000;
    int size;
    boolean[] isPrime = new boolean[INF];     // 0 ~ 10000000까지 수들이 각각 소수인지 여부
    int[] number;
    HashMap<Integer, Boolean> paperPrimeNumbers = new HashMap<>();    // 주어진 문자열에서 만들 수 있는 소수를 저장

    public int solution(String numbers) {
        size = numbers.length();
        number = new int[size];
        // 에라토스테네스의 체로 소수 먼저 구하고
        getPrimeNumbers();
        // 문자열 숫자 조합한 순열 각각의 경우가 소수인지 체크하여 개수 구하기
        int answer = countPrimeNumbers(numbers, "", new boolean[size]);
        return answer;
    }

    // 에라토스테네스의 체로 소수 구하기
    public void getPrimeNumbers() {
    	// 모든 수를 소수라고 초기화한 뒤
        for (int i=0; i<INF; i++) isPrime[i] = true;
        // 0, 1은 소수가 아니므로 초기화
        isPrime[0] = isPrime[1] = false;
        // 2부터 소수인 수는 자신의 배수를 모두 소수가 아닌 수로 갱신
        for (int i=2; i*i<INF; i++) {     // 이때 전체의 제곱근까지만 찾음
            if (isPrime[i]) {
            	// i*i 이하는 소수 판별 끝났으므로 이상부터 배수만 소수 아닌 수로 갱신
                for (int j=i*i; j<INF; j+=i) isPrime[j] = false;
            }
        }
    }

    // 순열 구하기
    public int countPrimeNumbers(String numbers, String str, boolean[] visited) {
        for (int i=0; i<size; i++) {
        	// 방문 처리
            if (visited[i]) continue;
            visited[i] = true;
            // 현재 수에 다음 수를 붙인 경우의 수, 이를 정수로 파싱
            String nextStr = str + numbers.charAt(i);
            int num = Integer.parseInt(nextStr);
            // 파싱한 수가 소수면 해시맵에 추가
            if (isPrime[num]) paperPrimeNumbers.put(num, true);
            // 다음 수를 붙이러 재귀 호출
            countPrimeNumbers(numbers, nextStr, visited);
            // 재귀에서 반환되면 방문 해제
            visited[i] = false;
        }
        // 해시맵 크기 구하기
        return paperPrimeNumbers.size();
    }
}
