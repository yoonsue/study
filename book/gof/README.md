# PATTERNS STUDY
아래 참고도서를 통해 고찰한 내용을 정리하는 레포지토리임.
### 참고도서
- [GoF의 디자인 패턴](http://www.yes24.com/Product/Goods/17525598)
- [Holub Design Patterns](https://www.holub.com/goodies/holub_design_patterns.pdf)

### 기호표
| 기호 | 표기 |
|:---:|:---:|
|<i>italic</i>|추후 다시 확인할것|
|<b>bold</b>| 강조|
|<u>underline</u>| 불확실, 추측 |

# Patterns

## 생성 패턴
### 1. Abstract Factory Pattern
- [Abstract Factory](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
* 객체를 생성하기 위한 인터페이스
* 일반화된 클래스(부모 클래스)가 더 상세한 클래스(서브클래스)를 묶고, 이 일반화된 클래스를 통해 클라이언트는 접근함. 
* 세부 구현은 상세한 클래스(서브 클래스)에서 이루어짐.
* 사용 예
  * 다양한 시스템에서 사용될때(시스템 독립적)
  * 구현 내용이 아닌 인터페이스를 노출시킬때

* 유의
  * <i>Builder: 실체를 만들 때 build 되는 내용에 대해 전혀 모르는 것</i>

### 2. Builder
- [Builder](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/builder)
* 복잡한 객체를 한단계씩 실행시키며 생성함. 마지막 단계에서 객체를 반환함.
* 각각 독립된 여러 단계를 거치며, 특정 단계의 선택폭(프로토콜, representation 방법)이 넓을 때 사용하면 좋을 듯함.
* 사용 예
  * RTF(서식있는 텍스트 파일) 리더
  
### 3. Factory Method
- [Factory Method](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
* 객체를 생성(지정)하는 메소드
  
* 유의
  * Abstract Factory(제품군 안내) > Factory(제품 생성 담당) > Factory Method (상세 부품 생성 담당)
  * Factory Method: 객체를 생성하는 메소드
    * 서브 클래스에 의해 override 될 수 있음
  * Abstract Factory: 내부에 (보통 팩토리 메소드를 사용해서) 여러 객체를 생성하는 하나의 객체(인터페이스)
  
### 4. Prototype
- [Prototype](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/prototype)
* clone()이 정의된 인터페이스, 상속받아 구현하는 구현체
* 프로토타입 객체의 서브클래스가 clone 연산을 정의해야함
  * clone 연산을 구현할 때 사본이 원본으로부터 무엇을 복사하고 공유할 것인지 잘 결정해야함
* 객체 자체를 복사하기 때문에 서브클래싱을 필요로 하지 않고, 오히려 초기화 동작을 필요로 함
* 런타임에 객체 생성/삭제
 
* 사용 예
  * GUI-style JavaBeans
  * 트랜잭션 <br/>(객체를 복사 -> 복사한 객체(Prototype)에 트랜잭션 수행 -> 원래 객체와 바꿔치기)
  
* 유의
  * 추상 팩토리: 프로토타입 집합을 저장, 필요할때 복제해서 제품 객체를 반환하도록 사용 가능
  * 프로토타입: 복사본 생성

### 5. Singleton
- [Singleton](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/singleton)
* 단 하나의 인스턴스만 갖는 클래스, 해당 인스턴스에 전역적 접근이 가능함 

* 사용 예
  * 메타클래스 

## 구조 패턴

### 6. Adapter 
- [Adapter](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/adapter)
* 기존 코드의 변경없이 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 변환
  * 클래스 Adapter: 타겟 클래스와 Adaptee 클래스 둘다 상속받아 타겟 클래스의 인터페이스로 개조
  * 객체 Adapter: 타겟 클래스를 상속받아 직접 변형(서브클래스가 이미 너무 많아 개조가 더 불편할때)

* 사용 예
  * InputStream - StringInputStream

* 유의
  * Mediator: 동적으로 다대다로 연결
  * Bridge: Adapter보다 큰 규모로, 서브시스템을 고립시킴 
  * Decorator: 몇몇 메소드를 수정하거나 메소드를 추가함 (Wrapped Object)

### 7. Bridge
- [Bridge](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/bridge)
* 추상적 개념에 해당하는 클래스와 구현에 해당하는 클래스를 분리
* 플랫폼 독립적인 코드(Abstraction)와 Application-specific code(Implementor)
<!-- Abstraction, RefinedAbstraction, Implementor, ConcreteImplementor -->
  * 개인적으로 DDD의 Repository interface와 Repository 구현체 간 관계가 이에 해당한다 생각함 아닌것 같군, INTERFACE(ABSTRACT CLASS에 대한 참조자가 있어야함)와 그걸 IMPLEMENTS한 ABSTRACT CLASS간 관계

### 8. Composite
- [Composite](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/composite)
<!-- Component(총괄인터페이스), Composite(하위그룹객체), Leaf(최하위객체), Client -->
* 계층 구조를 어떻게 형성하는지 보여줌 (파워포인트 그룹 객체와 같은 원리)
* 사용자가 어떤 Leaf나 Composite 클래스가 존재하는지 모르게 공통 연산들을 정의한 Component(총괄인터페이스)
  * Composite(그룹객체)은 linked list, array, tree, hash table 등을 써 자신의 자식들을 저장
  * 자식 구성요소에서 부모를 가리키는 참조자를 관리
  * 자식의 상태를 많은 부모와 공유해야할 경우 Flyweight 패턴을 사용하면 좋음.
  * 행동은 Leaf(최말단객체)가 수행 ([Chain of Responsibility](###-13.-chain-of-responsibility))

* 자식 관리 인터페이스를 Component가 갖느냐? Composite(그룹객체)이 갖느냐?
  * Component: Leaf 클러스의 인스턴스가 Add() Remove()와 같은 행동을 하지 않도록 안전성 유지를 해야함
  * Composite: Leaf 클래스 인스턴스가 무의미한 행동을 하지 않는 안전성을 보장하나, composite 클래스가 어떤 타입으로 자식들을 관리하는지 알 수 없음

* 사용 예
  * File Directory

### 9. Decorator
<!-- 
    Component: 동적으로 추가할 서비스를 가질 가능성이 있는 객체들에 대한 인터페이스
    ConcreteComponent: 추가적인 서비스가 실제로 정의될 필요가 있는 객체
    Decorator: Component 객체에 대한 참조자를 관리하면서 Component에 정의된 인터페이스를 만족하도록 인터페이스를 정의
    ConcreteDecorator: Component에 새롭게 추가할 서비스를 실제로 구현하는 클래스
-->
* 개별적인 객체에 새로운 책임을 추가하고자 할 경우 사용하는 패턴
* 런타임시 동적으로 새로운 책임 추가(재귀적 합성)하는 객체
* Component 인터페이스를 상속받는 Decorator 인터페이스를 생성해서 인스턴스(ConcreteDecorator의 인스턴스)를 생성

* 유의
  * Decorator: 객체의 겉(인터페이스)를 추가함
  * Strategy: 객체의 대체를 통해 객체 내부적으로 기능을 변경하거나 확장하는 방법을 제공
  * Adapter: Decorator는 일종의 Adapter이나, Adapter는 인터페이스의 변경에 초점을, Decorator는 객체의 책임과 행동의 변화에 초점을 둠
  * Composite: Decorator는 한 구성요소만을 갖는 Composite임

### 10. Facade
* 전체 서브시스템을 표현하는 객체(대표객체)

### 11. Flyweight
* 여러번 이용할 수 있도록 공유된 객체(구조를 정의)

### 12. Proxy
* 특정 객체를 대신하는 객체(대신되는 객체를 보호)

| 원격지 프록시 | 가상 프록시 | 보호용 프록시 |
|---|---|---|
| 객체가 다른 주소 공간에 존재함을 숨김 | 객체 생성 등의 처리를 최적화 | 요청한 대상이 권한이 있는지 확인 | 

* 유의
  * Adater: 인터페이스 변환
  * Proxy: 대상과 동일한 인터페이스 제공, 객체에 대한 접근 제어 
  * Decorator: 동적으로 하나 이상의 서비스를 추가하기 위함 


## 행동 패턴

### 13. Chain of Responsibility
<!-- 
    Handler: 요청 처리 인터페이스 정의, 후속 처리자와의 연결 구현 (요청의 시작)
    ConcreteHandler: 스스로 요청을 처리할 일은 하고 남은 일은 후속 처리자에게 넘김
    ConcreteState: 각 서브 클래스들 context 따라 처리되어야할 실제 행동 구현
-->
하나 이상의 객체가 요청을 처리해야 하고, 그 요청 처리자를 모를때 ConcreteHandler를 거쳐가며 처리자를 확정하는 패턴
(일의 전체 프로세스(Handler)가 있고, 공장 트레일러에서 본인(ConcreteHandler)이 맡은 역할만 수행하고 다음 사람(ConcreteHandler)에게 넘기는 식.)

* ConcreteHandler간 연결이 중요함 (이를 지키지 못한다면, 안정적인 결과가 보장되지 않음)
  * 코드에 따라 대응하는 처리 요청을 발생시키기 위해 조건문 정의
  * 상속을 이용하여 매개변수를 묶어 별도의 객체로 만듦
  
* 자주 Composite 패턴과 같이 사용됨

### 14. Command
객체 내부의 작업 혹은 요청을 캡슐화함

* 사용 예
  * Transaction - undo 시 기존 상태 기억을 위해 메멘토 패턴을 사용 
  
### 15. Interpreter
언어에 대한 문법을 정의하고, 그 문법을 계층화된 클래스로 구현하는 패턴

* 사용 예
  * Regular Expression

### 16. Iterator
내부 표현부를 노출하지 않고 집합의 원소에 접슥할 수 있도록 하는 패턴
* Java에서는 Iterator를 기본클래스로 제공
* 순회 도중 생성/삭제되는것을 방지하기 위해 집합 복사본에서 순회하거나, 순회중에는 집합 변경을 못하도록 막아야함

### 17. Mediator
한 객체가 다른 객체를 너무 많이 참조 혹은 복잡한 상호작용을 해,<br />중간단계(loose coupling)를 놓고 싶을때 사용하는 패턴 

* 유의
  * Facade: 서브시스템을 추상화하여 좀더 편한 인터페이스 제공, <br />Facade 객체 -> 서브시스템 객체
  * Mediator: 인터페이스는 간단하지만 runtime시 복잡한 동작을 수행하는 객체,<br /> Mediator <-> 서브 객체
  * Bridge: class들의 시스템(?)을 나타내는 인터페이스
  
### 18. Memento
* 상태 복원을 위해 객체의 이전 상태를 저장하는 패턴
* 객체의 상태를 얻는데 구현이 노출되어, 캡슐화를 할때 사용하는 패턴

* 유의
  * 인터페이스 생성 시, private / public을 잘 구분지어야 함
    * narrow interface(public): memento 클래스에게 제공하는 서비스
    * wide interface(public + private): originator 클래스에게 제공하는 서비스

### 19. Observer
<!-- 
 subject(감시자들을 알고 있음)
 observer(객체를 갱신하는데 필요한 인터페이스를 정의)
 concreteSubject(ConcreteObserver 객체에게 알려주어야 하는 상태를 저장, observer 에게 상태 변화 통보)
 concreteObject(ConcreteSubject 객체에 대한 참조자 관리, 주체의 상태와 일관성을 유지해야하는 상태를 저장)--> 
one-many pub-sub

변경사항 - aspect

- 구독 취소 시 이벤트 알림을 받지 않을 수 있음을 보장해야함
- concreteSubject 삭제시 concreteObserver 가 dangling 레퍼런스를 가짐
  - concreteSubject 삭제 시, 참조하고 있는 concreteObserver 에게 알려야함

* 사용 예
  * MVC(model - subject, view - observer)
  
* 유의
  * Command: Observer는 공지용으로만 쓰이는 반면, 일반적임. (개념: Observer << Command)
  * Strategy: <b>다시 확인할 것</b>

### 20. State
<!-- 
    Context: 사용자가 사용할 수 있는 기본 인터페이스 제공
    State: 각 상태별 필요한 행동을 캡슐화하여 인터페이스로 정의 (Singleton 임)
    ConcreteState: 각 서브 클래스들 context 따라 처리되어야할 실제 행동 구현
-->
상태를 객체로 승격시켜, 상태 전이를 명확하게 함
(종종 대량의 조건문을 만들지 않기 위해(aka 상태의 종류가 다양할 때) 상태를 독립적으로 두어 구현함)

* 유의
  * Strategy: state는 하나의 state 에 관한 구현을 하나, 외부의 entity 에 의한 Strategy 는 아님
  * Flyweight: state 객체 공유를 위해 사용함

### 21. Strategy
<!-- 
    Context: strategy 객체에 대한 참조자 관리(concreteStrategy 인스턴스), Strategy 객체가 자료에 접근하는데 필요한 인터페이스 정의
    Strategy: 제공하는 모든 알고리즘에 대한 공통 연산들을 인터페이스로 정의
    ConcreteStrategy: strategy 인터페이스를 실제 알고리즘으로 구현
-->
common operation 이 반복되는 것을 막기 위해 인터페이스를 정의함(캡슐화함, 동일 계열의 알고리즘 군을 정의함)

concreteStrategy(알고리즘) 간 서로 상호교환 가능함

사용자가 서로 다른 행동 각각의 특징을 이미 알고 있을 때 전략 패턴을 사용함

- 규모가 작은 클래스들이므로 플라이급 패턴으로 정의하는 것이 좋음

* 유의
  * Command: 일반적 <-> Strategy: 특정 액션

### 22. Template Method
* 추상 클래스에서 구현된 (보통 자주 사용되는 혹은 필수적인 절차) 메소드
* 코드 재사용을 위한 기술
* 훅 연산(오버라이드 가능한 메소드) 지정하는 것이 관건
  
### 23. Visitor
시스템의 이해, 유지보수, 변경 작업 등을 쉽게하기 위해 각 객체 내부에 구현되어 있던 공통 연산들을 떼어내  
Vistor라는 클래스를 따로 두어, 객체군(객체 구조 내 Elements)이 수행할 연산을 표현함

ConcreteElement 클래스가 추가되면 vistor 클래스가 모두 변경되어야하므로,<br />
Element 클래스의 구조의 변화가 없을 때 사용해야 함

* double dispatch: Accept 연산은 결과적으로 두 과정을 거침 
  * Accept(aVistor)를 호출하면
  * ConcreteElement가 Element 가 자기자신을 파라미터로 넘겨 visitor 연산을 호출 
  * 방향
    * client 
    <br/>-> objectStructure(Accept(aVisitor)) 
    <br/>-> concreteElement(VisitConcreteElement(aConcreteElement))
    <br/>-> visitor(Operation)

* 유의
  * Iterator: 방문자는 상속된 객체 혹은 부모 객체에 한해 접근할수 있음 <-> Visitor: Element에 접근할 권한이 허용됨
  * Strategy: Strategy가 더 폭넓은 개념, visitor는 visiting strategy임
