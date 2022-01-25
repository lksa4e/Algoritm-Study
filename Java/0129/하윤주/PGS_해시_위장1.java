import java.io.*;
import java.util.*;

/**
 * [0126] 프로그래머스 해시 위장
 * 해시맵, 부분집합
 * 
 * 1. 각 배열에서 2번째 원소를 해시맵의 키로 설정하고 해당 키의 값으로 1을 증가시킨다.
 * 2. 전체 키를 통해 부분집합을 구한다.
 * 3. 각 부분집합을 확인하며 값의 개수로 경우의 수를 곱한다.
 * 
 * 시간복잡도 : 2^N (부분집합) = 2^30 시간초과...ㅎㅎ
 *
 */

public class PGS_해시_위장1 {
    Map<String, Integer> categories = new HashMap<>();    // 키 : 의상 종류, 값 : 종류별 의상 개수
    int categorySize, subsetCount;
    List<String> categoryNames = new ArrayList<>();       // 키에 해당하는 의상 종류를 저장한 리스트(해시맵 인덱싱 용도)
    int[] subset;                                         // 각 부분집합의 가능한 경우(의상종류를 선택하거나 선택하지 않음)
    
    public int solution(String[][] clothes) {
        int answer = 0;
        
        // 해시맵에 의상 종류 별 개수를 저장
        getCategoryCount(clothes);
        categorySize = categories.size();
        
        if (categorySize == 1) {                 // 의상 종류가 1개일때는 자기 자신의 개수를 빠르게 정답으로
            answer = clothes.length;
        } else {                                 // 의상 종류가 2개 이상일때는 부분집합으로 의상을 선택
            subset = new int[categorySize];
            calcSubset(0);                       // 부분집합 구하기
            answer = subsetCount-1;              // 부분집합에는 모두 선택하지 않는 경우가 포함되므로 -1
        }
        
        return answer;
    }
    
    // 해시맵에 의상 종류 별 개수 저장
    public void getCategoryCount(String[][] clothes) {
        for (String[] cloth : clothes) {
            String category = cloth[1];
            if (categories.containsKey(category)) {
                categories.compute(category, (k, v) -> v+1);    // 키가 존재할 때
            } else {
                categories.put(category, 1);                    // 최초 등장하는 의상 종류
                categoryNames.add(category);
            }
        }
    }
    
    // 부분집합으로 종류별 의상을 선택하고 선택하지 않으며 경우의 수 계산
    public void calcSubset(int n) {
    	// n개의 종류를 모두 고려했다면 각 종류를 선택한 경우만 개수를 곱하여 조합 경우의 수 계산
        if (n==categorySize) {
            int total = 1;
            for (int i=0; i<categorySize; i++) {
                if (subset[i]!=0) total *= subset[i];
            }
            subsetCount += total;
            return;
        }
        
        // subset[] 배열에는 각 의상 종류가 선택될 경우 해당 종류의 의상 개수를 저장
        subset[n] = categories.get(categoryNames.get(n));
        calcSubset(n+1);

        // 의상 종류가 선택되지 않으면 0을 저장
        subset[n] = 0;
        calcSubset(n+1);
    }
}