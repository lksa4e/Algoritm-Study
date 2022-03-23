import java.util.*;

/*
 * ���α׷��ӽ� N���� ǥ��
 * 
 * ����� �� ã�Ƽ� ��Ŵٰ� �����ϱ⿡�� ��Ʈ�� ��� Ǯ����.
 * ��������� ä�������� ������ Ǯ�� �ȴ�.
 * ���� ��� (5)�� N�� 5�� �Ἥ ���� �� �ִ� ��� ����� ����� �ΰ�
 * <>�� ��Ģ �����̶�� �ΰ�, +�� ����� ���� ��ģ�ٴ� �ǹ̷� �θ�
 * 
 * (2) = (1) <> (1)
 * (3) = (1) <> (2) + (2) <> (1)
 * (4) = (1) <> (3) + (2) <> (2) + (3) <> (1)
 * ...
 * 
 * �̷������� �Ʒ����� ä������ ������ Ǯ �� �ִ�.
 */

class PG_N����ǥ�� {
    public int solution(int N, int number) {
        
        List<Integer> nCases[] = new ArrayList[9];
        nCases[1] = new ArrayList<Integer>();
        nCases[1].add(N);
        if(N == number)
            return 1;
        
        int answer = -1;
        
        for(int i = 2; i < 9; i++) {
            nCases[i] = new ArrayList<Integer>();
            
            countAllCase(nCases, i, N);
            if(nCases[i].contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    
    void countAllCase(List<Integer> nCases[], int num, int N) {
        HashSet<Integer> hash = new HashSet<>();
        // NN�� ���, ex) 22, 222, 2222
        int x = N;
        for(int i = 1; i < num; i++) {
            x *= 10;
            x += N;
        }
        nCases[num].add(x);
        hash.add(x);
        

        for(int i = 1; i < num; i++) {
            // ��� ����� �� ��ġ�� (1�� ���, num - 1�� ���), (2�� ���, num - 2 ���) ,,,
            List<Integer> first = nCases[i];
            List<Integer> second = nCases[num - i];
            
            // ��Ģ ����
            for(int a = 0, aSize = first.size(), bSize = second.size(); a < aSize; a++) {
                int num1 = first.get(a);
                for(int b = 0; b < bSize; b++) {
                    int num2 = second.get(b);
                    
                    // ���ϱ�
                    int result = num1 + num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // ����
                    result = num1 - num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // ���ϱ�
                    result = num1 * num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // ������ ( / 0 ����)
                    if(num2 != 0) {
                        result = num1 / num2;
                        if(!hash.contains(result)) {
                            hash.add(result);
                            nCases[num].add(result);
                        }
                    }
                    
                }
            }
        }
    }
    
    
}