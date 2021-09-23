# PATTERNS STUDY

## 개요

- 아래 참고도서를 통해 고찰한 내용을 정리하는 레포지토리임.

### 참고도서

- [GoF의 디자인 패턴](http://www.yes24.com/Product/Goods/17525598)
- [Holub Design Patterns](https://www.holub.com/goodies/holub_design_patterns.pdf)

### 기호표

| 기호 | 표기 |
|:---:|:---:|
|*italic*|추후 다시 확인할것|
|**bold**| 강조|
|~~line~~| 불확실, 추측 |

---

# Patterns

## 생성 패턴

### 1. Abstract Factory Pattern

- [Impl of Abstract Factory](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
  > 내부에서 객체를 생성하는 인터페이스  
  > *GoF:* 구체적인 클래스를 지정하지 않고 관련성을 갖는 객체들의 집합을 생성하거나 서로 독립적인 객체들의 집합을 생성할 수 있는 인터페이스를 제공  
  > *위키:* 동일한 주제의 다른 팩토리를 묶어 준다.

  - 일반화된 클래스(부모 클래스)가 더 상세한 클래스(서브클래스)를 묶고,  
    이 일반화된 클래스를 통해 클라이언트는 접근함.

  - 세부 구현은 상세한 클래스(서브 클래스)에서 이루어짐.

- 사용 예
  - 다양한 시스템에서 사용될때(시스템 독립적)
  - 구현 내용이 아닌 인터페이스를 노출시킬때

- 유의
  - *Builder: 실체를 만들 때 build 되는 내용에 대해 전혀 모르는 것*

### 2. Builder

- [Builder](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/builder)
  > 비즈니스 로직과 표현부 간의 분리. 복잡한 객체를 한단계씩 실행시키며 생성함. 마지막 단계에서 객체를 반환함  
  > *GoF:* 복합객체의 생성과정과 표현방법을 분리하여 동일한 생성절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴  
  > *위키:* 생성(construction)과 표기(representation)를 분리해 복잡한 객체를 생성한다

- 각각 독립된 여러 단계를 거치며, 특정 단계의 선택폭(프로토콜, representation 방법)이 넓을 때 사용하면 좋을 듯함.
- 사용 예
  - RTF(서식있는 텍스트 파일) 리더

### 3. Factory Method

a.k.a. Virtual Constructor

- [Impl of Factory Method](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
  > 객체를 생성(지정)하는 메소드  
  > *GoF:* 객체를 생성하는 인터페이스는 미리 정의하되, 인스턴스를 만들 클래스의 결정은 서브클래스 쪽에서 내리는 패턴. 팩토리 메서드 패턴에서는 클래스의 인스턴스를 만드는 시점을 서브클래스로 미룸  
  > *위키:* 생성할 객체의 클래스를 국한하지 않고 객체를 생성한다.
  
- 유의
  - Abstract Factory(제품군 안내) > Factory(제품 생성 담당) > Factory Method (상세 부품 생성 담당)
    - Factory Method: 객체를 생성하는 메소드
      - 서브 클래스에 의해 override 될 수 있음
    - Abstract Factory: 내부에 (보통 팩토리 메소드를 사용해서) 여러 객체를 생성하는 하나의 객체(인터페이스)

### 4. Prototype

a.k.a. Clone

- [Prototype](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/prototype)
  > clone()이 정의된 인터페이스. (a.k.a. 다른이름으로 저장하기)  
  > *GoF:* 생성할 객체의 종류를 명세화하는 데에 원형이 되는 예시물을 이용하고, 그 원형을 복사함으로써 새로운 객체를 생성하는 패턴  
  > *위키:* 기존 객체를 복제함으로써 객체를 생성한다.
- 프로토타입 객체의 서브클래스가 clone 연산을 정의해야함
  - ***중요포인트:*** clone 연산을 구현할 때 사본이 원본으로부터 무엇을 복사하고 공유할 것인지 잘 결정해야함
- 객체 자체를 복사하기 때문에 서브클래싱을 필요로 하지 않고, 오히려 초기화 동작을 필요로 함
- 런타임에 객체 생성/삭제

- 사용 예
  - `GUI-style JavaBeans`
  - `트랜잭션`   (객체를 복사 -> 복사한 객체(Prototype)에 트랜잭션 수행 -> 원래 객체와 바꿔치기)
  - `악보 생성 프로그램`
  - `initial 상태의 XML 파일 복사`할때 
  
- 유의
  - 추상 팩토리: 프로토타입 집합을 저장, 필요할때 복제해서 제품 객체를 반환하도록 사용 가능
  - 프로토타입: 복사본 생성

### 5. Singleton

- [Singleton](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/singleton)
  > 내부에 전역적 접근이 가능한, 정해진 수의(보통 1개), 인스턴스를 갖는 클래스  
  > *GoF:* 어떤 클래스의 인스턴스는 오직 하나임을 보장하며, 이 인스턴스에 접근할 수 있는 전역적인 접촉점을 제공하는 패턴  
  > *위키:* 한 클래스에 한 객체만 존재하도록 제한한다.

- 실제 예
  - `Toolbox` : 메소드를 통해 runtime시 생성 (단점: Sync 맞추기)
  - `Border` : `static` 멤버 할당 및 `getter` 메소드 존재
  - `Class` 객체 : 내부의 멤버가 모두 `static`

## 구조 패턴

### 6. Adapter

a.k.a. Wrapper

<!-- 
    Target: 사용자가 사용할 응용 분야에 종속적인 인터페이스를 정의하는 클래스
    Client: Target 인터페이스를 만족하는 객체와 동작할 대상
    Adaptee: 인터페이스의 적응이 필요한 기존 인터페이스를 정의하는 클래스, 적응대상자
    Adapter: Target 인터페이스에 Adaptee의 인터페이스를 적응시키는 클래스
-->

- [Adapter](https://github.com/yoonsue/stuㄹdy/tree/master/book/gof/src/main/java/adapter)
  > Wrapper, 기존 코드의 변경없이 특정 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 변환(적응)  
  > *GoF:* 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 패턴. 호환성이 없는 인터페이스 떄문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해줌  
  > *위키:* 인터페이스가 호환되지 않는 클래스들을 함께 이용할 수 있도록, 타 클래스의 인터페이스를 기존 인터페이스에 덧씌운다.

- 사용처
  - _대표적으로 API 모듈이지 않을까? --> 이건 facade 이겠다._
  - _legacy (재사용성 없는)의 인터페이스를 사용해서 새롭게 만들고 싶을 때_
  - 이미 존재하기는 하지만 현재 이를 사용하고자 하는 클래스와는 __아무런 연관 없이 개발될__ 클래스
  - __다수의 서로 일치하지 않는 인터페이스를 갖는 클래스들을 잘 통합__ 하여 하나의 응용프로그램을 개발해야 할 때

- 상속에 따른 Adapter 구현 종류
  - 클래스 Adapter: 유연하지 못함. 다중상속(target & adaptee). 타겟 클래스와 Adaptee 클래스 둘다 상속받아 타겟 클래스의 인터페이스로 개조
  - 객체 Adapter: Adapter 전체 코드 구현 필요. target 만 상속 후 adaptee 인스턴스 내부 생성(객체 합성). 타겟 클래스를 상속받아 직접 변형  
    (서브클래스가 이미 너무 많아 개조가 더 불편할때)

- 유의
  - Adapter: 존재하는 객체의 인터페이스 변경의 책임
  - Mediator: 동적으로 다대다로 연결
  - Bridge: Adapter보다 큰 규모로, 서브시스템을 고립시킴 (개념을 분리하여 서로에게 영향을 주지 않고 각각 확장할 수 있도록 함)
  - Decorator: 인터페이스 변경 없이, 메소드를 수정하거나 메소드를 추가함 (Wrapped Object, 응용프로그램을 위해 더 좋은 방식, 재귀적 합성도 가능)
  - Proxy: 다른 객체에 대한 대표자 또는 대리인 역할을 수행함(인터페이스 변경 책임 X)

### 7. Bridge

<!-- 
 Abstraction: 높은 수준(추상화)의 제어 로직 제공. 실제 작업은 구현 객체에 의존
 RefinedAbstraction: 제어 논리의 변형 제공
 Implementor: 모든 ConcreteImplementor에 대한 공통 인터페이스 선언
 ConcreteImplementor: 플랫폼별 코드 -->

- [Bridge](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/bridge)
  > 추상적 개념에 해당하는 클래스와 구현에 해당하는 클래스를 분리  
  > *GoF:* 구현부에서 추상층을 분리하여 각자 독립적으로 변형할 수 있게 하는 패턴  
  > *위키:* 추상화와 구현을 분리해 둘을 각각 따로 발전시킬 수 있음

- 객체군의 다형성과 독립적인 다른 객체군과의 조합을 위해 사용. 개별적으로 각각 조합하기 보다는 추상화부와 구현부 객체를 따로 두고, 조합할 객체를 참조하도록. (e.g. 색과 모양, 크로스 플랫폼 어플리케이션)
- 플랫폼 독립적인 코드(Abstraction)와 Application-specific code(Implementor)

- 유의
  - Adapter: 기존 앱과 호환되지 않는 클래스를 잘 작동시키기 위해 사용. <-> Bridge: 일반적으로 사전에 설계 

### 8. Composite

- [Composite](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/composite)
- a.k.a. object tree

<!-- Component(총괄인터페이스), Composite(하위그룹객체), Leaf(최하위객체), Client -->
  > 사용자가 어떤 Leaf나 Composite 클래스가 존재하는지 모르게 공통 연산들을 정의한 Component(총괄인터페이스)  
  > (a.k.a. 파워포인트 그룹 객체)    
  > *GoF:* 객체들의 관계를 트리 구조로 구성하여 부분-전체 계층을 표현하는 패턴으로, 사용자가 단일 객체와 복합 객체 모두 동일하게 다로도록 함  
  > *위키:* 0개, 1개 혹은 그 이상의 객체를 묶어 하나의 객체로 이용할 수 있다.

- 계층 구조를 어떻게 형성하는지 보여줌 (파워포인트 그룹 객체와 같은 원리)
- 사용자가 어떤 Leaf나 Composite 클래스가 존재하는지 모르게 공통 연산들을 정의한 Component(총괄인터페이스)
  - Composite(그룹객체)은 linked list, array, tree, hash table 등을 써 자신의 자식들을 저장
  - 자식 구성요소에서 부모를 가리키는 참조자를 관리
  - 자식의 상태를 많은 부모와 공유해야할 경우 Flyweight 패턴을 사용하면 좋음.
  - 행동은 Leaf(최말단객체)가 수행 ([Chain of Responsibility](###-13.-chain-of-responsibility))

- 자식 관리 인터페이스를 Component가 갖느냐? Composite(그룹객체)이 갖느냐?
  - Component: Leaf 클래스의 인스턴스가 Add() Remove()와 같은 행동을 하지 않도록 안전성 유지를 해야함
  - Composite: Leaf 클래스 인스턴스가 무의미한 행동을 하지 않는 안전성을 보장하나, composite 클래스가 어떤 타입으로 자식들을 관리하는지 알 수 없음

- 사용 예
  - File Directory

- 유의
  - Chain of Responsibility: 리프 구성요소가 요청을 받으면 모든 상위 구성 요소의 체인을 통해 개체 트리의 루트까지 전달할 수 있음
  - Iterator: Composite 트리 탐색 가능
  - Visitor: Composite 트리에 대해 작업 실행 가능
  - Flyweight: Composite의 공유 리프 노드를 flyweight로 구현하여 RAM 절약 
  - Decorator: 둘다 재귀 합성에 의존하여 개방형 개체 수를 고성하기 때문에 유사한 구조 다이어그램을 가짐
    - 단, Decorator는 하나의 자식 component만 존재하며, 추가 책임 행위가 있음. Decorator가 Composite에서 분화하여 특수화된 케이스라고 보면 됨
  - Prototype: Decorator와 Composite과 같이 사용하면 좋음. 복잡한 구조를 복제함으로 재구성할 필요가 없게 함

### 9. Decorator

a.k.a. Wrapper

<!-- 
    Component: 동적으로 추가할 서비스를 가질 가능성이 있는 객체들에 대한 인터페이스
    ConcreteComponent: 추가적인 서비스가 실제로 정의될 필요가 있는 객체
    Decorator: Component 객체에 대한 참조자를 관리하면서 Component에 정의된 인터페이스를 만족하도록 인터페이스를 정의
    ConcreteDecorator: Component에 새롭게 추가할 서비스를 실제로 구현하는 클래스
-->
  > 개별 객체에 새로운 책임을 추가하고자 할 경우 사용하는 패턴 (a.k.a. 옵션 추가)  
  > *GoF:* 주어진 상황 및 용도에 따라 어떤 객체에 책임을 덧붙이는 패턴으로, 기능 확장이 필요할 때 서브클래싱 대신 쓸수 있는 유연한 대안이 될 수 있음  
  > *위키:* 기존 객체의 매서드에 새로운 행동을 추가하거나 오버라이드 할 수 있다.

- Component 인터페이스를 상속받는 Decorator 인터페이스를 생성해서 인스턴스
  (ConcreteDecorator의 인스턴스)를 생성 - `component가 decorator의 내부에 private 멤버로 존재함`
- 런타임시 동적으로 새로운 책임 추가하는 객체
  - 아래처럼 재귀적 합성을 통해 여러 책임 추가 가능

    ```java
    // Stream (Interface or AbstractClass)
    Stream* aStream = new CompressingStream(  // Concreate Decorator #2 - CompressingStream
      new ASCII7Stream (                      // Concreate Decorator #1 - ASCII7Stream
        new FileStream("aFileName")           // Concreate Stream
      )
    )
    ```

- 장점: 규모를 분할하고 계층화 하기 좋음
- 단점: 어디서 책임이 추가된 것인지 확인하기 어려울 수 있음
- 구현시 유의:
  - component 클래스는 가볍게 유지
  - decorator 인터페이스 일치
  - 단일 책임만 추가할 경우 decorator + concreateDecorator 둘 다 사용하지 않고,  
    concreateDecorator만 사용해도 됨

- 예
  - `InputStream`
  - `BlockingList implements List`

- 유의
  - Decorator: 객체의 *겉(인터페이스)*를 추가함
  - Strategy: 객체의 대체를 통해 객체 *내부*적으로 기능을 변경하거나 확장하는 방법을 제공
  - Adapter: Decorator는 일종의 Adapter이나,  
    Adapter는 *인터페이스*의 변경에 초점을,  
    Decorator는 객체의 *책임과 행동*의 변화에 초점을 둠
  - Composite: Decorator는 *한 구성요소만*을 갖는 Composite임
  - Chain of Responsibility: Decorator 는 ConcreteDecorator가 대부분 행동의 책임을 맡으나, Chain of Responsibility는 책임을 적절한 핸들러에게 넘기는 행위를 말함

### 10. Facade

  > 전체 서브시스템을 표현하는 인터페이스(대표/통합 인터페이스). 인터페이스의 단순/계층화  
  > *GoF:* 서브시스템에 있는 인터페이스 집합에 대해서 하나의 통합된 인터페이스를 제공하는 패턴. 서브시스템을 좀더 사용하기 편하게 만드는 상위 수준의 인터페이스를 정의  
  > *위키:* 많은 분량의 코드에 접근할 수 있는 단순한 인터페이스를 제공

- 복잡한 서브시스템에 대해 단순한 인터페이스 제공이 필요할 때
- 클라이언트 - 구현 클래스 간 종속성이 많을 때
- 서브 시스템을 계층화시킬때
- 서브 시스템 클래스를 직접 사용할 수도 있음
  - 공개할 서브시스템과 감출 서브 시스템 구분 필요

- 예
  - 컴파일러

- 유의
  - abstract factory: 서브시스템에 독립적인 방법. 서브시스템 객체를 생성하는 인터페이스를 제공하기 위해 함께 사용될 수 있음
    - facade의 대안으로 사용 가능. 서브시스템 객체가 생성되는 방식만 숨기고 싶을때(플랫폼에 종속적인 클래스를 감추는데) 사용
  - mediator: 기존에 존재하는 클래스의 기능성을 추상화한다는 점에서 유사하나,
    - 여러 객체들 사이의 협력 관계를 추상화하여 기능성의 집중화를 막는 목적 <-> facade : 서브시스템 인터페이스를 추상화하여 사용을 용이하게 함
    - 서로를 직접 알지 못하고 중재자를 통해서만 상호작용 가능 <-> facade : 새로운 기능성을 추가할 수 없고, 새로운 추가 기능에 대해서는 알수도 없음
  - flyweight: 작은 개체를 만드는 방법 <-> facade: 전체 하위 시스템을 나타내는 단일 개체를 만드는 방법
  - singleton: 하나만 있어도 된다면 singleton으로 구현
  - builder: 객체를 생성하는 방법 중 하나. 복잡한 객체 구성을 표현부와 구현부로 분리. <-> facade: 객체의 구조 중 하나. 하위 인터페이스에 접근하는데 사용할 수 있는 상위 인터페이스
<!--  https://stackoverflow.com/questions/39577474/builder-vs-facade-design-pattern  -->
  - adapter: 기존 인터페이스를 사용 가능하게 만들려고 할 때. 하나의 개체만 래핑 <-> facade: 기존 개체에 대한 새 인터페이스를 정의. 전체 하위 시스템과 함께 작동

### 11. Flyweight

a.k.a. Cache

  > 여러번 이용할 수 있도록 공유된 객체(구조를 정의)  
  > *GoF:* 크기가 작은 객체가 여러 개 있을 때, 공유를 통해 이들을 효율적으로 지원하는 패턴  
  > *위키:* 다수의 유사한 객체를 생성·조작하는 비용을 절감할 수 있다.

- 여러번 이용할 수 있도록 공유된 객체(구조를 정의)
  - 플라이급 객체의 공유된 풀에 존재하는 인스턴스에 대한 참조자를 갖도록 관리

- 중요포인트
  - 저장소 > 시간(연산/전송 런타임)
  - 공유할 객체 관리
  - 부가적 상태와 본질적 상태 구분

| 본질적 상태 | 부가적 상태 |
| --- | --- |
| intrinsic | extrinsic |
| ConcreteFlyweight | UnsharedConcreteFlyweight |
| 공유가능 | 공유 불가 |
| 문자 코드 | 위치, 스타일 등 |

- 사용처
  - 응용프로그램이 대량의 객체를 사용해야할 때
  - 객체의 수가 너무 많아서 저장 비용이 너무 높아질 때
  - 대부분의 객체 상태를 부가적인 것으로 만들 수 있을 때
  - 다수의 객체 집합을 적은 수의 공유된 객체로 대체할 수 있을 때
    - 본질적으로는 비슷하지만 부가 속성만 다른 경우
  - 응용프로그램이 객체의 정체성에 의존하지 않을 때
    - ID가 따로 부여되지 않아도 되는 객체일 경우

- 유의
  - Composite: Composite 패턴과 조합하여 그래프와 같이 계층적 구조를 모델링 하는데 사용됨
  - State: 플라이급을 활용해 구현할 수 있음
  - Strategy: 상동

### 12. Proxy

- 특정 객체를 대신하는 객체(대신되는 객체를 보호)

| 원격지 프록시 | 가상 프록시 | 보호용 프록시 |
|---|---|---|
| 객체가 다른 주소 공간에 존재함을 숨김 | 객체 생성 등의 처리를 최적화 | 요청한 대상이 권한이 있는지 확인 |

- 유의
  - Adater: 인터페이스 변환
  - Proxy: 대상과 동일한 인터페이스 제공, 객체에 대한 접근 제어
  - Decorator: 동적으로 하나 이상의 서비스를 추가하기 위함

## 행동 패턴

### 13. Chain of Responsibility

a.k.a. CoR, Chain of Command

<!-- 
    Handler: 요청 처리 인터페이스 정의, 후속 처리자와의 연결 구현 (요청의 시작)
    ConcreteHandler: 스스로 요청을 처리할 일은 하고 남은 일은 후속 처리자에게 넘김
    ConcreteState: 각 서브 클래스들 context 따라 처리되어야할 실제 행동 구현
-->

<!-- client가 특정 액션을 행할 하나의 객채를 선택. (어떤 객체가 될지 모름) -->

> 하나 이상의 객체가 요청을 처리해야 하고, 그 요청 처리자를 모를때 ConcreteHandler를 거쳐가며 처리자를 확정하는 패턴
  > (일의 전체 프로세스(Handler)가 있고, 공장 트레일러에서 본인(ConcreteHandler)이 맡은 역할만 수행하고 다음 사람(ConcreteHandler)에게 넘기는 식.)  
  > *GoF:* 요청을 처리할 수 있는 기회를 하나 이상의 객체에게 부여하여 요청을 보내는 객체와 그 요청을 받는 객체 사이의 결합을 피하는 패턴
  > 요청을 받을 수 있는 객체를 연쇄적으로 묶고, 실제 요청을 처리할 객체를 만날 때까지 객체 고리를 따라서 요청을 전달함  
  > *위키:* 책임들이 연결되어 있어 내가 책임을 못 질 것 같으면 다음 책임자에게 자동으로 넘어가는 구조

- ConcreteHandler간 연결이 중요함 (이를 지키지 못한다면, 안정적인 결과가 보장되지 않음)
  - 코드에 따라 대응하는 처리 요청을 발생시키기 위해 조건문 정의
  - 상속을 이용하여 매개변수를 묶어 별도의 객체로 만듦
  
- 자주 Composite 패턴과 같이 사용됨

### 14. Command

a.k.a. Action, Transaction

  > 중간에 Interface(Command)를 추가함으로써 객체 내부의 작업 혹은 요청을 캡슐화함  
  > *GoF:* 요청을 객체의 형태로 캡슐화하여, 서로 요청이 다른 사용자의 매개변수화, 요청 저장 또는 로깅, 그리고 연산의 취소를 지원하게 만드는 패턴  
  > *위키:* 명령어를 각각 구현하는 것보다는 하나의 추상 클래스에 메서드를 하나 만들고 각 명령이 들어오면 그에 맞는 서브 클래스가 선택되어 실행하는 것

- 사용 예
  - Transaction - undo 시 기존 상태 기억을 위해 메멘토 패턴을 사용
  - Concrete Command를 메서드 인수로 전달 가능, 큐잉(작업 예약), Undo가 필요한 작업(작업상태 저장, Memento 패턴)

- 유의
  - Chain of Responsibility: 같이 사용될수 있음. 잠재적 Receiver 중 하나가 처리할 때까지 순차적으로 요청을 전달
  - Mediator: 송신자와 수신자 간 직접 연결을 제거해 중재자 개체를 통해 간접 통신
    <-> Command: 발신자와 수신자 간 단방향 연결
  - Observer: 수신자가 수신 요청을 동적으로 구독/구독 취소할 수 있음
  - Memento: 실행 취소를 구현할때 같이 사용할 수 있음
  - Prototype: 명령 사본의 기록을 저장해야할 때 사용될 수 있음
  - Visitor: Command 패턴의 강력한 버전으로 취급할 수 있음
  - Strategy: 일부 작업으로 개체를 매개변수화할 수 있기에 비슷하지만, 의도가 다름
    - Command: 모든 작업의 개체화 가능
    - Strategy: 동일한 작업을 수행하는 여러 가지 방법이므로 단일 컨텍스트 클래스 내에서 알고리즘 교환만 가능
  
### 15. Interpreter

언어에 대한 문법을 정의하고, 그 문법을 계층화된 클래스로 구현하는 패턴

- 사용 예
  - Regular Expression

### 16. Iterator

<!-- next 메소드가 정의된 인터페이스 -->

  > 내부 표현부를 노출하지 않고 집합의 원소에 접슥할 수 있도록 하는 패턴  
  > *GoF:* 내부 표현부를 노출하지 않고 어떤 객체 집합에 속한 원소들을 순차적으로 접근할 수 있는 방법을 제공하는 패턴  
  > *위키:* 반복이 필요한 자료구조를 모두 동일한 인터페이스를 통해 접근할 수 있도록 메서드를 이용해 자료구조를 활용할 수 있도록 해준다.

- Java에서는 Iterator를 기본클래스로 제공
- 순회 도중 생성/삭제되는것을 방지하기 위해 집합 복사본에서 순회하거나, 순회중에는 집합 변경을 못하도록 막아야함

- 다음 객체(혹은 알고리즘)를 사용자가 지정할 수 있도록 함

### 17. Mediator

a.k.a. Intermediary, Controller

<!-- 
 mediator(객체 그룹 간의 상호작용을 제어하고 조화를 이루도록 하는 인터페이스)
 concreteMediator(그룹 내 객체들에 대한 포인터 관리)
 colleague(비즈니스 로직을 포함하는 다양한 클래스)-->

  > hub 역할의 인터페이스. 한 객체가 다른 객체를 너무 많이 참조 혹은 복잡한 상호작용을 해, 구성 요소간 직접 통신을 없애고 중간단계(loose coupling)를 놓아 간접 통신하는 패턴  
  > *GoF:* 한 집합에 속해있는 객체들의 상호작용을 캡슐화하는 객체를 정의하는 패턴. 객체들이 직접 서로를 참조하지 않도록 함으로써 객체 간 loose coupling 형성, 객체 간 상호작용을 다양화 하기 쉬움  
  > *위키:* 클래스간의 복잡한 상호작용을 캡슐화하여 한 클래스에 위임해서 처리 하는 디자인 패턴

- 객체의 프로토콜 단순화
- 통제의 집중화
  - 상호작용을 mediator 내부에서만 오가도록 함
- Mediator 구현체가 하나일 경우, 추상 클래스를 사용하지 않아도 됨
- Mediator 가 너무 많은 일을 하게 될 가능성 있음

- 대표예
  - MVC 패턴의 controller가 mediator 역할을 한다고 볼 수 있음  
  M:N 관계가 많이 생성되면 전체가 복잡해지기 때문에, 중간에 중재자를 두어 이벤트가 발생하고 전달하는 것을 단순화함

- 유의
  - Facade: 서브시스템을 추상화하여 좀더 편한 인터페이스 제공,  
  (단방향 메세지 전달) Facade 객체 -> 서브시스템 객체,  
  서브 시스템간 직접 통신 가능
  - Mediator: 인터페이스는 간단하지만 runtime시 복잡한 동작을 수행하는 객체,  
  (양방향 메세지 전달) Mediator <-> 서브 객체,  
  서브 객체간 직접 통신 불가능
  - Bridge: 구현부와 추상화부를 분리하는 방법에 대한 패턴
  - Adapter: 기존 인터페이스를 필요한 형태로 수정해 재활용
  - Observer: colleague 객체가 subject 객체로 동작하여 상태 변화가 일어날때마다 중재자한테 통보 -> 중재자가 다른 객체에게 변경을 통보해 후처리 가능

### 18. Memento
a.k.a. Snapshot
[refactoring guru 코드 예시](https://refactoring.guru/design-patterns/memento)

  > 상태 복원을 위해 객체의 이전 상태를 저장하는 패턴  
  > *GoF:* 캡슐화를 위배하지 않은 채로 어떤 객체의 내부 상태를 잡아내고 실체화시켜, 이후에 해당 객체가 그 상태로 되돌아올 수 있도록 하는 패턴  
  > *위키:* Ctrl + z 와 같은 undo 기능 개발할 때 유용한 디자인패턴. 클래스 설계 관점에서 객체의 정보를 저장

- 상태 복원을 위해 객체의 이전 상태를 저장하는 패턴
- 객체의 상태를 얻는데 필요한 직접적인 인터페이스를 둠으로 객체를 캡슐화할때 사용하는 패턴

- 인터페이스 생성 시, private / public을 잘 구분지어야 함
  - narrow interface(public): caretaker 클래스에게 제공하는 서비스
  - wide interface(public + private): originator 클래스에게 제공하는 서비스

- 유의
  - Iterator: iterator 와 함께 사용하여 반복 상태를 캡처하고 필요한 경우 롤백할 수 있음
  - prototype: 히스토리에 저장하려는 상태인 객체가 상당히 간단하고 외부 리소스에 대한 링크가 없거나 링크가 다시 설정하기 쉬운 경우, 아예 clone 하는 prototype 패턴을 사용하는게 나음

### 19. Observer

a.k.a. Event-Subscriber, Listener

  > one-many pub-sub. 엑셀 특정 칸(subject)을 포함한 계산식을 여러 칸(observer)에 지정  
  > *GoF:* 객체들 사이에 일대다 의존 관계를 정의하여, 어떤 객체의 상태가 변할 때 그 객체에 의존성을 가진 다른 객체들이 변화를 통지받고 자동으로 갱신될 수   
  > *위키:* 어떤 클래스에 변화가 일어났을 때, 이를 감지하여 다른 클래스에 통보해주는 것

구조
- subject(publisher, notify. 감시자들을 알고 있음)
- observer(subscriber, update. 객체를 갱신하는데 필요한 인터페이스를 정의)
- concreteSubject(observer 에게 상태 변화 통보. ConcreteObserver 객체에게 알려주어야 하는 상태를 저장)
- concreteObserver(ConcreteSubject 객체에 대한 참조자 관리, 주체의 상태와 일관성을 유지해야하는 상태를 저장)

사용환경
- 한 객체의 변화를 확인해야하는 객체가 동적으로 바뀔때

유의
- 구독 취소 시 이벤트 알림을 받지 않을 수 있음을 보장해야함
- concreteSubject 삭제시 concreteObserver 가 dangling 레퍼런스를 가질 수 있음
  - concreteSubject 삭제 시, 참조하고 있는 concreteObserver 에게 알려서 참조자를 삭제하는 절차가 필요할 수 있음
- observer를 call 한 이후에 연산을 정의하게 되면, concreteObserver와 concreteSubject 간 상태 정보가 불일치할 수 있음
  - Subject 인터페이스에 override할 연산을 정의하고, override할 연산 후에 notify연산을 수행하도록 해야함
- 푸시 / 풀 모델
  - 푸시 : subject가 자신의 변경에 대한 상세 정보를 observer에게 전달
  - 풀 : subject가 최소한의 정보만 전달하고, 감시자가 다시 상세 정보 요청
- 여러 주체가 변경될 경우, 감사자들에게 두 번 이상 통보될 수 있음
  - 다음 역할을 수행하는 changeManager 객체 두어 해결할 수 있음
    1. 주체와 감시자를 매핑하고 유지하는 인터페이스 정의
    2. Notify 할 때 매핑 관계 loop를 돌면서 observer에게 전달하는 등의 방식

- 사용 예
  - MVC(model - subject, view - observer, controller - mediator)
  
- 유의
  - 요청의 수/발신자
    - Chain of Responsibility: 순차적으로(setNext 메소드) 잠재적 수신자 중 하나가 처리하기를 기다리며 요청 전달
    - Command: 발신자와 수신자 간 단방향. Observer보다 더 일반적인 범주
    - Observer: 발신자와 수신자 간 단방향. 수신자가 수신요청을 동적으로 구독/구독취소
    - Mediator: 발신자와 수신자간 직접 연결 제거. 중재자 개체를 통해 간접 통신

### 20. State

<!-- 
    Context: 사용자가 사용할 수 있는 기본 인터페이스 제공
    State: 각 상태별 필요한 행동을 캡슐화하여 인터페이스로 정의 (Singleton 임)
    ConcreteState: 각 서브 클래스들 context 따라 처리되어야할 실제 행동 구현
-->
  > 동일한 동작을 객체의 상태에 따라 다르게 처리해야 할 때 사용하는 패턴  
  > *GoF:* 객체의 내부 상태에 따라 스스로 행동을 변경할 수 있게끔 허가하는 패턴으로, 이렇게 하면 객체는 마치 자신의 클래스를 바꾸는 것처럼 보임  
  > *위키:* 동일한 동작을 객체의 상태에 따라 다르게 처리해야 할 때 사용하는 디자인 패턴

- 상태를 객체로 승격시켜, 상태 전이를 명확하게 함  
  (종종 대량의 조건문을 만들지 않기 위해(aka 상태의 종류가 다양할 때) 상태를 독립적으로 두어 구현함)
- 일반적으로 switch - case 문이 길어질 경우 변경함

- 구현유의:
  - 상태 전이의 책임은 state 클래스에 있도록 구현하는 것이 이상적  
    (호출해서 사용하는 방식을 지향해야함)
  - State instance 생성과 삭제
    - 필요할때만 생성 후 삭제: 상태가 자주 바뀌지 않을 때
    - 필요하지 않아도 미리 생성 & 삭제하지 않음: 상태 변화가 자주 있을 떄

- 유의
  - Strategy: state는 하나의 state 에 관한 구현을 하나, 외부의 entity 에 의한 Strategy 는 아님
  - Flyweight: state 객체 공유를 위해 사용함

### 21. Strategy

<!-- 
    Context: strategy 객체에 대한 참조자 관리(concreteStrategy 인스턴스), Strategy 객체가 자료에 접근하는데 필요한 인터페이스 정의
    Strategy: 제공하는 모든 알고리즘에 대한 공통 연산들을 인터페이스로 정의
    ConcreteStrategy: strategy 인터페이스를 실제 알고리즘으로 구현
-->

  > Policy, common operation 이 반복되는 것을 막기 위해 인터페이스를 정의함(캡슐화함, 동일 계열(개념)의 알고리즘 군을 정의함)  
  > *GoF:* 동일 계열의 알고리즘군을 정의하고, 각각의 알고리즘을 캡슐화하며, 이들을 상호 교환 가능하도록 만드는 패턴.  
    알고리즘을 사용하는 사용자와 상관없이 독립적으로 알고리즘을 다양하게 변경할 수 있게 함  
  > *위키:* 알고리즘 군을 정의하고 각각 하나의 클래스로 캡슐화한 다음, 필요할 때 서로 교환해서 사용할 수 있게 해준다.  
    템플릿 메서드 패턴 (Template method pattern): 상위 클래스에서는 추상적으로 표현하고 그 구체적인 내용은 하위 클래스에서 결정되는 디자인 패턴

- 같은 개념이지만, 다른 행동을 하는 다양한 알고리즘 중 어떤 것을 채택할 것인지 정할 수 있도록 집합을 명시하는 인터페이스
- 사용자가 서로 다른 행동 각각의 특징을 이미 알고 있을 때 전략 패턴을 사용함
- 규모가 작은 클래스들이므로 플라이급 패턴으로 정의하는 것이 좋음

- 활용처
  - 행동이 조금 다를 뿐 개념적으로 관련된 많은 클래스들이 존재할때 (e.g. 다양한 sorting법)
  - 알고리즘의 변형이 필요할때
    - 메모리 공간과 처리 속도 간 절충에 따라 다른 알고리즘을 채택하고자 할 경우
    - concreteStrategy(알고리즘) 간 서로 상호교환 가능함
  - 사용자가 몰라야하는 데이터(노출하지 말아야할 자료 구조)를 사용하는 알고리즘이 있을 때
  - 하나의 클래스가 많은 행동을 정의하고, 그 행동들이 클래스 연산 안에서 복잡한 다중 조건문의 모습을 취할 때

- 유의
  - Command: 일반적 <-> Strategy: 특정 액션

### 22. Template Method

  > 추상 클래스에서 정의/구현된 구조(자주 사용되는, 로직 구성 혹은 절차)를 제공하는 메서드  
  > *GoF:* 객체의 연산에는 알고리즘의 뼈대만을 정의하고 각 단계에서 수행할 구체적 처리는 서브클래스 쪽으로 미루는 패턴  
  > *위키:* 상위 클래스에서는 추상적으로 표현하고 그 구체적인 내용은 하위 클래스에서 결정되는 디자인 패턴

- ***중요포인트:*** 훅 연산(오버라이드 가능한 메소드) 선정하는 것이 관건
  - template method: never overriden
  - primitive(abstract) methods: must be overriden
  - hook methods: may be overriden
- 코드 재사용을 위한 기술

- 유의
  - 템플릿 메서드: 구조(절차, 로직)에 대한 명세를 해둔 메서드 (혹은 구조)
  - 팩토리 메서드: 생성에 책임이 있는 메서드 (혹은 구조)
  > 메서드로만 보는건지, 패턴 구조를 포함해서 보는건지 의견이 분분한 거 같다.  
  > `팩토리 메서드는 템플릿 메서드의 파생인가?` 에 대한 의견이 분분한거 같다. (내 결론은 `비슷하지만 아니다.` 이다.)
  >
  > - [팩토리 메서드는 템플릿 메서드의 파생인가? (1)](https://stackoverflow.com/questions/55461586/is-factory-method-pattern-a-specialized-case-of-template-method-pattern)
  > - [팩토리 메서드는 템플릿 메서드의 파생인가? (2)](https://softwareengineering.stackexchange.com/questions/340099/factory-method-is-a-specialization-of-template-method-how)

### 23. Visitor

  > 시스템의 이해, 유지보수, 변경 작업 등을 쉽게하기 위해 각 객체 내부에 구현되어 있던 "공통 연산"들을 떼어내 Vistor라는 클래스를 따로 두어, 객체군(객체 구조 내 Elements)이 수행할 연산을 표현함  
  > - e.g. 디렉토리 구조, 각 부품의 cost 계산 통계내고자할때 사용하는 느낌
  > *GoF:* 객체 구조를 이루는 원소에 대해 수행할 연산을 표현하는 패턴. 연산을 적용할 원소의 클래스를 변경하지 않고도 새로운 연산을 정의할 수 있음  
  > *위키:* 각 클래스의 데이터 구조로부터 처리 기능을 분리하여 별도의 visitor 클래스로 만들어놓고 해당 클래스의 메서드가 각 클래스를 돌아다니며 특정 작업을 수행하도록 하는 것

- 구조
  - 연산이 적용되는 원소에 대한 클래스 계통(Node 클래스 계통)
  - 적용할 연산을 정의하는 방문자 클래스 계통(NodeVisitor 클래스 계통)

- 중요포인트
  - 객체의 구조에 적용될 알고리즘의 변화가 자주 발생하는가(Visitor의 변화) --> 적합
  - 구조를 구성하는 객체 클래스의 변화가 자주 발생하는가(Element의 변화) --> 부적합, 객체 구조를 정의한 클래스에 연산을 추가하는게 나음

- 활용
  - 다른 인터페이스를 가진 클래스가 객체 구조에 포함되어 있으며, Concrete 클래스에 따라 달라진 연산을 클래스 객체에 대해 수행하고자 할 때
  - 각각 특징이 있고, 관련되지 않은 많은 연산이 한 객체 구조에 속해있는 객체들에 대해 수행될 필요가 있으며, 연산으로 클래스들을 "더럽히고" 싶지 않을 떄.
    - visitor 클래스는 관련된 모든 연산을 하나의 클래스 안에다 정의해 놓음으로써 관련된 연산이 함께 있을 수 있도록 함
  - 객체 구조가 많은 응용프로그램에게 공유될 때
  - 객체 구조를 정의한 클래스는 거의 변하지 않지만 전체 구조에 걸쳐 새로운 연산을 추가하고 싶을 때
    - 객체 구조를 변경하려면 모든 방문자에 대한 인터페이스를 재정의해야 하는데 이 작업에 대해 잠재된 비용이 클 수 있음
    - c.f. 전체 구조가 자주 변경될 때는 해당 연산을 클래스에 정의하는 편이 더 나음

- double dispatch: __실행되는 연산이 요청의 종류와 두 수신자의 타입에 따라 달라짐__
  - Accept(aVistor)를 호출하면
  - ConcreteElement를 파라미터로 넘겨 visitor 연산을 호출
  - 방향
    - client
      -> objectStructure: Accept(aVisitor)
      -> concreteElement: VisitConcreteElement(aConcreteElement)
      -> concreteVisitor: Operation

- 유의
  - Composite: 객체 구조에 대해 연산을 적용하는데 방문자를 사용할 수 있음
  - Interpreter: 해석 과정에서 방문자를 사용할 수 있음
  - Iterator: 방문자는 상속된 객체 혹은 부모 객체에 한해 접근할수 있음 <-> Visitor: Element에 접근할 권한이 허용됨
  - Strategy: Strategy가 더 폭넓은 개념, visitor는 visiting strategy임
