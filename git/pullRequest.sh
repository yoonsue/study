# contribute 할 repository를 fork
# fork한 repository 삭제를 원하면 github에서 setting으로 들어가서 삭제해야 한다.

git clone https://github.com/yoonsue/{fork-repository}.git

cd {fork-repository}

git remote -v

# remote에 upstream 추가하기
git remote add upstream https://github.com/yoonsue/{fork-repository}.git

# issue에 따라 branch 나누기
git checkout -b cold-store

# branch 확인
git branch
