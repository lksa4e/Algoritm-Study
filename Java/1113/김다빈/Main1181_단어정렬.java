import java.io.*;
import java.util.*;

/**
 * 백준 1181번 단어정렬
 * 
 * 1. 길이가 짧은 것부터
 * 2. 길이가 같으면 사전 순으로
 * 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.
 * 
 * 29296KB	380ms
 */
public class Main1181_단어정렬 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> input = new ArrayList<String>();
        
        // 단어 입력받기 
        while(N-- > 0) {
        	input.add(br.readLine());
        }
        
        // 단어 정렬하기 
        Collections.sort(input, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {	// 2. 길이가 같으면 사전순으로 출력 
					return o1.compareTo(o2);
				} else {	// 1. 길이가 짧은 것부터 
					return o1.length() - o2.length();
				}
			}
		});
        
        // 단어 출력하기 - 이전 출력했던 문자열과 다르면 출력
        String prev = "", cur = "";
        for (int i = 0; i < input.size(); i++) {
        	cur = input.get(i);
        	
			if(!prev.equals(cur)) {
				sb.append(cur+"\n");
				prev = cur;
			}
		}
        
        System.out.println(sb);
    }
    
}