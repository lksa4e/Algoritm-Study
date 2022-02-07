import java.io.*;
import java.util.*;

/**
 * [0209] PGS 스택큐 다리를 지나는 트럭
 * 큐
 * 
 * sol)
 * 배열 트럭 순서대로 현재 트럭이 큐에 삽입될 수 있는지(큐 무게 합이 제한 무게보다 작을 때) 확인하고 큐에 삽입한다.
 * 각 트럭이 큐에 삽입되면 자신이 삽입된 시각을 기록한다.
 * 큐 가장 앞에 위치한 트럭이 삽입된 시각을 확인하여 다리 길이만큼 시간이 경과했다면 큐에서 제거하고 큐 무게 합에서도 차감한다
 * 
 * 위 과정을 마지막 트럭까지 반복한다.
 * 만약 마지막 트럭이 큐에서 제거됐다면 현재 시각이 정답이 되고, 
 * 아니라면 마지막 트럭만 다리를 지나면 되므로 현재 시각에 다리길이를 더하면 정답이 된다.
 *
 */

public class PGS_스택큐_다리를지나는트럭 {
    int size, time = 0;
    Queue<Integer> bridge = new ArrayDeque<>();    // 현재 다리에 존재하는 트럭을 저장
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        size = truck_weights.length;
        passBridge(bridge_length, weight, truck_weights);
        int answer = time;
        return answer;
    }
    
    // 큐에 트럭을 삽입하며 총 경과시간 계산
    public void passBridge(int bridge_length, int weight, int[] truck_weights) {
        int truck = 0;
        int sum = 0;
        int[] beginTimes = new int[size];     // 각 트럭이 큐에 삽입된 시각을 기록
        
        while(truck<size) {
            time++;
            int nextTruck = truck_weights[truck];
            
            // 만약 현재 트럭이 다리 무게 제한 이내라면
            if (sum+nextTruck <= weight) {
                sum += nextTruck;              // 큐 무게 합에 자신의 무게를 누적하고
                bridge.offer(truck);           // 큐에 삽입한 뒤
                beginTimes[truck++] = time;    // 삽입된 시각을 기록
            }
            
            // 만약 큐 맨 앞 트럭이 시간을 다 채워 다리에서 나갈 수 있어지면
            int headTruck = bridge.peek();
            if ((time-beginTimes[headTruck]) == (bridge_length-1)) {
                sum -= truck_weights[bridge.poll()];       // 큐에서 제거한 뒤 큐 무게 합에서도 자신의 무게를 차감
            }
            
            // 만약 마지막 트럭만 다리에 남아있다면 현재 시각에 다리의 길이만큼을 더함(while문 조건에 의해 마지막 트럭은 이제 방금 큐에 삽입됨)
            if (!bridge.isEmpty() && truck == size) time += bridge_length;
            
        }
    }
}