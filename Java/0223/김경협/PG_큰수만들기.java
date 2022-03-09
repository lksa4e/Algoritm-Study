import java.util.*;

/*
 * ���α׷��ӽ� ū �� �����
 * 
 * ������ Ȱ���ߴ�.
 * �պ��� ���ÿ� �����鼭, peek�� üũ�ؼ� �������� ���� �� ũ�� ���ÿ��� ���� �� üũ�ϰ� ���� �̷������� �����ߴ�.
 * �׷��� �ؼ� ���� K���� �Ǹ� ������ stack�� Ǫ���ϰ�, �� ������� ���ڿ��� ���� ��ȯ�ߴ�.
 */

class PG_ū������� {
    public String solution(String number, int k) {
        
        Stack<Character> stk = new Stack<>();
        
        for(int i = 0, size = number.length(); i < size; i++) {
            char curr = number.charAt(i);
            while(!stk.isEmpty()) {	// ������ ����ų�, ������ ���� ���� �� ũ�ų�, K�� ��ŭ �̹� ���ٸ� �н�,
                if(stk.peek() >= curr) break;
                if(k-- <= 0) break;
                stk.pop();	// �� ������ �ƴ϶��, ���ÿ��� ���ֱ�
            }
            
            stk.push(curr);
        }
        
        // �������� ���� ��, K�ڸ��� ���ܳ��� break
        while(k-- > 0) {
            stk.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        for(char c : stk) {
            sb.append(c);
        }
        return sb.toString();
    }
}