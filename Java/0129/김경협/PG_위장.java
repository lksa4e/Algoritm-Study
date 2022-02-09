import java.util.*;

/*
 * 프로그래머스 해시 - 위장
 * 
 * 옷 별로 입을 수 있는 조합을 구하는 문제.
 * 해시로 옷 카테고리 별로 몇 개씩 있는지 구해준 뒤, 
 * 종류 * 종류로 조합을 구했다.
 * 그리고 안 입는 경우의 수도 구해야 하기 때문에 (종류 + 1) * (종류 + 1) * ...
 * 를 한 뒤, 모두 안 입는 경우의 수 1을 빼줬다.
 */

public class PG_위장 {
	public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        int size = clothes.length;
        
        // 옷 종류 별로 몇 개씩 있는지 집계
        for(int i = 0; i < size; i++) {
            String curr = clothes[i][1];
            if(map.get(curr) != null)
                map.put(curr, map.get(curr) + 1);
            else
                map.put(curr, 1);
        }
        
        size = map.size();
        
        int answer = 1;
        // entrySet으로 Map에서 꺼내주기
        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            answer *=  entry.getValue() + 1;
        }
                    
        return answer - 1;
    }
}
