import java.io.*;
import java.util.*;

/**
 * [0129] PGS 정렬 가장 큰 수
 * 정렬, 문자열
 * 
 * sol)
 * Comparator 인터페이스의 compare() 메서드를 오버라이드하여
 * 두 문자열을 이어 붙인 상태의 크기를 비교한다.
 *    ex. "11" vs "100" -> "11100" vs "10011"
 *
 */
public class PGS_정렬_가장큰수 {
    boolean isAllZero;       // 모든 숫자가 0으로만 구성되어 있는 경우 0을 리턴하기 위한 용도
    int size;
    String[] stringNums;

    public String solution(int[] numbers) {
        String answer;
        size = numbers.length;
        stringNums = new String[size];
        // 배열 숫자를 문자열로 대치한 뒤
        changeNumberToString(numbers);
        // 모든 숫자가 0이면 "0"을 리턴하고
        if (isAllZero) return answer = "0";
        else {
        	// 아니면 문자열끼리 비교하여 내림차순 정렬
            sortStrings();
            // 정렬 결과 이어붙여 리턴
            answer = appendStrings();
        }

        return answer;
    }

    // 숫자를 문자열로
    public void changeNumberToString(int[] numbers) {
        isAllZero = true;

        for (int i=0; i<size; i++) {
            if (numbers[i] != 0) isAllZero = false;
            stringNums[i] = Integer.toString(numbers[i]);
        }
    }

    // 문자열 내림차순 정렬
    public void sortStrings() {
    	// Comparator 인터페이스의 compare() 메서드를 재정의하여 사용
        Arrays.sort(stringNums, new Comparator<String>() {
        	
            @Override
            public int compare(String a, String b) {
            	// 길이가 같다면 문자열끼리 비교
                if (a.length() == b.length()) return b.compareTo(a);
                else {
                	// 길이가 다르다면 두 문자열을 이어붙인 상태로 문자열 비교
                    String ab = a+b;
                    String ba = b+a;
                    return ba.compareTo(ab);
                }
            }
        });
    }

    // 정렬된 문자열을 이어붙임
    public String appendStrings() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<size; i++) {
            sb.append(stringNums[i]);
        }
        return sb.toString();
    }
}
