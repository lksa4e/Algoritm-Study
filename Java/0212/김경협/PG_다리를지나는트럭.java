import java.util.*;

/*
 * 프로그래머스 다리를 지나는 트럭
 * 
 * 다리가 견딜 수 있는 무게 weight와 다리의 길이,
 * 그리고 트럭들의 무게가 주어진다.
 * Queue를 이용해 트럭이 지나는 경우를 계산, 1초마다 트럭을 q에 넣어주고,
 * 무게 초과로 트럭을 못 넣을 경우 0을 대신 넣었다. (큐의 사이즈가 다리의 길이에 도달했을 경우 꺼내줘야하기 때문에)
 */

class PG_다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int curr_w = 0, idx = 0, size = truck_weights.length;   // 각각 현재 다리에 있는 트럭들 무게, 참조변수, 트럭들 개수
        
        Queue<Integer> q = new LinkedList<>();
        
        while(true) {
            if(idx == size) break;  // 마지막 트럭이 다리에 올라가면 루프 탈출
            
            // 다리에 있는 트럭이 다리 끝에 도착하면, 다리 위 트럭들 무게 갱신
            if(q.size() == bridge_length) {
                curr_w -= q.poll();
            
            } else if (curr_w + truck_weights[idx] > weight) {
                // 트럭을 못 올릴 경우,
                q.offer(0);
                answer++;
            } else {
                q.offer(truck_weights[idx]);
                curr_w += truck_weights[idx];
                answer++;
                idx++;
            }
        }
        
        // 마지막 트럭이 다리에 올라간 상태에서 break 되었기 때문에, 다리 길이만큼 더해줘서 return
        return answer + bridge_length;
    }
}