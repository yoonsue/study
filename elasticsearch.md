# Elasticsearch

## 명령어

| 기능 | 명령어 |
| --- | --- |
| 클러스터에 존재하는 모든 index 조회 | `curl -XGET 'localhost:9200/_cat/indices?v'` |
| index 추가 | `curl -XPUT 'localhost:9200/index?pretty'` |
| document 추가 |  `curl -XPOST 'localhost:9200/index1/info/1?pretty' -H 'Content-Type: application/json' -d '{ "info": "infomation#1" }'` |
| 1) Id 없는 경우 | `curl -XPOST 'localhost:9200/index2/info?pretty' -H 'Content-Type: application/json' -d '{ "info": "infomation#2" }'` |
| 2) 파일로 넣기 | `curl -XPOST 'localhost:9200/customer2/info/2?pretty' -H 'Content-Type: application/json' -d @data.json` |
| document조회 | `curl -XGET 'localhost:9200/customer2/_search?pretty'` |
|  | `curl -XGET 'localhost:9200/_all/_search?pretty'` |
|  | `curl -XGET 'localhost:9200/index2/info/1?pretty'` |
|  | `curl -XGET 'localhost:9200/index2/info/2?pretty&filter_path=_source'` |
|  | `curl -XGET 'localhost:9200/index2/info/2?pretty&filter_path=_source.name'` |
| document 수정 | `curl -XPUT 'localhost:9200/index2/info/1?pretty' -H 'Content-Type: application/json' -d '{ "info": "infomation_update" }'` |
| index,document 삭제 | `curl -XDELETE 'localhost:9200/index2/info/1?pretty'`|
|  | `curl -XDELETE 'localhost:9200/index2?pretty'` |
| count 세기 | `curl 'localhost:9200/epsserverlog-*/_count?pretty'` |

## 필요 개념

* 색인 (indexing) : 데이터가 검색될 수 있는 구조로 변경하기 위해 원본 문서를 검색어 토큰들로 변환하여 저장하는 일련의 과정
* 인덱스 (index) : 데이터의 논리적 집합 단위. 색인 과정을 거친 결과물 혹은 색인된 데이터가 저장되는 저장소
* 검색 (search) : 인덱스에 들어있는 검색어 토큰들을 포함하고 있는 문서를 찾아가는 과정
* 질의 (query) : 사용자가 원하는 문서를 찾거나 집계 결과를 출력하기 위해 검색시 입력하는 검색어 또는 검색 조건
> * 역파일 색인 구조 (inverted index)
> 역색인, 역 인덱스(inverted index), 역 파일(inverted file)은 낱말이나 숫자와 같은 내용물로부터의 매핑 정보를 데이터베이스 파일의 특정 지점이나 문서 또는 문서 집합 안에 저장하는 색인 데이터 구조
목적은 문서가 데이터베이스에 추가될 때 늘어나는 처리를 위해 빠른 전문 검색을 가능케 하는 것
> - 참고 URL: https://ko.wikipedia.org/wiki/%EC%97%AD%EC%83%89%EC%9D%B8

## 용어

* documet: 단일 데이터 단위
* index: 도큐먼트를 모아 놓은 집합
* shard: 인덱스가 분리된 단위. 각 노드에 분산되어 저장됨. 루씬의 단일 검색 인스턴스
* primary shard: 처음 생성된 샤드
* replica: 복제본. 복제본과 샤드는 반드시 서로 다른 노드에 저장됨
* dictionary : 용어들을 모아 놓은것
* posting list : 해당 용어를 포함하는 문서들의 집합
