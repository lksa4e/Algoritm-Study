import java.util.*;

/*
 * 프로그래머스 소수찾기
 * 
 * 순열을 이용한 문제, 대신 기저 조건이 따로 없고
 * depth가 0보다 크면 그때 그때 바로 숫자를 만들어서 소수인지 체크해줬다.
 * 
 */

class PG_소수찾기 {
    static boolean[] visited;
    static int[] nums;
    static int answer = 0, size;
    static Map<Integer, Boolean> map = new HashMap<>();	// hashmap으로 숫자 중복체크
    
    
    public int solution(String numbers) {
        size = numbers.length();
        nums = new int[size];
        for(int i = 0; i < size; i ++) {	// 숫자 뽑기 쉽게하기 위해서 numbers 스트링을 int 배열에 저장
            nums[i] = numbers.charAt(i) - '0';
        }
        
        visited = new boolean[size];
        int[] result = new int[size];
        perm(0, result);	// 순열
        
        return answer;
    }
    
    public void perm(int depth, int[] result) {
        if(depth > 0) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < depth; i++) {	// 현재 depth 길이만큼 저장되어 있기 때문에 depth 길이 만큼만 만들어준다.
                sb.append(result[i]);
            }
            if(sb.length() == 0) return;
            
            countPrime(Integer.parseInt(sb.toString()));
            
            if(depth == size)	// 기저 조건, depth가 size가 되면 return한다.
                return;
        }
        
        for(int i = 0; i < size; i++) {	// 순열 코드
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = nums[i];
                perm(depth+1, result);
                visited[i] = false;
            }
        }
        
    }
    
    public void countPrime(int num) {	// 소수가 맞는지 체크하는 코드
        if(map.get(num) != null) return;	// {1,1}의 경우 순서가 바뀌어도 같은 숫자이기 때문에 map으로 중복을 체크했다.
        map.put(num, true);
        
        if(num == 0 || num == 1) return;
        int limit = (int)Math.sqrt(num);
        for(int i = 2; i <= limit; i++) {
            if(num % i == 0) return;
        }
        System.out.println(num);
        answer++;
        return;
    }
}