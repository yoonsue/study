Git 구조
```
작업 공간 (Working Directory) | 임시 공간 (Stage or Cache) | .git (Remote Directory)
- untracked                  |                       --(commit)-->
- tracked               --(add)-->
```

참조 그림 [URL: Happygrammer](https://happygrammer.github.io/guide/git-workflow-diagram/?fbclid=IwAR1AV-BF4b51u9Io5mcUVU34amh-DYkcBymkUeouIsbgXqTs3vEGF3TjAKw)
![git-workflow-diagram](https://user-images.githubusercontent.com/22068030/115115854-a333e300-9fd1-11eb-8c8c-aed6c68d237b.png)



| 명령어 | 역할 |
|---|---|
| `git commit --amend` | Commit message만 수정 |
| `git rebase -i <commit>` | 과거 커밋 내용 수정 |
| `git rebase -i HEAD~<# of commit(s)>` | 과거 커밋 내용 수정 |
| `git reflog` | HEAD가 가리킨 커밋 목록 |
| `git reset --hard HEAD~` | 이전 커밋 취소 |
| `git cherry-pick "<commit>"` | 현재 브랜치로 특정 커밋 복사 |
| `git log --grep "<pattern>"` | 특정 코멘트(패턴) 검색 |
| `git push <remote_name> :<branch_name>` | 리모트 브랜치 삭제 |
| `git remote add <remote_name> <URL>` | 리모트 추가 |
