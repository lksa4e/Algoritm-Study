import java.util.*;

/*
 * ���α׷��ӽ� �ؽ� - ����
 * 
 * �� ���� ���� �� �ִ� ������ ���ϴ� ����.
 * �ؽ÷� �� ī�װ� ���� �� ���� �ִ��� ������ ��, 
 * ���� * ������ ������ ���ߴ�.
 * �׸��� �� �Դ� ����� ���� ���ؾ� �ϱ� ������ (���� + 1) * (���� + 1) * ...
 * �� �� ��, ��� �� �Դ� ����� �� 1�� �����.
 */

public class PG_���� {
	public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        int size = clothes.length;
        
        // �� ���� ���� �� ���� �ִ��� ����
        for(int i = 0; i < size; i++) {
            String curr = clothes[i][1];
            if(map.get(curr) != null)
                map.put(curr, map.get(curr) + 1);
            else
                map.put(curr, 1);
        }
        
        size = map.size();
        
        int answer = 1;
        // entrySet���� Map���� �����ֱ�
        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            answer *=  entry.getValue() + 1;
        }
                    
        return answer - 1;
    }
}
