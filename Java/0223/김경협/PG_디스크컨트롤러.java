import java.util.*;

/*
 * 프로그래머스 - 디스크 컨트롤러
 * 
 * 프로세스 스케쥴러로 시간 관리해서 평균 값 줄이기.
 * 
 * 두 개의 PQ를 사용했다.
 * 첫 번째 PQ에는 현재 있는 모든 jobs를 넣어준다. 이 때, 시작 시간이 빠른 순서대로,
 * 시작 순서가 같다면 총 작업시간이 짧은 순서대로 정렬한다.
 * 두 번째 PQ에는 작업 시간이 짧은 순서대로만 정렬한다.
 * 
 * idx를 사용하여 0초 부터 끝날 때 까지 1초씩 늘리면서 진행한다.
 * 첫 번째 PQ의 peek에서 요청이 시작하는 시간과 idx가 같아지면  두번 째 PQ로 넣어준다.
 * 두 번째 PQ에서는 현재 작업 중인 일이 없다면 바로 꺼내서 작업을 시작하고,
 * 작업 중이었다면 작업이 끝날 때까지 기다렸다가 작업을 시작한다.
 * 첫번째 PQ와 두번째 PQ가 비워질 때까지 계속한다.
 * 
 */

class PG_디스크컨트롤러 {
    public int solution(int[][] jobs) {
    	// 첫 번째 PQ, 모든 jobs를 넣어준다. 시작 시간이 빠른 순서대로, 같다면 작업시간이 짧은 순서대로 정렬한다.
        PriorityQueue<int[]> jobScheduler = new PriorityQueue<>((o1,o2)->o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));
        // 두 번째 PQ, 작업 시간이 짧은 순서대로만 정렬한다.
        PriorityQueue<int[]> tasks = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        
        // todo는 현재 해야 하는 일을, idx는 현재 시간을, sum은 총 작업시간을 의미한다.
        int todo = 0, idx = 0, sum = 0;
        
        for(int[] job : jobs) {	// 모든 작업을 첫 번째 PQ에 넣어준다.
            jobScheduler.offer(job);
        }
        
        while(true) {
        	// 첫 번째 PQ가 비어있지 않고, 현재 peek인 일의 시작시간과 현재 시간이 같다면 두 번째 PQ로 옮겨준다.
            while(!jobScheduler.isEmpty() && jobScheduler.peek()[0] == idx) {
                tasks.offer(jobScheduler.poll());
            }
            if(todo == 0) {	// 현재 작업 하는 일이 없는 상태,
                if(tasks.size() == 0) {
                    if(jobScheduler.size() == 0) break; // 모든 PQ가 비어 있으므로 종료
                    idx++;	// 아직 시작시간이 안되어서 두 번째 PQ에 들어오지 않은 작업이 있으므로 idx만 올려주고 continue
                    continue;
                }
                int[] nextWork = tasks.poll();	// 다음 작업 뽑아오기
                todo = nextWork[1];	// 다음 작업 할당량
                sum += todo + idx - nextWork[0];	// 작업의 대기 시간은 작업량 + 현재 시간 - 시작시간
            }
            todo--;
            idx++;
        }
        int answer = sum / jobs.length;
        return answer;
    }
}