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

## 1. Abstract Factory Pattern
- [Abstract Factory](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
* 제품을 생성하기 위한 인터페이스
* 일반화된 클래스(부모 클래스)가 더 상세한 클래스(서브클래스)를 묶고, 이 일반화된 클래스를 통해 클라이언트는 접근함. 
* 세부 구현은 상세한 클래스(서브 클래스)에서 이루어짐.
* 사용 예
  * 다양한 시스템에서 사용될때(시스템 독립적)
  * 구현 내용이 아닌 인터페이스를 노출시킬때

* 유의
  * <i>Builder: 실체를 만들 때 build 되는 내용에 대해 전혀 모르는 것</i>

## 2. Builder
- [Builder](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/builder)
* 복잡한 객체를 한단계씩 실행시키며 생성함. 마지막 단계에서 객체를 반환함.
* 각각 독립된 여러 단계를 거치며, 특정 단계의 선택폭(프로토콜, representation 방법)이 넓을 때 사용하면 좋을 듯함.
* 사용 예
  * RTF(서식있는 텍스트 파일) 리더
  
## 3. Factory Method
- [Factory Method](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/abstract_factory)
* Factory 객체 내에서 한 객체를 생성(지정)하는 메소드
 
* 유의
<br/>
  Abstract Factory(제품군 안내) > Factory(제품 생성 담당) > Factory Method (상세 부품 생성 담당)
  * Factory Method: Abstract factory 객체 내에 한 객체를 생성하는 하나의 메소드
    * 서브 클래스에 의해 override 될 수 있음
  * Abstract Factory Method: 내부에 (보통 팩토리 메소드를 사용해서) 여러 객체를 생성하는 하나의 객체(인터페이스)
  
## 4. Prototype
- [Prototype](https://github.com/yoonsue/study/tree/master/book/gof/src/main/java/prototype)
* 프로토타입 객체의 서브클래스가 clone 연산을 정의해야함
  * clone 연산을 구현할 때 사본이 원본으로부터 무엇을 복사하고 공유할 것인지 잘 결정해야함
* 팩토리메소드와  달리 프로토타입 패턴은 객체 자체를 복사하기 때문에 <br />
 서브클래싱을 필요로하지 않고, 오히려 초기화 동작을 필요로 함
* 런타임에 객체 생성/삭제
 
* 사용 예
  * GUI-style JavaBeans
  * 트랜잭션 <br/>(객체를 복사 -> 복사한 객체(Prototype)에 트랜잭션 수행 -> 원래 객체와 바꿔치기)
  
* 유의
  * 추상 팩토리: 프로토타입 집합을 저장, 필요할때 복제해서 제품 객체를 반환하도록 사용 가능
  * 프로토타입: 복사본 생성