import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// int 페어 저장을 위한 클래스
class Pair {
	int x;
	int y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Algo_bj_11650 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Pair> spots = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			spots.add(new Pair(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
		}
		
		// x,y 오름차순
		Collections.sort(spots, (a, b) -> {
			if(a.x > b.x)
				return 1;
			else if(a.x < b.x)
				return -1;
			else {
				if(a.y > b.y)
					return 1;
				else
					return -1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(Pair a : spots)
			sb.append(a.x + " " + a.y + "\n");
		
		System.out.println(sb);
	}
}
