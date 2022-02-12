import java.util.*;

/*
 * ���α׷��ӽ� �Ҽ�ã��
 * 
 * ������ �̿��� ����, ��� ���� ������ ���� ����
 * depth�� 0���� ũ�� �׶� �׶� �ٷ� ���ڸ� ���� �Ҽ����� üũ�����.
 * 
 */

class PG_�Ҽ�ã�� {
    static boolean[] visited;
    static int[] nums;
    static int answer = 0, size;
    static Map<Integer, Boolean> map = new HashMap<>();	// hashmap���� ���� �ߺ�üũ
    
    
    public int solution(String numbers) {
        size = numbers.length();
        nums = new int[size];
        for(int i = 0; i < size; i ++) {	// ���� �̱� �����ϱ� ���ؼ� numbers ��Ʈ���� int �迭�� ����
            nums[i] = numbers.charAt(i) - '0';
        }
        
        visited = new boolean[size];
        int[] result = new int[size];
        perm(0, result);	// ����
        
        return answer;
    }
    
    public void perm(int depth, int[] result) {
        if(depth > 0) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < depth; i++) {	// ���� depth ���̸�ŭ ����Ǿ� �ֱ� ������ depth ���� ��ŭ�� ������ش�.
                sb.append(result[i]);
            }
            if(sb.length() == 0) return;
            
            countPrime(Integer.parseInt(sb.toString()));
            
            if(depth == size)	// ���� ����, depth�� size�� �Ǹ� return�Ѵ�.
                return;
        }
        
        for(int i = 0; i < size; i++) {	// ���� �ڵ�
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = nums[i];
                perm(depth+1, result);
                visited[i] = false;
            }
        }
        
    }
    
    public void countPrime(int num) {	// �Ҽ��� �´��� üũ�ϴ� �ڵ�
        if(map.get(num) != null) return;	// {1,1}�� ��� ������ �ٲ� ���� �����̱� ������ map���� �ߺ��� üũ�ߴ�.
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