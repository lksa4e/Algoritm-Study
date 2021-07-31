# Allgorithm-study

## Pull Request를 이용한 협업 프로젝트 연습을 합시다!

### branch 생성 및 push (git bash)

**(사전준비 : remote add origin을 통해 등록해두기)**

- **git branch 생성하기**

  ```git
  $ git branch <branchName>
  ```

- **생성한 branch로 이동하기**

  ```git
  $ git checkout <branchName>
  ```

- **원격 저장소에 push하기**

  ```git
  $ git push -u origin <branchName>
  ```

  - 한번 push -u 해놓으면 이후에는 git push 명령어만 입력하면 끝!
  - 물론 push 하기 전에 git add, commit 하기

### Main branch로 Pull Request하기 (github)
1. 본인이 생성한 branch로 이동하기
2. Contribute 클릭 (초록색 코드 버튼 밑에)
3. 활성화된 Open pull request 클릭
4. pull request 내용 입력
