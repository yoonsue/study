#### 1. [REST](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
<i>Fielding RT. Architectural styles and the design of network-based software architectures. PhD thesis, University of California, Irvine. 2000.</i>
* client-server: 
* stateless: 모든 요청은 필요한 모든 정보를 담고 있어야한다
* cache
* uniform interface 
* layered system: 
* code--on-demand: 
 
 메타데이터를 사용하여 데이터 유형에 대한 공유 이해에 초점을 맞추고 표준회된 인터페이스
 자원 ID를 사용하여 구송 요소간의 사호 작용에 관련된 특정 자원을 식별
 REST connector는 멤버쉽 함수 정의 방법 또는 요청을 처리하는 소프트웨어 유형에 관계없이 자원의 값 세트에 액세스하고 조작하기 위한 일반 인터페이스를 제공함
 
 자원의 상태를 캡쳐하고 상태를 구성요소간에 전송함으로 자원에 대한 조치를 수행함
 데이터, 데이터를 설명하는 메타데이터, 메타데이터를 설명하기 위한 메타 데이터(메세지 무결성 확인)
 
 미디어 유형 = 표현의 데이터 형식
 
 커넥터: 구성요소 통신을 위한 추상 인터페이스를 제공하여 우려사항을 명확하게 분리하고 리소스 및 통신 메커니즘의 기본 구현을 숨겨 단순성을 향상시킴
  
#### 2.