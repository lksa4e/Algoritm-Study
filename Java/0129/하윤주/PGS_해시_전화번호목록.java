import java.io.*;
import java.util.*;

/**
 * [0126] 프로그래머스 해시 전화번호 목록
 * 해시맵, 문자열
 * 
 * 길이가 1인것부터 자신의 길이까지 접두사를 모두 해시맵에 넣어보고 키로 갖고있으면 오답
 * 
 * 시간복잡도 : N * M(1,000,000*20 = 20,000,000)
 *
 */

public class PGS_해시_전화번호목록 {
	
    public boolean solution(String[] phone_book) {
        boolean answer = sortAndHashing(phone_book);
        return answer;
    }
    
    public boolean sortAndHashing(String[] phone_book) {
        Map<String, Boolean> prefixs = new HashMap<>();
        
        // 모든 전화번호에 대해
        for (String number : phone_book) {
        	// 앞에서부터 길이가 (1~자기자신길이)인 부분문자열을 잘라내어 해시맵 키로 갖고있는지 확인
            for (int size=number.length(), i=1; i<=size; i++) {
                String sub = number.substring(0, i);
                if (prefixs.containsKey(sub)) return false;     // 갖고있으면 접두사 일치
            }
            prefixs.put(number, true);
        }
        return true;
    }
}