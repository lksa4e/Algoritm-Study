import java.util.*;

/**
 * [0219] PGS 힙(Heap) 더맵게
 * 힙, 우선순위 큐, 그리디, sjf 알고리즘
 * 
 * sol)
 * 총 소요시간을 줄이기 위해서는 대기 시간을 줄여야하고, 이를 위해서는 수행 시간이 짧은 작업을 우선적으로 마쳐야 한다.
 * 
 * 작업을 요청이 들어온 순서로 정렬한다. 요청시간이 같은 것 중에서는 수행 시간이 짧은 작업을 우선적으로 수행한다.
 * 하나의 작업이 끝나면 현재 시각은 갱신되는데, 이 시각을 기준으로 현재 시각 이전에 요청이 들어와 대기중인 작업들을 확인한다. 
 * 
 * 만약 대기중인 작업이 존재해 곧바로 다음 작업을 시작할 수 있다면 수행 시간이 가장 짧은 작업을 우선적으로 수행한다.
 * 하지만 대기중인 작업이 없다면 다음으로 요청이 빨리 들어온 작업을 수행한다.
 * 
 * 전자의 상황에서 총 소요시간은 순수 작업 수행 시간에 미리 요청이 들어와 대기중이던 시간을 포함하고,
 * 후자의 상황에서는 순수 작업 수행 시간만을 포함한다
 *
 */

public class PGS_힙_디스크컨트롤러 {
    final int ENTER_TIME = 0;        // 작업 요청이 들어온 시간
    final int WORKING_TIME = 1;      // 작업 수행 시간
    int size;
    int totalTime = 0;
    
    public int solution(int[][] jobs) {
        size = jobs.length;
        // 작업들을 요청이 먼저 들어온 순서로 정렬
        Arrays.sort(jobs, (a, b) -> { return a[ENTER_TIME] == b[ENTER_TIME] ? a[WORKING_TIME] - b[WORKING_TIME] : a[ENTER_TIME] - b[ENTER_TIME]; });
        // 총 소요시간 계산
        calcTotalTime(jobs);
        int answer = (int) totalTime / size;
        return answer;
    }
    
    // 총 소요시간 계산
    public void calcTotalTime(int[][] jobs) {
    	// 큐는 수행 시간이 짧은 작업, 수행 시간이 같다면 요청 시간이 이른 것을 우선적으로 poll() 할 수 있도록 정렬
        PriorityQueue<int[]> workingTimeSortedJobs = new PriorityQueue<>((a, b) -> {
            return a[WORKING_TIME] == b[WORKING_TIME] ? a[ENTER_TIME] - b[ENTER_TIME] : a[WORKING_TIME] - b[WORKING_TIME];
        });
        int endTime = 0;        // 이전 작업이 끝나고 난 후 현재 시각
        int finished = 0;        // 완료한 작업의 수
        int i = 0;
        int enterTime = 0;
        int workingTime = 0;
        
        while(finished++ < size) {
            while (i<size) {
            	// 현재 시각(이전 작업을 종료하고 난 뒤 현재 시각)을 기준으로 요청이 들어와 대기 중인 작업들을 모두 찾아 큐에 삽입(바로 시작할 수 있는 작업들을 찾는 과정)
                if (jobs[i][ENTER_TIME] <= endTime) workingTimeSortedJobs.offer(jobs[i++]);
                else break;
            }
            
            // 만약 현재 시각까지 요청이 들어와 대기중인 작업이 없다면 그 다음으로 요청이 가장 빨리 들어온 작업 수행
            if (workingTimeSortedJobs.isEmpty()) {
                enterTime = jobs[i][ENTER_TIME];
                workingTime = jobs[i++][WORKING_TIME];
                endTime = enterTime + workingTime;                    // 현재 시각을 다음 작업이 요청 들어오고 작업 수행한 이후로 재설정
                totalTime += workingTime;                             // 총 소요 시간은 다음 작업의 순 수행 시간
            } else {      // 대기중인 작업이 있으면 수행 시간이 짧은 것부터 수행
                int[] shortestJob = workingTimeSortedJobs.poll();     // 수행 시간이 가장 짧은 작업
                enterTime = shortestJob[ENTER_TIME];
                workingTime = shortestJob[WORKING_TIME];
                endTime += workingTime;                               // 현재 시각을 재설정
                totalTime += (endTime - enterTime);                   // 총 소요시간에 대기 시간까지 포함해야 함
            }
        }
    }
}