'''
[0814] BOJ 14888 연산자 끼워넣기

sol) next permutation을 이용한 순열 구하기
tc) O(N!) : 
'''

import sys
input = sys.stdin.readline

N = int(input())
M = N-1
nums = list(map(int, input().split()))
operators = list(map(int, input().split()))
minimum = 1000000000
maximum = -1000000000


# 연산자 순열 구하기
def next_permutation():
    top = M-1
    while(top>0 and oper_perm[top-1] >= oper_perm[top]): top -= 1

    if top == 0: return False

    target = M-1
    while(oper_perm[top-1] >= oper_perm[target]): target -= 1

    swap(top-1, target)

    
    bottom = M-1
    while(top < bottom):
        swap(top, bottom)
        top += 1
        bottom -= 1

    return True

def swap(_from, _to):
    tmp = oper_perm[_from]
    oper_perm[_from] = oper_perm[_to]
    oper_perm[_to] = tmp

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
oper_perm = []
for o in range(len(operators)):
    for j in range(operators[o]):
        oper_perm.append(o)

minimum = min(minimum, calculate(oper_perm))
maximum = max(maximum, calculate(oper_perm))

while(next_permutation()):
    minimum = min(minimum, calculate(oper_perm))
    maximum = max(maximum, calculate(oper_perm))

print(maximum)
print(minimum)