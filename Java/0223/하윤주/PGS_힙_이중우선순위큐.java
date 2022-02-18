import java.util.*;

/**
 * [0219] PGS 힙(Heap) 이중우선순위큐
 * 최대힙, 최소힙, 우선순위큐, 문자열
 * 
 * sol)
 * 우선순위큐를 Comparator의 compare 메서드를 오버라이딩하여 최대힙, 최소힙으로 구현한다.
 * 두 힙은 같은 연산 결과를 위해 동기화가 진행되어야 한다.
 * 
 * 문자열을 파싱하여 연산을 수행하되 삽입 연산은 최소힙, 최대힙 모두에 동일하게 push()하고,
 * 삭제 연산은 최소힙에서 poll() 하거나 최대힙에서 poll() 한 뒤 나머지 힙에서 remove()해야한다.
 *
 */

public class PGS_힙_이중우선순위큐 {
    int size;
    // 최소힙
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a-b;
        }
    });
    // 최대힙
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return b-a;
        }
    });
    int[] result;
    
    public int[] solution(String[] operations) {
        size = operations.length;
        result = new int[2];
        // 명령어 수행
        operateCommand(operations);
        
        int[] answer = result;
        return answer;
    }
    
    // 명령어 수행
    public void operateCommand(String[] operations) {
        String engCommand;
        int numCommand;
        String[] command = new String[2];
        
        // 명령어 연산 수행
        for (int i=0; i<size; i++) {
            command = operations[i].split(" ");           // 공백을 기준으로 명령어 문자열 파싱
            engCommand = command[0];                      // 알파벳 명령어(연산자)
            numCommand = Integer.parseInt(command[1]);    // 숫자 명령어(피연산자)
            
            if (engCommand.equals("I")) {                 // 삽입 연산은 두 힙에 동일하게 수행
                minHeap.offer(numCommand);
                maxHeap.offer(numCommand);
            } else {
                if (minHeap.isEmpty()) continue;
                
                if (numCommand == 1) {                    // 최댓값 삭제 연산은
                    int max = maxHeap.poll();             // 최대힙에서 poll()한 값을
                    minHeap.remove(max);                  // 최소힙에도 반영
                } else {                                  // 최솟값 삭제 연산은
                    int min = minHeap.poll();             // 최소힙에서 poll()한 값을
                    maxHeap.remove(min);                  // 최대 힙에도 반영
                }
            }
        }
        
        // 명령어 연산이 수행된 뒤 큐가 비어있지 않다면
        if (!maxHeap.isEmpty()) {
            result[0] = maxHeap.poll();                   // 최댓값과 최솟값 산출
            result[1] = minHeap.poll();
        }
    }
}