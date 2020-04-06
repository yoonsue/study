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
<br/>
  Abstract Factory(제품군 안내) > Factory(제품 생성 담당) > Factory Method (상세 부품 생성 담당)
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
* 계층 구조를 어떻게 형성하는지 보여줌
* 사용자가 어떤 Leaf나 Composite 클래스가 존재하는지 모르게 공통 연산들을 정의한 Component(인터페이스)
  * Composite은 linked list, array, tree, hash table 등을 써 자신의 자식들을 저장
  * 자식 구성요소에서 부모를 가리키는 참조자를 관리
<!-- Component(총괄인터페이스), Leaf(최하위객체), Composite(하위그룹객체), Client -->

* 사용 예
  * File Directory

### 9. Decorator
* 런타임시 동적으로 새로운 책임 추가(재귀적 합성)하는 객체


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
    * narrow interface(public): memento 클래스에게 제ㅈ공하는 서비스
    * wide interface(public + private): originator 클래스에게 제공하는 서비스

### 19. Observer

### 20. State

### 21. Strategy

### 22. Template Method
* 추상 클래스에서 구현된 (보통 자주 사용되는 혹은 필수적인 절차) 메소드
* 코드 재사용을 위한 기술
* 훅 연산(오버라이드 가능한 메소드) 지정하는 것이 관건
  
### 23. Visitor
