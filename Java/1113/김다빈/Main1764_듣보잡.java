import java.io.*;
import java.util.*;

/**
 * 백준 1764번 듣보잡 
 * 
 * 1. 듣도 못한 사람과 보도 못한 사람을 Map에 각각 저장 
 * 2. 듣도 못한 사람 기준 보도 못한 사람에 겹치는 사람이 있으면 ArrayList에 추가 
 * 3. ArrayList 사전순으로 정렬 후 결과 출력 
 * 
 * 28712KB	360ms
 */
public class Main1764_듣보잡 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());	// 듣도 못한 사람 수 
        int M = Integer.parseInt(st.nextToken());	// 보도 못한 사람 수 
        
        Map<String, Integer> notHear = new HashMap<String, Integer>();
        Map<String, Integer> notSee = new HashMap<String, Integer>();
        
        // 듣도 못한 사람 입력받기 
        while(N-- > 0) notHear.put(br.readLine(), null);
        
        // 보도 못한 사람 입력받기 
        while(M-- > 0) notSee.put(br.readLine(), null);
        
        // 듣도 못한 사람을 기준으로 보도 못한 사람 중 겹치는 사람이 있으면 결과값에 추가 
        ArrayList<String> result = new ArrayList<String>();
        for (String person : notHear.keySet()) {
			if(notSee.containsKey(person)) result.add(person);
		}
        
        // 결과값 사전순으로 정렬 
        Collections.sort(result);
        
        sb.append(result.size()+"\n");
        for (String str : result) {
			sb.append(str+"\n");
		}
        System.out.println(sb);
    }
    
}