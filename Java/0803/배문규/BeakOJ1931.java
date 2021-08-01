package study.date0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BeakOJ1931 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 회의 시간들 추가
		int[][] time = new int[N][];
		for(int i = 0; i < N; i++) {
			int[] data = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++) 
				data[j] = Integer.parseInt(st.nextToken());
			time[i] = data;
		}
		
		//Lambda Sort
		Arrays.sort(time, new Comparator<int[]>() {
			@Override
			public int compare(int[] t1, int[] t2) {
				// 시작 시간이 같으면,
				if(t1[0] == t2[0])
					// 종료 시간이 빠른 회의가 앞으로 오게 소팅
					return t1[1] - t2[1];
				else
					return t1[0] - t2[0];
			}
		});
		
		// 스케줄링을 할 room 변수 생성 - add를 위해 List를 사용
		List<int[]> room = new ArrayList<int[]>();
		
		// 가장 먼저 시작하는 회의는 일단 추가
		room.add(time[0]);
		
		for(int i = 1; i < N; i++) {
			// 변경시킬 수도 있는 마지막 인덱스 저장
			int lastidx = room.size()-1;
			
			// 아직 스케줄링 되지 않은 회의의 시작시간이
			// 스케줄링 된 마지막 회의의 끝나는 시간 보다 빨리 시작하면 
			if(room.get(lastidx)[1] > time[i][0]) {
				
				// 두 회의의 종료시간을 비교하여
				// 아직 스케줄링 되지 않은 회의가 더 빨리 끝나거나 동시에 종료되면
				if(room.get(lastidx)[1] >= time[i][1]) 
					// 해당 회의로 교체함
					room.set(lastidx, time[i]);
			}
			
			// 아직 스케줄링 되지 않은 회의의 시작시간이
			// 스케줄링 된 마지막 회의의 끝나는 시간 이후 시작하면 회의 추가
			else room.add(time[i]);
		}
		
		System.out.println(room.size());
	}
	
}