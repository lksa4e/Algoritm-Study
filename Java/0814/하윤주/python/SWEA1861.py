'''
[0810] SWEA 1861 정사각형 방

sol) dfs
tc) O(N^2)
'''

import sys
input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    global cnt

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if (0 <= nx < N and 0 <= ny < N and (maps[nx][ny] == maps[x][y] + 1)):
            visited[maps[nx][ny]] = 1
            cnt += 1
            dfs(nx, ny)
    

T = int(input())
for tc in range(1, T+1):
    N = int(input())
    maps = [list(map(int, input().split())) for _ in range(N)]
    visited = [0] * (N**2 + 1)
    max_cnt = 0
    max_start = 0

    for i in range(N):
        for j in range(N):
            if not visited[maps[i][j]]:
                visited[maps[i][j]] = 1
                cnt = 1
                dfs(i, j)
                if (cnt > max_cnt):
                    max_cnt = cnt
                    max_start = maps[i][j]

    print("#", tc, " ", max_start, max_cnt)
