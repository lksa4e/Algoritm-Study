/*
 * ���α׷��ӽ� ���̽�ƽ
 * 
 * ���� ������ �˾Ҵµ� ���� ������� ����
 * 
 * ���ĺ� ��ȯ�ϴ°� A���� ����, Z���� ���� ����ؼ� ������ �����ϸ� �ȴ�.
 * 
 * Ŀ���� �Űܼ� �ּ� �Ÿ��� ���ϴ°� ������µ�,
 * ���� ���� ��ġ���� A ������ ������, �ǵ��ư��°� ������ ���Ѵ�.
 * �ǵ��� ���°� �����ٸ� ������� �� i���� �ǵ��ư��� i���� ���ϰ�, �ǵ��� ���� ���� �����ش�.
 * �׸��� ���� ó������ �ǵ��ư��� ���°� �����ٸ� ó������ �ǵ��ư��ٿ� �� + ���� index�� �����ش�.
 * �׸��� �� �� �߿��� �ּڰ��� ���� ��ġ���� ���� �ּڰ��� �ȴ�.
 * 
 */
class PG_���̽�ƽ {
    public int solution(String name) {
        int answer = 0;
        
        int len = name.length(), minLen = len - 1;
        
        for(int i = 0; i < len; i++) {
            answer += convert(name.charAt(i));
            
            int nextIdx = i + 1;
            while(nextIdx < len && name.charAt(nextIdx) == 'A')
                nextIdx++;
            
            // �߰� ��ġ���� �ǵ��� ���°� ���� ���,
            minLen = Math.min(minLen, i * 2 + len - nextIdx);
            // ó������ �ڷ� ���ٰ� �ٽ� ���ƿ��°� ���� ���.
            minLen = Math.min(minLen, (len - nextIdx) * 2 + i);
        }
        
        answer += minLen;
        
        return answer;
    }
    
    int convert(char target) {
        return Math.min(target - 'A', 'Z' - target + 1);
    }

}