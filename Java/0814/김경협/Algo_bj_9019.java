package baekjoon.passed;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair2 {
	int value;
	String command;

	Pair2(int v, String str) {
		value = v;
		command = str;
	}
}

public class Algo_bj_9019 {
	static boolean visited[];
	static String[] commands = { "D", "S", "L", "R" };

	static int exeCommand(int value, String command) {
		int changedValue = 0;
		switch (command.charAt(0)) {
		case 'D':
			changedValue = (value * 2) % 10000;
			break;
		case 'S':
			changedValue = (value == 0) ? 9999 : value - 1;
			break;
		case 'L':
			changedValue = (value % 1000) * 10 + value / 1000;
			break;
		case 'R':
			changedValue = (value / 10) + (value % 10) * 1000;
			break;
		}
		return changedValue;
	}

	static String bfs(int start, int target) {
		Queue<Pair2> q = new LinkedList<>();

		q.offer(new Pair2(start, ""));

		while (!q.isEmpty()) {
			Pair2 curr = q.poll();
			
			if (curr.value == target)
				return curr.command;
			

			for (int i = 0; i < 4; i++) {
				int next = exeCommand(curr.value, commands[i]);
				if (!visited[next]) {
					q.offer(new Pair2(next, curr.command + commands[i]));
					visited[next] = true;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(tk.nextToken());
			int target = Integer.parseInt(tk.nextToken());
			visited = new boolean[10000];

			sb.append(bfs(start, target));
			sb.append("\n");

		}
		System.out.println(sb);

	}

}
