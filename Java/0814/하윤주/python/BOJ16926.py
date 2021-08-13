'''
[0814] BOJ 16926 정사각형 방

sol) 배열 회전 열심히 하자
tc) O(N^2)
'''

import sys
input = sys.stdin.readline

N, M, R = map(int, input().split())
K = int(min(N, M) / 2)

matrix = [list(map(int, input().split())) for _ in range(N)]

def rotateMatrix() :
    for k in range(K):
        start = matrix[k][k]

        # 윗변(좌로 이동)
        for j in range(k, M-1-k): matrix[k][j] = matrix[k][j+1]

        # 우변(위로 이동)
        for i in range(k, N-1-k): matrix[i][M-1-k] = matrix[i+1][M-1-k]

        # 아랫변(우로 이동)
        for j in range(M-1-k, k, -1): matrix[N-1-k][j] = matrix[N-1-k][j-1]

        # 좌변(아래로 이동)
        for i in range(N-1-k, k, -1): matrix[i][k] = matrix[i-1][k]

        matrix[k+1][k] = start

for _ in range(R):
    rotateMatrix()

for r in matrix:
    for c in r:
        print(c, end=" ")
    print()
