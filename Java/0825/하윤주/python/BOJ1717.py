'''
[0824] BOJ 1717 집합의 표현

sol) union-find, disjoint set, path compression
tc) O(1) ~ O(log n)
'''

import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

# union-find 구현
def find_set(p):
    if (parents[p] < 0): return p
    
    parents[p] = find_set(parents[p])
    return parents[p]

def union(p, q):
    root_p = find_set(p)
    root_q = find_set(q)

    if (root_p == root_q): return False

    if (parents[root_p] < parents[root_q]):
        parents[root_p] += parents[root_q]
        parents[root_q] = root_p
    else:
        parents[root_q] += parents[root_p]
        parents[root_p] = root_q

    return True

def is_set(p, q):
    if find_set(p) == find_set(q): print("YES")
    else: print("NO")


n, m = map(int, input().split())
parents = [-1 for _ in range(n+1)]

for _ in range(m):
    oper, a, b = map(int, input().split())

    if oper: is_set(a, b)
    else: union(a, b)
