#### 1. [REST](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
<i>Fielding RT. Architectural styles and the design of network-based software architectures. PhD thesis, University of California, Irvine. 2000.</i>
* client-server: 
* stateless: 모든 요청은 필요한 모든 정보를 담고 있어야한다
* cache
* uniform interface: 
  *  identification of resources: 리소스가 URI로 식별되어야함
  * manipulation of resources through representations: 리소스 생성/삭제/업데이트 시 메세지에 표현을 담아 전송해야함 
  * self-descriptive messages: Host Header 등
  * hyper as the engine of application state (HATEOAS): 애플리케이션의 상태는 Hyperlink를 통해서 전이되어야함
* layered system: 
* code--on-demand: 서버에서 코드를 클라이언트로 보내서 실행할 수 있어야한다 (javascript)
 
##### Uniform interface
<ol>
<li>Self-description:
    <ol>
    <li>IANA에 Media type을 등록해서 각각에 대한 설명을 명시함</li>
    <li>profile: 각각의 정보가 담긴 문서의 Link를 걸어줌<br/>
    client가  link해더와 profile을 이해해야한다<br/>
    content negociation이 안됨</li>
    </ol>
    </li>
<li>HATEOAS
    <ol>
      <li>data로 본문에다가 링크를 박아놓는 방법</li>
      <li>Link, Location 등의 HTTP 헤더로 링크 표현 </li>
    </ol>
    </li>
</ol>
  
[Naver D2: 그런 REST API로 괜찮은가](https://www.youtube.com/watch?feature=share&v=RP_f5dMoHFc&app=desktop)

REST: 분산 하이퍼미디어 시스템을 위한 아키텍쳐 스타일(제약조건의 집합)
* 상호운용성(interoperability)에 대해 엄청난 고려

* 메타데이터를 사용하여 데이터 유형에 대한 공유 이해에 초점을 맞추고 표준회된 인터페이스
*  자원 ID를 사용하여 구성 요소간의 상호 작용에 관련된 특정 자원을 식별
* REST connector 는 멤버쉽 함수 정의 방법 또는 요청을 처리하는 소프트웨어 유형에 관계없이 자원의 값 세트에 액세스하고 조작하기 위한 일반 인터페이스를 제공함
  * 독립적 진화
    * 서버와 클라이언트가 각각 독립적으로 진화
    * 서버의 기능이 변경되어도 클라이언트를 업데이트할 필요가 없다



#### 2.