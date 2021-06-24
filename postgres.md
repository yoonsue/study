> 아래 링크를 참고하여 정리하였음
> URL: https://browndwarf.tistory.com/51

# POSTGRES

## 1. PSQL

### 1.1. DB 접속 명령어

* `psql -h <IP> -p <port, default: 5432> -U <Username> -d <targetDB>`

### 1.2. PSQL 명령어

| 분류 | 명령어 | 내용 |
| --- | --- | --- |
| 도움말 | \? | psql 명령어 조회 가능 |
| - | \h | SQL 명령어 조회 가능 |
| 종료 | \q | psql 종료 |
| DB | \c [DB Name][Connection User] | 접속한 DB Instance 변경 |
| - | \list (or \l) | 전체 Datatbase Instance 목록 |
| - | \dt | 접속한 DB Instance의 테이블 목록 |
| - | \ds | Sequence 목록 |
| - | \df | Function 목록 |
| - | \dv | View 목록 |
| - | \du | User 목록 |
| - | \dn | Schema 몰고 |
| - | \di | Index 목록 |
| - | \d [tableName] | 특정 Table의 상세 정보 조회 |
| History | \g | 방금전 실행한 명령어 실행(위 방향키) |
| - | \s | Command History |
| Layout | \x | xscrollbar 기능, Column들을 한줄로 조회하기 힘들때, Column을 세로 배치해서 Display하는 기능 on/off |
| - | \a | space에 의한 정렬 기능, Column Align on/off. Default는 Align on 상태 |
| - | \H | HTML `<table>`로 추출하기 위함. Column 명과 결과 값을 HTMP Table 형식으로 Display 하는 기능 on/off |
| Query | \timing | Query 실행 시간 표시 |
| - | /i [fileName] | 외부 파일을 통한 Query 실행 |
| Editor | \e [fileName] | 외부 Editor 사용. fileName 없을 시 Buffer를 통해 작성하며, 실행 후 작성한 Query 삭제. 존재하지 않는 파일명 입력시 오류 발생하며 종료됨 |
| - | \ef [functionName] | Query 실행 시간 표시 on/off |
| - | \! [shellCommand] | shell command 실행 |
