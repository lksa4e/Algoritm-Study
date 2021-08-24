'''
[0825] BOJ 1976 여행 가자

sol) 그래프 탐색, union-find를 이용하여 여행할 도시들이 모두 같은 집합인지 확인하면 연결된 도시인지 확인할 수 있음
tc) O(1) ~ O(log n)
'''

import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

# union-find 구현

def find(p):
    if parents[p] == p:
        return p

    parents[p] = find(parents[p])
    return parents[p]

def union(p, q):
    root_p = find(p)
    root_q = find(q)

    if root_p == root_q:
        return False

    parents[root_q] = root_p
    return True

def is_set(p, q):
    if find(p) == find(q):
        return True
    return False


N = int(input())
M = int(input())

adj_matrix = [list(map(int, input().split())) for _ in range(N)]    # 인접 행렬
cities = list(map(int, input().split()))
parents = [i for i in range(N+1)]

# 인접한 도시들은 같은 집합으로 연결
for i in range(N):
    for j in range(N):
        if adj_matrix[i][j]:
            union(i+1, j+1)

# 같은 집합이 아니면 연결된 도시가 아님
flag = False
for i in range(1, M):
    if not is_set(cities[i-1], cities[i]):
        flag = True
        break

if flag:
    print("NO")
else:
    print("YES")