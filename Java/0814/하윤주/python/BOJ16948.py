'''
[0810] 백준 16948 데스나이트

sol) bfs
tc) O(N^2) 인데 먼저 도착하면 반환하도록 함
'''

from collections import deque
import sys
input = sys.stdin.readline

dx = [-2, -2, 0, 0, 2, 2]
dy = [-1, 1, -2, 2, -1, 1]

def bfs(x, y): 
    q = deque([[x, y]])

    while(q):
        x, y = q.popleft()

        if (x == r2 and y == c2):
            return visited[x][y]

        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny] == 0:
                visited[nx][ny] = visited[x][y] + 1
                q.append([nx, ny])
    
    return -1


N = int(input())
r1, c1, r2, c2 = map(int, input().split())
visited = [[0] * N for _ in range(N)]

print(bfs(r1, c1))