> 아래 링크를 참고하여 정리하였음
> URL: https://browndwarf.tistory.com/51

# POSTGRES

## 1. PSQL

### 1.1. DB 접속 명령어

* `psql -h <IP> -p <port, default: 5432> -U <Username> -d <targetDB>`

### 1.2. PSQL 명령어

| 명령어 | 내용 |
| --- | --- |
| \? | psql 명령어 조회 가능 |
| \h | SQL 명령어 조회 가능 |
| \x | 쿼리 결과 Display 설정 |
| \a | 쿼리 결과 Display 설정 |
| \H | 쿼리 결과 Display 설정 |
| \c [DB Name][Connection User] | 접속한 DB Instance 변경 |
| \list (or \l) | 전체 Datatbase Instance 목록 |
| \dt | 접속한 DB Instance의 테이블 목록 |
| \ds | Sequence 목록 |
| \df | Function 목록 |
| \dv | View 목록 |
| \du | User 목록 |
| \dn | Schema 몰고 |
| \di | Index 목록 |
| \d [tableName] | 특정 Table의 상세 정보 조회 |
| \g | 방금전 실행한 명령어 실행(위 방향키) |
| \s | Command History |
