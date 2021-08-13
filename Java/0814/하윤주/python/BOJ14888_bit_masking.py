'''
[0814] BOJ 14888 연산자 끼워넣기

sol) bit masking을 이용한 순열 구하기
tc) O(N!)
'''

import sys
input = sys.stdin.readline

N = int(input())
M = N-1
nums = list(map(int, input().split()))
oper_cnt = list(map(int, input().split()))
minimum = 1000000000
maximum = -1000000000


# 연산자 순열 구하기
def bit_masking(depth, flag):
    global minimum
    global maximum

    if (depth == M): 
        minimum = min(minimum, calculate(oper_perm))
        maximum = max(maximum, calculate(oper_perm))
        return
    
    for o in range(M):
        if ((flag & 1<<o) != 0): continue

        oper_perm[depth] = operators[o]

        bit_masking(depth+1, flag | 1<<o)

# 순열이 완성되면 계산하기
def calculate(oper_perm):
    global nums
    answer = nums[0]

    for i in range(len(oper_perm)):
        if oper_perm[i] == 0:
            answer += nums[i+1]

        elif oper_perm[i] == 1:
            answer -= nums[i+1]

        elif oper_perm[i] == 2:
            answer *= nums[i+1]

        elif oper_perm[i] == 3:
            if answer < 0:
                answer = ((answer*(-1)) // nums[i+1]) * (-1)
            else:
                answer //= nums[i+1]
    return answer


# 연산자 배열 생성 후 순열 돌면서 계산
operators = []
for o in range(len(oper_cnt)):
    for j in range(oper_cnt[o]):
        operators.append(o)

oper_perm = [-1] * M

bit_masking(0, 0)

print(maximum)
print(minimum)