import java.io.*;
import java.util.*;

public class Main17143 {
	/*
	 * 1. 낚시꾼 열 이동
	 * 2. 상어잡기
	 * 3. 상어 이동 (새로운 맵 만들고  크기 비교로 클때만 박기)
	 * 
	 * 속력만큼 for문 도는 부분 개선하는게 포인트
	 * if(d<3) s %= (R-1)*2;
	 * else s %= (C-1)*2;
	 * 개선 전 메모리 26124 시간 2232
	 * 개선 후 메모리 25724 시간 516
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int R, C, M, map[][];
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	static Shark[][] sharkMap;
	static Shark[][] newSharkMap;
	
	static class Shark{
		int s,d,z;

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		public void setD(int d) {
			this.d = d;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sharkMap = new Shark[R+1][C+1];

		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			sharkMap[r][c] = new Shark(s,d,z);
		}
		
		int totSize = 0;
		for(int i=1; i<=C; i++) { // 낚시꾼 열 이동
			for(int j=1; j<=R; j++) { // 제일 땅에 가까운 상어 잡기
				if(sharkMap[j][i] != null) { 
					totSize += sharkMap[j][i].z;
					sharkMap[j][i] = null;
					break;
				}
			}
			
			newSharkMap = new Shark[R+1][C+1]; // 이동한 상어를 박아줄 맵
			moveShark(); // 상어 이동
			sharkMap = newSharkMap;
		}
		System.out.println(totSize);
	}
	
	private static void moveShark() {
		Shark shark;
		int r=0, c=0, s=0, d=0;
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(sharkMap[i][j]!=null) {
					shark = sharkMap[i][j];
					s = shark.s;
					d = shark.d;
					r = i;
					c = j;
					
					if(d<3) s %= (R-1)*2; // 속력 줄여주기
					else s %= (C-1)*2;
					
					for(int k=0; k<s; k++) { // 속력만큼 이동
						if(r+dr[d]<1) d=2; // 끝에 갔을 때 방향전환
						else if(r+dr[d]>R) d=1;
						else if(c+dc[d]<1) d=3;
						else if(c+dc[d]>C) d=4;
						
						r += dr[d];
						c += dc[d];
					}
					
					// 이동 후 갈 자리가 비어있거나, 해당 자리에 있는 상어보다 사이즈가 크다면 그 자리에 넣어주기
					if(newSharkMap[r][c]==null || newSharkMap[r][c].z < shark.z) {
						shark.setD(d); // 이동하면서 방향바꼇을 수 있기에 다시 설정
						newSharkMap[r][c] = shark;
					}
				}
			}
		}
	}
	
}
