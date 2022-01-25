import java.io.*;
import java.util.*;

/**
 * [0126] 프로그래머스 해시 위장
 * 해시맵, 부분집합
 * 
 * 부분집합을 직접 모두 구해보지 말고, 아예 해시맵에 (의상 종류 별 의상 개수 + 1)만큼씩 저장하여
 * 현재 의상 종류가 선택되지 않는 경우의 수를 빠르게 구한다.
 * 
 * 시간복잡도 : N * 2
 *
 */

public class PGS_위장2 {
    Map<String, Integer> categories = new HashMap<>();
    int categorySize, subsetCount;
    List<String> categoryNames = new ArrayList<>();
    
    public int solution(String[][] clothes) {
        int answer = 0;
        getCategoryCount(clothes);
        categorySize = categories.size();
        
        if (categorySize == 1) {
            answer = clothes.length;
        } else {
            calcSubset();
            answer = subsetCount-1;
        }
        
        return answer;
    }
    
    public void getCategoryCount(String[][] clothes) {
        for (String[] cloth : clothes) {
            String category = cloth[1];
            if (categories.containsKey(category)) {
                categories.compute(category, (k, v) -> v+1);
            } else {
            	// 최초 등장한 의상 종류는 자기 자신 개수 1개(최초 등장)에 자기 자신 종류가 선택되지 않을 경우의 수를 더해 2개로 설정
                categories.put(category, 2);      
                categoryNames.add(category);
            }
        }
    }
    
    // 모든 가능한 조합 경우의 수 계산
    public void calcSubset() {
    	// 재귀를 통해 현재 의상을 선택하고 선택하지 않는 시뮬레이션 돌릴 필요 없음
    	// 각 의상 종류의 개수에는 현재 의상을 선택하지 않는 경우인 1이 더해져있으므로!!!
        subsetCount = 1;
        for (String name : categoryNames) {
            subsetCount *= categories.get(name);
        }
    }
}